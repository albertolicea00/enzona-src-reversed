#!/usr/bin/env python3
"""Synchronize the README progress bar with TODO.md and mapping-stats.sh.

Combines equal-weight TODO task completion with main-package Java source
reconstruction links (`single_candidate / source_files`). Core Flutter/Dart logic
in `libapp.so` is not captured by Java source links. Use --check in CI/checks.
"""

import argparse
import re
import subprocess
from pathlib import Path

MARKER = "<!-- do not delete this progress-bar-->"


def parse_mapping_stats(stats_output):
    """Extract source_files and single_candidate counts from mapping-stats.sh output."""
    source_match = re.search(r"^\s*source_files:\s*(\d+)", stats_output, re.MULTILINE)
    single_match = re.search(r"^\s*single_candidate:\s*(\d+)", stats_output, re.MULTILINE)
    if not source_match or not single_match:
        raise ValueError("Unable to parse source_files / single_candidate from mapping-stats.sh")
    return int(source_match.group(1)), int(single_match.group(1))


def read_mapping_stats(root):
    """Run scripts/mapping-stats.sh and return (source_files, single_candidate)."""
    proc = subprocess.run(
        ["bash", str(root / "scripts" / "mapping-stats.sh")],
        cwd=root,
        capture_output=True,
        text=True,
        check=True,
    )
    return parse_mapping_stats(proc.stdout)


def update(readme, todo, source_files, single_candidate):
    tasks = re.findall(r"^- \[([ xX])\] ", todo, re.MULTILINE)
    if not tasks:
        raise ValueError("TODO.md has no task checkboxes")
    completed = sum(value.lower() == "x" for value in tasks)
    todo_pct = completed * 100 // len(tasks)
    source_pct = single_candidate * 100 // source_files if source_files > 0 else 0
    overall_pct = (todo_pct + source_pct) // 2
    blocks = overall_pct * 50 // 100
    bar = "█" * blocks + "░" * (50 - blocks)
    replacement = (
        '<!-- 50 blocks; each full block = 2% of overall progress --> ' + MARKER + "\n"
        f'<span style="white-space: nowrap;"><code>[{bar}] {overall_pct}%</code></span>\n\n'
        f'**Task-list progress: {completed}/{len(tasks)} completed ({todo_pct}%); '
        f'{len(tasks) - completed} pending | '
        f'Source reconstruction: {single_candidate}/{source_files} ({source_pct}%).** '
        'Tasks have equal weight and overall progress averages checklist completion with '
        'Java wrapper source reconstruction (core application logic is compiled in Flutter AOT `libapp.so`). '
        'Run `make progress` or `make inventory` to refresh from [TODO.md](TODO.md) and `make stats`.'
    )
    pattern = (
        r"<!--[^\n]*" + re.escape(MARKER) + r"\n<span[^\n]*</span>"
        r"(?:\n\n\*\*Task-list progress:[^\n]*)?"
    )
    result, count = re.subn(pattern, lambda _: replacement, readme)
    if count != 1:
        raise ValueError("Expected exactly one README progress bar marker")
    return result


def main():
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("--root", type=Path, default=Path(__file__).resolve().parents[1])
    parser.add_argument("--check", action="store_true", help="Fail if README progress is stale")
    args = parser.parse_args()
    path = args.root / "README.md"
    previous = path.read_text()
    source_files, single_candidate = read_mapping_stats(args.root)
    current = update(previous, (args.root / "TODO.md").read_text(), source_files, single_candidate)
    if args.check:
        if previous != current:
            parser.exit(1, "README progress is stale; run make progress\n")
        print("README progress matches TODO.md and mapping-stats.sh")
    else:
        if previous != current:
            path.write_text(current)
        print(re.search(r"\*\*Task-list progress:.*?\*\*", current)[0].strip("*"))


if __name__ == "__main__":
    main()
