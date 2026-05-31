import PropTypes from 'prop-types';
import './RewardList.css';

function formatDate(dateStr) {
  if (!dateStr) return '—';
  let d;
  if (Array.isArray(dateStr)) {
    d = new Date(dateStr[0], dateStr[1] - 1, dateStr[2], dateStr[3] || 0, dateStr[4] || 0, dateStr[5] || 0);
  } else {
    d = new Date(dateStr);
  }
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

export default function RewardList({ rewards, loading }) {
  if (loading) {
    return (
      <div className="reward-list-card">
        <div className="loading-container">
          <div className="spinner" />
          <span>Cargando recompensas...</span>
        </div>
      </div>
    );
  }

  const totalPoints = rewards.reduce((sum, r) => sum + (Number(r.pointsEarned || r.points || 0)), 0);
  const totalCashback = rewards.reduce((sum, r) => sum + (Number(r.cashbackAmount || r.cashbackEarned || r.cashback || 0)), 0);

  return (
    <div className="reward-list-card">
      <div className="reward-list-header">
        <h3>🎁 Recompensas Otorgadas</h3>
        <span className="reward-count-badge">{rewards.length} registros</span>
      </div>

      {/* Summary */}
      <div className="reward-summary">
        <div className="reward-summary-item">
          <span className="reward-summary-label">Total Puntos</span>
          <span className="reward-summary-value gradient-text">{totalPoints.toLocaleString()}</span>
        </div>
        <div className="reward-summary-divider" />
        <div className="reward-summary-item">
          <span className="reward-summary-label">Total Cashback</span>
          <span className="reward-summary-value gradient-text-cool">{formatCurrency(totalCashback)}</span>
        </div>
      </div>

      {rewards.length === 0 ? (
        <div className="empty-state">
          <div className="empty-state-icon">🎁</div>
          <p className="empty-state-text">No hay recompensas registradas</p>
        </div>
      ) : (
        <div className="reward-table-wrap">
          <table className="reward-table">
            <thead>
              <tr>
                <th>Fecha</th>
                <th>Cliente</th>
                <th>Monto Transacción</th>
                <th>Puntos Ganados</th>
                <th>Cashback</th>
              </tr>
            </thead>
            <tbody>
              {rewards.map((r, index) => (
                <tr key={r.id || r._id || index} style={{ animationDelay: `${index * 40}ms` }}>
                  <td className="td-date">{formatDate(r.timestamp || r.date || r.createdAt)}</td>
                  <td className="td-customer">
                    {r.customer?.name || r.customerName || `#${r.customerId}`}
                  </td>
                  <td className="td-amount">{formatCurrency(r.transactionAmount || r.amount)}</td>
                  <td>
                    <span className="points-badge">
                      ⭐ {r.pointsEarned || r.points || 0}
                    </span>
                  </td>
                  <td>
                    <span className="cashback-badge">
                      💰 {formatCurrency(r.cashbackEarned || r.cashbackAmount || r.cashback || 0)}
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
}

RewardList.defaultProps = {
  rewards: [],
  loading: false,
};

RewardList.propTypes = {
  rewards: PropTypes.arrayOf(PropTypes.object),
  loading: PropTypes.bool,
};
