import { render, screen } from '@testing-library/react';
import { describe, it, expect, vi } from 'vitest';
import DashboardPage from './DashboardPage';
import api from '../api/client';

vi.mock('../api/client', () => ({
  default: {
    getDashboardStats: vi.fn().mockResolvedValue({
      totalCustomers: 10,
      totalTransactions: 20,
      totalPointsAwarded: 100,
      totalCashback: 50.5,
      recentTransactions: []
    }),
  },
}));

vi.mock('../components/Dashboard/RecentTransactions', () => ({
  default: () => <div data-testid="recent-tx">Recent</div>
}));

describe('DashboardPage', () => {
  it('renders loading, then stats', async () => {
    render(<DashboardPage />);
    expect(screen.getByText('Cargando dashboard...')).toBeInTheDocument();
    
    await screen.findByText('Dashboard');
    expect(screen.getByText('10')).toBeInTheDocument();
    expect(screen.getByText('20')).toBeInTheDocument();
    expect(screen.getByText('100')).toBeInTheDocument();
    expect(screen.getByText('$50.50')).toBeInTheDocument();
    expect(screen.getByTestId('recent-tx')).toBeInTheDocument();
  });

  it('renders error state', async () => {
    api.getDashboardStats.mockRejectedValueOnce(new Error('Network error'));
    render(<DashboardPage />);
    
    await screen.findByText('Error al cargar datos');
    expect(screen.getByText('Network error')).toBeInTheDocument();
  });
});
