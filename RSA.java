
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class RSA {
    private BigInteger p,q,N,e,d,phi;
    private int bitlength = 1024;
    private Random r;
    
    public  RSA() {
        r = new Random();
        p = BigInteger.probablePrime(bitlength, r);
        q = BigInteger.probablePrime(bitlength, r);
        e = BigInteger.probablePrime(bitlength / 2, r);
        
        N = p.multiply(q);
        phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        
        while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0) {
            e.add(BigInteger.ONE);
        }
        
        System.out.println("Public key: " + e);
        d = e.modInverse(phi);
        System.out.println("Private key: " + d);
    }
    
    public void main(String[] args)  throws Exception {
        RSA rsa = new RSA();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the string: ");
        String testString = br.readLine();
        System.out.println("String in bytes: " + bytesToString(testString.getBytes()));
        byte[] encrypted = rsa.encrypt(testString.getBytes());
        byte[] decrypted = rsa.decrypt(encrypted);
        System.out.println("Decrypted string: " + new String(decrypted, StandardCharsets.UTF_8));
    }
    
    private static String bytesToString(byte[] message) {
        StringBuilder result = new StringBuilder();
        for (byte b : message) {
            result.append(Byte.toString(b));
        }
        return result.toString();
    }
    
    private byte[] encrypt (byte[] message) {
        return (new BigInteger(message)).modPow(e, N).toByteArray();
    }
    
    private byte[] decrypt (byte[] message) {
        return (new BigInteger(message)).modPow(d, N).toByteArray();
    }
}