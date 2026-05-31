import { render, screen } from '@testing-library/react';
import { describe, it, expect, vi } from 'vitest';
import App from './App';
import api from './api/client';

vi.mock('./api/client', () => ({
  default: {
    getDashboardStats: vi.fn().mockResolvedValue({}),
  },
}));

describe('App', () => {
  it('renders application', () => {
    render(<App />);
    expect(screen.getByText('Sistema de Recompensas')).toBeInTheDocument();
  });
});
