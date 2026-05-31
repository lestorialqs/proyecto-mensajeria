import PropTypes from 'prop-types';
import './StatCard.css';

const StatCard = ({ title, value, icon, gradient = '', delay = 0 }) => {
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
};

StatCard.propTypes = {
  title: PropTypes.string.isRequired,
  value: PropTypes.oneOfType([PropTypes.string, PropTypes.number]),
  icon: PropTypes.string,
  gradient: PropTypes.string,
  delay: PropTypes.number,
};

export default StatCard;
