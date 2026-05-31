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

  it('renders complex transactions', () => {
    const txs = [
      { id: '1', date: '2023-10-10', customerName: 'Alice', amount: 50, status: 'success' },
      { id: '2', date: null, customer: { name: 'Bob' }, restaurantCode: 'R01', amount: null, status: 'error' },
      { _id: '3', createdAt: '2023-10-11', customerId: 'C3', restaurant: 'R02', amount: 100, status: 'pending' },
      { status: null } // testing missing fields
    ];
    render(<RecentTransactions transactions={txs} />);
    expect(screen.getByText(/Transacciones Recientes/)).toBeInTheDocument();
    
    // Check missing date fallback
    expect(screen.getAllByText('-').length).toBeGreaterThan(0);
    
    // Check fallback customer name
    expect(screen.getByText('Cliente #C3')).toBeInTheDocument();
    
    // Check amount formats
    expect(screen.getByText('$50.00')).toBeInTheDocument();
    
    // Check statuses
    expect(screen.getByText('error')).toBeInTheDocument();
    expect(screen.getByText('pending')).toBeInTheDocument();
    expect(screen.getByText('completada')).toBeInTheDocument();
  });

  it('renders empty state', () => {
    render(<RecentTransactions transactions={[]} />);
    expect(screen.getByText('No hay transacciones recientes')).toBeInTheDocument();
  });
});
