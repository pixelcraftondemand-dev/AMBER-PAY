import { Outlet } from 'react-router-dom';
import { BottomNav } from './BottomNav';
import { useOnline } from '../hooks/useOnline';

export function Layout() {
  const online = useOnline();

  return (
    <div className="app-shell">
      {!online && (
        <div className="offline-banner" role="status">
          You're offline — you can view, but money movement is disabled until you reconnect.
        </div>
      )}
      <main className="app-body">
        <Outlet />
      </main>
      <BottomNav />
    </div>
  );
}
