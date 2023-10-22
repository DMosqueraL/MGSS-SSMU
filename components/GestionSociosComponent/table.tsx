import React from 'react';
import { ButtonSocios } from './ButtonSocios';
import { BsFillEyeFill } from 'react-icons/bs'

interface Data {
  cedula: string;
  name: string;
  telefono: string;
  licencia: string;
  pasJudicial: string;
  estado: string;
}

interface Props {
  data: Data[];
}

const TablaGestionSocios: React.FC<Props> = ({ data }) => {
  return (
    <table className='w-full'>
      <thead className='bg-gray-socio border-double border-2 pd-1'>
        <tr className='text-[15px] text-center'>
          <th>Cédula</th>
          <th>Nombre</th>
          <th>Telefono</th>
          <th>Licencia</th>
          <th>P.Judicial</th>
          <th>Estado</th>
          <th>Info</th>
        </tr>
      </thead>
      <style>{`
        tbody tr:nth-child(odd) {
          background-color: #EFEFF8;
        }
      `}
      </style>
      <tbody className='text-center text-[14px] bg-gray-200'>
        {data.map((item, index) => (
          <tr key={index} className='h-10'>
            <td>{item.cedula}</td>
            <td>{item.name}</td>
            <td>{item.telefono}</td>
            <td>{item.licencia}</td>
            <td>{item.pasJudicial}</td>
            {item.estado.toUpperCase() === 'ACEPTADO' ? (
              <td>
                <ButtonSocios color='#ABD08D' onClick={() => { }}>
                  <span>Aceptado</span>
                </ButtonSocios>
              </td>
            ) : item.estado.toUpperCase() === 'RECHAZADO' ? (
              <td>
                <ButtonSocios color='#F39A9E' onClick={() => { }}>
                  <span>Rechazado</span>
                </ButtonSocios>
              </td>
            ) : item.estado.toUpperCase() === 'PENDIENTE' ? (
              <td>
                <ButtonSocios color='#FBCB9E' onClick={() => { }}>
                  <span>Pendiente</span>
                </ButtonSocios>
              </td>
            ) : item.estado.toUpperCase() === 'RETIRADO' ? (
              <td>
                <ButtonSocios color='rgb(156 163 175)' onClick={() => { }}>
                  <span>Retirado</span>
                </ButtonSocios>
              </td>
            ) : (
              <td>{item.estado}</td>
            )}
            <td>
              <button onClick={() => {
              }}>
                <BsFillEyeFill className="mr-2 text-3xl hover:scale-105" />
              </button>
            </td>
          </tr>
        ))}
      </tbody>
    </table>
  );
};

export { TablaGestionSocios };
