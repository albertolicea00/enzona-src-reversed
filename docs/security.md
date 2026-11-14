# Security & Cryptography

Security assessment of **ENZONA** (`cu.xetid.apk.enzona` v1.11.0, `versionCode` `20003`).

## 1. ECDSA Device/Biometric Signing (`MainActivity.java`)

`MainActivity` exposes `MethodChannel("cu.xetid.apk.enzona/methods")` with client-side elliptic-curve cryptography:
- `getKeyValidate` generates an **EC `secp256r1`** (`KeyPairGenerator.getInstance("EC")`, `ECGenParameterSpec("secp256r1")`) keypair and returns `[base64PublicKey, hexEncodedPrivateKey]` directly to Dart.
- `getFingerPrint(username, pvKey)` reconstructs the PKCS#8 `PrivateKey` from the hex string passed over the MethodChannel and signs `username` using **`SHA256withECDSA`**, returning Base64 output.
- **Security observation**: Private keys are serialized as hex strings across the Flutter MethodChannel rather than kept non-exportable inside `AndroidKeyStore`.

## 2. Manifest & Transport Security

- Only `MainActivity` is `exported="true"` among activities/providers; `FileProvider` instances and `DownloadService` are non-exported (except `SystemJobService` and `DiagnosticsReceiver` / `ProfileInstallReceiver` with system/dump permissions).
- `android:usesCleartextTraffic="true"` and `network_security_config.xml` allow HTTP cleartext to `util.enzona.net` (`http://util.enzona.net/enzona.apk`), and `libapp.so` also references `http://nyfy-push.enzona.net/ntfy/send`.

## 3. Authentication & TOTP Endpoints (`libapp.so`)

- `https://identity.enzona.net` handles OAuth2 flows, alongside `/account/v1.0.0/account/authentication`, `/account/TOTP/init`, `/account/TOTP/disable`, `/account/paymentpassword/reset`, and `/account/phone/verified`.
