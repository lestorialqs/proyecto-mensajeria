import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

export default defineConfig({
  plugins: [react()],
  server: {
    port: 5173,
    proxy: {
      '/api': {
        target: 'http://localhost:3000',
        changeOrigin: true,
      },
    },
  },
  test: {
    environment: 'happy-dom',
    setupFiles: ['./src/setupTests.js'],
    globals: true,
    coverage: {
      provider: 'v8',
      reporter: ['lcov', 'text'],
      include: ['src/**/*.js', 'src/**/*.jsx'],
      exclude: ['src/main.jsx', 'src/api/client.js', 'src/hooks/useApi.js', 'src/**/*.css'],
    },
  },
});
