package feelmeal.api.member.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostSignUpServiceDto {
    private String id;
    private String password;
    private String name;
    private String address;

    @Builder
    public PostSignUpServiceDto(String id, String password, String name, String address) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.address = address;
    }
}
