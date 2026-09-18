import java.io.File;
public class DeleteFile {
    public static void main(String[] args){
        File file = new File("Student.txt");

        if(file.delete()){
            System.out.println("File Deleted Sucessfully");
        }else{
            System.out.println("File does not exist");
        }
    }
}
