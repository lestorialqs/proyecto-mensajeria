import PropTypes from 'prop-types';
import { NavLink, useLocation } from 'react-router-dom';
import './Sidebar.css';

const navItems = [
  { path: '/', label: 'Dashboard', icon: '🏠' },
  { path: '/transactions', label: 'Transacciones', icon: '💳' },
  { path: '/rewards', label: 'Recompensas', icon: '🎁' },
  { path: '/customers', label: 'Clientes', icon: '👥' },
];

export default function Sidebar({ isOpen, onClose }) {
  const location = useLocation();

  return (
    <>
      {isOpen && (
        <div
          className="sidebar-overlay"
          role="button"
          tabIndex={0}
          onClick={onClose}
          onKeyDown={(e) => { if (e.key === 'Enter' || e.key === ' ') { onClose(); } }}
          aria-label="Cerrar menú"
        />
      )}
      <aside className={`sidebar ${isOpen ? 'sidebar--open' : ''}`}>
        {/* Brand */}
        <div className="sidebar-brand">
          <div className="sidebar-brand-icon">
            <span className="brand-emoji">🍽️</span>
          </div>
          <div className="sidebar-brand-text">
            <h1>Rewards</h1>
            <span>Restaurant System</span>
          </div>
        </div>

        {/* Divider */}
        <div className="sidebar-divider" />

        {/* Navigation */}
        <nav className="sidebar-nav">
          <ul>
            {navItems.map((item) => {
              const isCurrentPath = location.pathname === item.path
                || (item.path !== '/' && location.pathname.startsWith(item.path));
              return (
                <li key={item.path}>
                  <NavLink
                    to={item.path}
                    className={({ isActive }) =>
                      `sidebar-link ${isActive ? 'sidebar-link--active' : ''}`
                    }
                    end={item.path === '/'}
                    onClick={onClose}
                  >
                    <span className="sidebar-link-icon">{item.icon}</span>
                    <span className="sidebar-link-label">{item.label}</span>
                    {isCurrentPath ? (
                      <span className="sidebar-link-indicator" />
                    ) : null}
                  </NavLink>
                </li>
              );
            })}
          </ul>
        </nav>

        {/* Footer */}
        <div className="sidebar-footer">
          <div className="sidebar-footer-card">
            <span className="sidebar-footer-emoji">⭐</span>
            <p>Sistema de Recompensas</p>
            <span className="sidebar-footer-version">v1.0.0</span>
          </div>
        </div>
      </aside>
    </>
  );
}

Sidebar.propTypes = {
  isOpen: PropTypes.bool.isRequired,
  onClose: PropTypes.func.isRequired,
};
