package apiserviciotransporte.apiserviciotransporte.servicios;

import apiserviciotransporte.apiserviciotransporte.entidades.SolicitudAdicional;
import apiserviciotransporte.apiserviciotransporte.entidades.SolicitudParada;
import apiserviciotransporte.apiserviciotransporte.entidades.SolicitudServicio;
import apiserviciotransporte.apiserviciotransporte.repositorios.SolicitudServicioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SolicitudServicioService implements apiserviciotransporte.apiserviciotransporte.interfaces.SolicitudServicioService {

    private final SolicitudServicioRepository repositorio;

    @Override
    public List<SolicitudServicio> listar() {
        return repositorio.findAll();
    }

    @Override
    public SolicitudServicio obtener(Long id) {
        var solicitudServicio = repositorio.findById(id);
        return solicitudServicio.isEmpty() ? null : solicitudServicio.get();
    }

    @Override
    public List<SolicitudServicio> buscarPorFecha(Date fecha) {
        return repositorio.buscarPorFecha(fecha);
    }

    @Override
    public List<SolicitudServicio> buscarPorUsuario(String usuario) {
        return repositorio.buscarPorUsuario(usuario);
    }

    @Override
    public SolicitudServicio guardar(SolicitudServicio solicitudServicio) {
        return repositorio.save(solicitudServicio);
    }

    @Override
    public boolean eliminar(Long id) {
        try {
            repositorio.deleteById(id);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    /***** Adicionales *****/

    @Override
    public List<SolicitudAdicional> listarAdicionales(Long id) {
        // Buscar la solicitud por su ID
        var solicitudServicioBuscada = repositorio.findById(id);

        if (solicitudServicioBuscada.isPresent()) {
            var solicitudServicio = solicitudServicioBuscada.get();

            return solicitudServicio.getAdicionales();
        }
        return null;
    }

    public void agregarAdicional(Long id, SolicitudAdicional solicitudAdicionales) {
        // Buscar la solicitud por su ID
        var solicitudServicioBuscada = repositorio.findById(id);

        if (solicitudServicioBuscada.isPresent()) {
            var solicitudServicio = solicitudServicioBuscada.get();

            // Agregar el adicional a la lista de adicionales de la solicitud
            solicitudServicio.getAdicionales().add(solicitudAdicionales);

            // Actualizar el autor en la base de datos
            repositorio.save(solicitudServicio);
        } else {
            // Manejar el caso en que no se encuentra la solicitud
            throw new EntityNotFoundException("No se encontró la Solicitud de Servicio con ID: " + id);
        }
    }

    /***** Paradas *****/

    @Override
    public List<SolicitudParada> listarParadas(Long id) {
        // Buscar la solicitud por su ID
        var solicitudServicioBuscada = repositorio.findById(id);

        if (solicitudServicioBuscada.isPresent()) {
            var solicitudServicio = solicitudServicioBuscada.get();

            return solicitudServicio.getParadas();
        }
        return null;
    }

    public void agregarParada(Long id, SolicitudParada solicitudParada) {
        // Buscar la solicitud por su ID
        var solicitudServicioBuscada = repositorio.findById(id);

        if (solicitudServicioBuscada.isPresent()) {
            var solicitudServicio = solicitudServicioBuscada.get();

            // Agregar el adicional a la lista de adicionales de la solicitud
            solicitudServicio.getParadas().add(solicitudParada);

            // Actualizar el autor en la base de datos
            repositorio.save(solicitudServicio);
        } else {
            // Manejar el caso en que no se encuentra la solicitud
            throw new EntityNotFoundException("No se encontró la Solicitud de Servicio con ID: " + id);
        }
    }

}
