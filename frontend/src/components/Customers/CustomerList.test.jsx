import { render, screen } from '@testing-library/react';
import { describe, it, expect } from 'vitest';
import CustomerList from './CustomerList';

describe('CustomerList', () => {
  it('renders loading state', () => {
    render(<CustomerList loading={true} />);
    expect(screen.getByText('Cargando clientes...')).toBeInTheDocument();
  });

  it('renders empty state', () => {
    render(<CustomerList loading={false} customers={[]} />);
    expect(screen.getByText('No hay clientes registrados')).toBeInTheDocument();
  });

  it('renders customer data', () => {
    const customers = [
      { id: '1', name: 'Zack', email: 'z@z.com', totalPoints: 10, totalCashback: 5 },
    ];
    render(<CustomerList loading={false} customers={customers} />);
    expect(screen.getByText('Zack')).toBeInTheDocument();
    expect(screen.getByText('z@z.com')).toBeInTheDocument();
    expect(screen.getByText('⭐ 10')).toBeInTheDocument();
  });
});
