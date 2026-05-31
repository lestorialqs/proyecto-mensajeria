import { useEffect, useState } from 'react';
import api from '../api/client';
import StatCard from '../components/Dashboard/StatCard';
import RecentTransactions from '../components/Dashboard/RecentTransactions';
import './DashboardPage.css';

export default function DashboardPage() {
  const [stats, setStats] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    loadStats();
  }, []);

  const loadStats = async () => {
    try {
      const data = await api.getDashboardStats();
      setStats(data);
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  };

  if (loading) {
    return (
      <div className="dashboard-page">
        <div className="loading-container">
          <div className="spinner" />
          <span>Cargando dashboard...</span>
        </div>
      </div>
    );
  }

  if (error) {
    return (
      <div className="dashboard-page">
        <div className="dashboard-error">
          <span className="error-icon">⚠️</span>
          <h3>Error al cargar datos</h3>
          <p>{error}</p>
          <button className="btn-retry" onClick={() => { setLoading(true); setError(null); loadStats(); }}>
            🔄 Reintentar
          </button>
        </div>
      </div>
    );
  }

  return (
    <div className="dashboard-page">
      {/* Page Header */}
      <div className="page-header">
        <div>
          <h1 className="page-title">Dashboard</h1>
          <p className="page-subtitle">Resumen general del programa de recompensas</p>
        </div>
        <div className="page-header-badge">
          <span className="live-dot" />
          En vivo
        </div>
      </div>

      {/* Stat Cards */}
      <div className="stats-grid">
        <StatCard
          title="Total Clientes"
          value={stats?.totalCustomers ?? 0}
          icon="👥"
          gradient="primary"
          delay={0}
        />
        <StatCard
          title="Total Transacciones"
          value={stats?.totalTransactions ?? 0}
          icon="💳"
          gradient="warm"
          delay={80}
        />
        <StatCard
          title="Puntos Otorgados"
          value={(stats?.totalPointsAwarded ?? 0).toLocaleString()}
          icon="⭐"
          gradient="cool"
          delay={160}
        />
        <StatCard
          title="Cashback Total"
          value={`$${(stats?.totalCashback ?? 0).toFixed(2)}`}
          icon="💰"
          gradient="sunset"
          delay={240}
        />
      </div>

      {/* Recent Transactions */}
      <RecentTransactions transactions={stats?.recentTransactions || []} />
    </div>
  );
}
