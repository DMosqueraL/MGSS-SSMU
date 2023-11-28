package apiserviciotransporte.apiserviciotransporte.controladores;

import apiserviciotransporte.apiserviciotransporte.controladores.dto.UsuarioDto;
import apiserviciotransporte.apiserviciotransporte.interfaces.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping(value = "", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> crearusuario(@Valid @RequestBody UsuarioDto usuarioDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return this.getServerResponseErrorEntity(bindingResult);
        }
        UsuarioDto solicitudGuardada = this.usuarioService.guardar(usuarioDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(solicitudGuardada);
    }

    @GetMapping(value = "", produces = "application/json")
    public ResponseEntity<List<UsuarioDto>> listarUsuarios() {
        List<UsuarioDto> solicitudes = this.usuarioService.listar();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(solicitudes);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<UsuarioDto> obtener(@PathVariable("id") String id) {
        UsuarioDto usuarioDto = usuarioService.obtener(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(usuarioDto);
    }


    @GetMapping(value = "/email/{email}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<UsuarioDto> buscarPorCorreo(@PathVariable("email") String email) {
        UsuarioDto usuarioDto = this.usuarioService.buscarPorEmail(email);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(usuarioDto);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable("id") String id) {
        this.usuarioService.eliminar(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    private ResponseEntity<Map<String, String>> getServerResponseErrorEntity(BindingResult bindingResult) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(bindingResult.getFieldErrors()
                        .stream()
                        .collect(Collectors.toMap(FieldError::getField, DefaultMessageSourceResolvable::getDefaultMessage)));
    }

}

