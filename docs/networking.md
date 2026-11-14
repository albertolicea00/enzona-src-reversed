# Networking, API Gateway & TLS

Analysis of network hosts, endpoints, and TLS configuration in `cu.xetid.apk.enzona`.

## Network Security Configuration (`res/xml/network_security_config.xml`)

- `AndroidManifest.xml` sets `android:usesCleartextTraffic="true"` and references `@xml/network_security_config`, which explicitly permits cleartext HTTP for `util.enzona.net` (`includeSubdomains="true"`), matching the APK self-update URL `http://util.enzona.net/enzona.apk` in `libapp.so`.

## Observed ENZONA Hosts (`libapp.so`)

| Host | Protocol | Purpose |
|---|---|---|
| `https://api.enzona.net` | HTTPS | Primary WSO2 API Manager gateway (`account/v1.0.0`, `cards/v1.0.0`, `coremz/1.0.2`, `transfer/v1.0.0`, `payment/v1.0.0`, `invoice/v1.0.0`, `qr/v1.0.0`, `bulevar/1.0`, `nomenclator/v1.0.0`, `recipent/1.0.1`, `contact/1.0`) |
| `https://identity.enzona.net` | HTTPS | WSO2 Identity Server OAuth2 authentication (`/authenticationendpoint/oauth2_error.do`, `/authenticationendpoint/privacy_policy.do`) |
| `https://media.enzona.net/` | HTTPS | User avatars (`/images/user/avatar/`) and media |
| `https://service-core.enzona.net/atm` | HTTPS | ATM / service core lookup |
| `https://buscador.enzona.net` | HTTPS | ENZONA search portal |
| `https://www.enzona.net/apkHelp` | HTTPS | In-app help portal |
| `http://nyfy-push.enzona.net/ntfy/send` | HTTP | Push notification dispatch (`ntfy`) |
| `http://util.enzona.net/enzona.apk` | HTTP | APK self-update download URL |

## Flutter TLS Note

- Dart/Flutter uses its own compiled BoringSSL stack inside `libflutter.so` rather than Android's system proxy or `TrustManager` for Dart `HttpClient` requests.
