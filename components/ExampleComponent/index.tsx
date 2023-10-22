interface ExampleComponentProps {
  text: string;
}

const ExampleComponent = ({ text }: ExampleComponentProps) => {
  return <span className='text-3xl font-semibold tracking-wider'>{text}</span>;
};

export { ExampleComponent };
