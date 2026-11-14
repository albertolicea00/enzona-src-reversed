# Domain Model

Catalog of native Android wrapper classes and inferred Dart API domains for `cu.xetid.apk.enzona`.

## Android Host Classes

| Class | Role |
|---|---|
| `cu.xetid.apk.enzona.MainActivity` | `FlutterFragmentActivity` exposing `cu.xetid.apk.enzona/methods` (`getFingerPrint`, `getKeyValidate`, `parseKeyPair`, `getCarrierName`, `getActiveApnName`, `getSdkNumber`, `getVersionInfo`) |
| `cu.xetid.apk.enzona.BuildConfig` | Generated build constants (`1.11.0`, `20003`) |

## Dart AOT (`libapp.so`) API Domains

Inferred from `https://api.enzona.net/*` routes in `libapp.so`:
- **Account & Security**: `account/v1.0.0` (authentication, TOTP, password, payment password, phone verification)
- **Cards & CoreMZ**: `cards/v1.0.0`, `coremz/1.0.2` (bank cards, PIN activation, Fincimex QR/transfers)
- **Payments & Transfers**: `transfer/v1.0.0`, `payment/v1.0.0`, `invoice/v1.0.0`, `qr/v1.0.0`
- **Commerce & Catalogs**: `bulevar/1.0`, `recipent/1.0.1`, `contact/1.0`, `nomenclator/v1.0.0`
