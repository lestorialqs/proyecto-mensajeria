import { render, screen } from '@testing-library/react';
import { describe, it, expect } from 'vitest';
import RewardList from './RewardList';

describe('RewardList', () => {
  it('renders loading state', () => {
    render(<RewardList loading={true} />);
    expect(screen.getByText('Cargando recompensas...')).toBeInTheDocument();
  });

  it('renders empty state when no rewards', () => {
    render(<RewardList loading={false} rewards={[]} />);
    expect(screen.getByText('No hay recompensas registradas')).toBeInTheDocument();
  });

  it('renders rewards table', () => {
    const rewards = [
      { id: '1', date: '2023-10-10', customerName: 'John', transactionAmount: 100, pointsEarned: 200, cashbackAmount: 5 }
    ];
    render(<RewardList loading={false} rewards={rewards} />);
    expect(screen.getByText('John')).toBeInTheDocument();
    expect(screen.getByText('⭐ 200')).toBeInTheDocument();
    expect(screen.getByText('Total Puntos')).toBeInTheDocument();
  });
});
