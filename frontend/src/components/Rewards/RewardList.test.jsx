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
      { id: '1', date: '2023-10-10', customerName: 'John', transactionAmount: 100, pointsEarned: 200, cashbackAmount: 5 },
      { _id: '2', timestamp: [2023, 10, 11, 10, 30, 0], customer: { name: 'Alice' }, amount: null, points: 50, cashbackEarned: null },
      { createdAt: null, customerId: 'C3', cashback: 10 }
    ];
    render(<RewardList loading={false} rewards={rewards} />);
    expect(screen.getByText('John')).toBeInTheDocument();
    expect(screen.getByText('Alice')).toBeInTheDocument();
    expect(screen.getByText('#C3')).toBeInTheDocument();
    expect(screen.getByText('⭐ 200')).toBeInTheDocument();
    
    // Check fallback empty date
    expect(screen.getAllByText('—').length).toBeGreaterThan(0);
    
    // Check points and cashback sum (200+50 = 250 points, 5+10 = 15 cashback)
    expect(screen.getByText('250')).toBeInTheDocument();
    expect(screen.getByText('$15.00')).toBeInTheDocument();
  });
});
