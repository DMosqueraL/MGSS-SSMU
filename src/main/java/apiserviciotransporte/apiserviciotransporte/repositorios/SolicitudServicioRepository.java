package apiserviciotransporte.apiserviciotransporte.repositorios;

import apiserviciotransporte.apiserviciotransporte.entidades.SolicitudServicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface SolicitudServicioRepository extends JpaRepository<SolicitudServicio, Long> {

    @Query("SELECT ss FROM SolicitudServicio ss WHERE ss.fecha=?1")
    List<SolicitudServicio> buscarPorFecha(Date fecha);

    @Query("SELECT ss FROM SolicitudServicio ss WHERE ss.usuario.usuario=?1")
    List<SolicitudServicio> buscarPorUsuario(String usuario);

}
