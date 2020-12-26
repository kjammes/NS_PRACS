import java.io.*;

class RailFence {
  public static encipher(String text,int key){
    char[][] RailFence = new char[key][text.length()];
    int row = 0;
    for( int col = 0 ; col<text.length() ; col++ ){
      if( row==key-1 )
        row = 0;
      
      RailFence[row++][col] = text.charAt(col);
      row = inc ? row+1 : row-1;
    }
    for( int[] curRow:RailFence ){
      for(int el:curRow){
        if(  )
      }
    }
  }

  public static decipher(String etext,int key){

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