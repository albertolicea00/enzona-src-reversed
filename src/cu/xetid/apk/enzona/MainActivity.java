package cu.xetid.apk.enzona;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.util.Log;
import androidx.core.content.a;
import com.pichillilorenzo.flutter_inappwebview_android.credential_database.URLCredentialContract;
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

/* JADX INFO: loaded from: classes.dex */
public class MainActivity extends FlutterFragmentActivity {
    private static final String CHANNEL = "cu.xetid.apk.enzona/methods";
    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    private static final String METHOD_EXECUTED_KEY = "method_executed";
    private static final String PREFS_NAME = "MyAppPrefs";

    public static String bytesToHex(byte[] bArr) {
        char[] cArr = new char[bArr.length * 2];
        for (int i6 = 0; i6 < bArr.length; i6++) {
            int i7 = bArr[i6] & 255;
            int i8 = i6 * 2;
            char[] cArr2 = HEX_ARRAY;
            cArr[i8] = cArr2[i7 >>> 4];
            cArr[i8 + 1] = cArr2[i7 & 15];
        }
        return new String(cArr);
    }

    private String getActiveApnName() {
        try {
            if (a.a(this, "android.permission.READ_PHONE_STATE") != 0) {
                return "permission_denied";
            }
            Cursor cursorQuery = getContentResolver().query(Uri.parse("content://telephony/carriers/preferapn"), null, null, null, null);
            if (cursorQuery == null || !cursorQuery.moveToFirst()) {
                return "no_active_apn";
            }
            int columnIndex = cursorQuery.getColumnIndex("name");
            String string = columnIndex >= 0 ? cursorQuery.getString(columnIndex) : "no_apn_name";
            cursorQuery.close();
            return string;
        } catch (Exception e6) {
            return "error_getting_apn: " + e6.getMessage();
        }
    }

    private String getCarrierName() {
        try {
            String networkOperatorName = ((TelephonyManager) getSystemService("phone")).getNetworkOperatorName();
            return (networkOperatorName == null || networkOperatorName.isEmpty()) ? "unknown_carrier" : networkOperatorName;
        } catch (Exception unused) {
            return "error_getting_carrier";
        }
    }

    public static byte[] hexStringToByteArray(String str) {
        int length = str.length();
        byte[] bArr = new byte[length / 2];
        for (int i6 = 0; i6 < length; i6 += 2) {
            bArr[i6 / 2] = (byte) ((Character.digit(str.charAt(i6), 16) << 4) + Character.digit(str.charAt(i6 + 1), 16));
        }
        return bArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$configureFlutterEngine$0(MethodCall methodCall, MethodChannel.Result result) {
        String str = methodCall.method;
        str.hashCode();
        switch (str) {
            case "getFingerPrint":
                result.success(Build.VERSION.SDK_INT >= 23 ? getFingerPrint((String) methodCall.argument(URLCredentialContract.FeedEntry.COLUMN_NAME_USERNAME), (String) methodCall.argument("pvKey")) : null);
                return;
            case "getCarrierName":
                result.success(getCarrierName());
                return;
            case "getSdkNumber":
                result.success(Integer.valueOf(Build.VERSION.SDK_INT));
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
                    result.success(parseKeyPair((KeyPair) methodCall.arguments()));
                    return;
                } catch (JSONException e6) {
                    throw new RuntimeException(e6);
                }
            default:
                result.notImplemented();
                return;
        }
    }

    @Override // io.flutter.embedding.android.FlutterFragmentActivity, io.flutter.embedding.android.FlutterEngineConfigurator
    public void configureFlutterEngine(FlutterEngine flutterEngine) {
        GeneratedPluginRegistrant.registerWith(flutterEngine);
        super.configureFlutterEngine(flutterEngine);
        new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), CHANNEL).setMethodCallHandler(new MethodChannel.MethodCallHandler() { // from class: z3.a
            @Override // io.flutter.plugin.common.MethodChannel.MethodCallHandler
            public final void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
                this.f9982a.lambda$configureFlutterEngine$0(methodCall, result);
            }
        });
    }

    public String getFingerPrint(String str, String str2) {
        try {
            Log.i("getFingerPrint", "getFingerPrint: " + str2);
            if (str2 != null && !str2.equals("")) {
                try {
                    Signature signatureInitSignature = initSignature(KeyFactory.getInstance("EC").generatePrivate(new PKCS8EncodedKeySpec(hexStringToByteArray(str2))));
                    signatureInitSignature.update(str.getBytes(StandardCharsets.UTF_8));
                    return Base64.encodeToString(signatureInitSignature.sign(), 2);
                } catch (NoSuchAlgorithmException | SignatureException | InvalidKeySpecException e6) {
                    e6.printStackTrace();
                    Log.i("Biometric", "SignatureException = " + e6.getMessage());
                }
            }
            return "";
        } catch (Exception e7) {
            e7.printStackTrace();
            Log.i("getFingerPrint", "dataBaseHelperException = " + e7.getMessage());
            return "";
        }
    }

    public List<String> getKeyValidated() {
        Log.i("getKeyValited", "getKeyValited: init");
        try {
            ArrayList arrayList = new ArrayList();
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");
            keyPairGenerator.initialize(new ECGenParameterSpec("secp256r1"), new SecureRandom());
            KeyPair keyPairGenerateKeyPair = keyPairGenerator.generateKeyPair();
            arrayList.add(Base64.encodeToString(keyPairGenerateKeyPair.getPublic().getEncoded(), 2));
            arrayList.add(bytesToHex(keyPairGenerateKeyPair.getPrivate().getEncoded()));
            return arrayList;
        } catch (InvalidAlgorithmParameterException | NoSuchAlgorithmException e6) {
            e6.printStackTrace();
            return null;
        }
    }

    public Map<String, String> getVersionInfo() {
        HashMap map = new HashMap();
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            map.put("versionName", packageInfo.versionName);
            map.put("nameApp", getPackageName());
            map.put("versionCode", String.valueOf(packageInfo.versionCode));
        } catch (PackageManager.NameNotFoundException e6) {
            e6.printStackTrace();
        }
        return map;
    }

    public Signature initSignature(PrivateKey privateKey) throws NoSuchAlgorithmException, InvalidKeyException {
        Signature signature = Signature.getInstance("SHA256withECDSA");
        signature.initSign(privateKey);
        return signature;
    }

    public JSONObject parseKeyPair(KeyPair keyPair) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        String strEncodeToString = Base64.encodeToString(keyPair.getPublic().getEncoded(), 2);
        String strBytesToHex = bytesToHex(keyPair.getPrivate().getEncoded());
        try {
            jSONObject.put("public", strEncodeToString);
            jSONObject.put("private", strBytesToHex);
            return jSONObject;
        } catch (JSONException e6) {
            throw new RuntimeException(e6);
        }
    }
}
