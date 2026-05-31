import PropTypes from 'prop-types';
import { useState, useEffect } from 'react';
import api from '../../api/client';
import './TransactionForm.css';

const TransactionForm = ({ onSuccess = () => {} }) => {
  const [customers, setCustomers] = useState([]);
  const [loading, setLoading] = useState(false);
  const [form, setForm] = useState({
    customerId: '',
    restaurantCode: '',
    amount: '',
    description: '',
  });

  useEffect(() => {
    api.getCustomers()
      .then((data) => setCustomers(Array.isArray(data) ? data : []))
      .catch(() => setCustomers([]));
  }, []);

  const handleChange = (e) => {
    setForm((prev) => ({ ...prev, [e.target.name]: e.target.value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!form.customerId || !form.restaurantCode || !form.amount) return;

    setLoading(true);
    try {
      await api.createTransaction({
        customerId: form.customerId,
        restaurantCode: form.restaurantCode,
        amount: Number.parseFloat(form.amount),
        description: form.description,
      });
      setForm({ customerId: '', restaurantCode: '', amount: '', description: '' });
      if (onSuccess) onSuccess();
    } catch (err) {
      if (onSuccess) onSuccess(err.message);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="transaction-form-card">
      <div className="form-card-header">
        <h3>💳 Nueva Transacción</h3>
        <p>Registrar una nueva compra</p>
      </div>
      <form className="transaction-form" onSubmit={handleSubmit}>
        <div className="form-grid">
          <div className="form-group">
            <label htmlFor="customerId">Cliente</label>
            <select
              id="customerId"
              name="customerId"
              value={form.customerId}
              onChange={handleChange}
              required
            >
              <option value="">Seleccionar cliente...</option>
              {customers.map((c) => (
                <option key={c.id || c._id} value={c.id || c._id}>
                  {c.name} — {c.cardNumber || c.email}
                </option>
              ))}
            </select>
          </div>

          <div className="form-group">
            <label htmlFor="restaurantCode">Código Restaurante</label>
            <input
              id="restaurantCode"
              name="restaurantCode"
              type="text"
              placeholder="Ej: REST-001"
              value={form.restaurantCode}
              onChange={handleChange}
              required
            />
          </div>

          <div className="form-group">
            <label htmlFor="amount">Monto ($)</label>
            <input
              id="amount"
              name="amount"
              type="number"
              step="0.01"
              min="0.01"
              placeholder="0.00"
              value={form.amount}
              onChange={handleChange}
              required
            />
          </div>

          <div className="form-group">
            <label htmlFor="description">Descripción</label>
            <input
              id="description"
              name="description"
              type="text"
              placeholder="Descripción opcional"
              value={form.description}
              onChange={handleChange}
            />
          </div>
        </div>

        <button type="submit" className="btn-submit" disabled={loading}>
          {loading ? (
            <span className="btn-loading">
              <span className="spinner spinner-sm" />
              <span>Procesando...</span>
            </span>
          ) : (
            <span>🚀 Registrar Transacción</span>
          )}
        </button>
      </form>
    </div>
  );
};

TransactionForm.propTypes = {
  onSuccess: PropTypes.func,
};

export default TransactionForm;
