# Changelog

## 2026-11-14 — cu.xetid.apk.enzona-v20003.apk — SHA-256: `9125313c01eb522522c2a1e312c62ad09a81193bc5b427a36b34114c701a4c0c`

- Initialized the ENZONA reverse-engineering repository structure (`app/assets/`, `app/res/`, `app/src/main/java/`, `src/`, `smali/`, `mappings/evidence/`, `scripts/tests/`, `docs/`, `.workspace/bin/`, `.workspace/archives/`) mirroring `transfermovil-src-reversed` prior to decompilation.
- Verified target APK metadata (`cu.xetid.apk.enzona` v`1.11.0`, code `20003`, Min SDK `21`, Target SDK `33`, Compile SDK `34`) and documented extraction in `docs/target-metadata.md`.
- Added `Makefile` and 7 analysis toolchain scripts (`verify-apk.sh`, `decompile.sh`, `search-strings.sh`, `mapping-stats.sh`, `reconstruction-inventory.py`, `update-progress.py`, `compile-reconstruction.py`).
- Created full 12-document template set in `docs/` (`target-metadata.md`, `versioning.md`, `architecture.md`, `ecommerce.md`, `networking.md`, `security.md`, `storage.md`, `domain-model.md`, `reconstruction-inventory.md`, `unmapped-source-review.md`, `java-compilation.md`, `runtime-validation.md`).
- Updated `README.md`, `AGENTS.md`, and `TODO.md` with documentation links and reference links (`enzona.net`, `APK_EnZona`, `ENZONA-TPV_(apk)` on EcuRed).
