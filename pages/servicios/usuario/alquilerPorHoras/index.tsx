import Header from '@/components/SolicitudesServicios/Header';
import {useForm} from 'react-hook-form';
import Swal from 'sweetalert2';
import {ApiService} from '@/services/api.service';

export default function alquilerPorHoras() {

    const {register, formState: {errors}, handleSubmit, getValues} = useForm();
    const apiService = new ApiService();

    const onSubmit = async function(data:any){
      // await apiService.post(data, 'url endpoint').then((rta:any) =>{
      //   Swal.fire({title: rta.message, focusConfirm: false, confirmButtonColor: '#3085d6', confirmButtonText: 'Aceptar'})          
      // });
      window.location.replace('/servicios/usuario/buscandoSocio')
    }

    return(
      <>
        <div className='m-4 mb-5 overflow-auto m-0 p-0'>
          <div className="flex items-center justify-center m-0 p-0">
            <Header/>
            <div className="max-w-md w-full bg-white rounded-lg shadow-lg">
              <div className='mt-24 px-6 m-0 p-0'>
                <h1 className="text-2xl font-semibold text-center text-gray-500 mt-4 mb-6">Alquiler por horas</h1>
                <form>
                  <div className="mb-4">
                    <label className="block mb-2 text-sm text-gray-600">Punto de partida</label>
                    <input type="text" id="start" className="w-full bg-transparent ring-2 ring-gray-300 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-cyan-500" 
                      required
                      {...register("start", {required: true})}
                    />
                    {(errors.start?.type === 'required') && <p className="text-red-500">Establece un punto de partida</p>}
                  </div>
                  <div className="mb-6">
                    <label className="block mb-2 text-sm text-gray-600">Tipo de vehículo</label>
                    <select id="vehicleCategory" className="w-full bg-transparent ring-2 ring-gray-300 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-cyan-500" 
                      required
                      {...register("vehicleCategory", {required: true})}
                    >
                        <option value="estandar">Estandar</option>
                        <option value="premium">Premium</option>
                    </select>
                    {(errors.vehicleCategory?.type === 'required') && <p className="text-red-500">Selecciona el tipo de vehículo</p>}
                  </div>
                  <div className="mb-6">
                    <label className="block mb-2 text-sm text-gray-600">Hora de partida</label>
                    <input type="time" id="time" className="w-full bg-transparent ring-2 ring-gray-300 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-cyan-500" 
                      required
                      {...register("time", {required: true})}
                    />
                    {(errors.time?.type === 'required') && <p className="text-red-500">Selecciona la hora en la que deseas viajar</p>}
                  </div>
                  <button onClick={handleSubmit(onSubmit)} type="submit" className="my-8 w-full rounded-2xl border-b-4 border-b-blue-600 bg-blue-500 py-2 font-bold text-white hover:bg-blue-400 active:translate-y-[0.125rem] active:bord    er-b-blue-400">Solicitar servicio</button>
                </form>

              </div> 
            </div>
          </div>
        </div>
    
      </>
    );
}