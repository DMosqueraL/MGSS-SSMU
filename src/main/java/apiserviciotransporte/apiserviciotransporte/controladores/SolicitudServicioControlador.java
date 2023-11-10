package apiserviciotransporte.apiserviciotransporte.controladores;

import apiserviciotransporte.apiserviciotransporte.entidades.SolicitudAdicional;
import apiserviciotransporte.apiserviciotransporte.entidades.SolicitudParada;
import apiserviciotransporte.apiserviciotransporte.entidades.SolicitudServicio;
import apiserviciotransporte.apiserviciotransporte.entidades.Usuario;
import apiserviciotransporte.apiserviciotransporte.interfaces.ISolicitudServicioServicio;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController

@RequestMapping("/solicitud_servicio")

public class SolicitudServicioControlador {
    private ISolicitudServicioServicio servicio;

    public SolicitudServicioControlador(ISolicitudServicioServicio servicio) {
        this.servicio = servicio;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public List<SolicitudServicio> listar() {
        return servicio.listar();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/obtener/{id}", method = RequestMethod.GET)
    public SolicitudServicio obtener(@PathVariable long id) {
        return servicio.obtener(id);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/buscarPorFecha/{fecha}", method = RequestMethod.GET)
    public List<SolicitudServicio> buscarPorFecha(@PathVariable Date fecha) {
        return servicio.buscarPorFecha(fecha);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/buscarPorUsuario/{usuario}", method = RequestMethod.GET)
    public List<SolicitudServicio> buscarPorUsuario(@PathVariable Usuario usuario) {
        return servicio.buscarPorUsuario(usuario.getUsuario());
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/agregar", method = RequestMethod.POST)
    public SolicitudServicio crear(@RequestBody SolicitudServicio solicitudServicio) {
        solicitudServicio.setId(0L);
        return servicio.guardar(solicitudServicio);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/eliminar/{id}", method = RequestMethod.DELETE)
    public boolean eliminar(@PathVariable long id) {
        return servicio.eliminar(id);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/modificar", method = RequestMethod.PUT)
    public SolicitudServicio actualizar(@RequestBody SolicitudServicio solicitudServicio) {
        if (servicio.obtener(solicitudServicio.getId()) != null) {
            return servicio.guardar(solicitudServicio);
        } else {
            return null;
        }
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/listarAdicionales/{id}", method = RequestMethod.GET)
    public List<SolicitudAdicional> listarAdicionales(@PathVariable long id) {
        return servicio.listarAdicionales(id);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/agregarAdicional/{id}", method = RequestMethod.POST)
    public void agregarAdicional(@PathVariable Long id, @RequestBody SolicitudAdicional solicitudAdicionales) {
        servicio.agregarAdicional(id, solicitudAdicionales);//ojo, retorno
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/listarParadas/{id}", method = RequestMethod.GET)
    public List<SolicitudParada> listarParadas(@PathVariable long id) {
        return servicio.listarParadas(id);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/agregarParada/{id}", method = RequestMethod.POST)
    public void agregarParada(@PathVariable Long id, @RequestBody SolicitudParada solicitudParada) {
         servicio.agregarParada(id, solicitudParada);
    }

}

