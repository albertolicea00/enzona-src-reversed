# Runtime Validation

Scope and status of dynamic/runtime validation for **ENZONA** (`cu.xetid.apk.enzona` v1.11.0, `versionCode` `20003`).

## Static Verification Completed

- `make verify` confirmed package `cu.xetid.apk.enzona`, `versionName` `1.11.0`, `versionCode` `20003`, `minSdkVersion` `21`, `targetSdkVersion` `33`, `compileSdkVersion` `34`, and APK SHA-256 `9125313c01eb522522c2a1e312c62ad09a81193bc5b427a36b34114c701a4c0c`.
- JADX 1.5.6 extracted all 3 classes in `cu.xetid.apk.enzona.*`.
- Apktool 3.0.3 decoded `AndroidManifest.xml`, 92 XML resources, `flutter_assets` manifests, and native libraries (`libapp.so`, `libflutter.so`, `libscannative.so`, `libmsoptimize.so`).
- Extracted `api.enzona.net` and `identity.enzona.net` endpoints directly from `lib/arm64-v8a/libapp.so`.

## Outstanding Runtime Checks

- Dart AOT symbol recovery on `libapp.so` via `blutter` + `ghidra` and Frida BoringSSL TLS hooking on `libflutter.so` to inspect live JSON request/response schemas.
