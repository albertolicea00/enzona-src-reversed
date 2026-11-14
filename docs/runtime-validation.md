# Local Android runtime validation

Target: `cu.xetid.apk.enzona-v20003.apk`, SHA-256
`9125313c01eb522522c2a1e312c62ad09a81193bc5b427a36b34114c701a4c0c`.

## Scope and safety rules

When performing dynamic or hybrid-APK validation in an Android emulator:

1. Use an isolated Android Virtual Device (AVD) dedicated to research.
2. Disable radio/network connectivity or isolate traffic unless explicitly inspecting local protocol framing.
3. Do not submit live user credentials, bank card numbers, payment passwords, or unauthorized requests to production `*.enzona.net` servers.
4. Keep all modified/hybrid APKs, UI hierarchy dumps, screenshots, and logcat captures inside `.workspace/` (excluded from Git).

## Observed results

Pending decompilation and initial static analysis. Record emulator environment, API level, startup behavior, and screen hierarchy comparisons here once dynamic checks are performed.
