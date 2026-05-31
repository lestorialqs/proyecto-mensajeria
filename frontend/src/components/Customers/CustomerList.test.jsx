import { render, screen, fireEvent, waitFor } from '@testing-library/react';
import { describe, it, expect, vi } from 'vitest';
import CustomerList from './CustomerList';

import api from '../../api/client';

vi.mock('../../api/client', () => ({
  default: {
    getCustomer: vi.fn(),
  },
}));

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
  it('handles customer click to fetch and show details', async () => {
    const mockDetail = { id: 'c1', name: 'Alice', email: 'alice@mail.com', phone: '999' };
    api.getCustomer.mockResolvedValueOnce(mockDetail);

    render(<CustomerList customers={[{ id: 'c1', name: 'Alice' }]} />);

    const row = screen.getByText('Alice').closest('tr');
    fireEvent.click(row);

    // Wait for the detail row to appear and loading to finish
    await waitFor(() => {
      expect(screen.getByText('alice@mail.com')).toBeInTheDocument();
      expect(screen.getByText('999')).toBeInTheDocument();
    });

    // Click again to close
    fireEvent.click(row);
    await waitFor(() => {
      expect(screen.queryByText('alice@mail.com')).not.toBeInTheDocument();
    });
  });

  it('handles customer click with api error', async () => {
    api.getCustomer.mockRejectedValueOnce(new Error('API Error'));

    render(<CustomerList customers={[{ id: 'c2', name: 'Bob' }]} />);

    const row = screen.getByText('Bob').closest('tr');
    fireEvent.click(row);

    await waitFor(() => {
      expect(screen.getByText('No se pudieron cargar los detalles')).toBeInTheDocument();
    });
  });
});
