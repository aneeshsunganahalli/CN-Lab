import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class RSA {

    //5 components: message(m), p,q,n,phi,e,d

    private BigInteger d; // private key
    private BigInteger e; // public key
    private BigInteger n; // modulus OR n=p*q

    public RSA(int bitLength) {
        generateKeyPairs(bitLength);
    }

    private void generateKeyPairs(int bitLength) {
        Random random = new Random();
        BigInteger p = BigInteger.probablePrime(bitLength, random);
        BigInteger q = BigInteger.probablePrime(bitLength, random);

        n = p.multiply(q);

        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE)); //φ(n) = (p-1) * (q-1).

        // Choose public exponent 'e' such that 1 < e < φ(n) and e is coprime to φ(n)
        e = BigInteger.probablePrime(bitLength / 2, random);

        while(phi.gcd(e).intValue() > 1) {
            e = e.add(BigInteger.ONE);
        }

        // Calculate the d such that (d * e) % φ(n) = 1.
        d = e.modInverse(phi); // (e-1)%phi
    }

    public BigInteger encrypt(BigInteger message) {
        return message.modPow(e, n);  //c ≡ m^e (mod n).
    }

    public BigInteger decrypt(BigInteger encryptedMessage) {
        return encryptedMessage.modPow(d, n); //m ≡ c^d (mod n).
    }

    public static void main(String[] args) {
        int bitLength = 1024;
        String originalMessage;
        Scanner sc=new Scanner(System.in);

        RSA rsa = new RSA(bitLength);

        System.out.print("Enter a string: ");
        originalMessage=sc.nextLine();

        BigInteger message = new BigInteger(originalMessage.getBytes());
        // String->byte array->BigInteger
        
        BigInteger encryptedMessage = rsa.encrypt(message);
        System.out.println("Encrypted message: " + encryptedMessage);
        
        BigInteger decryptedMessage = rsa.decrypt(encryptedMessage);
        System.out.println("Decrypted message: " + new String(decryptedMessage.toByteArray()));
        // BigInteger->byte array->String
    }
}


// Output 
// Enter a string: ulteriorNewt's got some moves
// Encrypted message: 12707778386607386062916512873933191455785217269554116925043952693439289429621384705410951184730357091201378061293405277218353249162655333072548268394031019986933042139787027361671966655501471883499131619252385790194371767391395220090869149143495972458893935114928588430909694540586012074786391245213949928106697145091158451081104138734992403423075240996439188686154751712194263657573094787032866784243133503780155382112054306971307984372324787898300167297657652632041773809703044201988466654330049037966723287359202858707094239324308786436644015392606921228431476063238016562169566232449716819033821294423966293264909
// Decrypted message: ulteriorNewt's got some moves