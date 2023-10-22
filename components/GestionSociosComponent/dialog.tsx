import React, { useState } from "react";
import { AiOutlineRight, AiFillCloseCircle } from "react-icons/ai";
import { IoDocumentTextSharp } from "react-icons/io5";

interface InputDialog {
  dialogo: string;
}

const Dialog = ({ dialogo }: InputDialog) => {
  const [open, setOpen] = useState(false);

  const handleOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  return (
    <div>
      <button onClick={handleOpen}>
        <h1 className='flex justify-around font-bold p-2 hover:scale-105'>
          <IoDocumentTextSharp className="mr-3 text-3xl" />
          {dialogo}
          <AiOutlineRight className="mr-3 text-3xl" />
        </h1>
      </button>
      {open && (
        <div className="relative inset-0 z-0 flex items-center p-0">
          <div className="bg-white p-4 rounded shadow-lg w-[100%] h-[200px]">
            <h2 className="text-lg font-semibold">{dialogo}</h2>
            <p>...</p>
            <button className="absolute bottom-0 right-0" onClick={handleClose}>
              <AiFillCloseCircle className="mr-3 text-3xl text-red-500"/>
            </button>
          </div>
        </div>
      )}
    </div>
  );
}
export {Dialog};




