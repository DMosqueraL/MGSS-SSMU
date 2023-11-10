package apiserviciotransporte.apiserviciotransporte.controladores;

import apiserviciotransporte.apiserviciotransporte.controladores.dto.SolicitudServicioDto;
import apiserviciotransporte.apiserviciotransporte.entidades.SolicitudAdicional;
import apiserviciotransporte.apiserviciotransporte.entidades.SolicitudParada;
import apiserviciotransporte.apiserviciotransporte.entidades.SolicitudServicio;
import apiserviciotransporte.apiserviciotransporte.entidades.Usuario;
import apiserviciotransporte.apiserviciotransporte.interfaces.SolicitudServicioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/solicitud-servicio")
@RequiredArgsConstructor
public class SolicitudServicioController {

    private final SolicitudServicioService servicioServicio;

    @PostMapping(value = "", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> crearSolicitudServicio(@Valid @RequestBody SolicitudServicioDto solicitudServicioDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return this.getServerResponseErrorEntity(bindingResult);
        }
        SolicitudServicio solicitudGuardada = this.servicioServicio.guardarSolicitudServicio(solicitudServicioDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(solicitudGuardada);
    }

    @GetMapping(value = "/listar", consumes = "application/json", produces = "application/json")
    public List<SolicitudServicio> listar() {
        return servicioServicio.listar();
    }

    @GetMapping(value = "/obtener/{id}", consumes = "application/json", produces = "application/json")
    public SolicitudServicio obtener(@PathVariable long id) {
        return servicioServicio.obtener(id);
    }

    @GetMapping(value = "/buscarPorFecha/{fecha}", consumes = "application/json", produces = "application/json")
    public List<SolicitudServicio> buscarPorFecha(@PathVariable Date fecha) {
        return servicioServicio.buscarPorFecha(fecha);
    }

    @GetMapping(value = "/buscarPorUsuario/{usuario}", consumes = "application/json", produces = "application/json")
    public List<SolicitudServicio> buscarPorUsuario(@PathVariable Usuario usuario) {
        return servicioServicio.buscarPorUsuario(usuario.getUsuario());
    }


    @DeleteMapping(value = "/eliminar/{id}", consumes = "application/json", produces = "application/json")
    public boolean eliminar(@PathVariable long id) {
        return servicioServicio.eliminar(id);
    }

    @PutMapping(value = "/modificar", consumes = "application/json", produces = "application/json")
    public SolicitudServicio actualizar(@RequestBody SolicitudServicioDto solicitudServicio) {
    return null;
    }

    @GetMapping(value = "/listarAdicionales/{id}", consumes = "application/json", produces = "application/json")
    public List<SolicitudAdicional> listarAdicionales(@PathVariable long id) {
        return servicioServicio.listarAdicionales(id);
    }

    @PostMapping(value = "/agregarAdicional/{id}", consumes = "application/json", produces = "application/json")
    public void agregarAdicional(@PathVariable Long id, @RequestBody SolicitudAdicional solicitudAdicionales) {
        servicioServicio.agregarAdicional(id, solicitudAdicionales);//ojo, retorno
    }

    @GetMapping(value = "/listarParadas/{id}", consumes = "application/json", produces = "application/json")
    public List<SolicitudParada> listarParadas(@PathVariable long id) {
        return servicioServicio.listarParadas(id);
    }

    @PostMapping(value = "/agregarParada/{id}", consumes = "application/json", produces = "application/json")
    public void agregarParada(@PathVariable Long id, @RequestBody SolicitudParada solicitudParada) {
        servicioServicio.agregarParada(id, solicitudParada);
    }

    private ResponseEntity<Map<String, String>> getServerResponseErrorEntity(BindingResult bindingResult) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(bindingResult.getFieldErrors()
                        .stream()
                        .collect(Collectors.toMap(FieldError::getField, DefaultMessageSourceResolvable::getDefaultMessage)));
    }

}

