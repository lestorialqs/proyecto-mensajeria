import { render, screen } from '@testing-library/react';
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
});
