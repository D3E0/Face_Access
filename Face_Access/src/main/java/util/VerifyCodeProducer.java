package util;

public class VerifyCodeProducer {

    public static String getDigitVerifyCode() {
        int ans = (int) (Math.random() * 9 + 1);
        for (int i = 0; i < 4; i++) {
            ans *= 10;
            ans += (int) (Math.random() * 10);
        }
        return String.valueOf(ans);
    }


}
