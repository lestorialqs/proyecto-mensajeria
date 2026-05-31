import { render, screen } from '@testing-library/react';
import { describe, it, expect } from 'vitest';
import TransactionList from './TransactionList';

describe('TransactionList', () => {
  it('renders loading state', () => {
    render(<TransactionList loading={true} />);
    expect(screen.getByText('Cargando transacciones...')).toBeInTheDocument();
  });

  it('renders empty state', () => {
    render(<TransactionList loading={false} transactions={[]} />);
    expect(screen.getByText('No hay transacciones registradas')).toBeInTheDocument();
  });

  it('renders transaction data', () => {
    const txs = [
      { id: '1', date: '2023-10-10', customerName: 'Alice', restaurantCode: 'R1', amount: 50, description: 'Lunch', status: 'success' },
      { id: '2', date: '2023-10-11', customerName: 'Bob', restaurantCode: 'R2', amount: 120, description: 'Dinner', status: 'error' },
      { id: '3', date: '2023-10-12', customerName: 'Charlie', restaurantCode: 'R3', amount: 75, description: 'Breakfast', status: 'pending' },
    ];
    render(<TransactionList loading={false} transactions={txs} />);
    expect(screen.getByText('Alice')).toBeInTheDocument();
    expect(screen.getByText('R1')).toBeInTheDocument();
    expect(screen.getByText('$50.00')).toBeInTheDocument();
    expect(screen.getByText('Bob')).toBeInTheDocument();
    expect(screen.getByText('$120.00')).toBeInTheDocument();
    expect(screen.getByText('Charlie')).toBeInTheDocument();
  });
});
