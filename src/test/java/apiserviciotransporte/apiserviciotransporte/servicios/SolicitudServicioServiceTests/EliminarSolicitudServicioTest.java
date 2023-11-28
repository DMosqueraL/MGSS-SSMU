package apiserviciotransporte.apiserviciotransporte.servicios.SolicitudServicioServiceTests;

import apiserviciotransporte.apiserviciotransporte.controladores.dto.SolicitudServicioDto;
import apiserviciotransporte.apiserviciotransporte.entidades.SolicitudAdicional;
import apiserviciotransporte.apiserviciotransporte.entidades.SolicitudServicio;
import apiserviciotransporte.apiserviciotransporte.entidades.TipoServicio;
import apiserviciotransporte.apiserviciotransporte.servicios.SolicitudServicioServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EliminarSolicitudServicioTest {

    @Mock
    SolicitudServicioServiceImpl solicitudServicioTest;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void eliminarSolicitudServicio() throws InstantiationException, IllegalAccessException {

        var solicitudServicioDto = new SolicitudServicio();
        solicitudServicioDto.setId(1L);
        solicitudServicioDto.setOrigen("Calle 1 # 6 -7");
        solicitudServicioDto.setDestino("Calle 3 # 16B -7");
        solicitudServicioDto.setTipo(new TipoServicio(1,SolicitudServicioDto.EnumTipoServicio.NORMAL));
        solicitudServicioDto.setActiva(true);
        solicitudServicioDto.setCantidadPasajeros(1);
        solicitudServicioDto.setCondicionesServicio(SolicitudAdicional.builder()
                        .mascotas(true)
                        .maletas(true)
                .build());
        solicitudServicioDto.setInmediato(true);
        solicitudServicioDto.setParadasIntermedias(false);
        solicitudServicioDto.setFecha(LocalDateTime.now());
        solicitudServicioDto.setParadas(Arrays.asList());

        when(solicitudServicioTest.obtener(solicitudServicioDto.getId())).thenReturn(new SolicitudServicioDto());
        when(solicitudServicioTest.guardarSolicitudServicio(any())).thenReturn(new SolicitudServicioDto());

        boolean result = solicitudServicioTest.eliminar(solicitudServicioDto.getId());

        assertFalse(result);
    }
}
