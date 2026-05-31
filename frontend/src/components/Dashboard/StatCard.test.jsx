import { render, screen } from '@testing-library/react';
import { describe, it, expect } from 'vitest';
import StatCard from './StatCard';

describe('StatCard', () => {
  it('renders title and value', () => {
    render(<StatCard title="Total Users" value={100} icon="👥" gradient="warm" />);
    expect(screen.getByText('Total Users')).toBeInTheDocument();
    expect(screen.getByText('100')).toBeInTheDocument();
  });

  it('renders dash when value is null', () => {
    render(<StatCard title="Total" value={null} />);
    expect(screen.getByText('—')).toBeInTheDocument();
  });

  it('applies correct gradient class', () => {
    const { container } = render(<StatCard title="Total" value={100} gradient="cool" />);
    expect(container.querySelector('.stat-card-glow--cool')).toBeInTheDocument();
  });
});
