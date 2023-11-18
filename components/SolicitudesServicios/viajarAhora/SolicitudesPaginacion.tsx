import React from 'react';

const SolicitudesPaginacion = () => {
  const irAPaginaSiguiente = () => {};
  const irAPaginaAnterior = () => {};

  return (
    <div className='flex justify-center'>
      <button
        onClick={() => irAPaginaSiguiente()}
        type='button'
        className='mx-1 px-4 rounded-2xl border-b-4 border-b-blue-600 bg-blue-500 py-2 font-bold text-white hover:bg-blue-400 active:translate-y-[0.125rem] active:border-b-blue-400'
      >
        Página anterior
      </button>
      <button
        onClick={() => irAPaginaAnterior()}
        type='button'
        className='mx-1 px-4 rounded-2xl border-b-4 border-b-blue-600 bg-blue-500 py-2 font-bold text-white hover:bg-blue-400 active:translate-y-[0.125rem] active:border-b-blue-400'
      >
        Página siguiente
      </button>
    </div>
  );
};

export default SolicitudesPaginacion;
