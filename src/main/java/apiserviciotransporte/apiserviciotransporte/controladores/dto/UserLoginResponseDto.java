package apiserviciotransporte.apiserviciotransporte.controladores.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserLoginResponseDto {
    private String accessToken;
    private String idToken;
}
