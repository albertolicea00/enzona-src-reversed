# Unlinked main-package source review

Target: `cu.xetid.apk.enzona-v20003.apk`, SHA-256
`9125313c01eb522522c2a1e312c62ad09a81193bc5b427a36b34114c701a4c0c`.

## Purpose and limits

This ledger tracks Java source files in the main application package (`cu.xetid.apk.enzona`) that lack exact clean-source links in `app/src/main/java/`. The ledger is an audit queue, not a decompilation-success metric. JADX decompilation success and source-to-clean class identity links are separate measures, and a link does not prove behavioral equivalence.

Classification categories:
- `application behavior`: UI, routing, domain rules, persistence, payment protocol, network, or security logic.
- `application support`: shared helpers, base classes, adapters, or internal utilities supporting app behavior.
- `generated`: resource/build output, synthetic bridges, binding code, or compiler-generated machinery (`BuildConfig`, `R`, R8 synthetic classes).
- `third-party/library`: vendored or dependency implementation rather than ENZONA-specific behavior.

## Current status

Pending initial decompilation (`make decompile`). Populate package-by-package review tables after generating the baseline inventory with `make inventory`.
