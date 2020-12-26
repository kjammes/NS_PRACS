import java.io.*;
import java.security.*;

public class Receive 
{
    public static void main(String args[]) 
	{
        try 
		{
        
			FileInputStream fis = new FileInputStream("test");
            ObjectInputStream ois = new ObjectInputStream(fis);
            
			Object o = ois.readObject();
            
			if (!(o instanceof String)) 
			{
                System.out.println("Unexpected data in file");
                System.exit(-1);
            }
            
			String data = (String) o;
            System.out.println("Got message " + data);
            o = ois.readObject();
            
			if (!(o instanceof byte[])) 
			{
                System.out.println("Unexpected data in file");
                System.exit(-1);
            }
            
			byte origDigest[] = (byte []) o;
            
			MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(data.getBytes());

            if (MessageDigest.isEqual(md.digest(), origDigest))
			{
                System.out.println("\n\n\n--> Message is valid");
			}
            else
			{
				System.out.println("\n\n\n--> Message is corrupted");
			}
        } 
		catch (java.io.StreamCorruptedException se) 
		{
            System.out.println("\n\n\n--> Message is corrupted");
        }
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }
}
