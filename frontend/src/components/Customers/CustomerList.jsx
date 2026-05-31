import { Fragment, useState } from 'react';
import PropTypes from 'prop-types';
import api from '../../api/client';
import './CustomerList.css';

function formatCurrency(amount) {
  if (amount == null) return '$0.00';
  return `$${Number(amount).toFixed(2)}`;
}

export default function CustomerList({ customers = [], loading = false }) {
  const [selectedCustomer, setSelectedCustomer] = useState(null);
  const [customerDetail, setCustomerDetail] = useState(null);
  const [detailLoading, setDetailLoading] = useState(false);

  const renderCustomerDetail = () => {
    if (detailLoading) {
      return (
        <div className="loading-container" style={{ padding: '20px' }}>
          <div className="spinner" />
        </div>
      );
    }

    if (!customerDetail) {
      return (
        <p style={{ padding: '16px', color: 'var(--text-muted)' }}>
          No se pudieron cargar los detalles
        </p>
      );
    }

    return (
      <div className="customer-detail-content">
        <div className="detail-section">
          <h4>Informacion del Cliente</h4>
          <div className="detail-grid">
            <div><strong>Nombre:</strong> {customerDetail.name}</div>
            <div><strong>Email:</strong> {customerDetail.email}</div>
            <div><strong>Tarjeta:</strong> {customerDetail.cardNumber || '-'}</div>
            <div><strong>Telefono:</strong> {customerDetail.phone || '-'}</div>
            <div><strong>Puntos:</strong> {customerDetail.totalPoints || customerDetail.points || 0}</div>
            <div><strong>Cashback:</strong> {formatCurrency(customerDetail.totalCashback || customerDetail.cashback || 0)}</div>
          </div>
        </div>
      </div>
    );
  };

  const handleCustomerClick = async (customer) => {
    const id = customer.id || customer._id;
    if (selectedCustomer === id) {
      setSelectedCustomer(null);
      setCustomerDetail(null);
      return;
    }

    setSelectedCustomer(id);
    setDetailLoading(true);
    try {
      const detail = await api.getCustomer(id);
      setCustomerDetail(detail);
    } catch {
      setCustomerDetail(null);
    } finally {
      setDetailLoading(false);
    }
  };

  if (loading) {
    return (
      <div className="customer-list-card">
        <div className="loading-container">
          <div className="spinner" />
          <span>Cargando clientes...</span>
        </div>
      </div>
    );
  }

  return (
    <div className="customer-list-card">
      <div className="customer-list-header">
        <h3>Directorio de Clientes</h3>
        <span className="customer-count-badge">{customers.length} clientes</span>
      </div>

      {customers.length === 0 ? (
        <div className="empty-state">
          <div className="empty-state-icon">Clientes</div>
          <p className="empty-state-text">No hay clientes registrados</p>
        </div>
      ) : (
        <div className="customer-table-wrap">
          <table className="customer-table">
            <thead>
              <tr>
                <th>Nombre</th>
                <th>Email</th>
                <th>Tarjeta</th>
                <th>Telefono</th>
                <th>Puntos</th>
                <th>Cashback</th>
              </tr>
            </thead>
            <tbody>
              {customers.map((c, index) => {
                const id = c.id || c._id;
                const isSelected = selectedCustomer === id;
                return (
                  <Fragment key={id || index}>
                    <tr
                      className={`customer-row ${isSelected ? 'customer-row--selected' : ''}`}
                      onClick={() => handleCustomerClick(c)}
                      style={{ animationDelay: `${index * 40}ms`, cursor: 'pointer' }}
                    >
                      <td className="td-customer">
                        <span className="customer-avatar">
                          {(c.name || '?')[0].toUpperCase()}
                        </span>
                        {c.name}
                      </td>
                      <td className="td-email">{c.email || '-'}</td>
                      <td>
                        <span className="card-number-badge">{c.cardNumber || '-'}</span>
                      </td>
                      <td className="td-phone">{c.phone || '-'}</td>
                      <td>
                        <span className="points-badge">⭐ {c.totalPoints || c.points || 0}</span>
                      </td>
                      <td>
                        <span className="cashback-badge">{formatCurrency(c.totalCashback || c.cashback || 0)}</span>
                      </td>
                    </tr>
                    {isSelected && (
                      <tr className="customer-detail-row">
                        <td colSpan="6">
                          <div className="customer-detail">
                            {renderCustomerDetail()}
                          </div>
                        </td>
                      </tr>
                    )}
                  </Fragment>
                );
              })}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
}

CustomerList.propTypes = {
  customers: PropTypes.arrayOf(PropTypes.object),
  loading: PropTypes.bool,
};
