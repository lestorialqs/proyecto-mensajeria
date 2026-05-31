import PropTypes from 'prop-types';
import './StatCard.css';

export default function StatCard({ title, value, icon, gradient, delay }) {
  const gradientSuffix = gradient === 'warm'
    ? '-warm'
    : gradient === 'cool'
      ? '-cool'
      : gradient === 'sunset'
        ? '-sunset'
        : '';

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
      <div className={`stat-card-value gradient-text${gradientSuffix}`}>
        {value !== null && value !== undefined ? value : '—'}
      </div>
      <div className="stat-card-shine" />
    </div>
  );
}

StatCard.defaultProps = {
  delay: 0,
  gradient: '',
};

StatCard.propTypes = {
  title: PropTypes.string.isRequired,
  value: PropTypes.oneOfType([PropTypes.string, PropTypes.number]),
  icon: PropTypes.string,
  gradient: PropTypes.string,
  delay: PropTypes.number,
};
