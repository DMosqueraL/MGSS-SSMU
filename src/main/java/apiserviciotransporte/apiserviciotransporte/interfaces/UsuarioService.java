package apiserviciotransporte.apiserviciotransporte.interfaces;

import apiserviciotransporte.apiserviciotransporte.controladores.dto.UsuarioDto;

import java.util.List;

public interface UsuarioService {

    List<UsuarioDto> listar();
    UsuarioDto obtener(String id);
    void eliminar(String id);
    UsuarioDto guardar(UsuarioDto usuarioDto);

    UsuarioDto buscarPorEmail(String email);
}
