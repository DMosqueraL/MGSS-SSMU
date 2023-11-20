package apiserviciotransporte.apiserviciotransporte.controladores;

import apiserviciotransporte.apiserviciotransporte.controladores.dto.UserLoginRequestDto;
import apiserviciotransporte.apiserviciotransporte.controladores.dto.UserLoginResponseDto;
import apiserviciotransporte.apiserviciotransporte.controladores.dto.UserRegisterRequestDto;
import apiserviciotransporte.apiserviciotransporte.entidades.DetalleUsuario;
import apiserviciotransporte.apiserviciotransporte.interfaces.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PreAuthorize("permitAll()")
    @PostMapping(path = "/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegisterRequestDto userRegisterRequestDto, BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()) {
            return this.getServerResponseErrorEntity(bindingResult);
        }
        this.authService.register(userRegisterRequestDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @PreAuthorize("permitAll()")
    @PostMapping(path = "/login", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> login(@Valid @RequestBody UserLoginRequestDto userLoginRequestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return this.getServerResponseErrorEntity(bindingResult);
        }
        UserLoginResponseDto result = this.authService.login(userLoginRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(result);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(path = "/refresh")
    public ResponseEntity<?> refreshToken(Authentication authentication) {
        DetalleUsuario detalleUsuario = (DetalleUsuario) authentication.getPrincipal();
        if (detalleUsuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Token not found");
        }
        UserLoginResponseDto result = this.authService.refreshToken(detalleUsuario.getEmail());
        return ResponseEntity.status(HttpStatus.OK)
                .body(result);
    }

    private ResponseEntity<Map<String, String>> getServerResponseErrorEntity(BindingResult bindingResult) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(bindingResult.getFieldErrors()
                        .stream()
                        .collect(Collectors.toMap(FieldError::getField, DefaultMessageSourceResolvable::getDefaultMessage)));
    }
}
