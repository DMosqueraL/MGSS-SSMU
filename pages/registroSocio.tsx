import { Input } from '@/components/GestionSociosComponent/Input';
import { HiIdentification } from 'react-icons/hi2'
import { BsFillPersonFill, BsFillPlusCircleFill} from 'react-icons/bs'
import { MdEmail } from 'react-icons/md'
import { FaPhoneSquareAlt } from 'react-icons/fa'
import { MainButtonSocio } from '@/components/GestionSociosComponent/MainButtonSocio';

const RegistroSocio = () => {
    return (
        <div className='flex h-screen'>
            <div className='w-[35%] bg-[#F2F2F2]'>
                <img src="/images/ssmu.png" alt="registro" />
            </div>
            <div className='w-[65%]'>
                <div className="flex items-center justify-center bg-blue-socio p-10">
                    <h1 className="text-white text-center font-semibold text-4xl">
                        Registrar nuevo socio
                    </h1>
                </div>
                <div className='flex flex-col items-center justify-center mt-10 gap-3'>

                    <div className='flex w-2/4 items-center'>
                        <HiIdentification className="mr-3 text-3xl text-green-socio" />
                        <Input placeHolder='Numero de cédula' />
                    </div>
                    <div className='flex w-2/4 items-center'>
                        <BsFillPersonFill className="mr-3 text-3xl text-green-socio" />
                        <Input placeHolder='Nombre' />
                    </div>
                    <div className='flex w-2/4 items-center'>
                        <MdEmail className="mr-3 text-3xl text-green-socio" />
                        <Input placeHolder='Correo' />
                    </div>
                    <div className='flex w-2/4 items-center'>
                        <FaPhoneSquareAlt className="mr-3 text-3xl text-green-socio" />
                        <Input placeHolder='Teléfono' />
                    </div>
                    <div className='mt-5'>
                        <MainButtonSocio name='Consultar Pasado Judicial' color='#6662D9' onClick={() => { }} />
                    </div>
                    <div className='flex flex-wrap gap-4 mt-4'>
                        <div className='flex items-center'>
                            <strong className='text-xl'>Pasado Judicial</strong>
                            <BsFillPlusCircleFill className="ml-3 mr-4 text-3xl text-red-socio hover:scale-110" />
                        </div>

                        <div className='flex items-center'>
                            <strong className='text-xl'>Licencia</strong>
                            <BsFillPlusCircleFill className="ml-3 mr-4 text-3xl text-red-socio hover:scale-110" />
                        </div>

                        <div className='flex items-center'>
                            <strong className='text-xl'>Foto</strong>
                            <BsFillPlusCircleFill className="ml-3 text-3xl text-red-socio hover:scale-110" />
                        </div>
                    </div>

                    <div className='mt-10'>
                        <MainButtonSocio name='Registrar' color='#6662D9' onClick={() => { }} />
                    </div>

                </div>

            </div>
        </div>
    )
}

export default RegistroSocio;