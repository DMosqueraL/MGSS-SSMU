package apiserviciotransporte.apiserviciotransporte.mappers;

import apiserviciotransporte.apiserviciotransporte.controladores.dto.SolicitudServicioDto;
import apiserviciotransporte.apiserviciotransporte.entidades.SolicitudAdicional;
import apiserviciotransporte.apiserviciotransporte.entidades.SolicitudParada;
import apiserviciotransporte.apiserviciotransporte.entidades.SolicitudServicio;
import apiserviciotransporte.apiserviciotransporte.entidades.TipoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SolicitudServicioMapper {

    public SolicitudServicio toEntity(SolicitudServicioDto dto, TipoServicio tipoServicio) {
        return SolicitudServicio.builder()
                .origen(dto.getOrigen())
                .destino(dto.getDestino())
                .cantidadPasajeros(dto.getCantidadPasajeros())
                .tipo(tipoServicio)
                .inmediato(dto.getInmediato())
                .condicionesServicio(SolicitudAdicional.builder()
                        .maletas(dto.getCondicionesServicio().isMaletas())
                        .mascotas(dto.getCondicionesServicio().isMascotas())
                        .build())
                .paradasIntermedias(!dto.getParadas().isEmpty())
                .fecha(dto.getFecha())
                .paradas(dto.getParadas().stream().map(direccion -> SolicitudParada.builder()
                                .direccion(direccion)
                                .build())
                        .toList())
                .build();
    }

    public SolicitudServicioDto toDto(SolicitudServicio entity) {
        return SolicitudServicioDto.builder()
                .id(entity.getId())
                .origen(entity.getOrigen())
                .destino(entity.getDestino())
                .activa(entity.isActiva())
                .cantidadPasajeros(entity.getCantidadPasajeros())
                .tipo(entity.getTipo().getTipo())
                .condicionesServicio(SolicitudServicioDto.SolicitudAdicional.builder()
                        .maletas(entity.getCondicionesServicio().isMaletas())
                        .mascotas(entity.getCondicionesServicio().isMascotas())
                        .build())
                .inmediato(entity.isInmediato())
                .fecha(entity.getFecha())
                .paradas(entity.getParadas()
                        .stream()
                        .map((SolicitudParada::getDireccion))
                        .toList())
                .build();
    }

}
