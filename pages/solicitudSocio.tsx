import React from 'react';
import {TablaGestionSocios} from '@/components/GestionSociosComponent/table';

const data = [
  {
    cedula: '111',
    name: 'xxxxxx',
    telefono: '####',
    licencia: '',
    pasJudicial: 'P.Judicial',
    estado: 'ACEPTADO'
  },
  {
    cedula: '112',
    name: 'xxxxxx',
    telefono: '####',
    licencia: '',
    pasJudicial: 'P.Judicial',
    estado: 'rechazado'
  },
];

const secondPage: React.FC = () => {
  return (
    <div>
      <div className="flex items-center justify-center bg-blue-socio p-4 rounded-sm">
        <h1 className="text-[35px] text-white text-center font-semibold">
          Solicitud de Socios
        </h1>
      </div>
      <div className="p-4">
        <TablaGestionSocios data={data} />
      </div>
    </div>
  );
}

export default secondPage;