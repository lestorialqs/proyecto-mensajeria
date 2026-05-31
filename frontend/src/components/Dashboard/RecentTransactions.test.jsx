import { render, screen } from '@testing-library/react';
import { describe, it, expect } from 'vitest';
import RecentTransactions from './RecentTransactions';

describe('RecentTransactions', () => {
  it('renders transactions', () => {
    const txs = [
      { id: '1', date: '2023-10-10', customerName: 'Alice', amount: 50, status: 'success' },
    ];
    render(<RecentTransactions transactions={txs} />);
    expect(screen.getByText(/Transacciones Recientes/)).toBeInTheDocument();
    expect(screen.getByText('Alice')).toBeInTheDocument();
    expect(screen.getByText('$50.00')).toBeInTheDocument();
  });

  it('renders empty state', () => {
    render(<RecentTransactions transactions={[]} />);
    expect(screen.getByText('No hay transacciones recientes')).toBeInTheDocument();
  });
});
