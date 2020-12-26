import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ColumnarTransposition{
    private static StringBuilder encrypt(String text,String key){
        StringBuilder encryptedMssg = new StringBuilder();
        List<char[]> grid = new ArrayList<>();
        int keyLen = key.length();
        Map<Character,List<Integer>> alphabetIndexes = new HashMap<>();
        int ind = 0;
        for( char el:key.toCharArray() ){
            List<Integer> indexes;
            if( alphabetIndexes.containsKey(el) ){
                indexes = alphabetIndexes.get(el);
                indexes.add(ind++);
                alphabetIndexes.put(el,indexes);
            } else {
                indexes = new ArrayList<>();
                indexes.add(ind++);
                alphabetIndexes.put(el,indexes);
            }
        }
        
        for( int i=0 ; i<=text.length() ; i = i+keyLen ){
            String subS = i+keyLen>text.length() ? text.substring(i) : text.substring(i, i+keyLen);
            if(subS.length()<keyLen)
            while(subS.length()!=keyLen)
            subS += "*";
            System.out.println(subS);
            grid.add(subS.toCharArray());
        }
        char[] sKey = (key.toCharArray()); 
        Arrays.sort(sKey);
        for( char el:sKey ){
            List<Integer> indexes = alphabetIndexes.get(el);
            int currentIndex = indexes.get(0);
            indexes.remove(0);
            for( char[] row:grid ){
                encryptedMssg.append(row[currentIndex]);
            }
        }
        return encryptedMssg;
    }

    private static StringBuilder decrypt(StringBuilder etext,String key){
        StringBuilder ogMssg = new StringBuilder();
        int len = etext.length();
        int keyLen = key.length();
        int nofRows = len/keyLen;
        char[][] grid = new char[nofRows][keyLen];
        char[] sortedKey = key.toCharArray();
        Arrays.sort(sortedKey);
        int pointer = 0;
        for( char el:sortedKey ){
            int col = key.indexOf(el);
            key = key.substring(0, col) + '*' + key.substring(col + 1); 
            for( int row=0 ; row<nofRows ; row++ ){
                grid[row][col] = etext.charAt(pointer++);
            }
        }
        for( char row[]:grid ){
            for( char el:row ){
                if( el=='*' )
                    break;
                ogMssg.append(el);
            }
        }
        return ogMssg;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter text to encrypt:-");
        String text = br.readLine();
        System.out.println();
        System.out.print("Enter key to encrypt data:-");
        String key = br.readLine();
        StringBuilder encryptedText = encrypt(text, key);
        System.out.println("Encrypted version of text:-"+encryptedText);
        System.out.println("Decrypted version of text:-"+decrypt(encryptedText,key));
    }
}