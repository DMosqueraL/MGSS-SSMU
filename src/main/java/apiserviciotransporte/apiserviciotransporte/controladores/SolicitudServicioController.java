package apiserviciotransporte.apiserviciotransporte.controladores;
import apiserviciotransporte.apiserviciotransporte.controladores.dto.SolicitudDeleteResponseDto;
import apiserviciotransporte.apiserviciotransporte.controladores.dto.SolicitudServicioDto;
import apiserviciotransporte.apiserviciotransporte.controladores.dto.SolicitudesServicioResponseDto;
import apiserviciotransporte.apiserviciotransporte.entidades.DetalleUsuario;
import apiserviciotransporte.apiserviciotransporte.entidades.SolicitudServicio;
import apiserviciotransporte.apiserviciotransporte.interfaces.SolicitudServicioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.models.annotations.OpenAPI30;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/solicitudes-servicios")
@RequiredArgsConstructor
public class SolicitudServicioController {

    private final SolicitudServicioService solicitudServicio;

    @PreAuthorize("hasRole('ROLE_USUARIO')")
    @Operation(summary = "- Endpoint para realizar una solicitud de servicio en SSMU")
    @ApiResponse(responseCode = "200", description = "Solicitud Realizada con Éxito")
    @PostMapping(value = "", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> crearSolicitudServicio(@Valid @RequestBody SolicitudServicioDto solicitudServicioDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return this.getServerResponseErrorEntity(bindingResult);
        }
        SolicitudServicioDto solicitudGuardada = this.solicitudServicio.guardarSolicitudServicio(solicitudServicioDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(solicitudGuardada);
    }

    @PreAuthorize("hasRole('ROLE_USUARIO')")
    @Operation(summary = "- Endpoint para obtener el listado de solicitudes realizadas en SSMU")
    @ApiResponse(responseCode = "200", description = "Listado de Solicitudes de Servicio")
    @GetMapping(value = "", produces = "application/json")
    public ResponseEntity<SolicitudesServicioResponseDto> listarPorUsuario(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("type") SolicitudServicioDto.TipoSolicitud type
    ) {
        SolicitudesServicioResponseDto responseDto = this.solicitudServicio.listarPorUsuario(page, size, type);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseDto);
    }

    @PreAuthorize("hasRole('ROLE_USUARIO')")
    @Operation(summary = "- Endpoint para obtener una solicitud de servicio en SSMU por su id")
    @ApiResponse(responseCode = "200", description = "Operación Exitosa")
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<SolicitudServicioDto> obtener(@PathVariable long id) {
        SolicitudServicioDto solciitudDto = solicitudServicio.obtener(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(solciitudDto);
    }

    @PreAuthorize("hasRole('ROLE_USUARIO')")
    @Operation(summary = "- Endpoint para buscar una solicitud de servicio en SSMU por fecha")
    @ApiResponse(responseCode = "200", description = "Solicitud de Servicio por fecha obtenida con éxito")
    @GetMapping(value = "/date/{fecha}", produces = "application/json")
    public ResponseEntity<List<SolicitudServicioDto>> buscarPorFecha(@PathVariable("fecha") LocalDateTime fecha) {
        List<SolicitudServicioDto> solicitudes = solicitudServicio.buscarPorFecha(fecha);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(solicitudes);
    }

    @PreAuthorize("hasRole('ROLE_USUARIO')")
    @Operation(summary = "- Endpoint para eliminar una solicitud de servicio en SSMU")
    @ApiResponse(responseCode = "204", description = "Solicitud de Servicio eliminada con éxito")
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<SolicitudDeleteResponseDto> eliminar(@PathVariable("id") long id) {
        boolean eliminado = solicitudServicio.eliminar(id);
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
    public SolicitudServicio actualizar(@PathVariable("id") String id, @RequestBody SolicitudServicioDto solicitudServicio) {
        return null;
    }

    private ResponseEntity<Map<String, String>> getServerResponseErrorEntity(BindingResult bindingResult) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(bindingResult.getFieldErrors()
                        .stream()
                        .collect(Collectors.toMap(FieldError::getField, DefaultMessageSourceResolvable::getDefaultMessage)));
    }

}