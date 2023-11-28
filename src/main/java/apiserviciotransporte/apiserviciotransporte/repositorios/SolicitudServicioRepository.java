package apiserviciotransporte.apiserviciotransporte.repositorios;

import apiserviciotransporte.apiserviciotransporte.entidades.SolicitudServicio;
import apiserviciotransporte.apiserviciotransporte.entidades.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.time.LocalDateTime;
import java.util.List;

public interface SolicitudServicioRepository extends JpaRepository<SolicitudServicio, Long>, QueryByExampleExecutor<SolicitudServicio> {
    List<SolicitudServicio> findByFecha(LocalDateTime fecha);
    List<SolicitudServicio> findByUsuario(Usuario usuario);
    Page<SolicitudServicio> findAllByActivaAndInmediato(boolean activa, boolean inmediato, Pageable pageable);
    Page<SolicitudServicio> findAllByUsuarioAndActivaAndInmediato(Usuario usuario, boolean activa, boolean inmediato, Pageable pageable);


}
