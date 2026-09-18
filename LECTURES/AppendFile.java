import java.io.FileOutputStream;
import java.io.IOException;

public class AppendFile {
    public static void main(String[] args){
        try{
            FileOutputStream fos = new FileOutputStream("Student.txt", true);
            String data = "\n This in additional information";

            fos.write(data.getBytes());

            fos.close();
            System.out.println("Data appended successfully");
        }
        catch(IOException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
