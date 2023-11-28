
package apiserviciotransporte.apiserviciotransporte.interfaces;

import apiserviciotransporte.apiserviciotransporte.controladores.dto.UserLoginRequestDto;
import apiserviciotransporte.apiserviciotransporte.controladores.dto.UserLoginResponseDto;
import apiserviciotransporte.apiserviciotransporte.controladores.dto.UserRegisterRequestDto;

public interface AuthService {

    void register(UserRegisterRequestDto userRegisterRequestDto);
    UserLoginResponseDto login(UserLoginRequestDto userLoginRequestDto);

    UserLoginResponseDto refreshToken(String accessToken);
}
