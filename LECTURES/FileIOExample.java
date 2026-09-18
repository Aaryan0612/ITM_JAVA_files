import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileIOExample {
    public static void main(String[] args){
        String fileName = "data.txt";

        try{
            FileOutputStream fos = new FileOutputStream(fileName);
            String message = "Java file handling Example";

            fos.write(message.getBytes());
            fos.close();

            System.out.println("Data writen successfully");

            FileInputStream fis = new FileInputStream(fileName);

            int data;
            System.out.println("Data from file: ");

            while ((data = fis.read())!= -1) {
                System.out.println((char) data);
            }
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
