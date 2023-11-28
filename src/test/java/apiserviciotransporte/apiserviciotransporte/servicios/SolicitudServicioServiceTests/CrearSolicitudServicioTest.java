package apiserviciotransporte.apiserviciotransporte.servicios.SolicitudServicioServiceTests;

import apiserviciotransporte.apiserviciotransporte.controladores.dto.SolicitudServicioDto;
import apiserviciotransporte.apiserviciotransporte.interfaces.SolicitudServicioService;
import apiserviciotransporte.apiserviciotransporte.repositorios.SolicitudServicioRepository;
import apiserviciotransporte.apiserviciotransporte.repositorios.TipoServicioRepository;
import apiserviciotransporte.apiserviciotransporte.repositorios.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CrearSolicitudServicioTest {

    @Mock
    SolicitudServicioService solicitudServicioTest;

    @Mock
    SolicitudServicioRepository repositoryServicioTest;

    @Mock
    TipoServicioRepository tipoServicioRepository;

    @Mock
    UsuarioRepository usuarioRepository;

    @BeforeEach
    void setup(){
        repositoryServicioTest = mock(SolicitudServicioRepository.class);
        tipoServicioRepository = mock(TipoServicioRepository.class);
        usuarioRepository = mock(UsuarioRepository.class);
    }

    @Test
    void crearSolicitudServicio() {

        var solicitudServicioDto = new SolicitudServicioDto();
        solicitudServicioDto.setId(1L);
        solicitudServicioDto.setOrigen("Calle 1 # 6 -7");
        solicitudServicioDto.setDestino("Calle 3 # 16B -7");
        solicitudServicioDto.setActiva(true);
        solicitudServicioDto.setCantidadPasajeros(1);

        when(solicitudServicioTest.guardarSolicitudServicio(solicitudServicioDto)).thenReturn(solicitudServicioDto);

        SolicitudServicioDto resultado = SolicitudServicioDto.builder()
                .id(1L)
                .origen("Calle 10 # 34A - 56")
                .destino("")
                .activa(true)
                .cantidadPasajeros(1)
                .build();

        assertEquals(1L, resultado.getId());
        assertNotNull(resultado.getDestino());
        assertEquals("Calle 10 # 34A - 56", resultado.getOrigen());
        assertTrue(resultado.isActiva());
        assertEquals(1, resultado.getCantidadPasajeros());
    }
}