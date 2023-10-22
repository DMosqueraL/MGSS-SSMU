import { render, screen } from '@testing-library/react';
import { ExampleComponent } from '.';

describe('ExampleComponentTests', () => {
  const mockProps = {
    text: 'test',
  };
  beforeEach(() => {
    render(<ExampleComponent {...mockProps} />);
  });

  it('renders without crashing', () => {
    const enterpriseElement = screen.getByText('test');
    expect(enterpriseElement).toBeInTheDocument();
  });

  it('has the correct styles', () => {
    const { getByText } = render(<ExampleComponent text='Styled Text' />);
    const spanElement = getByText('Styled Text');

    expect(spanElement).toHaveClass('text-3xl');
    expect(spanElement).toHaveClass('font-semibold');
    expect(spanElement).toHaveClass('tracking-wider');
  });
});
