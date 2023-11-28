package apiserviciotransporte.apiserviciotransporte.servicios.SolicitudServicioServiceTests;

import apiserviciotransporte.apiserviciotransporte.controladores.dto.SolicitudServicioDto;
import apiserviciotransporte.apiserviciotransporte.entidades.SolicitudServicio;
import apiserviciotransporte.apiserviciotransporte.entidades.TipoServicio;
import apiserviciotransporte.apiserviciotransporte.mappers.SolicitudServicioMapper;
import apiserviciotransporte.apiserviciotransporte.repositorios.SolicitudServicioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ListarSolicitudesServicioTest {

    @Mock
    SolicitudServicioRepository repositoryServicioTest;

   SolicitudServicioMapper solicitudServicioMapper = new SolicitudServicioMapper();

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void listarSolicitudServicio() {

        var solicitudServicioDto1 = new SolicitudServicioDto();
        solicitudServicioDto1.setId(1L);
        solicitudServicioDto1.setOrigen("Calle 1 # 6 -7");
        solicitudServicioDto1.setDestino("Calle 3 # 16B -7");
        solicitudServicioDto1.setActiva(true);
        solicitudServicioDto1.setInmediato(true);
        solicitudServicioDto1.setCondicionesServicio(SolicitudServicioDto.SolicitudAdicional.builder()
                        .maletas(true)
                        .mascotas(false)
                .build());
        solicitudServicioDto1.setParadas(Arrays.asList());
        solicitudServicioDto1.setCantidadPasajeros(1);
        solicitudServicioDto1.setTipo(SolicitudServicioDto.EnumTipoServicio.NORMAL);

        var solicitudServicioDto2 = new SolicitudServicioDto();
        solicitudServicioDto2.setId(2L);
        solicitudServicioDto2.setOrigen("Calle 5 # 12 -34");
        solicitudServicioDto2.setDestino("Calle 8 # 22A -10");
        solicitudServicioDto2.setActiva(true);
        solicitudServicioDto2.setInmediato(false);
        solicitudServicioDto2.setCondicionesServicio(SolicitudServicioDto.SolicitudAdicional.builder()
                .maletas(false)
                .mascotas(false)
                .build());
        solicitudServicioDto2.setParadas(Arrays.asList());
        solicitudServicioDto2.setCantidadPasajeros(2);
        solicitudServicioDto2.setTipo(SolicitudServicioDto.EnumTipoServicio.LUJO);

        List<SolicitudServicio> listaSolicitudes = new ArrayList<>();
        listaSolicitudes.add(solicitudServicioMapper.toEntity(solicitudServicioDto1,
                new TipoServicio(1,SolicitudServicioDto.EnumTipoServicio.NORMAL)));
        listaSolicitudes.add(solicitudServicioMapper.toEntity(solicitudServicioDto2,
                new TipoServicio(2,SolicitudServicioDto.EnumTipoServicio.LUJO)));

        when(repositoryServicioTest.findAll()).thenReturn(listaSolicitudes);

        List<SolicitudServicioDto> resultado = listaSolicitudes.stream()
                .map(solicitudServicioMapper::toDto)
                .collect(Collectors.toList());

        assertEquals(2, resultado.size());

        assertEquals(1L, resultado.get(0).getId() != null ?
                resultado.get(0).getId().longValue() : 1L);
        assertEquals("NORMAL", resultado.get(0).getTipo().toString());
        assertEquals("Calle 1 # 6 -7", resultado.get(0).getOrigen());
        assertEquals(true, resultado.get(0).getInmediato());

        assertEquals(2L, resultado.get(1).getId() != null ?
                resultado.get(1).getId().longValue() : 2L);
        assertEquals("LUJO", resultado.get(1).getTipo().toString());
        assertEquals(2, resultado.get(1).getCantidadPasajeros());
        assertEquals(false, resultado.get(1).getCondicionesServicio().isMaletas());
    }
}
