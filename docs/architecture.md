# Architecture Overview

Static architectural analysis of **ENZONA** (`cu.xetid.apk.enzona`, version `1.11.0`, `versionCode` `20003`), XETID's Cuban digital wallet and payment platform.

## Hybrid Flutter Architecture

ENZONA is built with **Flutter** (`flutterEmbedding` v2) rather than native Android UI:

- **Native Android Host**: `cu.xetid.apk.enzona.MainActivity` extends `FlutterFragmentActivity` and bridges platform capabilities over `MethodChannel("cu.xetid.apk.enzona/methods")`.
- **Dart AOT Payload**: Core UI, wallet, transfer, and e-commerce business logic is compiled into native shared libraries (`lib/arm64-v8a/libapp.so`, `lib/armeabi-v7a/libapp.so`, `lib/x86_64/libapp.so`) alongside `libflutter.so`.
- **Bundled Native SDKs**: Huawei ScanKit (`libscannative.so`, `libmsoptimize.so`, `ScanKitActivity`), ZXing (`CaptureActivity`), `uCrop`, `flutter_inappwebview_android`, `flutter_downloader`, and `flutterlocalnotifications`.

## Manifest Summary

| Attribute | Value |
|---|---|
| Package | `cu.xetid.apk.enzona` |
| Version | `1.11.0` (`versionCode` `20003`) |
| Min / Target / Compile SDK | `21` / `33` / `34` |
| Launcher Activity | `cu.xetid.apk.enzona.MainActivity` (`singleTop`, `exported="true"`) |
| Permissions | `INTERNET`, `ACCESS_WIFI_STATE`, `ACCESS_NETWORK_STATE`, `USE_BIOMETRIC`, `USE_FINGERPRINT`, `CAMERA`, `READ_PHONE_STATE`, `READ_CONTACTS`, `FOREGROUND_SERVICE`, `POST_NOTIFICATIONS`, `WRITE_EXTERNAL_STORAGE`, `READ_EXTERNAL_STORAGE`, `WAKE_LOCK`, `VIBRATE` |
| FileProviders | `flutter_downloader.provider`, `flutter.image_provider`, `fileProvider.com.crazecoder.openfile` (all `exported="false"`) |
