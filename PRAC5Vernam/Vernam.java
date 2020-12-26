import java.io.*;

class Vernam {
  // static String alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  public static StringBuilder encipher(String text,String key){
    text = text.toUpperCase();
    key = key.toUpperCase();

    int len = text.length();
    int keyLen = key.length();
    
    if( len != keyLen )
      return new StringBuilder("Length Of Text and Key has to be same!");
      
    StringBuilder etext = new StringBuilder("");

    for( int i=0 ; i<len ; i++ ) {
      int res = (int)text.charAt(i) + (int)key.charAt(i) - 65 ;
      if( res>(65+26) )
        res = res - 26;
      etext.append((char)(res));
    }

    return etext;
  }

  public static StringBuilder decipher(String etext,String key){
    etext = etext.toUpperCase();
    key = key.toUpperCase();

    int len = etext.length();
    int keyLen = key.length();
    
    if( len != keyLen )
      return new StringBuilder("Length Of Text and Key has to be same!");

    StringBuilder text = new StringBuilder("");

    for( int i=0 ; i<len ; i++ ) {
      int res = (int)etext.charAt(i) - (int)key.charAt(i) + 65;
      if( res<65 )
        res += 26;
      text.append((char)res);
    }

    return text;
  }

  public static void main(String args[]){
    try {
      BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
      System.out.print("Enter the word/sentence to be encrypted:-");
      String text = br.readLine();
      System.out.print("Enter the key of equal length for encryption:-");
      String key = br.readLine();
      StringBuilder etext = encipher(text,key);
      System.out.print("ENCRYPTED:-");
      System.out.println(etext);
      System.out.println("DECRYPTED:-"+decipher(etext.toString(),key));
    } catch( IOException e ) {
      e.printStackTrace();
    }
  }
}