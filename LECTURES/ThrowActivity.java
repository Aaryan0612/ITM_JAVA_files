public class ThrowActivity {
    public static  void main(String[]args){
        int age = 18;
        try{
            if(age < 10){
                throw new IllegalArgumentException(
                    "Age must be 10 or above"
                );
            }
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
        System.out.println("Eligible to vote");
    }
}
