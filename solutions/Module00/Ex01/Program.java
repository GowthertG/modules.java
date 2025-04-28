import java.util.Scanner;

public class Program {

  public static void FindPrime(int number, int index) {
    if ((number % index) != 0 && index > 1) {
      FindPrime(number, --index);
      return;
    }
    if (index != 1)
      System.out.println("not prime");
    else
      System.out.println("prime");
  }

  public static void main(String[] args) {
    Scanner Obj = new Scanner(System.in);
    int number = Obj.nextInt();
    if (number <= 1)
      System.err.println("IllegalArgument");
    FindPrime(number, --number);
    Obj.close();
  }
}
