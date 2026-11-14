# Hybrid Java compiler audit

Target: `cu.xetid.apk.enzona-v20003.apk`, SHA-256
`9125313c01eb522522c2a1e312c62ad09a81193bc5b427a36b34114c701a4c0c`.

`scripts/compile-reconstruction.py` compiles every Java source under
`app/src/main/java` against a **local APK-derived bytecode jar** and a **local
Android platform jar**. This is a dependency-backed compiler audit: it detects
unresolved symbols, invalid signatures, type mismatches, and other Java errors
in the reconstructed tree while retaining the APK's raw dependencies.
It is not a standalone Android build, an APK produced from clean source, a
resource-packaging test, behavioral equivalence, or runtime verification.

## Local prerequisites and provenance

- Python 3 and a JDK with `javac` on `PATH`.
- The target APK remains local in `.workspace/bin/cu.xetid.apk.enzona-v20003.apk`.
- Pin dex2jar to **2.4**, using its official
  [v2.4 release](https://github.com/pxb1988/dex2jar/releases/tag/v2.4) and
  [dex-tools-v2.4.zip asset](https://github.com/pxb1988/dex2jar/releases/download/v2.4/dex-tools-v2.4.zip)
  under `.workspace/tools/dex-tools-v2.4/`.
- Convert the target APK bytecode to `.workspace/tools/target-bytecode.jar`:

```sh
bash .workspace/tools/dex-tools-v2.4/d2j-dex2jar.sh \
  -f -o .workspace/tools/target-bytecode.jar \
  .workspace/bin/cu.xetid.apk.enzona-v20003.apk
```

## Run the full audit

Once decompilation (`src/cu/xetid/apk/enzona/R.java`) and clean Java files exist under `app/src/main/java/`:

```sh
make compile-audit ANDROID_JAR="$HOME/Library/Android/sdk/platforms/android-34/android.jar"
```

The script writes `.workspace/compile-reconstruction/report.json` and `.workspace/compile-reconstruction/javac.log`.
