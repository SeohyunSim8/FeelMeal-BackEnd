package feelmeal.global.config;

import feelmeal.domain.member.repository.MemberRepository;
import feelmeal.global.common.jwt.JwtFilter;
import feelmeal.global.common.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final MemberRepository memberRepository;
    private final JwtService jwtService;
    private final SecurityProperties securityProperties;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        List<String> filterIgnorePaths = securityProperties.getIgnorePaths();
        List<String> filterPostIgnorePaths = securityProperties.getPostIgnorePaths();
        List<String> filterPatchIgnorePaths = securityProperties.getPatchIgnorePaths();
        List<String> filterGetIgnorePaths = securityProperties.getGetIgnorePaths();

        http.authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/error").permitAll()
                        .requestMatchers("/**").permitAll()
                        .requestMatchers("/api/swagger-ui.html/**", "/api/swagger-ui/**", "/api/swagger-ui/index.html/**", "/api/v3/api-docs/**").permitAll()
                )
                .csrf(AbstractHttpConfigurer::disable)  // 전체 CSRF 보호 비활성화
                .headers(headers -> headers
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)  // iframe 허용
                )
                .addFilterBefore(new JwtFilter(memberRepository, jwtService, filterIgnorePaths, filterPostIgnorePaths, filterPatchIgnorePaths, filterGetIgnorePaths), UsernamePasswordAuthenticationFilter.class)
                .cors(withDefaults());  // CORS 설정 적용

        return http.build();
    }

    // CORS 설정을 위한 Bean 정의
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:8090", "http://localhost:5173"));  // 특정 출처만 허용
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
