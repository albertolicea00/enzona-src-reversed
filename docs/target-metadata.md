# Extracting target metadata

The target metadata in the [README](../README.md) comes from `.workspace/bin/cu.xetid.apk.enzona-v20003.apk`. Run these commands from the repository root. The APK is needed to calculate its SHA-256 hash; a decompiled `AndroidManifest.xml` alone cannot provide that hash.

1. Install Android SDK Build Tools and make `aapt2` (or `aapt`) available on your `PATH`. On macOS, it is usually under `$HOME/Library/Android/sdk/build-tools/<version>/aapt2`.
2. Run the following Bash script. It reads the package and SDK fields from `aapt2 dump badging` (falling back to `aapt`) and calculates the checksum from the APK bytes.

```bash
APK=".workspace/bin/cu.xetid.apk.enzona-v20003.apk"
BADGE=$(aapt2 dump badging "$APK" 2>/dev/null || aapt dump badging "$APK" 2>/dev/null)
HASH=$(shasum -a 256 "$APK" | awk '{print $1}')
PKG=$(printf '%s\n' "$BADGE" | sed -n "s/^package: name='\([^']*\)'.*/\1/p")
VNAME=$(printf '%s\n' "$BADGE" | sed -n "s/^package:.*versionName='\([^']*\)'.*/\1/p")
VCODE=$(printf '%s\n' "$BADGE" | sed -n "s/^package:.*versionCode='\([^']*\)'.*/\1/p")
MIN=$(printf '%s\n' "$BADGE" | sed -nE "s/^(min[Ss]dk|sdk)Version:'([^']*)'.*/\2/p" | head -n 1)
TGT=$(printf '%s\n' "$BADGE" | sed -n "s/^targetSdkVersion:'\([^']*\)'.*/\1/p")
printf '## Target Metadata\n\n| Property | Value |\n| :--- | :--- |\n| **Package Name** | `%s` |\n| **Target Version** | `%s` |\n| **Version Code** | `%s` |\n| **Min SDK** | `%s` |\n| **Target SDK** | `%s` |\n| **SHA-256 Hash** | `%s` |\n' "$PKG" "$VNAME" "$VCODE" "$MIN" "$TGT" "$HASH"
```

Alternatively, run `make verify` from the repository root. If `aapt2`/`aapt` is not on your `PATH`, invoke it through its full Android SDK Build Tools path or add that directory to `PATH`. On Linux, `sha256sum "$APK"` can replace `shasum -a 256 "$APK"`.
