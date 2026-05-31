import { render, screen, fireEvent } from '@testing-library/react';
import { describe, it, expect, vi } from 'vitest';
import TransactionForm from './TransactionForm';
import api from '../../api/client';

vi.mock('../../api/client', () => ({
  default: {
    getCustomers: vi.fn().mockResolvedValue([{ id: '1', name: 'Zack' }]),
    createTransaction: vi.fn(),
  },
}));

describe('TransactionForm', () => {
  it('renders correctly', () => {
    render(<TransactionForm onSuccess={vi.fn()} />);
    expect(screen.getByText('💳 Nueva Transacción')).toBeInTheDocument();
  });

  it('submits form successfully', async () => {
    api.createTransaction.mockResolvedValueOnce({});
    const onSuccess = vi.fn();
    
    render(<TransactionForm onSuccess={onSuccess} />);
    
    await new Promise((r) => setTimeout(r, 0)); // wait for customers to load

    fireEvent.change(screen.getByLabelText('Cliente'), { target: { value: '1' } });
    fireEvent.change(screen.getByLabelText('Código Restaurante'), { target: { value: 'R1' } });
    fireEvent.change(screen.getByLabelText('Monto ($)'), { target: { value: '50.50' } });
    fireEvent.change(screen.getByLabelText('Descripción'), { target: { value: 'Food' } });
    
    fireEvent.submit(screen.getByRole('button', { name: /Registrar Transacción/i }));
    
    await new Promise((r) => setTimeout(r, 0));
    
    expect(api.createTransaction).toHaveBeenCalledWith({
      customerId: '1',
      restaurantCode: 'R1',
      amount: 50.50,
      description: 'Food',
    });
    expect(onSuccess).toHaveBeenCalledWith();
  });
  it('handles API error on submit', async () => {
    api.createTransaction.mockRejectedValueOnce(new Error('Network error'));
    const onSuccess = vi.fn();
    
    render(<TransactionForm onSuccess={onSuccess} />);
    
    await new Promise((r) => setTimeout(r, 0)); // wait for customers to load

    fireEvent.change(screen.getByLabelText('Cliente'), { target: { value: '1' } });
    fireEvent.change(screen.getByLabelText('Código Restaurante'), { target: { value: 'R1' } });
    fireEvent.change(screen.getByLabelText('Monto ($)'), { target: { value: '50.50' } });
    
    fireEvent.submit(screen.getByRole('button', { name: /Registrar Transacción/i }));
    
    await new Promise((r) => setTimeout(r, 0));
    
    expect(onSuccess).toHaveBeenCalledWith('Network error');
  });

  it('handles customer load error', async () => {
    api.getCustomers.mockRejectedValueOnce(new Error('Error'));
    render(<TransactionForm />);
    await new Promise((r) => setTimeout(r, 0));
    // Should gracefully fallback to empty array without crashing
    expect(screen.getByText('💳 Nueva Transacción')).toBeInTheDocument();
  });
});
