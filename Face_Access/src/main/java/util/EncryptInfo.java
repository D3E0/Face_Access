package util;

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


}
