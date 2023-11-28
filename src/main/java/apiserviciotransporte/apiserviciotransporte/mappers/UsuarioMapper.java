package apiserviciotransporte.apiserviciotransporte.mappers;

import apiserviciotransporte.apiserviciotransporte.controladores.dto.UserRegisterRequestDto;
import apiserviciotransporte.apiserviciotransporte.controladores.dto.UsuarioDto;
import apiserviciotransporte.apiserviciotransporte.entidades.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class UsuarioMapper {

    public Usuario toEntity(UsuarioDto dto) {
        return Usuario.builder()
                .id(dto.getId())
                .nombreCompleto(dto.getNombreCompleto())
                .email(dto.getEmail())
                .telefono(dto.getTelefono())
                .build();
    }

    public Usuario toEntity(UserRegisterRequestDto dto) {
        return Usuario.builder()
                .email(dto.getEmail())
                .nombreCompleto(dto.getFullName())
                .telefono(dto.getPhone())
                .solicitudesServicio(new ArrayList<>())
                .build();
    }

    public UsuarioDto toDto(Usuario entity) {
        return UsuarioDto.builder()
                .id(entity.getId())
                .nombreCompleto(entity.getNombreCompleto())
                .email(entity.getEmail())
                .telefono(entity.getTelefono())
                .build();
    }

}
