# Security & Cryptography

Target: `cu.xetid.apk.enzona` v1.11.0 (versionCode `20003`).

> [!NOTE]
> **Status:** Structural template (pending decompilation). Populate with concrete evidence from `app/AndroidManifest.xml`, `src/`, and `smali/` after decompiling.

## Audit Checklist

1. **Manifest Security Posture**
   - `android:debuggable`, `android:allowBackup`, `android:usesCleartextTraffic`, and exported Activities, Services, BroadcastReceivers, and ContentProviders.
2. **Authentication, Payment Password & Biometrics**
   - Login and OAuth2 token lifecycle.
   - Payment password (`contraseña de pago`) verification and biometric enrollment (`USE_BIOMETRIC`, `USE_FINGERPRINT`, Android Keystore).
3. **Cryptography & Secrets**
   - Cipher algorithms, key derivation, IV generation, and any hardcoded OAuth2 client credentials or signing keys in DEX/native libraries.
4. **Local Data Protection**
   - Plaintext vs. encrypted storage in local databases and `SharedPreferences`.
