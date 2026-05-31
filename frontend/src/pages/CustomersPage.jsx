import { useEffect, useState, useCallback } from 'react';
import api from '../api/client';
import CustomerForm from '../components/Customers/CustomerForm';
import CustomerList from '../components/Customers/CustomerList';
import './CustomersPage.css';

export default function CustomersPage() {
  const [customers, setCustomers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [toasts, setToasts] = useState([]);

  const loadCustomers = async () => {
    try {
      const data = await api.getCustomers();
      setCustomers(Array.isArray(data) ? data : []);
    } catch (err) {
      addToast(err.message, 'error');
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    loadCustomers();
  }, []);

  const addToast = useCallback((message, type = 'success') => {
    const id = Date.now() + Math.random();
    setToasts((prev) => [...prev, { id, message, type }]);
    setTimeout(() => {
      setToasts((prev) => prev.map((t) => (t.id === id ? { ...t, exiting: true } : t)));
      setTimeout(() => setToasts((prev) => prev.filter((t) => t.id !== id)), 300);
    }, 3500);
  }, []);

  const handleCustomerSuccess = (errorMsg) => {
    if (errorMsg) {
      addToast(errorMsg, 'error');
    } else {
      addToast('✅ Cliente registrado exitosamente');
      loadCustomers();
    }
  };

  return (
    <div className="customers-page">
      {/* Toasts */}
      {toasts.length > 0 && (
        <div className="toast-container">
          {toasts.map((t) => (
            <div key={t.id} className={`toast toast-${t.type} ${t.exiting ? 'toast-exit' : ''}`}>
              {t.message}
            </div>
          ))}
        </div>
      )}

      {/* Page Header */}
      <div className="page-header">
        <div>
          <h1 className="page-title">Clientes</h1>
          <p className="page-subtitle">Gestionar clientes del programa de recompensas</p>
        </div>
      </div>

      {/* Form */}
      <div className="customers-form-section">
        <CustomerForm onSuccess={handleCustomerSuccess} />
      </div>

      {/* List */}
      <CustomerList customers={customers} loading={loading} />
    </div>
  );
}
