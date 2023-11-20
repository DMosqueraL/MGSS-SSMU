package apiserviciotransporte.apiserviciotransporte.controladores.dto;

import apiserviciotransporte.apiserviciotransporte.entidades.Role;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRegisterRequestDto {
    private Role.UserRoles role;
    private String email;
    private String password;
    private String fullName;
    private String phone;

}
