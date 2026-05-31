import './RecentTransactions.css';

function formatDate(dateStr) {
  if (!dateStr) return '—';
  const d = new Date(dateStr);
  return d.toLocaleDateString('es-ES', {
    day: '2-digit',
    month: 'short',
    year: 'numeric',
  });
}

function formatCurrency(amount) {
  if (amount == null) return '—';
  return `$${Number(amount).toFixed(2)}`;
}

export default function RecentTransactions({ transactions = [] }) {
  if (!transactions || transactions.length === 0) {
    return (
      <div className="recent-transactions">
        <div className="recent-transactions-header">
          <h3>🕐 Transacciones Recientes</h3>
        </div>
        <div className="empty-state">
          <div className="empty-state-icon">💳</div>
          <p className="empty-state-text">No hay transacciones recientes</p>
        </div>
      </div>
    );
  }

  return (
    <div className="recent-transactions">
      <div className="recent-transactions-header">
        <h3>🕐 Transacciones Recientes</h3>
        <span className="recent-transactions-count">{transactions.length}</span>
      </div>
      <div className="recent-transactions-table-wrap">
        <table className="recent-transactions-table">
          <thead>
            <tr>
              <th>Fecha</th>
              <th>Cliente</th>
              <th>Restaurante</th>
              <th>Monto</th>
              <th>Estado</th>
            </tr>
          </thead>
          <tbody>
            {transactions.map((tx, index) => (
              <tr key={tx.id || tx._id || index} style={{ animationDelay: `${index * 60}ms` }}>
                <td className="td-date">{formatDate(tx.date || tx.createdAt)}</td>
                <td className="td-customer">
                  {tx.customer?.name || tx.customerName || `Cliente #${tx.customerId}`}
                </td>
                <td className="td-restaurant">
                  <span className="restaurant-badge">{tx.restaurantCode || tx.restaurant || '—'}</span>
                </td>
                <td className="td-amount">{formatCurrency(tx.amount)}</td>
                <td>
                  <span className={`badge badge-${tx.status === 'error' ? 'error' : tx.status === 'pending' ? 'pending' : 'success'}`}>
                    {tx.status || 'completada'}
                  </span>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}
