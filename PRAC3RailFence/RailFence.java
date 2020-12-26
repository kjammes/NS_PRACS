import java.io.*;
import java.util.*;

class RailFence {
  public static StringBuilder encipher(String text,int key){
    StringBuilder etext = new StringBuilder("");
    char[][] RailFence = new char[key][text.length()];
    int row = 0;
    boolean inc = false;
    for( int col = 0 ; col<text.length() ; col++ ){
      if( row==key-1 || row==0 )
        inc = !inc;
      
      RailFence[row][col] = text.charAt(col);
      row = inc ? row+1 : row-1;
    }
    for( char[] curRow:RailFence ){
      for(char el:curRow){
        if( el!='\u0000' )
          etext.append(el);
      }
    }
    return etext;
  }

  public static StringBuilder decipher(String etext,int key){
    StringBuilder text = new StringBuilder("");
    char[][] RailFence = new char[key][etext.length()];
    int row = 0;
    boolean inc = false;
    for( int col = 0 ; col<etext.length() ; col++ ){
      if( row==key-1 || row==0 )
        inc = !inc;
      
      RailFence[row][col] = '*';
      row = inc ? row+1 : row-1;
    }
    int pointer = 0;
    for( int curRow=0 ; curRow<key ; curRow++ ){
      for( int col=0 ; col<etext.length() ; col++ ){
        if( RailFence[curRow][col]=='*' )
          RailFence[curRow][col] = etext.charAt(pointer++);
      }
    }

    row = 0;
    inc = false;
    for( int col = 0 ; col<etext.length() ; col++ ){
      if( row==key-1 || row==0 )
        inc = !inc;
      text.append(RailFence[row][col]);
      row = inc ? row+1 : row-1;
    }
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