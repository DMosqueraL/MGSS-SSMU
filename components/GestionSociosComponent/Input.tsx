interface InputProps{
    placeHolder: string;
}

const Input = ({placeHolder}: InputProps) => {
    return <input placeholder={placeHolder}/>
};

export {Input};