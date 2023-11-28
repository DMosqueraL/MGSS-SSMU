package apiserviciotransporte.apiserviciotransporte.servicios.SolicitudPaqueteServiceTests;

import apiserviciotransporte.apiserviciotransporte.controladores.dto.SolicitudPaqueteDto;
import apiserviciotransporte.apiserviciotransporte.interfaces.SolicitudPaqueteService;
import apiserviciotransporte.apiserviciotransporte.repositorios.SolicitudPaqueteRepository;
import apiserviciotransporte.apiserviciotransporte.repositorios.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CrearSolicitudPaqueteTest {

    @Mock
    SolicitudPaqueteService solicitudPaqueteTest;

    @Mock
    SolicitudPaqueteRepository solicitudPaqueteRepository;

    @Mock
    UsuarioRepository usuarioRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void crearSolicitudPaquete() {

        var solicitudPaqueteDto = new SolicitudPaqueteDto();
        solicitudPaqueteDto.setId(1L);
        solicitudPaqueteDto.setActiva(true);
        solicitudPaqueteDto.setAlimentosOMercado(false);
        solicitudPaqueteDto.setOrigen("Calle 1 # 6 -7");
        solicitudPaqueteDto.setDestino("Calle 3 # 16B -7");
        solicitudPaqueteDto.setFecha(LocalDateTime.now());
        solicitudPaqueteDto.setDimensiones(SolicitudPaqueteDto.DimensionesPaqueteDto
                .builder()
                .alto(10)
                .ancho(20)
                .largo(25)
                .peso(15)
                .build());

        when(solicitudPaqueteTest.guardarSolicitudPaquete(solicitudPaqueteDto)).thenReturn(solicitudPaqueteDto);

        SolicitudPaqueteDto resultado = SolicitudPaqueteDto.builder()
                .id(1L)
                .origen("Calle 1 # 6 -7")
                .destino("Calle 3 # 16B -7")
                .dimensiones(SolicitudPaqueteDto.DimensionesPaqueteDto
                        .builder()
                        .alto(10)
                        .ancho(20)
                        .largo(25)
                        .peso(15)
                        .build())
                .build();

        assertEquals(1L, resultado.getId());
        assertEquals(10, resultado.getDimensiones().getAlto());
        assertEquals(20, resultado.getDimensiones().getAncho());
        assertEquals(25, resultado.getDimensiones().getLargo());
        assertEquals(15, resultado.getDimensiones().getPeso());
    }
}
