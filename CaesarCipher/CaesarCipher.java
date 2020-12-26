import java.io.*;
class CaesarCipher {
  public static StringBuilder encipher(String text,int key) {
    StringBuilder etext = new StringBuilder();
    for( char el:text.toCharArray() ) {
      if( !Character.isAlphabetic(el) )
        etext.append(el);
      else {
        int asciEl = ((int)el) + key;
        if( Character.isUpperCase(el) ){
          if( asciEl>90 )
            asciEl = 64 + (asciEl-90);
          etext.append((char)asciEl);
        } else {
          if( asciEl>122 )
            asciEl = 96 + (asciEl-122);
          etext.append((char)asciEl);
        }
      }
    }
    return etext;
  }

  public static StringBuilder decipher(String text,int key) {
    StringBuilder etext = new StringBuilder();
    for( char el:text.toCharArray() ) {
      if( !Character.isAlphabetic(el) )
        etext.append(el);
      else {
        int asciEl = ((int)el) - key;
        if( Character.isUpperCase(el) ){
          if( asciEl<64 )
            asciEl = el + (26-key);
          etext.append((char)asciEl);
        } else {
          if( asciEl<97 )
            asciEl = el + (26-key);
          etext.append((char)asciEl);
        }
      }
    }
    return etext;
  }

  public static void main(String args[]){
    try {
      BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
      System.out.print("Enter the sentence to be encrypted:-");
      String text = br.readLine();
      System.out.print("Enter the numerical key for encryption:-");
      int key = Integer.parseInt(br.readLine());
      StringBuilder etext = encipher(text,key);
      System.out.print("ENCRYPTED:-");
      System.out.println(etext);
      System.out.println("DECRYPTED:-"+decipher(etext.toString(),key));
    } catch( IOException e ) {
      e.printStackTrace();
    }
  }
}