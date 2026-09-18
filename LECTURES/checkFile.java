import java.io.File;

public class checkFile {
    public static void main(String[] args){
        File file = new File("Student.txt");

        if(file.exists()){
            System.out.println("File Exists");
        }else{
            System.out.println("File does not exist");
        }
    }
}
