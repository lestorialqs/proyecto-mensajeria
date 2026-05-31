import { render, screen } from '@testing-library/react';
import { describe, it, expect, vi } from 'vitest';
import TransactionsPage from './TransactionsPage';
import api from '../api/client';

vi.mock('../api/client', () => ({
  default: {
    getTransactions: vi.fn().mockResolvedValue([{ id: '1', amount: 50 }]),
    createTransaction: vi.fn(),
  },
}));

vi.mock('../components/Transactions/TransactionForm', () => ({
  default: ({ onSuccess }) => <button onClick={() => onSuccess()} data-testid="mock-success">MockSuccess</button>
}));
vi.mock('../components/Transactions/TransactionList', () => ({
  default: () => <div data-testid="tx-list">List</div>
}));

describe('TransactionsPage', () => {
  it('renders page and handles success', async () => {
    render(<TransactionsPage />);
    await screen.findByText('Transacciones');
    expect(screen.getByTestId('tx-list')).toBeInTheDocument();
    
    screen.getByTestId('mock-success').click();
    expect(await screen.findByText('✅ Transacción registrada exitosamente')).toBeInTheDocument();
  });

  it('handles load error', async () => {
    api.getTransactions.mockRejectedValueOnce(new Error('Load error'));
    render(<TransactionsPage />);
    expect(await screen.findByText('Load error')).toBeInTheDocument();
  });
});
