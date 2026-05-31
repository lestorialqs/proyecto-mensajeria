import { render, screen } from '@testing-library/react';
import { describe, it, expect, vi } from 'vitest';
import RewardsPage from './RewardsPage';
import api from '../api/client';

vi.mock('../api/client', () => ({
  default: {
    getRewards: vi.fn().mockResolvedValue([{ id: '1', points: 10 }]),
  },
}));

vi.mock('../components/Rewards/RewardList', () => ({
  default: () => <div data-testid="reward-list">List</div>
}));

describe('RewardsPage', () => {
  it('renders page and loads rewards', async () => {
    render(<RewardsPage />);
    await screen.findByText('Recompensas');
    expect(screen.getByTestId('reward-list')).toBeInTheDocument();
  });

  it('handles load error', async () => {
    api.getRewards.mockRejectedValueOnce(new Error('Load error'));
    render(<RewardsPage />);
    expect(await screen.findByText('Load error')).toBeInTheDocument();
  });
});
