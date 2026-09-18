public class main2 {
    static void checkAge(int age) throws Exception{
        if(age < 18){
            throw new Exception("Age must be 18 or above");
        }
        System.out.println("Eligible");
    }
    public static void main(String[] args){
        try{
            checkAge(16);
        }
        catch(Exception e){
            System.out.println("Error" + e.getMessage());
        }
    }
}
