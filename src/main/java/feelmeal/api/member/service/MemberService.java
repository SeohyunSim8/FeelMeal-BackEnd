package feelmeal.api.member.service;

import feelmeal.api.member.controller.dto.response.GetRestaurantLikedListResponse;
import feelmeal.api.member.controller.dto.response.PostLoginResponse;
import feelmeal.api.member.controller.dto.response.PostSignUpResponse;
import feelmeal.api.member.service.dto.GetRestaurantLikedListServiceDto;
import feelmeal.api.member.service.dto.PatchAddressServiceDto;
import feelmeal.api.member.service.dto.PostLoginServiceDto;
import feelmeal.api.member.service.dto.PostSignUpServiceDto;

import java.util.List;

public interface MemberService {
    PostSignUpResponse signUp(PostSignUpServiceDto dto);

    PostLoginResponse login(PostLoginServiceDto dto);

    void modifyAddress(PatchAddressServiceDto dto);

    List<GetRestaurantLikedListResponse> getRestaurantLikedList(GetRestaurantLikedListServiceDto dto);
}
