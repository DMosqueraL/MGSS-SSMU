package apiserviciotransporte.apiserviciotransporte.mappers;

import apiserviciotransporte.apiserviciotransporte.controladores.dto.SolicitudServicioDto;
import apiserviciotransporte.apiserviciotransporte.entidades.SolicitudServicio;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class SolicitudServicioMapper {

    public SolicitudServicio toEntity(SolicitudServicioDto dto) {
        return SolicitudServicio.builder()
                .origen(dto.getOrigen())
                .destino(dto.getDestino())
                .cantidadPasajeros(dto.getCantidadPasajeros())
                .tipo(null)
                .condicionesServicio(null)
                .paradasIntermedias(dto.getParadasIntermedias())
                .inmediato(dto.getInmediato())
                .fecha(dto.getFecha())
                .horaProgramada(dto.getHoraProgramada())
                .paradas(new ArrayList<>())
                .build();
    }

    public SolicitudServicioDto toDto(SolicitudServicio entity) {
        return SolicitudServicioDto.builder()
                .origen(entity.getOrigen())
                .destino(entity.getDestino())
                .cantidadPasajeros(entity.getCantidadPasajeros())
                .tipo(null)
                .condicionesServicio(null)
                .paradasIntermedias(entity.isParadasIntermedias())
                .inmediato(entity.isInmediato())
                .fecha(entity.getFecha())
                .horaProgramada(entity.getHoraProgramada())
                .paradas(new ArrayList<>())
                .build();
    }

}
