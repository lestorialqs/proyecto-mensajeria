import { render, screen, fireEvent } from '@testing-library/react';
import { describe, it, expect } from 'vitest';
import { BrowserRouter } from 'react-router-dom';
import Layout from './Layout';

describe('Layout', () => {
  it('renders correctly', () => {
    render(
      <BrowserRouter>
        <Layout />
      </BrowserRouter>
    );
    expect(screen.getByText('Sistema de Recompensas')).toBeInTheDocument();
  });

  it('toggles sidebar on mobile menu button click', () => {
    render(
      <BrowserRouter>
        <Layout />
      </BrowserRouter>
    );
    
    // Initially closed (no sidebar--open class)
    const sidebar = screen.getByRole('complementary'); // aside
    expect(sidebar).not.toHaveClass('sidebar--open');

    // Click menu button
    const menuBtn = screen.getByLabelText('Abrir menú');
    fireEvent.click(menuBtn);

    // Should be open
    expect(sidebar).toHaveClass('sidebar--open');

    // Click overlay to close
    const overlay = screen.getByLabelText('Cerrar menú');
    fireEvent.click(overlay);

    // Should be closed
    expect(sidebar).not.toHaveClass('sidebar--open');
  });
});
