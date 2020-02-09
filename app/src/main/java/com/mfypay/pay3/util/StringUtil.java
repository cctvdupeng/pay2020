package com.mfypay.pay3.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.telephony.TelephonyManager;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringUtil {


    public static String SPLIT_CONTENT(String content, String startStr, String endStr) {

        if (content.indexOf(startStr) < 0) {
            return "";
        }

        try {

            int i = content.indexOf(startStr) + startStr.length();

            content = content.substring(i, content.indexOf(endStr, i));

            return content;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";

    }


    public static byte[] getBytes(String filePath) {
        byte[] buffer = null;
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    public static void getFile(byte[] bfile, String filePath, String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if (!dir.exists() && !dir.isDirectory()) {//
                dir.mkdirs();
            }
            file = new File(filePath + "\\" + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bfile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    public static String getimei(Context context) {
     try {
         TelephonyManager tm = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
         String imei = tm.getDeviceId();
         if (imei == null) {
             return "00000000000000000";
         } else {
             return tm.getDeviceId();
         }
     }catch (Exception e){
         return e.getLocalizedMessage();
     }
    }

    public static String getTextCenter(String text, String begin, String end) {
        try {
            int b = text.indexOf(begin) + begin.length();
            int e = text.indexOf(end, b);
            return text.substring(b, e);
        } catch (Exception e) {


            return "error";
        }
    }


    public final static String SHA1 = "SHA1";

    /**
     * 返回一个签名的对应类型的字符串
     *
     * @param context
     * @param packageName
     * @param
     * @return
     */
    public static String getSingInfo(Context context, String packageName) {


        String tmp = "";


        Signature[] signs = getSignatures(context, packageName);
        for (Signature sig : signs) {
            if (SHA1.equals(StringUtil.SHA1)) {
              tmp=  getSignatureString(sig, SHA1);
                break;
            }

        }
        return tmp;


    }

    /**
     * 返回对应包的签名信息
     *
     * @param context
     * @param packageName
     * @return
     */
    public static Signature[] getSignatures(Context context, String packageName) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_SIGNATURES);
            return packageInfo.signatures;
        } catch (Error e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable ee) {
            ee.printStackTrace();
        }
        return null;
    }

    /**
     * 获取相应的类型的字符串（把签名的byte[]信息转换成16进制）
     *
     * @param sig
     * @param type
     * @return
     */
    public static String getSignatureString(Signature sig, String type) {
        byte[] hexBytes = sig.toByteArray();
        String fingerprint = "error!";
        try {
            MessageDigest digest = MessageDigest.getInstance(type);
            if (digest != null) {
                byte[] digestBytes = digest.digest(hexBytes);
                StringBuilder sb = new StringBuilder();
                for (byte digestByte : digestBytes) {
                    sb.append((Integer.toHexString((digestByte & 0xFF) | 0x100)).substring(1, 3));
                }
                fingerprint = sb.toString();
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return fingerprint;
    }

}
