import java.io.File;
import java.io.IOException;

public class fileHEG {
    public static void main(String [] args) throws IOException{
        File file = new File("Student.txt");

        if(file.createNewFile()){
            System.out.println("File Created");
        }else{
            System.out.println("File already exists");
        }
        System.out.println("Name: " + file.getName());
        System.out.println("Path: "+ file.getPath());
        System.out.println("Size: " + file.length());
        System.out.println("Exists: " + file.exists());
        System.out.println("In file: "+ file.isFile());
    }
}
