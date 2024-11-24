package feelmeal.global.common.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import feelmeal.domain.member.entity.Member;
import feelmeal.domain.member.repository.MemberRepository;
import feelmeal.global.common.entity.Constant;
import feelmeal.global.common.exception.CustomException;
import feelmeal.global.common.exception.ErrorResponse;
import feelmeal.global.common.exception.ResponseCode;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final MemberRepository memberRepository;
    private final JwtService jwtService;

    private final List<String> filterIgnorePaths;
    private final List<String> filterPostIgnorePaths;
    private final List<String> filterPatchIgnorePaths;
    private final List<String> filterGetIgnorePaths;

    private final AntPathMatcher pathMatcher = new AntPathMatcher();
    private final Collection<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("USER"));

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Security Ignore 처리한 경로는 JWT 검사를 진행하지 않는다
        String requestUri = request.getRequestURI();
        String httpMethod = request.getMethod();

        if (checkIgnorePath(requestUri, httpMethod)) {
            filterChain.doFilter(request, response);
            return;
        }

        // Authorization Header가 없는 경우 예외 처리를 진행한다
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            filterExceptionHandler(response, ResponseCode.EMPTY_JWT);
            return;
        }

        // JWT가 헤더에 있는 경우 JWT 검사를 진행한다
        String token = authorizationHeader.split(" ")[1];

        // JWT에서 memberId를 추출한다
        Member member;
        try {
            Long memberIdx = jwtService.getMemberId(token);
            member = memberRepository.findByIdxAndStatus(memberIdx, Constant.Status.ACTIVE)
                    .orElseThrow(() -> new CustomException(ResponseCode.NOT_FOUND_USER));
        } catch (CustomException customException) {
            log.error("Invalid JWT token: {}", token, customException);
            filterExceptionHandler(response, customException.getResponseCode());
            return;
        }

        // 해당 JWT를 가진 유저가 존재한다면 userDetails를 생성한다
        UserDetails userDetails = new User(member.getId().toString(), member.getPw(), authorities);

        // 접근권한 인증 토큰을 생성한다
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        // 현재 Request의 Security Context에 접근권한 설정
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        filterChain.doFilter(request, response); // 다음 필터로 넘기기
    }

    public boolean checkIgnorePath(String path, String method) {
        if (filterIgnorePaths.stream().anyMatch(uri -> pathMatcher.match(uri, path))) return true;

        switch (method) {
            case "GET":
                if (!path.equals("/themes-reviews/users") && filterGetIgnorePaths.stream().anyMatch(uri -> pathMatcher.match(uri, path))) return true;
                break;
            case "PATCH":
                if (filterPatchIgnorePaths.stream().anyMatch(uri -> pathMatcher.match(uri, path))) return true;
                break;
            case "POST":
                if (filterPostIgnorePaths.stream().anyMatch(uri -> pathMatcher.match(uri, path))) return true;
                break;
        }

        return false;
    }

    // 필터에서 CustomException을 처리한다
    public void filterExceptionHandler(HttpServletResponse response, ResponseCode responseCode) {
        response.setStatus(responseCode.getStatus().value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            String json = new ObjectMapper().writeValueAsString(ErrorResponse.of(responseCode));
            response.getWriter().write(json);
        } catch (Exception e) {
            log.error("FILTER ERROR", e.getMessage());
        }
    }
}