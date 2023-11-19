package apiserviciotransporte.apiserviciotransporte.repositorios;

import apiserviciotransporte.apiserviciotransporte.entidades.NotificacionServicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificacionServicioRepository extends JpaRepository<NotificacionServicio, Long> { }

