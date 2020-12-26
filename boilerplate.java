import java.io.*;

class ColumnarTransposition {
  public static StringBuilder encipher(String text,String key){
    StringBuilder etext = new StringBuilder("");



    return etext;
  }

  public static StringBuilder decipher(String etext,String key){
    StringBuilder text = new StringBuilder("");

    

    return text;
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