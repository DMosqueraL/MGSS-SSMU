package apiserviciotransporte.apiserviciotransporte.servicios.SolicitudPaqueteServiceTests;

import apiserviciotransporte.apiserviciotransporte.controladores.dto.SolicitudPaqueteDto;
import apiserviciotransporte.apiserviciotransporte.repositorios.SolicitudPaqueteRepository;
import apiserviciotransporte.apiserviciotransporte.repositorios.UsuarioRepository;
import apiserviciotransporte.apiserviciotransporte.servicios.SolicitudPaqueteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EliminarSolicitudPaquete {

    @Mock
    SolicitudPaqueteServiceImpl solicitudPaqueteService;

    @Mock
    SolicitudPaqueteRepository solicitudPaqueteRepository;

    @Mock
    UsuarioRepository usuarioRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void eliminarSolicitudPaquete() {
        var solicitudPaqueteDto = new SolicitudPaqueteDto();
        solicitudPaqueteDto.setId(1L);
        solicitudPaqueteDto.setOrigen("Calle 1 # 6 -7");
        solicitudPaqueteDto.setDestino("Calle 3 # 16B -7");
        solicitudPaqueteDto.setActiva(true);
        solicitudPaqueteDto.setAlimentosOMercado(true);
        solicitudPaqueteDto.setDimensiones(SolicitudPaqueteDto.DimensionesPaqueteDto
                .builder()
                        .ancho(18)
                        .alto(57)
                        .largo(36)
                        .peso(21)
                .build());

        when(solicitudPaqueteService.obtener(solicitudPaqueteDto.getId())).thenReturn(new SolicitudPaqueteDto());
        when(solicitudPaqueteService.guardarSolicitudPaquete(any())).thenReturn(new SolicitudPaqueteDto());

        boolean result = solicitudPaqueteService.eliminar(solicitudPaqueteDto.getId());

        assertFalse(result);
    }
}
