package eu.ttbox.service.security.password;


import java.math.BigInteger;
import java.security.SecureRandom;

public class RandomString {

    public static String randomstring(int lo, int hi) {
        int n = rand(lo, hi);
        byte b[] = new byte[n];
        for (int i = 0; i < n; i++)
            b[i] = (byte) rand('a', 'z');
        return new String(b, 0);
    }

    private static int rand(int lo, int hi) {
        java.util.Random rn = new java.util.Random();
        int n = hi - lo + 1;
        int i = rn.nextInt(n);
        if (i < 0)
            i = -i;
        return lo + i;
    }

    public static String randomstring() {
        return randomstring(5, 25);
    }

    public static void main(String[] args) {
        SecureRandom random = new SecureRandom();
        String str = new BigInteger(130, random).toString(32);
        System.out.println("v1 : " + str);
        System.out.println("v2 : " + randomstring());
        Passphrase passwordGen = new Passphrase();
           System.out.println("v3 : " + passwordGen.genWord());

    }
}
