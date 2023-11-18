import Header from '@/components/SolicitudesServicios/shared/Header';
import { useForm } from 'react-hook-form';
import Swal from 'sweetalert2';
import { ApiService } from '@/services/api.service';
import { Fragment, useEffect, useState } from 'react';
import { v4 as uuidV4 } from 'uuid';
import SolicitudesServicioList from '@/components/SolicitudesServicios/viajarAhora/SolicitudesServicioList';
import CrearSolicitudServicio, {
  SolicitudServicioInfo,
} from '@/components/SolicitudesServicios/viajarAhora/CrearSolicitudServicio';
import SolicitudesPaginacion from '@/components/SolicitudesServicios/viajarAhora/SolicitudesPaginacion';

const ViajarAhoraPage = () => {
  const [paginacion, setPaginacion] = useState({
    page: 0,
    size: 10,
  });
  const apiService = new ApiService();
  const [solicitudesServicio, setSolicitudesServicio] = useState<
    SolicitudServicioInfo[]
  >([]);

  useEffect(() => {
    apiService
      .get<SolicitudServicioInfo[]>('/solicitudes-servicios', paginacion)
      .then((data) => {
        setSolicitudesServicio(data);
      })
      .catch((err) => {
        return Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: 'No se pudo obtener las solicitudes',
        });
      });
  }, []);

  let [userId] = useState<string>('e9c596b2-780b-4aea-845f-855d2678a8cd');

  return (
    <Fragment>
      <div className='h-screen  flex flex-row justify-evenly flex-wrap'>
        <div className='h-screen' style={{ minWidth: '500px' }}>
          <CrearSolicitudServicio
            setSolicitudesServicio={setSolicitudesServicio}
          />
        </div>

        <div
          className='ml-5 overflow-y-auto h-screen w-max flex-grow'
          style={{ minWidth: '500px' }}
        >
          <SolicitudesServicioList
            solicitudesServicio={solicitudesServicio}
            setSolicitudesServicio={setSolicitudesServicio}
          />
          <SolicitudesPaginacion />
        </div>
      </div>
    </Fragment>
  );
};

export default ViajarAhoraPage;
