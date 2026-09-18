import java.io.File;

public class DirectoryExample {
    public static void main(String [] args){
        File folder = new File("StudentData");

        if(folder.mkdir()){
            System.out.println("Directory created");
        }else{
            System.out.println("Directory already exists");
        }
    }
}
