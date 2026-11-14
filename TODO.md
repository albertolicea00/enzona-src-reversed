# ENZONA Reverse Engineering — Task List

## Extraction & Baseline Setup

- [x] Verify APK identity (SHA-256, package `cu.xetid.apk.enzona`, version `1.11.0`, code `20003`, SDK levels)
- [x] Initialize repository directory structure, documentation templates, and toolchain scripts
- [x] Run `make decompile` on `.workspace/bin/cu.xetid.apk.enzona-v20003.apk` to extract JADX Java (`src/`), Apktool disassembly (`smali/`), resources (`app/res/`), assets (`app/assets/`), and `app/AndroidManifest.xml`
- [x] Create local smali archive `.workspace/archives/smali-1.11.0.20003.zip` and record its SHA-256 in `docs/versioning.md`
- [x] Run `make inventory` to generate initial `.workspace/reconstruction-inventory.json` and record class counts in `README.md` and `docs/reconstruction-inventory.md`

## Architecture & Manifest Analysis

- [x] Decode and analyze `app/AndroidManifest.xml` (launch activity, activities, services, broadcast receivers, content providers, permissions, deep links, and exported components)
- [x] Identify obfuscation scheme (ProGuard / R8), third-party SDKs, and architectural pattern (MVP / MVVM / Clean Architecture)
- [x] Inventory bundled assets under `app/assets/` and key configuration resources under `app/res/`
- [x] Document findings in `docs/architecture.md`

## Authentication, Session & Biometrics

- [x] Trace user registration, login, and OAuth2 / token refresh flows
- [ ] Analyze payment password (contraseña de pago) and PIN verification mechanisms
- [x] Trace biometric / fingerprint authentication (`USE_BIOMETRIC`, `USE_FINGERPRINT`) and Android Keystore usage

## Networking, API Gateway & TLS

- [x] Identify HTTP client stack (OkHttp / Retrofit / Volley) and interceptor chains
- [x] Catalog ENZONA API gateway hosts and endpoints (`*.enzona.net`, `*.xetid.cu`, OAuth2 endpoints, merchant/TPV APIs)
- [x] Inspect TLS configuration, custom trust managers, or certificate pinning implementations
- [x] Document findings in `docs/networking.md`

## Core Wallet, Bank Cards & P2P Transfers

- [x] Trace bank card registration, validation, balance inquiry, and statement consultation (BPA, BANDEC, Metropolitano, BFI)
- [x] Trace P2P transfers (card-to-card, phone/alias lookup, QR-based transfer)
- [ ] Map contact integration (`READ_CONTACTS`) and beneficiary management

## QR Operations, Commerce & ENZONA-TPV Interoperability

- [x] Analyze QR payload format (static vs. dynamic QR codes for merchant payments and P2P transfers)
- [ ] Trace merchant payment confirmation, refund flows, and compatibility with ENZONA-TPV (`ENZONA-TPV` POS workflows)
- [x] Trace Boulevard / virtual store e-commerce checkout and deep-link handling
- [x] Document findings in `docs/ecommerce.md`

## Utility & Public Service Payments

- [ ] Trace electricity bill payment (UNE) flow
- [ ] Trace water bill payment (Aguas de La Habana / Hidrología) flow
- [ ] Trace telephone / ETECSA / Nauta service payment flows
- [ ] Trace manufactured gas payment (Cupet / Gas Manufacturado) flow
- [ ] Trace ONAT tax and public service obligation payments
- [ ] Trace Giros de Correos de Cuba (postal money orders) flows

## Local Storage & Persistence

- [x] Identify local database engine (SQLite / Room / Realm / ORMLite) and extract table schemas and migrations
- [x] Audit `SharedPreferences` and encrypted storage files for tokens, credentials, and cached user state
- [x] Document findings in `docs/storage.md`

## Security & Cryptography

- [x] Identify cryptographic primitives, cipher modes, key derivation, and any hardcoded secrets or client credentials
- [x] Audit exported activities, services, receivers, deep links, and `FileProvider` configurations
- [ ] Check for root detection, debugger detection, or tamper checks
- [x] Document findings in `docs/security.md`

## Domain Model & Clean Java Reconstruction

- [x] Catalog domain entities, API DTOs, and enums in `docs/domain-model.md`
- [x] Classify unlinked main-package sources in `docs/unmapped-source-review.md`
- [x] Populate `mappings/classes.json` with verified obfuscated-to-clean class mappings
- [x] Reconstruct clean Java classes under `app/src/main/java/` with `@obfuscated_class` annotations
- [ ] Set up local `target-bytecode.jar` with dex2jar and run `make compile-audit` (`docs/java-compilation.md`)

## Dynamic Validation (When Emulator Available)

- [ ] Install APK on isolated Android emulator and capture initial launch logcat and UI hierarchy
- [ ] Compare runtime screens and navigation against static manifest/code map in `docs/runtime-validation.md`
