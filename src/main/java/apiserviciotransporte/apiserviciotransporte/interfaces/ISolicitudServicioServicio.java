package apiserviciotransporte.apiserviciotransporte.interfaces;

import java.util.Date;
import java.util.List;

import apiserviciotransporte.apiserviciotransporte.entidades.SolicitudAdicional;
import apiserviciotransporte.apiserviciotransporte.entidades.SolicitudParada;
import apiserviciotransporte.apiserviciotransporte.entidades.SolicitudServicio;

public interface ISolicitudServicioServicio {

    public List<SolicitudServicio> listar();

    public SolicitudServicio obtener(Long id);

    List<SolicitudServicio> buscarPorFecha(Date fecha);

    List<SolicitudServicio> buscarPorUsuario(String usuario);

    public SolicitudServicio guardar(SolicitudServicio solicitud);

    public boolean eliminar(Long id);

    public List<SolicitudAdicional> listarAdicionales(Long id);

    public void agregarAdicional(Long id, SolicitudAdicional solicitudAdicionales);

    public List<SolicitudParada> listarParadas(Long id);

    public void agregarParada(Long id, SolicitudParada solicitudParada);

}
