const BASE_URL = 'http://localhost:3000/api';

async function request(endpoint, options = {}) {
  const url = `${BASE_URL}${endpoint}`;

  const config = {
    headers: {
      'Content-Type': 'application/json',
      ...options.headers,
    },
    ...options,
  };

  try {
    const response = await fetch(url, config);

    if (!response.ok) {
      const errorData = await response.json().catch(() => ({}));
      throw new Error(errorData.message || errorData.error || `Error ${response.status}: ${response.statusText}`);
    }

    const text = await response.text();
    return text ? JSON.parse(text) : null;
  } catch (error) {
    if (error.name === 'TypeError' && error.message === 'Failed to fetch') {
      throw new Error('No se pudo conectar con el servidor. Verifica que el backend esté corriendo.');
    }
    throw error;
  }
}

const api = {
  // Dashboard
  getDashboardStats: () => request('/dashboard/stats'),

  // Customers
  getCustomers: () => request('/customers'),
  getCustomer: (id) => request(`/customers/${id}`),
  createCustomer: (data) =>
    request('/customers', {
      method: 'POST',
      body: JSON.stringify(data),
    }),

  // Transactions
  getTransactions: () => request('/transactions'),
  getCustomerTransactions: (customerId) => request(`/transactions/customer/${customerId}`),
  createTransaction: (data) =>
    request('/transactions', {
      method: 'POST',
      body: JSON.stringify(data),
    }),

  // Rewards
  getRewards: () => request('/rewards'),
  getCustomerRewards: (customerId) => request(`/rewards/customer/${customerId}`),
};

export default api;
