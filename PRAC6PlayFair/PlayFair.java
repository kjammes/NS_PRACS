import java.io.*;
import java.util.*;
class PlayFair{
    
    private static StringBuilder encrypt(String text,String key) {
        StringBuilder encryptedText = new StringBuilder();
        key = key.toUpperCase();
        text = text.toUpperCase();
        char keySquare[][] = new char[5][5];
        int keyLen = key.length();
        Set<Character> keyChars = new HashSet<>();
        
        int pointer = 0;
        int character = 65;
        for( int row=0 ; row<5 ; row++ ){
            for( int col=0 ; col<5 ; col++ ){
                if(pointer<keyLen){    
                    keyChars.add(key.charAt(pointer));
                    keySquare[row][col] = key.charAt(pointer++);
                } else {
                    char curEl = (char)character;
                    if( !keyChars.contains(curEl) ){
                        if( curEl=='J' ){
                            character += 1;
                            curEl = (char)character;
                        }
                        keySquare[row][col] = curEl;
                        character++;
                    } else {
                        while( keyChars.contains(curEl) ){
                            character += 1;
                            curEl = (char)character;
                        }
                        if( curEl=='J' ){
                            character += 1;
                            curEl = (char)character;
                        }
                        keySquare[row][col] = curEl;
                        character += 1;
                    }
                }
            }
        }
        System.out.println("Key Square generated is:-");
        for( char row[]:keySquare ){
            for( char el:row ){
                System.out.print(el+" ");
            }
            System.out.println();
        }
        for( int i=0 ; i<text.length() ; i++ ){
            if( text.charAt(i)=='J' ){
                text = text.substring(0,i) + 'I' + text.substring(i+1);
            }
        }
        if(text.length()%2!=0)
            text += 'Z';
        for(int i=0 ; i<text.length() ; i += 2){
            char el1 = text.charAt(i);
            char el2 = text.charAt(i+1);
            String position1 = getPosition(keySquare,el1);
            String position2 = getPosition(keySquare,el2);
            int col1 = position1.charAt(1) - '0';
            int col2 = position2.charAt(1) - '0';
            int row1 = position1.charAt(0) - '0';
            int row2 = position2.charAt(0) - '0';
            if( row1==row2 ){ //Condition for both elements being in same row 
                if(col1==4){
                    encryptedText.append(keySquare[row1][0]);
                    encryptedText.append(keySquare[row2][col2+1]);
                }
                else if( col2==4 ){
                    encryptedText.append(keySquare[row1][col1+1]);
                    encryptedText.append(keySquare[row2][0]);
                }
                else {
                    encryptedText.append(keySquare[row1][col1+1]);
                    encryptedText.append(keySquare[row2][col2+1]);
                }
            } else if( col1==col2 ){
                if( row1==4 ){
                    encryptedText.append(keySquare[0][col1]);
                    encryptedText.append(keySquare[row2+1][col2]);
                }
                else if( row2==4 ){
                    encryptedText.append(keySquare[row1+1][col1]);
                    encryptedText.append(keySquare[0][col2]);
                } else {
                    encryptedText.append(keySquare[row1+1][col1]);
                    encryptedText.append(keySquare[row2+1][col2]);
                }
            } else {
                encryptedText.append(keySquare[row1][col2]);
                encryptedText.append(keySquare[row2][col1]);
            }
        }
        return encryptedText;
    }

    private static StringBuilder decrypt(String etext,String key){
        StringBuilder decryptedText = new StringBuilder();
        key = key.toUpperCase();
        char keySquare[][] = new char[5][5];
        int keyLen = key.length();
        Set<Character> keyChars = new HashSet<>();
        
        int pointer = 0;
        int character = 65;
        for( int row=0 ; row<5 ; row++ ){
            for( int col=0 ; col<5 ; col++ ){
                if(pointer<keyLen){    
                    keyChars.add(key.charAt(pointer));
                    keySquare[row][col] = key.charAt(pointer++);
                } else {
                    char curEl = (char)character;
                    if( !keyChars.contains(curEl) ){
                        if( curEl=='J' ){
                            character += 1;
                            curEl = (char)character;
                        }
                        keySquare[row][col] = curEl;
                        character++;
                    } else {
                        while( keyChars.contains(curEl) ){
                            character += 1;
                            curEl = (char)character;
                        }
                        if( curEl=='J' ){
                            character += 1;
                            curEl = (char)character;
                        }
                        keySquare[row][col] = curEl;
                        character += 1;
                    }
                }
            }
        }
        for(int i=0 ; i<etext.length() ; i += 2){
            char el1 = etext.charAt(i);
            char el2 = etext.charAt(i+1);
            String position1 = getPosition(keySquare,el1);
            String position2 = getPosition(keySquare,el2);
            int col1 = position1.charAt(1) - '0';
            int col2 = position2.charAt(1) - '0';
            int row1 = position1.charAt(0) - '0';
            int row2 = position2.charAt(0) - '0';
            if( row1==row2 ){ //Condition for both elements being in same column 
                if(col1==0){
                    decryptedText.append(keySquare[row1][4]);
                    decryptedText.append(keySquare[row2][col2-1]);
                }
                else if( col2==0 ){
                    decryptedText.append(keySquare[row1][col1-1]);
                    decryptedText.append(keySquare[row2][4]);
                }
                else {
                    decryptedText.append(keySquare[row1][col1-1]);
                    decryptedText.append(keySquare[row2][col2-1]);
                }
            } else if( col1==col2 ){
                if( row1==0 ){
                    decryptedText.append(keySquare[4][col1]);
                    decryptedText.append(keySquare[row2-1][col2]);
                }
                else if( row2==0 ){
                    decryptedText.append(keySquare[row1-1][col1]);
                    decryptedText.append(keySquare[4][col2]);
                } else {
                    decryptedText.append(keySquare[row1-1][col1]);
                    decryptedText.append(keySquare[row2-1][col2]);
                }
            } else {
                decryptedText.append(keySquare[row1][col2]);
                decryptedText.append(keySquare[row2][col1]);
            }
        }

        return decryptedText;
    }

    private static String getPosition(char[][] keySquare,char el){
        for( int i=0 ; i<5 ; i++ ){
            for( int j=0 ; j<5 ; j++ ){
                if(keySquare[i][j]==el)
                    return ""+i+j;
            }
        }
        return "00";
    }

    public static void main(String[] args) {
        try{
            BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
            System.out.print("Enter the word to encrypt:-");
            String text = br.readLine();
            System.out.print("Enter the key to encrypt:-");
            String key = br.readLine();
            StringBuilder encryptedText = encrypt(text,key);
            System.out.println("Original Text-->"+text);
            System.out.println("Encrypted Text-->"+encryptedText);
            System.out.println("Decrypted Text-->"+decrypt(encryptedText.toString(),key));
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}