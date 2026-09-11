import { useCallback, useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { ApiError } from '../api/client';
import type { StatementEntry, StatementPage, Wallet } from '../api/types';
import { request } from '../api/requests';
import { IconEye, IconLock } from '../components/Icons';
import { TxRow } from '../components/TxRow';
import { ScreenState } from '../components/ScreenState';
import { formatMinorUnits } from '../components/Money';
import { useOnline } from '../hooks/useOnline';

type State =
  | { kind: 'loading' }
  | { kind: 'error'; message: string }
  | { kind: 'ready'; wallets: Wallet[]; recent: StatementEntry[] };

const RECENT_LIMIT = 20;

export function Home() {
  const navigate = useNavigate();
  const online = useOnline();
  const [state, setState] = useState<State>({ kind: 'loading' });
  const [balanceVisible, setBalanceVisible] = useState(true);

  const load = useCallback(async () => {
    setState({ kind: 'loading' });
    try {
      const wallets = await request<Wallet[]>('/wallets');
      // Recent activity = the primary wallet's real statement (the ledger's
      // ListEntries). No demo rows are ever rendered.
      let recent: StatementEntry[] = [];
      const primary = wallets[0];
      if (primary) {
        const page = await request<StatementPage>(
          `/wallets/${primary.id}/transactions?limit=${RECENT_LIMIT}`,
        );
        recent = page.entries;
      }
      setState({ kind: 'ready', wallets, recent });
    } catch (err) {
      const message =
        err instanceof ApiError ? err.message : 'Something went wrong loading your wallet.';
      setState({ kind: 'error', message });
    }
  }, []);

  useEffect(() => {
    if (online) void load();
  }, [load, online]);

  if (!online) {
    return <ScreenState kind="offline" onRetry={() => void load()} />;
  }
  if (state.kind === 'loading') return <ScreenState kind="loading" />;
  if (state.kind === 'error') {
    return <ScreenState kind="error" message={state.message} onRetry={() => void load()} />;
  }

  const { wallets, recent } = state;
  const primary = wallets[0];

  return (
    <div className="px" style={{ paddingBottom: 8 }}>
      <div className="greet">
        <div>
          <div className="faint" style={{ fontSize: '.75rem' }}>{greetingNow()}</div>
          <h2>AmberPay</h2>
        </div>
        <button
          type="button"
          className="icon-btn"
          aria-label="Security"
          onClick={() => navigate('/security')}
        >
          <IconLock stroke="var(--ink-500)" />
        </button>
      </div>

      {primary ? (
        <div className="balance-card">
          <div className="balance-label">
            <svg
              width="14"
              height="14"
              viewBox="0 0 24 24"
              fill="none"
              stroke="rgba(255,255,255,.7)"
              strokeWidth="1.8"
            >
              <path d="M2 12s3.5-7 10-7 10 7 10 7-3.5 7-10 7-10-7-10-7z" />
              <circle cx="12" cy="12" r="3" />
            </svg>
            Available balance
            <button
              type="button"
              className="icon-btn"
              style={{
                width: 22,
                height: 22,
                background: 'rgba(255,255,255,.12)',
                border: 'none',
                marginLeft: 'auto',
              }}
              aria-label={balanceVisible ? 'Hide balance' : 'Show balance'}
              onClick={() => setBalanceVisible((v) => !v)}
            >
              <IconEye stroke="#fff" />
            </button>
          </div>
          <div className="balance-amount">
            {balanceVisible ? (
              formatMinorUnits(primary.available_minor, primary.currency)
            ) : (
              <span className="masked">•• ••• ••</span>
            )}
          </div>
          <div className="balance-sub">
            {formatMinorUnits(primary.held_minor, primary.currency)} held ·{' '}
            {formatMinorUnits(primary.total_minor, primary.currency)} total · wallet{' '}
            {primary.id.slice(0, 4)} · {primary.currency}
          </div>
        </div>
      ) : (
        <div className="balance-card">
          <div className="balance-label">No wallet yet</div>
          <div className="balance-sub">
            Your wallet appears here once the data layer is connected.
          </div>
        </div>
      )}

      {wallets.length > 1 && (
        <div className="section-head">
          <h3>Other wallets</h3>
        </div>
      )}
      {wallets.slice(1).map((w) => (
        <div key={w.id} className="tx-row">
          <div className="tx-avatar" aria-hidden>
            {w.currency.slice(0, 2)}
          </div>
          <div className="tx-mid">
            <div className="tx-name">{w.currency} wallet</div>
            <div className="tx-meta">
              {formatMinorUnits(w.available_minor, w.currency)} available ·{' '}
              {formatMinorUnits(w.held_minor, w.currency)} held
            </div>
          </div>
        </div>
      ))}

      <div className="quick-actions">
        <button type="button" className="qa" onClick={() => navigate('/send')}>
          <span className="circ">
            <svg
              width="20"
              height="20"
              viewBox="0 0 24 24"
              fill="none"
              stroke="var(--navy-900)"
              strokeWidth="1.7"
              strokeLinecap="round"
              strokeLinejoin="round"
            >
              <path d="M3 11.5L20 3.5 12.5 20.5 10.2 12.8 3 11.5z" />
              <path d="M10.2 12.8L20 3.5" />
            </svg>
          </span>
          <span>Send</span>
        </button>
        <button type="button" className="qa" onClick={() => navigate('/topup')}>
          <span className="circ">
            <svg
              width="20"
              height="20"
              viewBox="0 0 24 24"
              fill="none"
              stroke="var(--navy-900)"
              strokeWidth="1.7"
              strokeLinecap="round"
              strokeLinejoin="round"
            >
              <path d="M3.5 8.2A2.2 2.2 0 0 1 5.7 6h11.6a2.2 2.2 0 0 1 2.2 2.2v.8h1a1.5 1.5 0 0 1 1.5 1.5v6a2.2 2.2 0 0 1-2.2 2.2H5.7a2.2 2.2 0 0 1-2.2-2.2z" />
              <path d="M15.2 13.3h4.3M17.35 11.15v4.3" />
            </svg>
          </span>
          <span>Add money</span>
        </button>
        <button type="button" className="qa" onClick={() => navigate('/activity')}>
          <span className="circ">
            <svg
              width="20"
              height="20"
              viewBox="0 0 24 24"
              fill="none"
              stroke="var(--navy-900)"
              strokeWidth="1.7"
              strokeLinecap="round"
              strokeLinejoin="round"
            >
              <path d="M3 12h4l2-7 4 14 2-7h6" />
            </svg>
          </span>
          <span>Request</span>
        </button>
        <button type="button" className="qa" onClick={() => navigate('/activity')}>
          <span className="circ">
            <svg
              width="20"
              height="20"
              viewBox="0 0 24 24"
              fill="none"
              stroke="var(--navy-900)"
              strokeWidth="1.7"
              strokeLinecap="round"
              strokeLinejoin="round"
            >
              <path d="M6 3h12v13.2l-1.5 1.4L15 16.2l-1.5 1.4L12 16.2l-1.5 1.4L9 16.2l-1.5 1.4L6 16.2z" />
              <path d="M9 7.4h6M9 10.4h6M9 13.4h3.2" />
            </svg>
          </span>
          <span>Pay bill</span>
        </button>
      </div>

      <div className="section-head">
        <h3>Recent activity</h3>
        <button type="button" className="link" onClick={() => navigate('/activity')}>
          View all
        </button>
      </div>
      {recent.length === 0 ? (
        <p className="empty-note">
          No transactions yet — send your first payment and it will appear here.
        </p>
      ) : (
        recent.map((entry) => (
          <div key={entry.entry_id}>
            <TxRow entry={entry} />
            <hr className="hair" />
          </div>
        ))
      )}
      {primary && (
        <p className="faint" style={{ fontSize: '.72rem', marginTop: 8 }}>
          Balances and entries come straight from the ledger on every load.
        </p>
      )}
    </div>
  );
}

function greetingNow(): string {
  const h = new Date().getHours();
  if (h < 12) return 'Good morning';
  if (h < 18) return 'Good afternoon';
  return 'Good evening';
}
