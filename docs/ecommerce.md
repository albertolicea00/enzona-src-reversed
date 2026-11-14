# E-Commerce, Transfers & QR Operations

API endpoints and native capabilities extracted from `libapp.so` and `MainActivity.java` for ENZONA (`cu.xetid.apk.enzona`).

## Observed `api.enzona.net` Service Modules (`libapp.so`)

- **Bulevar Virtual Stores**: `https://api.enzona.net/bulevar/1.0/tiendas/allTiendas`
- **Bank Cards & PIN Activation (`cards`, `coremz`)**:
  - `https://api.enzona.net/cards/v1.0.0/cards/default`
  - `https://api.enzona.net/coremz/1.0.2/card`
  - `https://api.enzona.net/coremz/1.0.2/card/card/activate/pin`
- **P2P & Fincimex Transfers (`transfer`, `coremz`)**:
  - `https://api.enzona.net/transfer/v1.0.0/transfer/account`
  - `https://api.enzona.net/transfer/v1.0.0/transfer/card`
  - `https://api.enzona.net/coremz/1.0.2/transfer/fincimex`
- **QR & Merchant Payments (`qr`, `payment`, `invoice`)**:
  - `https://api.enzona.net/qr/v1.0.0/qr/account`
  - `https://api.enzona.net/coremz/1.0.2/qr/postFincimex`
  - `https://api.enzona.net/payment/v1.0.0/payments/vendor/code`
  - `https://api.enzona.net/invoice/v1.0.0/client-id`
  - `https://api.enzona.net/invoice/v1.0.0/invoice/payment`
- **Beneficiaries & Nomenclators**:
  - `https://api.enzona.net/recipent/1.0.1/recipient/add`, `.../setRecipient`
  - `https://api.enzona.net/contact/1.0/api/account/contact-add`, `.../contact-remove-request`
  - `https://api.enzona.net/nomenclator/v1.0.0/nomenclator/banks`, `.../coordinates`

## QR Scanning

- Supports dual scanning stacks in `AndroidManifest.xml`: Huawei ScanKit (`com.huawei.hms.hmsscankit.ScanKitActivity`) and ZXing (`com.journeyapps.barcodescanner.CaptureActivity`).
