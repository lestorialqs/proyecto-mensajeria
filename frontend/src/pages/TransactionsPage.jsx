import { useEffect, useState, useCallback } from 'react';
import api from '../api/client';
import TransactionForm from '../components/Transactions/TransactionForm';
import TransactionList from '../components/Transactions/TransactionList';
import './TransactionsPage.css';

export default function TransactionsPage() {
  const [transactions, setTransactions] = useState([]);
  const [loading, setLoading] = useState(true);
  const [toasts, setToasts] = useState([]);

  const loadTransactions = async () => {
    try {
      const data = await api.getTransactions();
      setTransactions(Array.isArray(data) ? data : []);
    } catch (err) {
      addToast(err.message, 'error');
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    loadTransactions();
  }, []);

  const addToast = useCallback((message, type = 'success') => {
    const id = Date.now() + Math.random();
    setToasts((prev) => [...prev, { id, message, type }]);
    setTimeout(() => {
      setToasts((prev) => prev.map((t) => (t.id === id ? { ...t, exiting: true } : t)));
      setTimeout(() => setToasts((prev) => prev.filter((t) => t.id !== id)), 300);
    }, 3500);
  }, []);

  const handleTransactionSuccess = (errorMsg) => {
    if (errorMsg) {
      addToast(errorMsg, 'error');
    } else {
      addToast('✅ Transacción registrada exitosamente');
      loadTransactions();
    }
  };

  return (
    <div className="transactions-page">
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
          <h1 className="page-title">Transacciones</h1>
          <p className="page-subtitle">Gestionar compras y registros de clientes</p>
        </div>
      </div>

      {/* Form */}
      <div className="transactions-form-section">
        <TransactionForm onSuccess={handleTransactionSuccess} />
      </div>

      {/* List */}
      <TransactionList transactions={transactions} loading={loading} />
    </div>
  );
}
