import java.security.*;
import javax.crypto.*;
import java.security.spec.*;
import javax.crypto.spec.*;
import java.util.Scanner;

public class AESDES {

  Cipher encrypt;
  Cipher decrypt;

  AESDES(SecretKey key, String algorithm) {
    try {
      encrypt = Cipher.getInstance(algorithm);
      decrypt = Cipher.getInstance(algorithm);
      encrypt.init(Cipher.ENCRYPT_MODE, key);
      decrypt.init(Cipher.DECRYPT_MODE, key);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public String encrypt(String str) {
    try {
      byte[] utf8 = str.getBytes("UTF8");

      byte[] enc = encrypt.doFinal(utf8);
      
      return new sun.misc.BASE64Encoder().encode(enc);
    } catch (Exception e) {
			e.printStackTrace();
    }
    return null;
  }

  public String decrypt(String str) {
    try {
      byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(str);
      
      byte[] utf8 = decrypt.doFinal(dec);

      return new String(utf8,"UTF8");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
   
  public static void main(String[] args) {
    try {
      Scanner sc = new Scanner(System.in);
      System.out.print("Enter the string to be encrypted:-");
      String mssg = sc.nextLine();
      SecretKey key = KeyGenerator.getInstance("DES").generateKey();

      AESDES encrypter = new  AESDES(key, key.getAlgorithm());

      String encipheredText = encrypter.encrypt(mssg);
      
      String decipheredText = encrypter.decrypt(encipheredText);

      System.out.println("    Encrypted String : " + encipheredText);
      System.out.println("    Decrypted String : " + decipheredText);
      System.out.println();
    } catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
  }
}