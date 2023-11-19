package apiserviciotransporte.apiserviciotransporte.repositorios;

import apiserviciotransporte.apiserviciotransporte.entidades.SolicitudServicio;
import apiserviciotransporte.apiserviciotransporte.entidades.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface SolicitudServicioRepository extends JpaRepository<SolicitudServicio, Long>, QueryByExampleExecutor<SolicitudServicio> {
    List<SolicitudServicio> findByFecha(LocalDateTime fecha);
    List<SolicitudServicio> findByUsuario(Usuario usuario);
    Page<SolicitudServicio> findAllByActiva(boolean activa, PageRequest page);


}
