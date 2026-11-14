package cu.xetid.apk.enzona;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.util.Log;
import androidx.core.content.ContextCompat;
import io.flutter.embedding.android.FlutterFragmentActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugins.GeneratedPluginRegistrant;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Primary Flutter host activity for ENZONA ({@code cu.xetid.apk.enzona} v1.11.0, code 20003).
 *
 * <p>Exposes the {@code cu.xetid.apk.enzona/methods} {@link MethodChannel} to the Dart AOT
 * engine ({@code libapp.so}), providing secp256r1 ECDSA key generation and signing
 * ({@code getKeyValidate}, {@code getFingerPrint}, {@code parseKeyPair}), active APN lookup,
 * carrier name, SDK level, and APK version metadata.</p>
 *
 * @obfuscated_class cu.xetid.apk.enzona.MainActivity
 */
public class MainActivity extends FlutterFragmentActivity {
    private static final String CHANNEL = "cu.xetid.apk.enzona/methods";
    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int i = 0; i < bytes.length; i++) {
            int v = bytes[i] & 0xFF;
            hexChars[i * 2] = HEX_ARRAY[v >>> 4];
            hexChars[i * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static byte[] hexStringToByteArray(String hex) {
        int len = hex.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                    + Character.digit(hex.charAt(i + 1), 16));
        }
        return data;
    }

    private String getActiveApnName() {
        try {
            if (ContextCompat.checkSelfPermission(this, "android.permission.READ_PHONE_STATE")
                    != PackageManager.PERMISSION_GRANTED) {
                return "permission_denied";
            }
            Cursor cursor = getContentResolver().query(
                    Uri.parse("content://telephony/carriers/preferapn"), null, null, null, null);
            if (cursor == null || !cursor.moveToFirst()) {
                return "no_active_apn";
            }
            int nameIdx = cursor.getColumnIndex("name");
            String apnName = nameIdx >= 0 ? cursor.getString(nameIdx) : "no_apn_name";
            cursor.close();
            return apnName;
        } catch (Exception e) {
            return "error_getting_apn: " + e.getMessage();
        }
    }

    private String getCarrierName() {
        try {
            String operatorName = ((TelephonyManager) getSystemService("phone")).getNetworkOperatorName();
            return (operatorName == null || operatorName.isEmpty()) ? "unknown_carrier" : operatorName;
        } catch (Exception unused) {
            return "error_getting_carrier";
        }
    }

    @Override
    public void configureFlutterEngine(FlutterEngine flutterEngine) {
        GeneratedPluginRegistrant.registerWith(flutterEngine);
        super.configureFlutterEngine(flutterEngine);
        new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), CHANNEL)
                .setMethodCallHandler((call, result) -> onPlatformMethodCall(call, result));
    }

    private void onPlatformMethodCall(MethodCall call, MethodChannel.Result result) {
        switch (call.method) {
            case "getFingerPrint":
                result.success(Build.VERSION.SDK_INT >= 23
                        ? getFingerPrint((String) call.argument("username"), (String) call.argument("pvKey"))
                        : null);
                return;
            case "getCarrierName":
                result.success(getCarrierName());
                return;
            case "getSdkNumber":
                result.success(Build.VERSION.SDK_INT);
                return;
            case "getVersionInfo":
                result.success(getVersionInfo());
                return;
            case "getKeyValidate":
                result.success(getKeyValidated());
                return;
            case "getActiveApnName":
                result.success(getActiveApnName());
                return;
            case "parseKeyPair":
                try {
                    result.success(parseKeyPair((KeyPair) call.arguments()));
                    return;
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            default:
                result.notImplemented();
        }
    }

    public String getFingerPrint(String username, String privateKeyHex) {
        try {
            if (privateKeyHex != null && !privateKeyHex.isEmpty()) {
                PrivateKey privateKey = KeyFactory.getInstance("EC")
                        .generatePrivate(new PKCS8EncodedKeySpec(hexStringToByteArray(privateKeyHex)));
                Signature signature = initSignature(privateKey);
                signature.update(username.getBytes(StandardCharsets.UTF_8));
                return Base64.encodeToString(signature.sign(), Base64.NO_WRAP);
            }
            return "";
        } catch (NoSuchAlgorithmException | SignatureException | InvalidKeySpecException | InvalidKeyException e) {
            Log.i("Biometric", "SignatureException = " + e.getMessage());
            return "";
        }
    }

    public List<String> getKeyValidated() {
        try {
            List<String> keys = new ArrayList<>();
            KeyPairGenerator generator = KeyPairGenerator.getInstance("EC");
            generator.initialize(new ECGenParameterSpec("secp256r1"), new SecureRandom());
            KeyPair pair = generator.generateKeyPair();
            keys.add(Base64.encodeToString(pair.getPublic().getEncoded(), Base64.NO_WRAP));
            keys.add(bytesToHex(pair.getPrivate().getEncoded()));
            return keys;
        } catch (InvalidAlgorithmParameterException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Map<String, String> getVersionInfo() {
        Map<String, String> info = new HashMap<>();
        try {
            PackageInfo pkg = getPackageManager().getPackageInfo(getPackageName(), 0);
            info.put("versionName", pkg.versionName);
            info.put("nameApp", getPackageName());
            info.put("versionCode", String.valueOf(pkg.versionCode));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return info;
    }

    public Signature initSignature(PrivateKey privateKey) throws NoSuchAlgorithmException, InvalidKeyException {
        Signature signature = Signature.getInstance("SHA256withECDSA");
        signature.initSign(privateKey);
        return signature;
    }

    public JSONObject parseKeyPair(KeyPair keyPair) throws JSONException {
        JSONObject json = new JSONObject();
        json.put("public", Base64.encodeToString(keyPair.getPublic().getEncoded(), Base64.NO_WRAP));
        json.put("private", bytesToHex(keyPair.getPrivate().getEncoded()));
        return json;
    }
}
