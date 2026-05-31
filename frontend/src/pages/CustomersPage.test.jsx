import { render, screen } from '@testing-library/react';
import { describe, it, expect, vi } from 'vitest';
import CustomersPage from './CustomersPage';
import api from '../api/client';

vi.mock('../api/client', () => ({
  default: {
    getCustomers: vi.fn().mockResolvedValue([{ id: '1', name: 'Zack' }]),
    createCustomer: vi.fn(),
  },
}));

vi.mock('../components/Customers/CustomerForm', () => ({
  default: ({ onSuccess }) => <button onClick={() => onSuccess()} data-testid="mock-success">MockSuccess</button>
}));
vi.mock('../components/Customers/CustomerList', () => ({
  default: () => <div data-testid="customer-list">List</div>
}));

describe('CustomersPage', () => {
  it('renders page and handles success', async () => {
    render(<CustomersPage />);
    await screen.findByText('Clientes');
    expect(screen.getByTestId('customer-list')).toBeInTheDocument();
    
    screen.getByTestId('mock-success').click();
    expect(await screen.findByText('✅ Cliente registrado exitosamente')).toBeInTheDocument();
  });

  it('handles load error', async () => {
    api.getCustomers.mockRejectedValueOnce(new Error('Load error'));
    render(<CustomersPage />);
    expect(await screen.findByText('Load error')).toBeInTheDocument();
  });
});
