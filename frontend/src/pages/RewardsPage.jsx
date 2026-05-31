import { useEffect, useState } from 'react';
import api from '../api/client';
import RewardList from '../components/Rewards/RewardList';
import './RewardsPage.css';

export default function RewardsPage() {
  const [rewards, setRewards] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    loadRewards();
  }, []);

  const loadRewards = async () => {
    try {
      const data = await api.getRewards();
      setRewards(Array.isArray(data) ? data : []);
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  };

  if (error) {
    return (
      <div className="rewards-page">
        <div className="page-header">
          <div>
            <h1 className="page-title">Recompensas</h1>
            <p className="page-subtitle">Puntos y cashback otorgados</p>
          </div>
        </div>
        <div className="dashboard-error">
          <span className="error-icon">⚠️</span>
          <h3>Error al cargar recompensas</h3>
          <p>{error}</p>
          <button className="btn-retry" onClick={() => { setLoading(true); setError(null); loadRewards(); }}>
            🔄 Reintentar
          </button>
        </div>
      </div>
    );
  }

  return (
    <div className="rewards-page">
      {/* Page Header */}
      <div className="page-header">
        <div>
          <h1 className="page-title">Recompensas</h1>
          <p className="page-subtitle">Historial de puntos y cashback otorgados a clientes</p>
        </div>
        <div className="rewards-total-badge">
          🎁 {rewards.length} recompensas
        </div>
      </div>

      {/* Reward List */}
      <RewardList rewards={rewards} loading={loading} />
    </div>
  );
}
