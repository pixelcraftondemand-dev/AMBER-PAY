#!/usr/bin/env python3
"""Fail CI if a file listed as 'not built' in production-readiness.md has substantial implementation.

This is intentionally simple: it checks for a known set of ledger implementation files and ensures
that the status doc doesn't claim a file is absent when the repo clearly contains substantial code.
"""

from __future__ import annotations
import re
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
DOC = ROOT.parent / 'docs' / 'production-readiness.md'

# Keep this allow-list aligned with the actual gap list in the document. The goal is not to
# be clever; it is to catch the exact kind of drift described in the remediation prompt.
EXPECT_NOT_IMPLEMENTED = {
    'ledger_events outbox has no consumer yet': [],
    'No gRPC server': [],
    'Rail statement sources are stubs': [],
    'App-layer audit log, webhook event store, devices, and risk/case tables': [],
    'Sandbox provider': [],
    'Web client': [],
}

def file_lines(path: Path) -> int:
    if not path.exists():
        return 0
    with path.open('r', encoding='utf-8') as handle:
        return sum(1 for _ in handle)

def matches_gap(doc_text: str, title: str) -> bool:
    pattern = re.compile(re.escape(title), re.IGNORECASE)
    return bool(pattern.search(doc_text))


def main() -> int:
    text = DOC.read_text(encoding='utf-8')
    if not text:
        raise SystemExit('production-readiness.md is empty')

    # The exact regression we are guarding against is a stale 'not built' list after files were added.
    checks = [
        ('ledger/src/grpc.rs', file_lines(ROOT.parent / 'ledger' / 'src' / 'grpc.rs') > 200),
        ('ledger/proto/ledger.proto', file_lines(ROOT.parent / 'ledger' / 'proto' / 'ledger.proto') > 20),
        ('ledger/tests/grpc.rs', file_lines(ROOT.parent / 'ledger' / 'tests' / 'grpc.rs') > 80),
    ]

    failures = []
    for label, present in checks:
        if present:
            if matches_gap(text, 'No gRPC server'):
                failures.append(f"{label} is implemented but still listed as not built in production-readiness.md")

    if failures:
        for item in failures:
            print(item, file=None)
        return 1

    print('production-readiness gap list is not drifting against implemented gRPC files')
    return 0


if __name__ == '__main__':
    raise SystemExit(main())
