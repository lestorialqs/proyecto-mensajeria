import { render, screen, fireEvent } from '@testing-library/react';
import { describe, it, expect, vi } from 'vitest';
import CustomerForm from './CustomerForm';
import api from '../../api/client';

vi.mock('../../api/client', () => ({
  default: {
    createCustomer: vi.fn(),
  },
}));

describe('CustomerForm', () => {
  it('renders correctly', () => {
    render(<CustomerForm onSuccess={vi.fn()} />);
    expect(screen.getByText('👤 Nuevo Cliente')).toBeInTheDocument();
  });

  it('submits form successfully', async () => {
    api.createCustomer.mockResolvedValueOnce({});
    const onSuccess = vi.fn();
    
    render(<CustomerForm onSuccess={onSuccess} />);
    
    fireEvent.change(screen.getByLabelText('Nombre Completo'), { target: { value: 'Test User' } });
    fireEvent.change(screen.getByLabelText('Correo Electrónico'), { target: { value: 'test@test.com' } });
    fireEvent.change(screen.getByLabelText('Número de Tarjeta'), { target: { value: '123' } });
    fireEvent.change(screen.getByLabelText('Teléfono'), { target: { value: '999' } });
    
    fireEvent.submit(screen.getByRole('button', { name: /Registrar Cliente/i }));
    
    // Test wait for async
    await new Promise((r) => setTimeout(r, 0));
    
    expect(api.createCustomer).toHaveBeenCalledWith({
      name: 'Test User',
      email: 'test@test.com',
      cardNumber: '123',
      phone: '999',
    });
    expect(onSuccess).toHaveBeenCalledWith();
  });
  it('handles API error on submit', async () => {
    api.createCustomer.mockRejectedValueOnce(new Error('Network error'));
    const onSuccess = vi.fn();
    
    render(<CustomerForm onSuccess={onSuccess} />);
    
    fireEvent.change(screen.getByLabelText('Nombre Completo'), { target: { value: 'Test User' } });
    fireEvent.change(screen.getByLabelText('Correo Electrónico'), { target: { value: 'test@test.com' } });
    
    fireEvent.submit(screen.getByRole('button', { name: /Registrar Cliente/i }));
    
    await new Promise((r) => setTimeout(r, 0));
    
    expect(onSuccess).toHaveBeenCalledWith('Network error');
  });
});
