package apiserviciotransporte.apiserviciotransporte.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import apiserviciotransporte.apiserviciotransporte.entidades.TipoServicio;

public interface TipoServicioRepositorio extends JpaRepository<TipoServicio, Long> {
    
}
