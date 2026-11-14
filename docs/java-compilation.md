# Java Compilation Audit

Static compilation notes for `cu.xetid.apk.enzona` (`1.11.0`, `versionCode` `20003`).

## Scope & Dependencies

- `app/src/main/java/cu/xetid/apk/enzona/MainActivity.java` depends on `io.flutter.embedding.*`, `io.flutter.plugin.common.*`, `androidx.core.content.*`, and `com.pichillilorenzo.flutter_inappwebview_android.*` (bundled in `classes.dex`).
- Full `javac` compilation requires generating `target-bytecode.jar` from the APK's DEX via `dex2jar` alongside `android-34` `android.jar` (`make compile-audit ANDROID_JAR=... BYTECODE_JAR=...`).
