import java.util.Scanner;

public class Program {

  public static int isPrime(int number) {

    int index = number - 1;
    while (number % index-- != 0 && index >= 1)
      ;
    return (index);
  }

  public static int sum(int number) {
    int sum = 0;
    while (number > 10) {
      sum += number % 10;
      number /= 10;
    }
    return (sum + number);
  }

  public static void main(String[] args) {

    int counter = 0;
    Scanner Obj = new Scanner(System.in);
    int number = 0;
    while (true) {
      System.out.print("-> ");
      number = Obj.nextInt();
      if (number == 42) {
        System.out.println("Count of coffee-request : " + counter);
        Obj.close();
        return;
      }
      if (isPrime(sum(number)) == 0)
        counter++;
    }
  }
}
