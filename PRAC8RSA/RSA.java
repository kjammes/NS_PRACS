import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import static java.lang.Math.random;
import static java.lang.StrictMath.random;
import java.math.BigInteger;
import java.security.*;
import java.math.*;
import java.util.Random;
import java.util.Scanner;

public class RSA {
	public static void main(String args[]) throws Exception {
		String plnText, pad, cphText;
		int rnd;
		byte[] b = new byte[20];
		BigInteger p, q, n, e, d, m;
		plnText = "helloworld";
		SecureRandom random = new SecureRandom();
		random.nextBytes(b);
		p = new BigInteger(5, 100, random);
		System.out.println("P=" + p);
		q = new BigInteger(5, 100, random);
		System.out.println("Q=" + q);
		n = p.multiply(q);
		System.out.println("N=P*Q=" + n);
		e = new BigInteger("3");
		m = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
		System.out.println("(P-1)*(Q-1)=" + m);

		while (m.gcd(e).intValue() != 1) {
			System.out.println(m.gcd(e).intValue());
			e = e.add(new BigInteger("2"));
			System.out.println("e=" + e);
		}

		d = e.modInverse(m);
		System.out.println("d=" + d);
		BigInteger b2 = new BigInteger("5");
		RSA r = new RSA();
		cphText = r.RSAEN(plnText, e.intValue(), n);
		FileWriter normalWriter = new FileWriter("ptext.txt");
		FileWriter cipherWriter = new FileWriter("ciphertext.txt");
		System.out.println("Cipher Text: " + cphText);
		String dplnText = r.RSADE(cphText, d.intValue(), n);
		for (int i = 0; i < dplnText.length() ; i++ )
		normalWriter.write(dplnText.charAt(i));
		normalWriter.close();
		for (int i = 0; i < cphText.length() ; i++ )
		cipherWriter.write(cphText.charAt(i));
		cipherWriter.close();
		System.out.println("Plain Text: " + dplnText);
		System.out.println("---authentucation checking for data which is not modified----");
		System.out.println(checkAuthen(plnText, dplnText));

		FileReader fr = new FileReader("ciphertext.txt");
		Scanner sc = new Scanner(new File("ciphertext.txt"));
		String newcipher = "";
		while (sc.hasNext()) {
			newcipher = sc.next();
		}

		newcipher = newcipher + "s";
		System.out.println(" Cipher Text: " + newcipher);
		String pplnText = r.RSADE(newcipher, d.intValue(), n);
		System.out.println("Plain Text: " + pplnText);
		System.out.println("---authentucation checking for data which is  modified----");
		System.out.println(checkAuthen(plnText, pplnText));
	}
 


	public String RSAEN(String plnText, int e, BigInteger n) {
		String cphText;
		int c1, c2, result;
		String str;
		cphText = "";
		for (int i = 0; i < plnText.length(); i++) {
			c1 = (int)plnText.charAt(i);
			str = "" + c1;
			BigInteger big = new BigInteger(str);
			BigInteger cph = big.pow(e).mod(n);
			cphText = cphText + (char)cph.intValue();
		}
		return cphText;
	}

	public String RSADE(String cphText, int d, BigInteger n) {
		String plnText;
		int c1, c2, result;
		String str;
		plnText = "";
		for(int i = 0; i < cphText.length(); i++) {
			c1 = (int)cphText.charAt(i);
			str = "" + c1;
			BigInteger big = new BigInteger(str);
			BigInteger pln = big.pow(d).mod(n);
			plnText = plnText + (char)pln.intValue();
		}
		return plnText;
	}
	
	public static String checkAuthen(String original, String decrypted) {
	if (original.equals(decrypted)) 
		return "message is not modified";
	else  
		return "message is  modified"; 
	}
}