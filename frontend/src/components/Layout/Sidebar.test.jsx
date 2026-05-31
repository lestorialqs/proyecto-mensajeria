import { render, screen } from '@testing-library/react';
import { describe, it, expect, vi } from 'vitest';
import { BrowserRouter } from 'react-router-dom';
import Sidebar from './Sidebar';

describe('Sidebar', () => {
  it('renders correctly', () => {
    render(
      <BrowserRouter>
        <Sidebar isOpen={true} onClose={vi.fn()} />
      </BrowserRouter>
    );
    expect(screen.getByText('Dashboard')).toBeInTheDocument();
    expect(screen.getByText('Transacciones')).toBeInTheDocument();
    expect(screen.getByText('Recompensas')).toBeInTheDocument();
    expect(screen.getByText('Clientes')).toBeInTheDocument();
    expect(screen.getByText('Sistema de Recompensas')).toBeInTheDocument();
  });
});
