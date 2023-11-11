package apiserviciotransporte.apiserviciotransporte.repositorios;

import apiserviciotransporte.apiserviciotransporte.controladores.dto.SolicitudServicioDto;
import apiserviciotransporte.apiserviciotransporte.entidades.TipoServicio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TipoServicioRepository extends JpaRepository<TipoServicio, Long> {
    Optional<TipoServicio> findByTipo(SolicitudServicioDto.EnumTipoServicio tipo);
}
