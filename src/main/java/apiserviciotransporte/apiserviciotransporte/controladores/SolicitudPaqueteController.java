package apiserviciotransporte.apiserviciotransporte.controladores;

import apiserviciotransporte.apiserviciotransporte.controladores.dto.SolicitudDeleteResponseDto;
import apiserviciotransporte.apiserviciotransporte.controladores.dto.SolicitudPaqueteDto;
import apiserviciotransporte.apiserviciotransporte.controladores.dto.SolicitudesPaqueteResponseDto;
import apiserviciotransporte.apiserviciotransporte.entidades.SolicitudPaquete;
import apiserviciotransporte.apiserviciotransporte.interfaces.SolicitudPaqueteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/solicitudes-paquetes")
@RequiredArgsConstructor
public class SolicitudPaqueteController {

    private final SolicitudPaqueteService solicitudPaqueteService;

    @PreAuthorize("hasRole('ROLE_USUARIO')")
    @Operation(summary = "- Endpoint para realizar una solicitud de servicio en SSMU")
    @ApiResponse(responseCode = "200", description = "Solicitud Realizada con Éxito")
    @PostMapping(value = "", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> crearSolicitudPaquete(@Valid @RequestBody SolicitudPaqueteDto solicitudPaqueteDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return this.getServerResponseErrorEntity(bindingResult);
        }
        SolicitudPaqueteDto solicitudGuardada = this.solicitudPaqueteService.guardarSolicitudPaquete(solicitudPaqueteDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(solicitudGuardada);
    }

    @PreAuthorize("hasRole('ROLE_USUARIO')")
    @Operation(summary = "- Endpoint para obtener el listado de solicitudes realizadas en SSMU")
    @ApiResponse(responseCode = "200", description = "Listado de Solicitudes de Servicio")
    @GetMapping(value = "", produces = "application/json")
    public ResponseEntity<SolicitudesPaqueteResponseDto> listarSolicitudesDeServicio(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        SolicitudesPaqueteResponseDto responseDto = this.solicitudPaqueteService.listarPorUsuario(page, size);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseDto);
    }

    @PreAuthorize("hasRole('ROLE_USUARIO')")
    @Operation(summary = "- Endpoint para obtener una solicitud de servicio en SSMU por su id")
    @ApiResponse(responseCode = "200", description = "Operación Exitosa")
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<SolicitudPaqueteDto> obtener(@PathVariable long id) {
        SolicitudPaqueteDto solciitudDto = solicitudPaqueteService.obtener(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(solciitudDto);
    }

    @PreAuthorize("hasRole('ROLE_USUARIO')")
    @Operation(summary = "- Endpoint para buscar una solicitud de servicio en SSMU por fecha")
    @ApiResponse(responseCode = "200", description = "Solicitud de Servicio por fecha obtenida con éxito")
    @GetMapping(value = "/date/{fecha}", produces = "application/json")
    public ResponseEntity<List<SolicitudPaqueteDto>> buscarPorFecha(@PathVariable("fecha") LocalDateTime fecha) {
        List<SolicitudPaqueteDto> solicitudes = solicitudPaqueteService.buscarPorFecha(fecha);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(solicitudes);
    }

    @PreAuthorize("hasRole('ROLE_USUARIO')")
    @Operation(summary = "- Endpoint para eliminar una solicitud de servicio en SSMU")
    @ApiResponse(responseCode = "200", description = "Solicitud de Servicio eliminada con éxito")
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<SolicitudDeleteResponseDto> eliminar(@PathVariable("id") long id) {
        boolean eliminado = solicitudPaqueteService.eliminar(id);
        SolicitudDeleteResponseDto solicitudDeleteResponseDto = SolicitudDeleteResponseDto.builder()
                .deleted(eliminado)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(solicitudDeleteResponseDto);
    }

    @Operation(summary = "- Endpoint para actualizar una solicitud de servicio en SSMU")
    @ApiResponse(responseCode = "200", description = "Solicitud de Servicio actualizada con éxito")
    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public SolicitudPaquete actualizar(@PathVariable("id") String id, @RequestBody SolicitudPaqueteDto solicitudServicio) {
        return null;
    }

    private ResponseEntity<Map<String, String>> getServerResponseErrorEntity(BindingResult bindingResult) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(bindingResult.getFieldErrors()
                        .stream()
                        .collect(Collectors.toMap(FieldError::getField, DefaultMessageSourceResolvable::getDefaultMessage)));
    }

}