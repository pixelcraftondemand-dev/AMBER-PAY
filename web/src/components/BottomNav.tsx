import { NavLink } from 'react-router-dom';
import { IconActivity, IconHome, IconSecurity, IconSend, IconTopup } from './Icons';

const ITEMS = [
  { to: '/home', label: 'Home', icon: <IconHome /> },
  { to: '/activity', label: 'Activity', icon: <IconActivity /> },
  { to: '/send', label: 'Send', icon: <IconSend /> },
  { to: '/topup', label: 'Top-up', icon: <IconTopup /> },
  { to: '/security', label: 'Security', icon: <IconSecurity /> },
];

export function BottomNav() {
  return (
    <nav className="bottomnav" aria-label="Main">
      {ITEMS.map((it) => (
        <NavLink
          key={it.to}
          to={it.to}
          className={({ isActive }) => `navbtn${isActive ? ' active' : ''}`}
        >
          {it.icon}
          <span>{it.label}</span>
        </NavLink>
      ))}
    </nav>
  );
}
