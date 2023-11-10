package apiserviciotransporte.apiserviciotransporte.repositorios;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import apiserviciotransporte.apiserviciotransporte.entidades.SolicitudServicio;

public interface SolicitudServicioRepositorio extends JpaRepository<SolicitudServicio, Long> {

    @Query("SELECT ss FROM SolicitudServicio ss WHERE ss.fecha=?1")
    List<SolicitudServicio> buscarPorFecha(Date fecha);

    @Query("SELECT ss FROM SolicitudServicio ss WHERE ss.usuario.usuario=?1")
    List<SolicitudServicio> buscarPorUsuario(String usuario);

}
