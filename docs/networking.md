# Networking & API Gateway

Target: `cu.xetid.apk.enzona` v1.11.0 (versionCode `20003`).

> [!NOTE]
> **Status:** Structural template (pending decompilation). Known ecosystem domains are listed below as initial references; verify exact hardcoded URLs, HTTP clients, and interceptors from decompiled source.

## Ecosystem & Known Infrastructure Domains

| Domain / URL | Role |
| :--- | :--- |
| `https://www.enzona.net/` | Official ENZONA web portal and payment platform |
| `https://api.enzona.net/` / `*.enzona.net` | Core REST API gateway and OAuth2 token endpoints |
| `https://bulevar.enzona.net/` | ENZONA Boulevard virtual store and merchant e-commerce platform |
| `https://www.ecured.cu/APK_EnZona` | EcuRed encyclopedia documentation for APK EnZona |
| `https://www.ecured.cu/ENZONA-TPV_(apk)` | EcuRed documentation for the companion merchant POS app ENZONA-TPV |

## Pending Analysis Sections (Post-Decompilation)

- **HTTP Stack:** OkHttp / Retrofit / Volley / native HTTP client, interceptors, User-Agent strings, and retry policies.
- **OAuth2 & Token Management:** Consumer key/secret handling, access/refresh token flows, and `Bearer` header injection.
- **TLS & Certificate Pinning:** `network_security_config.xml`, custom `X509TrustManager` / `CertificatePinner`, and cleartext traffic flags.
