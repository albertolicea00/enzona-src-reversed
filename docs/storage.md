# Local Storage & Persistence

Analysis of local storage mechanisms in `cu.xetid.apk.enzona`.

## Native Android Layer

- `MainActivity.java` declares `PREFS_NAME = "MyAppPrefs"` and queries `content://telephony/carriers/preferapn` (`getActiveApnName()`) when `READ_PHONE_STATE` is granted.
- `AndroidManifest.xml` bundles `androidx.room.MultiInstanceInvalidationService` and `androidx.work` WorkManager (`flutter_downloader` background downloads), plus `com.pichillilorenzo.flutter_inappwebview_android` (`URLCredentialContract`).
- `requestLegacyExternalStorage="true"` and `WRITE_EXTERNAL_STORAGE` / `READ_EXTERNAL_STORAGE` are declared for saving/sharing QR receipts and downloaded APK updates (`file_paths.xml`, `filepaths.xml`, `flutter_image_picker_file_paths.xml`, `image_share_filepaths.xml`).

## Flutter Layer

- Core user session tokens, ECDSA private keys (`pvKey`), and cached wallet state are managed in Dart AOT (`libapp.so`) via Flutter plugins (SharedPreferences / SQLite / secure storage plugins).
