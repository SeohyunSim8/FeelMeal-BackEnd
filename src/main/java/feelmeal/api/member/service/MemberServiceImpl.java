package feelmeal.api.member.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import feelmeal.api.member.controller.dto.response.GetRestaurantLikedListResponse;
import feelmeal.api.member.controller.dto.response.PostLoginResponse;
import feelmeal.api.member.controller.dto.response.PostSignUpResponse;
import feelmeal.api.member.service.dto.GetRestaurantLikedListServiceDto;
import feelmeal.api.member.service.dto.PatchAddressServiceDto;
import feelmeal.api.member.service.dto.PostLoginServiceDto;
import feelmeal.api.member.service.dto.PostSignUpServiceDto;
import feelmeal.domain.member.entity.Member;
import feelmeal.domain.member.repository.MemberRepository;
import feelmeal.domain.restaurant.repository.RestaurantLikeRepository;
import feelmeal.global.common.entity.Constant;
import feelmeal.global.common.exception.CustomException;
import feelmeal.global.common.exception.ResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

import static feelmeal.global.common.exception.ResponseCode.INVALID_PASSWORD;
import static feelmeal.global.common.exception.ResponseCode.NOT_FOUND_MEMBER;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final RestaurantLikeRepository restaurantLikeRepository;

    @Value("${geocoder.key}")
    private String geocoderKey;

    // 회원가입 API
    @Transactional
    public PostSignUpResponse signUp(PostSignUpServiceDto dto) {
        // 이미 존재하는 멤버인지 확인한다
        Optional<Member> existMember = findOptionalMemberById(dto.getId(), Constant.Status.ACTIVE);
        if (existMember.isPresent()) throw new CustomException(ResponseCode.EXIST_MEMBER);

        // 존재하지 않는 멤버라면 회원가입을 진행한다
        memberRepository.save(Member.builder()
                .id(dto.getId())
                .pw(dto.getPassword())
                .name(dto.getName())
                .address(dto.getAddress())
                .status(Constant.Status.ACTIVE)
                .build());

        Member member = findMemberById(dto.getId(), Constant.Status.ACTIVE);

        // 주소를 좌표로 변환하여 저장
        List<Double> coordinate = geocoder("경기도 성남시 " + dto.getAddress());
        member.modifyCoordinate(coordinate.get(0), coordinate.get(1));

        // 멤버 고유번호 반환
        return PostSignUpResponse.of(member.getIdx());
    }

    // 로그인 API
    public PostLoginResponse login(PostLoginServiceDto dto) {
        // 회원가입된 멤버인지 확인한다
        Member member = findMemberById(dto.getId(), Constant.Status.ACTIVE);

        // 비밀번호가 일치하는지 확인한다
        if(!dto.getPassword().equals(member.getPw())) throw new CustomException(ResponseCode.INVALID_PASSWORD);

        return PostLoginResponse.of(member.getIdx());
    }

    // 주소 수정 API
    @Transactional
    public void modifyAddress(PatchAddressServiceDto dto) {
        // 회원가입된 멤버인지 확인한다
        Member member = findMemberByIdx(dto.getIdx(), Constant.Status.ACTIVE);

        // 주소 수정
        member.modifyAddress(dto.getAddress());

        // 좌표 수정
        List<Double> coordinate = geocoder("경기도 성남시 " + dto.getAddress());
        member.modifyCoordinate(coordinate.get(0), coordinate.get(1));
    }

    // 좋아요 한 식당 목록 조회 API
    public List<GetRestaurantLikedListResponse> getRestaurantLikedList(GetRestaurantLikedListServiceDto dto) {
        // 회원가입된 멤버인지 확인한다
        Member member = findMemberByIdx(dto.getIdx(), Constant.Status.ACTIVE);

        // 좋아요 한 식당 목록 조회
        return restaurantLikeRepository.findAllRestaurantLikedByMemberIdx(dto.getIdx());
    }

    public Optional<Member> findOptionalMemberById(String id, Constant.Status status) {
        return memberRepository.findByIdAndStatus(id, status);
    }

    public Member findMemberById(String id, Constant.Status status) {
        return memberRepository.findByIdAndStatus(id, status)
                .orElseThrow(() -> new CustomException(ResponseCode.NOT_FOUND_MEMBER));
    }

    public Member findMemberByIdx(Long idx, Constant.Status status) {
        return memberRepository.findByIdxAndStatus(idx, status)
                .orElseThrow(() -> new CustomException(ResponseCode.NOT_FOUND_MEMBER));
    }


    // 주소를 좌표로 변환
    private List<Double> geocoder(String searchAddr) {
        StringBuilder sb = new StringBuilder("https://api.vworld.kr/req/address" +
                "?service=address&request=getcoord&format=json&crs=epsg:4326&type=parcel");
        sb.append("&key=" + geocoderKey);
        sb.append("&address=" + URLEncoder.encode(searchAddr, StandardCharsets.UTF_8));

        try{
            // 요청
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(sb.toString()))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(response.body());

            // JSON 필드 탐색
            JsonNode pointNode = rootNode.path("response").path("result").path("point");

            // 결과값 반환
            double x = pointNode.path("x").asDouble();
            double y = pointNode.path("y").asDouble();

            return List.of(x, y);
        } catch (IOException | URISyntaxException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
