import './StatCard.css';

export default function StatCard({ title, value, icon, gradient, delay = 0 }) {
  return (
    <div
      className="stat-card"
      style={{ animationDelay: `${delay}ms` }}
    >
      <div className={`stat-card-glow stat-card-glow--${gradient}`} />
      <div className="stat-card-header">
        <span className="stat-card-icon">{icon}</span>
        <span className="stat-card-title">{title}</span>
      </div>
      <div className={`stat-card-value gradient-text${
        gradient === 'warm' ? '-warm' : gradient === 'cool' ? '-cool' : gradient === 'sunset' ? '-sunset' : ''
      }`}>
        {value !== null && value !== undefined ? value : '—'}
      </div>
      <div className="stat-card-shine" />
    </div>
  );
}
