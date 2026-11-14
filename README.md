# ENZONA Source (Reversed)

![Target Version](https://img.shields.io/badge/Target%20Version-1.11.0-blue?style=flat-square)
![Version Code](https://img.shields.io/badge/Version%20Code-20003-informational?style=flat-square)
![Min SDK](https://img.shields.io/badge/Min%20SDK-21-orange?style=flat-square)
![Target SDK](https://img.shields.io/badge/Target%20SDK-33-green?style=flat-square)
![Last Update](https://img.shields.io/badge/Last%20Update-November%202026-lightgrey?style=flat-square)

<!-- 50 blocks; each full block = 2% of TODO tasks --> <!-- do not delete this progress-bar-->
<span style="white-space: nowrap;"><code>[██░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░] 4%</code></span>

**Task-list progress: 2/43 completed; 41 pending.** Tasks have equal weight. This is not verified code coverage or a working-app percentage. The value can fall when scope grows. Run `make progress` or `make inventory` to refresh it from [TODO.md](TODO.md).

**Main-package exact source links: 0/0 (0% — pre-decompilation).** This counts original Java classes with one exact clean-source candidate; it does not verify implementation completeness. The [reconstruction inventory](docs/reconstruction-inventory.md) reports current counts, and the [unlinked-source review ledger](docs/unmapped-source-review.md) tracks per-source classifications once `make decompile` has been executed.

## Overview

This repository contains reverse-engineered source code, extracted resources, and structural analysis of the **ENZONA** Android client binary (`cu.xetid.apk.enzona`). The primary objective is to inspect implementation details, verify communication protocols (HTTPS/REST/OAuth2/QR), and document internal logic for financial operations, P2P transfers, utility payments, and Boulevard e-commerce / TPV merchant interoperability.

> [!WARNING]
> **Disclaimer:** This repository is strictly intended for interoperability research, protocol analysis, and security auditing. All original application logic, trademarks, and associated intellectual property belong to **XETID** (Empresa de Tecnologías de la Información para la Defensa). No proprietary binaries are hosted for redistribution.

## Reference Links & Ecosystem Context

| Resource | URL | Description |
| :--- | :--- | :--- |
| **ENZONA Official Portal** | [https://www.enzona.net/](https://www.enzona.net/) | Official web platform, payment gateway, and service portal operated by XETID. |
| **APK EnZona (EcuRed)** | [https://www.ecured.cu/APK_EnZona](https://www.ecured.cu/APK_EnZona) | Collaborative encyclopedia reference detailing the consumer Android client (`cu.xetid.apk.enzona`), features, supported banks (BPA, BANDEC, Metropolitano), utility payments, and release history. |
| **ENZONA-TPV (EcuRed)** | [https://www.ecured.cu/ENZONA-TPV_(apk)](https://www.ecured.cu/ENZONA-TPV_(apk)) | Reference documentation for the companion merchant Point-of-Sale (Terminal Punto de Venta) Android application used by businesses and self-employed workers to accept QR payments, validate transactions, and issue refunds. |

## Current Target Metadata

| Property | Value |
| :--- | :--- |
| **Package Name** | `cu.xetid.apk.enzona` |
| **Target Version** | `1.11.0` |
| **Version Code** | `20003` |
| **Project tag** | `1.11.0.20003-v1.0` *(planned)* |
| **Min SDK** | `21` |
| **Target SDK** | `33` |
| **Compile SDK** | `34` |
| **SHA-256 Hash** | `9125313c01eb522522c2a1e312c62ad09a81193bc5b427a36b34114c701a4c0c` |

See [metadata extraction](docs/target-metadata.md) to reproduce these values from `.workspace/bin/cu.xetid.apk.enzona-v20003.apk`.

## Project status

| Area | Status | Current state |
| :--- | :--- | :--- |
| APK identification and baseline setup | Complete for current APK | Target APK placed in `.workspace/bin/cu.xetid.apk.enzona-v20003.apk`; metadata, SHA-256, and repository structure verified. |
| Static extraction (`src/`, `smali/`, `app/`) | Pending | Run `make decompile` to extract JADX Java, Apktool smali, decoded resources, assets, and manifest. |
| Clean Java reconstruction | Not started | Directory layout and `mappings/classes.json` initialized; awaits static extraction. |
| Protocol and feature analysis | Not started | Topic templates created in `docs/` for architecture, e-commerce/TPV, networking, storage, security, and domain model. |
| Build and behavioral validation | Not started | Compiler audit (`scripts/compile-reconstruction.py`) and runtime validation docs prepared. |

See the [task list](TODO.md) for specific remaining work. Paused sessions record a temporary checkpoint in `.workspace/handoff.md`. The bar above tracks completed checklist items; full reconstruction and Android behavior remain unverified.

## Documentation

| Document | Contents |
| :--- | :--- |
| [Target metadata](docs/target-metadata.md) | APK identity, version, SDK levels, and SHA-256 extraction. |
| [Versioning](docs/versioning.md) | Repository versions, tags (`1.11.0.20003-v1.0`), local smali archives, and cross-version comparisons. |
| [Architecture](docs/architecture.md) | Build properties, permissions, manifest components, navigation, dependencies, and assets. |
| [E-commerce & ENZONA-TPV](docs/ecommerce.md) | QR payment payloads, Boulevard e-commerce checkout, utility payments, and `ENZONA-TPV` merchant flows. |
| [Networking](docs/networking.md) | HTTP stack, OAuth2 interceptors, `*.enzona.net` endpoints, and TLS configuration. |
| [Security](docs/security.md) | Authentication, payment password, biometrics, cryptography, and manifest/component security audit. |
| [Storage](docs/storage.md) | Local SQLite/Room/Realm/ORMLite schema, entities, migrations, and SharedPreferences. |
| [Domain model](docs/domain-model.md) | Entities (bank cards, wallet, P2P transfers, QR payments, utility bills) and enum mappings. |
| [Reconstruction inventory](docs/reconstruction-inventory.md) | Reproducible source links, duplicate candidates, mapping conflicts, and review limits. |
| [Unlinked-source review ledger](docs/unmapped-source-review.md) | Per-source classification and evidence for unlinked main-package Java files. |
| [Java compilation audit](docs/java-compilation.md) | Dependency-backed compiler audit (`scripts/compile-reconstruction.py`) and classpath limits. |
| [Android runtime validation](docs/runtime-validation.md) | Isolated emulator validation guidelines, safety rules, and runtime observations. |

For work in progress, use the [task list](TODO.md). Paused sessions also provide `.workspace/handoff.md`. [CHANGELOG.md](CHANGELOG.md) records completed changes; [AGENTS.md](AGENTS.md) is the repository guide for contributors and coding agents.

---

## Versioning Strategy

Release names and Git tags use `<APK versionName>.<APK versionCode>-v<internal version>`. The current target's first project release will be `1.11.0.20003-v1.0`. Later work on the same APK uses `-v1.1`, `-v1.2`, and so on; a new APK starts its own internal sequence at `-v1.0`.

The project follows a **single-branch workflow** (`main`). Raw JADX Java in `src/` stays in Git for mapping references and cross-version diffs. Smali stays in the local `smali/` directory, ignored by Git, with APK-specific ZIPs in `.workspace/archives/` for local comparison or possible future Release assets.

For complete details, branching guidelines, and the APK upgrade procedure, see [docs/versioning.md](docs/versioning.md).

## Tag History

| Tag | APK version | Version code | Internal release | GitHub Release |
| :--- | :--- | ---: | :--- | :--- |
| `1.11.0.20003-v1.0` *(planned)* | `1.11.0` | `20003` | `v1.0` | Not published |

---

## Project structure

```text
.
├── app/
│   ├── assets/                     Extracted JSON and other assets (pending decompilation)
│   ├── res/                        Extracted Android resources (pending decompilation)
│   └── src/main/java/              Clean, reconstructed Java source
├── src/                            Raw, obfuscated JADX Java output (pending decompilation)
├── smali/                          Local Dalvik disassembly (Git-ignored)
├── mappings/
│   ├── evidence/                   Bytecode/smali verification evidence
│   └── classes.json                Obfuscated-to-clean name mappings
├── docs/                           Topic-specific analysis and procedures
├── scripts/                        Extraction, verification, inventory, and search tools
│   └── tests/                      Reconstruction unit tests
├── .workspace/
│   ├── handoff.md                  Temporary pause/resume note, tracked by Git when present
│   ├── prompts/                    Initial and session analysis prompts
│   ├── bin/                        Local APK inputs (cu.xetid.apk.enzona-v20003.apk)
│   └── archives/                   Local versioned smali ZIPs
├── Makefile                        Analysis commands
├── TODO.md                         Remaining project tasks
├── CHANGELOG.md                    Completed changes by analysis session
├── AGENTS.md                       Repository working guide
└── README.md
```

*Generated files under `.workspace/` and the local `smali/` directory are excluded from Git, except for `.gitkeep` files, which keep the empty directory structure in clones. `handoff.md` is tracked so a paused session can be resumed. A fresh clone needs the matching APK to regenerate smali, or a separately obtained versioned smali ZIP.*

Repository inventory for the current target (2026-11-14, pre-decompilation):

| Item | Count | Location |
| :--- | ---: | :--- |
| Decompiled Java files | 0 | `src/` (pending `make decompile`) |
| Clean Java reconstruction files | 0 | `app/src/main/java/` |
| Local smali files | 0 | `smali/` (ignored by Git; pending `make decompile`) |
| Extracted resource files | 0 | `app/res/` (pending `make decompile`) |
| Extracted asset files | 0 | `app/assets/` (pending `make decompile`) |
| Topic documents | 12 | `docs/` |
| Class mappings | 0 | `mappings/classes.json` |
| Analysis scripts | 7 | `scripts/` |

*These are file and mapping counts, not a measure of reconstruction completeness.*

## Legal Notice

**This is not the official source code for ENZONA.** It is a close approximation reconstructed from the publicly distributed build of the application for research purposes.

The Copyright Laws of the United States recognize "fair use" of copyrighted content. Section 107 of the U.S. Copyright Act states:

> "Notwithstanding the provisions of sections 106 and 106A, the fair use of a copyrighted work, including such use by reproduction in copies or phonorecords or by any other means specified by that section, for purposes such as criticism, comment, news reporting, teaching (including multiple copies for classroom use), scholarship, or research, is not an infringement of copyright."

This repository and its contents are covered under fair use for the purposes of interoperability research, security auditing, and protocol analysis. Any DMCA takedown notice targeting this repository on these grounds would be invalid.

## Related Repositories

| Repository | Package | Description |
| :--- | :--- | :--- |
| [transfermovil-src-reversed](https://github.com/albertolicea00/transfermovil-src-reversed) | `cu.etecsa.cubacel.tr.tm` | Reverse-engineered source code, resources, and protocol analysis for ETECSA Transfermóvil (mobile banking, MiTransfer wallet, USSD/SMS, and Boulevard e-commerce). |
| [todus-src-reversed](https://github.com/albertolicea00/todus-src-reversed) | `cu.todus.android` | Reverse-engineered source code, resources, and documentation for the toDus instant messaging and collaborative platform. |
| [apklist-src-reversed](https://github.com/albertolicea00/apklist-src-reversed) | `cu.uci.android.apklis` | Reverse-engineered source code, resources, and documentation for the official Cuban Android app store client (Apklis). |
| [nauta-src-reversed](https://github.com/albertolicea00/nauta-src-reversed) | `cu.etecsa.enp_oficial` | Reverse-engineered source code, resources, and documentation for the official ETECSA Nauta captive portal and Wi-Fi client. |
| [bancuba-src-reversed](https://github.com/albertolicea00/bancuba-src-reversed) | `cu.bpa.bancuba` | Reverse-engineered source code, resources, and financial calculator analysis for the Banco Popular de Ahorro (BPA) BanCuba client. |
