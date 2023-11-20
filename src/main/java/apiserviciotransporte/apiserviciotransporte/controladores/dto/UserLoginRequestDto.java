package apiserviciotransporte.apiserviciotransporte.controladores.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserLoginRequestDto {
    private String email;
    private String password;
}
