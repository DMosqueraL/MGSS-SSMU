package apiserviciotransporte.apiserviciotransporte.repositorios;

import apiserviciotransporte.apiserviciotransporte.entidades.SolicitudPaquete;
import apiserviciotransporte.apiserviciotransporte.entidades.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SolicitudPaqueteRepository extends JpaRepository<SolicitudPaquete, Long>, QueryByExampleExecutor<SolicitudPaquete> {
    List<SolicitudPaquete> findByFecha(LocalDateTime fecha);
    List<SolicitudPaquete> findByUsuario(Usuario usuario);
    Page<SolicitudPaquete> findAllByActiva(boolean activa, Pageable pageable);


}
