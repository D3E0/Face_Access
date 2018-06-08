package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptInfo {

    public static String encryptName(String name) {
        StringBuffer sb = new StringBuffer();
        sb.append(name.charAt(0));
        for (int i = 1; i < name.length(); i++) {
            sb.append('*');
        }
        return String.valueOf(sb);
    }

    public static String encryptTelephone(String telephone) {
        StringBuffer sb = new StringBuffer(telephone);
        sb.replace(3, 7, "****");
        return String.valueOf(sb);
    }

    /**
     * 生成32位md5码
     *
     * @param key
     * @return
     */
    public static String MD5(String key) {

        // 得到一个信息摘要器
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("md5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] result = digest.digest(key.getBytes());
        StringBuffer buffer = new StringBuffer();
        // 把每一个byte 做一个与运算 0xff;
        for (byte b : result) {
            // 与运算
            int number = b & 0xff;// 加盐
            String str = Integer.toHexString(number);
            if (str.length() == 1) {
                buffer.append("0");
            }
            buffer.append(str);
        }

        // 标准的md5加密后的结果
        return buffer.toString();

    }


}
