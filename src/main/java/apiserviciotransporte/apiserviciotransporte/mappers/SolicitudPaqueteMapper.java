package apiserviciotransporte.apiserviciotransporte.mappers;

import apiserviciotransporte.apiserviciotransporte.controladores.dto.SolicitudPaqueteDto;
import apiserviciotransporte.apiserviciotransporte.entidades.DimensionesPaquete;
import apiserviciotransporte.apiserviciotransporte.entidades.SolicitudPaquete;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SolicitudPaqueteMapper {

    public SolicitudPaquete toEntity(SolicitudPaqueteDto dto) {
        return SolicitudPaquete.builder()
                .origen(dto.getOrigen())
                .activa(dto.isActiva())
                .destino(dto.getDestino())
                .dimensiones(DimensionesPaquete.builder()
                        .alto(dto.getDimensiones().getAlto())
                        .ancho(dto.getDimensiones().getAncho())
                        .largo(dto.getDimensiones().getLargo())
                        .peso(dto.getDimensiones().getPeso())
                        .build())
                .fecha(dto.getFecha())
                .alimentosOMercado(dto.getAlimentosOMercado())
                .origen(dto.getOrigen())
                .destino(dto.getDestino())
                .fecha(dto.getFecha())
                .build();

    }

    public SolicitudPaqueteDto toDto(SolicitudPaquete entity) {
        return SolicitudPaqueteDto.builder()
                .id(entity.getId())
                .usuarioId(entity.getUsuario().getId())
                .origen(entity.getOrigen())
                .activa(entity.isActiva())
                .destino(entity.getDestino())
                .dimensiones(SolicitudPaqueteDto.DimensionesPaqueteDto.builder()
                        .alto(entity.getDimensiones().getAlto())
                        .ancho(entity.getDimensiones().getAncho())
                        .largo(entity.getDimensiones().getLargo())
                        .peso(entity.getDimensiones().getPeso())
                        .build())
                .fecha(entity.getFecha())
                .alimentosOMercado(entity.isAlimentosOMercado())
                .origen(entity.getOrigen())
                .destino(entity.getDestino())
                .fecha(entity.getFecha())
                .build();

    }

}
