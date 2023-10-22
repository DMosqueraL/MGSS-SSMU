import Header from '@/components/SolicitudesServicios/Header';
import {useForm} from 'react-hook-form';
import Swal from 'sweetalert2';
import {ApiService} from '@/services/api.service';
import { useState } from 'react';

export default function ViajarAhora() {

    const {register, unregister, formState: {errors}, handleSubmit, getValues} = useForm();
    const apiService = new ApiService();
    let [paradas, setParadas] = useState<string[]>([]);

    const onSubmit = async function(data:any){
      data.paradas = [];

      Object.entries(data).forEach(([key, value]) => {
        if(key.includes('parada') && getValues(key)!=undefined && getValues(key)!= ""){
          data.paradas.push(getValues(key))
          delete data[key];
        }
      });

      // await apiService.post(data, 'url endpoint').then((rta:any) =>{
      //   Swal.fire({title: rta.message, focusConfirm: false, confirmButtonColor: '#3085d6', confirmButtonText: 'Aceptar'})          
      // });
      // window.location.replace('/servicios/usuario/buscandoSocio');
      console.log(data)
    }

    const agregarParada = function(){
      setParadas(prevParadas => [...prevParadas, '']);
    }

    return(
      <>
        <div className='m-4 mb-5 overflow-auto m-0 p-0'>
          <div className="flex items-center justify-center m-0 p-0">
            <Header/>
            <div className="max-w-md w-full bg-white rounded-lg shadow-lg">
              <div className='mt-24 px-6 m-0 p-0'>
                <h1 className="text-2xl font-semibold text-center text-gray-500 mt-4 mb-6">Servicio de viaje</h1>
                <form>
                  <div className="mb-4">
                    <label className="block mb-2 text-sm text-gray-600">Punto de partida</label>
                    <input type="text" id="start" className="w-full bg-transparent ring-2 ring-gray-300 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-cyan-500" 
                      required
                      {...register("start", {required: true})}
                    />
                    {(errors.start?.type === 'required') && <p className="text-red-500">Establece un punto de partida</p>}
                  </div>
                  {
                    paradas.map((parada:any, i:number = paradas.indexOf(parada))=>{
                      return(
                        <div key={i} className="mb-4">
                          <div className='flex justify-between'>
                            <label className="block mb-2 text-sm text-gray-600">Parada {i+1}</label>
                            {/* <label onClick={()=>{unregister(`parada${i+1}`);setParadas(paradas.splice(0,paradas.length-1))}} className="block mb-2 text-sm text-red-600">Quitar</label> */}
                          </div>
                          <input type="text" id='parada' className="w-full bg-transparent ring-2 ring-gray-300 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-cyan-500" 
                            required
                            {...register(`parada${i+1}`, {required: true})}
                          />
                        </div>
                      )
                    })
                  }
                  <div className="mb-4">
                    <label className="block mb-2 text-sm text-gray-600">¿A donde viajas?</label>
                    <input type="text" id="destiny" className="w-full bg-transparent ring-2 ring-gray-300 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-cyan-500" 
                      required
                      {...register("destiny", {required: true})}
                    />
                    {(errors.destiny?.type === 'required') && <p className="text-red-500">Establece un punto de llegada</p>}
                  </div>
                  <div className="mb-4">
                    <button onClick={agregarParada} type='button' className="w-full rounded-2xl border-b-4 border-b-blue-600 bg-blue-500 py-2 font-bold text-white hover:bg-blue-400 active:translate-y-[0.125rem] active:border-b-blue-400">Agregar parada</button>
                    {paradas.length>0&&<button onClick={()=>{unregister(`parada${paradas.length}`);setParadas(paradas.splice(0,paradas.length-1))}} type='button' className="w-full rounded-2xl border-b-4 border-b-red-600 bg-red-500 py-2 font-bold text-white hover:bg-red-400 active:translate-y-[0.125rem] active:border-b-red-400 mt-4">Quitar parada</button>}
                  </div>
                  <div className="mb-6">
                    <label className="block mb-2 text-sm text-gray-600">Número de viajeros</label>
                    <select id="passengersNumber" className="w-full bg-transparent ring-2 ring-gray-300 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-cyan-500" 
                      required
                      {...register("passengersNumber", {required: true})}
                    >
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                    </select>
                    {(errors.passengersNumber?.type === 'required') && <p className="text-red-500">Selecciona el número de pasajeros</p>}
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
                    <label className="block mb-2 text-sm text-gray-600">Hora del viaje</label>
                    <input type="time" id="time" className="w-full bg-transparent ring-2 ring-gray-300 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-cyan-500" 
                      required
                      {...register("time", {required: true})}
                    />
                    {(errors.time?.type === 'required') && <p className="text-red-500">Selecciona la hora en la que deseas viajar</p>}
                  </div>
                  <div className="w-full mb-6 inline-flex items-center">
                    <input type="checkbox" id="pet" className="form-checkbox w-16" 
                      {...register("pet", {required: false})}
                    />
                    <label className="ml-2">Viajo con mi mascota</label>
                  </div>
                  <div className="w-full mb-6 inline-flex items-center">
                    <input type="checkbox" id="suitcase" className="form-checkbox w-16" 
                      {...register("suitcase", {required: false})}
                    />
                    <label className="ml-2">Viajo con maletas</label>
                  </div>
                  <button onClick={handleSubmit(onSubmit)} type="submit" className="my-8 w-full rounded-2xl border-b-4 border-b-blue-600 bg-blue-500 py-2 font-bold text-white hover:bg-blue-400 active:translate-y-[0.125rem] active:border-b-blue-400">Solicitar servicio</button>
                </form>

              </div> 
            </div>
          </div>
        </div>
    
      </>
    );
}