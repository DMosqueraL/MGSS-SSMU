import { HiIdentification } from 'react-icons/hi2'
import { BsFillPersonFill } from 'react-icons/bs'
import { MdEmail, MdLocationCity } from 'react-icons/md'
import { FaPhoneSquareAlt } from 'react-icons/fa'
import { ButtonSocios } from '@/components/GestionSociosComponent/ButtonSocios';
import { Dialog } from '@/components/GestionSociosComponent/dialog';

const nombreSocio: React.FC = () => {
  return (
    <div className='bg-[#F2F2F2]'>
      <div className="flex items-center justify-center bg-blue-socio p-4 rounded-sm">
        <h1 className="text-[35px] text-white text-center font-semibold">
          Nombre de Socio
        </h1>
      </div>
      <div className="p-4 ">
        <div className='flex flex-row justify-around'>
          <div>
            <div>
              <img src="/images/image.png" alt="registro" className="w-[300px] px-25" />
            </div>
            <div>
              <Dialog dialogo='Licencia de conducir' />
            </div>
            <div>
              <Dialog dialogo='Pasado Judicial' />
            </div>
          </div>
          <div className='flex flex-col mt-8 gap-3 '>
            <div className='flex w-[160%]'>
              <HiIdentification className="mr-3 text-3xl text-green-socio" />
              <div className="relative w-[110%]">
                <input type="text" id="cedula" className="border-solid border-2 border-neutral-200" disabled={true} />
                <label htmlFor="cedula" className="absolute top-0 left-2 -mt-2 text-black-600 px-1 ">Cédula</label>
              </div>
            </div>
            <div className='flex w-[160%]'>
              <BsFillPersonFill className="mr-3 text-3xl text-green-socio" />
              <div className="relative w-[110%]">
                <input type="text" id="name" className="border-solid border-2 border-neutral-200" disabled={true} />
                <label htmlFor="name" className="absolute top-0 left-2 -mt-2 text-black-600 px-1 ">Nombre</label>
              </div>
            </div>
            <div className='flex w-[160%]'>
              <MdEmail className="mr-3 text-3xl text-green-socio" />
              <div className="relative w-[110%]">
                <input type="text" id="cedula" className="border-solid border-2 border-neutral-200" disabled={true} />
                <label htmlFor="cedula" className="absolute top-0 left-2 -mt-2 text-black-600 px-1 ">Correo electrónico</label>
              </div>
            </div>
            <div className='flex w-[160%]'>
              <FaPhoneSquareAlt className="mr-3 text-3xl text-green-socio" />
              <div className="relative w-[110%]">
                <input type="text" id="cedula" className="border-solid border-2 border-neutral-200" disabled={true} />
                <label htmlFor="cedula" className="absolute top-0 left-2 -mt-2 text-black-600 px-1 ">Teléfono</label>
              </div>
            </div>
            <div className='flex w-[160%]'>
              <MdLocationCity className="mr-3 text-3xl text-green-socio" />
              <div className="relative w-[110%]">
                <input type="text" id="cedula" className="border-solid border-2 border-neutral-200" disabled={true} />
                <label htmlFor="cedula" className="absolute top-0 left-2 -mt-2 text-black-600 px-1 ">Ciudad de Servicio</label>
              </div>
            </div>
          </div>
        </div>
        <div className="p-2 pr-20 flex flex-row-reverse">
          <div className="px-5">
            <ButtonSocios color='#F39A9E' onClick={() => { }}>
              <span>Rechazar</span>
            </ButtonSocios>
          </div>
          <div className="px-5">
            <ButtonSocios color='#ABD08D' onClick={() => { }}>
              <span>Aceptar</span>
            </ButtonSocios>
          </div>
        </div>
      </div>
    </div>
  );
}

export default nombreSocio;