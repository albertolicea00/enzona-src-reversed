# Architecture Overview

Target: `cu.xetid.apk.enzona` v1.11.0 (versionCode `20003`), SHA-256 `9125313c01eb522522c2a1e312c62ad09a81193bc5b427a36b34114c701a4c0c`.

> [!NOTE]
> **Status:** Pre-decompilation baseline. Only APK badging metadata (`aapt2 dump badging`) has been extracted so far. Run `make decompile` to populate `src/`, `smali/`, `app/res/`, `app/assets/`, and `app/AndroidManifest.xml`.

## Build & Package Properties

| Property | Value |
| :--- | :--- |
| **Package Name** | `cu.xetid.apk.enzona` |
| **Version Name** | `1.11.0` |
| **Version Code** | `20003` |
| **Compile SDK** | `34` (Android 14) |
| **Min SDK** | `21` (Android 5.0) |
| **Target SDK** | `33` (Android 13) |
| **Launchable Activity** | `cu.xetid.apk.enzona.MainActivity` |
| **Native ABIs** | `arm64-v8a`, `armeabi-v7a`, `x86_64` |
| **APK Size** | 44,070,398 bytes (~43 MB) |

## Declared Permissions (from `aapt2 dump badging`)

- Network & Connectivity: `INTERNET`, `ACCESS_NETWORK_STATE`, `ACCESS_WIFI_STATE`, `WAKE_LOCK`, `FOREGROUND_SERVICE`
- Biometrics & Security: `USE_BIOMETRIC`, `USE_FINGERPRINT`
- Hardware & Media: `CAMERA`, `VIBRATE`, `POST_NOTIFICATIONS`
- Contacts & Telephony: `READ_CONTACTS`, `READ_PHONE_STATE`
- Storage: `READ_EXTERNAL_STORAGE`, `WRITE_EXTERNAL_STORAGE`

## Pending Analysis Sections (Post-Decompilation)

- **Manifest Components:** Activities, Services, BroadcastReceivers, ContentProviders, exported flags, and deep links.
- **Application Class & Dependency Injection:** Startup initialization, framework/architecture (native vs. Flutter/React Native/hybrid), and global state.
- **Package Structure & Obfuscation:** ProGuard/R8 obfuscation patterns and module boundaries.
- **Bundled Assets & Native Libraries:** Inspect `app/assets/` and native `.so` libraries under `lib/{arm64-v8a,armeabi-v7a,x86_64}/`.
