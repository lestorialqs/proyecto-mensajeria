import { useState } from 'react';
import PropTypes from 'prop-types';
import api from '../../api/client';
import './CustomerForm.css';

export default function CustomerForm({ onSuccess }) {
  const [loading, setLoading] = useState(false);
  const [form, setForm] = useState({
    name: '',
    email: '',
    cardNumber: '',
    phone: '',
  });

  const handleChange = (e) => {
    setForm((prev) => ({ ...prev, [e.target.name]: e.target.value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!form.name || !form.email) return;

    setLoading(true);
    try {
      await api.createCustomer({
        name: form.name,
        email: form.email,
        cardNumber: form.cardNumber,
        phone: form.phone,
      });
      setForm({ name: '', email: '', cardNumber: '', phone: '' });
      if (onSuccess) onSuccess();
    } catch (err) {
      if (onSuccess) onSuccess(err.message);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="customer-form-card">
      <div className="form-card-header">
        <h3>👤 Nuevo Cliente</h3>
        <p>Registrar un nuevo cliente en el programa</p>
      </div>
      <form className="customer-form" onSubmit={handleSubmit}>
        <div className="form-grid">
          <div className="form-group">
            <label htmlFor="name">Nombre Completo</label>
            <input
              id="name"
              name="name"
              type="text"
              placeholder="Ej: Juan Pérez"
              value={form.name}
              onChange={handleChange}
              required
            />
          </div>

          <div className="form-group">
            <label htmlFor="email">Correo Electrónico</label>
            <input
              id="email"
              name="email"
              type="email"
              placeholder="juan@email.com"
              value={form.email}
              onChange={handleChange}
              required
            />
          </div>

          <div className="form-group">
            <label htmlFor="cardNumber">Número de Tarjeta</label>
            <input
              id="cardNumber"
              name="cardNumber"
              type="text"
              placeholder="Ej: 4000-1234-5678"
              value={form.cardNumber}
              onChange={handleChange}
            />
          </div>

          <div className="form-group">
            <label htmlFor="phone">Teléfono</label>
            <input
              id="phone"
              name="phone"
              type="tel"
              placeholder="+505 8888-8888"
              value={form.phone}
              onChange={handleChange}
            />
          </div>
        </div>

        <button type="submit" className="btn-submit" disabled={loading}>
          {loading ? (
            <span className="btn-loading">
              <span className="spinner spinner-sm" />
              <span>Registrando...</span>
            </span>
          ) : (
            <span>Registrar Cliente</span>
          )}
        </button>
      </form>
    </div>
  );
}

CustomerForm.propTypes = {
  onSuccess: PropTypes.func,
};
