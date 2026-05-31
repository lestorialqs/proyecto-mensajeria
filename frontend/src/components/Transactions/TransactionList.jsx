import PropTypes from 'prop-types';
import './TransactionList.css';

function formatDate(dateStr) {
  if (!dateStr) return '—';
  const d = new Date(dateStr);
  return d.toLocaleDateString('es-ES', {
    day: '2-digit',
    month: 'short',
    year: 'numeric',
    hour: '2-digit',
    minute: '2-digit',
  });
}

function formatCurrency(amount) {
  if (amount == null) return '—';
  return `$${Number(amount).toFixed(2)}`;
}

function getStatusBadgeClass(status) {
  if (status === 'error') return 'badge-error';
  if (status === 'pending') return 'badge-pending';
  return 'badge-success';
}

const TransactionList = ({ transactions = [], loading = false }) => {
  if (loading) {
    return (
      <div className="transaction-list-card">
        <div className="loading-container">
          <div className="spinner" />
          <span>Cargando transacciones...</span>
        </div>
      </div>
    );
  }

  return (
    <div className="transaction-list-card">
      <div className="transaction-list-header">
        <h3>📋 Historial de Transacciones</h3>
        <span className="transaction-count-badge">{transactions.length} registros</span>
      </div>

      {transactions.length === 0 ? (
        <div className="empty-state">
          <div className="empty-state-icon">📄</div>
          <p className="empty-state-text">No hay transacciones registradas</p>
        </div>
      ) : (
        <div className="transaction-table-wrap">
          <table className="transaction-table">
            <thead>
              <tr>
                <th>Fecha</th>
                <th>Cliente</th>
                <th>Restaurante</th>
                <th>Monto</th>
                <th>Descripción</th>
                <th>Estado</th>
              </tr>
            </thead>
            <tbody>
              {transactions.map((tx, index) => (
                <tr key={tx.id || tx._id || index} style={{ animationDelay: `${index * 40}ms` }}>
                  <td className="td-date">{formatDate(tx.date || tx.createdAt)}</td>
                  <td className="td-customer">
                    {tx.customer?.name || tx.customerName || `#${tx.customerId}`}
                  </td>
                  <td>
                    <span className="restaurant-badge">{tx.restaurantCode || '—'}</span>
                  </td>
                  <td className="td-amount">{formatCurrency(tx.amount)}</td>
                  <td className="td-desc">{tx.description || '—'}</td>
                  <td>
                    <span className={`badge ${getStatusBadgeClass(tx.status)}`}>
                      {tx.status || 'completada'}
                    </span>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
};

TransactionList.propTypes = {
  transactions: PropTypes.arrayOf(PropTypes.object),
  loading: PropTypes.bool,
};
export default TransactionList;
