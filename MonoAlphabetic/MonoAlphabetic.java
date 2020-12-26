import java.util.*;
import java.io.*;
class MonoAlphabetic{
    static char ed[][] = { 
                { 'A','Z' },
                { 'B','Y' },
                { 'C','X' },
                { 'D','W' },
                { 'E','V' },
                { 'F','U' },
                { 'G','T' },
                { 'H','S' },
                { 'I','R' },
                { 'J','Q' },
                { 'K','P' },
                { 'L','O' },
                { 'M','N' },
                { 'N','M' },
                { 'O','L' },
                { 'P','K' },
                { 'Q','J' },
                { 'R','I' },
                { 'S','H' },
                { 'T','G' },
                { 'U','F' },
                { 'V','E' },
                { 'W','D' },
                { 'X','C' },
                { 'Y','B' },
                { 'Z','A' },
            };
    static StringBuilder encrypt(String mssg){
        mssg = mssg.toUpperCase();
        StringBuilder et = new StringBuilder();
        for( char el:mssg.toCharArray() ){
            if( !Character.isAlphabetic(el) )
                et.append(el);
            else{
                int ind = (int)el - 65;
                et.append( ed[ind][1] );
            }
        }
        return et;
    }

    static StringBuilder decrypt(StringBuilder emssg){
        StringBuilder dt = new StringBuilder();
        for( char el:emssg.toString().toCharArray() ){
            if( !Character.isAlphabetic(el) )
                dt.append(el);
            else{
                int ind = (int)el - 65;
                dt.append( ed[ind][1] );
            }
        }
        return dt;
    }

    public static void main(String[] args){
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter the text message to be encrypted:-");
            String mssg = br.readLine();
            System.out.println();
            StringBuilder encryptMssg = encrypt(mssg);
            System.out.println("Encryped mssg-->"+encryptMssg);
            System.out.println();
            System.out.println("Decrypted mssg-->"+decrypt(encryptMssg));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}