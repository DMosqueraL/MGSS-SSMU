
package apiserviciotransporte.apiserviciotransporte.interfaces;

import apiserviciotransporte.apiserviciotransporte.controladores.dto.*;

import java.time.LocalDateTime;
import java.util.List;

public interface AuthService {

    void register(UserRegisterRequestDto userRegisterRequestDto);
    UserLoginResponseDto login(UserLoginRequestDto userLoginRequestDto);

    UserLoginResponseDto refreshToken(String accessToken);
}
