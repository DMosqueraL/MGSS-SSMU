
package apiserviciotransporte.apiserviciotransporte.interfaces;

import apiserviciotransporte.apiserviciotransporte.controladores.dto.*;

public interface AuthService {

    UsuarioDto register(UserRegisterRequestDto userRegisterRequestDto);
    UserLoginResponseDto login(UserLoginRequestDto userLoginRequestDto);

    UserLoginResponseDto refreshToken(String accessToken);
}
