package apiserviciotransporte.apiserviciotransporte.servicios.SolicitudPaqueteServiceTests;

import apiserviciotransporte.apiserviciotransporte.controladores.dto.SolicitudPaqueteDto;
import apiserviciotransporte.apiserviciotransporte.controladores.dto.SolicitudServicioDto;
import apiserviciotransporte.apiserviciotransporte.controladores.dto.SolicitudesPaqueteResponseDto;
import apiserviciotransporte.apiserviciotransporte.entidades.DimensionesPaquete;
import apiserviciotransporte.apiserviciotransporte.entidades.SolicitudPaquete;
import apiserviciotransporte.apiserviciotransporte.entidades.Usuario;
import apiserviciotransporte.apiserviciotransporte.mappers.SolicitudPaqueteMapper;
import apiserviciotransporte.apiserviciotransporte.repositorios.SolicitudPaqueteRepository;
import apiserviciotransporte.apiserviciotransporte.repositorios.UsuarioRepository;
import apiserviciotransporte.apiserviciotransporte.servicios.SolicitudPaqueteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ListarSolicitudesPaqueteTest {

    @Mock
    SolicitudPaqueteServiceImpl solicitudPaqueteService;

    @Mock
    SolicitudPaqueteRepository solicitudPaqueteRepository;

    @Mock
    UsuarioRepository usuarioRepository;

    @Mock
    SolicitudPaqueteMapper mapper = new SolicitudPaqueteMapper();

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listarSolicitudesPaquete() {

        Usuario usuario = new Usuario();
        usuario.setId("1");

        DimensionesPaquete paquete1 = new DimensionesPaquete();
        paquete1.setAlto(10);
        paquete1.setAncho(20);
        paquete1.setLargo(30);
        paquete1.setPeso(17);

        var solicitudPaquete1 = new SolicitudPaquete();
        solicitudPaquete1.setId(1L);
        solicitudPaquete1.setUsuario(usuario);
        solicitudPaquete1.setActiva(true);
        solicitudPaquete1.setAlimentosOMercado(true);
        solicitudPaquete1.setOrigen("Calle 1 # 6 -7");
        solicitudPaquete1.setDestino("Calle 3 # 16B -7");
        solicitudPaquete1.setFecha(LocalDateTime.now());
        solicitudPaquete1.setDimensiones(DimensionesPaquete.builder()
                        .id(1L)
                        .largo(paquete1.getLargo())
                        .ancho(paquete1.getAncho())
                        .alto(paquete1.getAlto())
                        .peso(paquete1.getPeso())
                .build());

        var solicitudPaquete2 = new SolicitudPaquete();
        solicitudPaquete2.setId(2L);
        solicitudPaquete2.setUsuario(usuario);
        solicitudPaquete2.setActiva(true);
        solicitudPaquete2.setAlimentosOMercado(false);
        solicitudPaquete2.setOrigen("Calle 11 # 61 -17");
        solicitudPaquete2.setDestino("Calle 31 # 116B -71");
        solicitudPaquete2.setFecha(LocalDateTime.now());
        solicitudPaquete2.setDimensiones(DimensionesPaquete.builder()
                        .alto(18)
                        .ancho(25)
                        .largo(75)
                        .peso(35)
                .build());

        List<SolicitudPaquete> listaSolicitudes = new ArrayList<>();

        listaSolicitudes.add(solicitudPaquete1);
        listaSolicitudes.add(solicitudPaquete2);

        when(solicitudPaqueteRepository.findAll()).thenReturn(listaSolicitudes);

        List<SolicitudPaqueteDto> resultado = listaSolicitudes.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());

        assertNotNull(resultado);

        assertEquals(listaSolicitudes.size(), resultado.size());
    }
}
