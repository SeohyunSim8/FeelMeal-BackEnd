package feelmeal.api.member.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostLoginServiceDto {
    private String id;
    private String password;

    @Builder
    public PostLoginServiceDto(String id, String password) {
        this.id = id;
        this.password = password;
    }
}
