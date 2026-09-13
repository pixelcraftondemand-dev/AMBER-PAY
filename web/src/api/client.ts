import type { ApiErrorBody } from './types';

/** Thrown for every failed request. Never leaks internals: the message shown
 *  to the user is the server's safe message, and `request_id` enables support
 *  correlation (docs/security-controls.md §3). */
export class ApiError extends Error {
  constructor(
    /** HTTP status; 0 means the network itself failed. */
    public readonly status: number,
    public readonly code: string,
    message: string,
    public readonly requestId?: string,
    public readonly field?: string,
    /** Retry-After value from a 429, in seconds (docs/api.md §1). */
    public readonly retryAfter?: number,
  ) {
    super(message);
    this.name = 'ApiError';
  }
}

export interface RequestOptions {
  method?: 'GET' | 'POST' | 'PUT' | 'DELETE';
  body?: unknown;
  token?: string | null;
  /** Required for every money-moving POST (docs/transaction-state-machine.md §5). */
  idempotencyKey?: string;
  /** Short-lived transaction authorization token (docs/api.md §1/§4). */
  pinToken?: string;
  /** One-time code for high-value transfers, sent alongside Pin-Token. */
  otpCode?: string;
  headers?: Record<string, string>;
}

const BASE: string = import.meta.env.VITE_API_BASE_URL ?? '/v1';
const CORE_API_NOT_RUNNING =
  'Core API not running. Start the Core API service or set VITE_API_PROXY to a live endpoint before using the app.';

export async function apiRequest<T>(path: string, opts: RequestOptions = {}): Promise<T> {
  const { method = 'GET', body, token, idempotencyKey, pinToken, otpCode, headers } = opts;
  const h: Record<string, string> = { ...headers };
  if (body !== undefined) h['Content-Type'] = 'application/json';
  if (token) h['Authorization'] = `Bearer ${token}`;
  if (idempotencyKey) h['Idempotency-Key'] = idempotencyKey;
  if (pinToken) h['Pin-Token'] = pinToken;
  if (otpCode) h['Otp-Code'] = otpCode;

  let res: Response;
  try {
    res = await fetch(`${BASE}${path}`, {
      method,
      headers: h,
      body: body !== undefined ? JSON.stringify(body) : undefined,
    });
  } catch {
    throw new ApiError(0, 'core_api_unavailable', CORE_API_NOT_RUNNING);
  }

  if (!res.ok) {
    const problem = (await res.json().catch(() => null)) as ApiErrorBody | null;
    const err = problem?.error;
    const retryAfterHeader = res.headers.get('Retry-After');
    const retryAfter = retryAfterHeader ? Number(retryAfterHeader) : undefined;
    const coreApiUnavailable =
      res.status === 404 || res.status === 502 || res.status === 503 || res.status === 504;
    throw new ApiError(
      res.status,
      coreApiUnavailable ? 'core_api_unavailable' : err?.code ?? 'unknown_error',
      coreApiUnavailable ? CORE_API_NOT_RUNNING : err?.message ?? `Request failed (${res.status})`,
      err?.request_id,
      err?.field,
      Number.isFinite(retryAfter) ? retryAfter : undefined,
    );
  }
  return (await res.json()) as T;
}
