interface Props {
    color: string;
    onClick: () => void;
    name?: React.ReactNode;
  }
  
  const MainButtonSocio: React.FC<Props> = ({ 
      name,
      color,
      onClick, 
    }) => { 
      return (
          <button className=" text-white text-md rounded-full px-4 py-2 font-semibold hover:scale-105"
            onClick={onClick}
            style={{
               backgroundColor: color,
            }}
          >
          {name}
          </button>
        );
      }
  
  export {MainButtonSocio};