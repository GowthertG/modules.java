import java.util.Scanner;

public class ProgramTwo {
  static int input;
  static int number;
  static int counter;

  static int IsPrime() {
    if ((number % 6) == 1 || (number % 6) == 5) {

      int PrimeDiveder = number - 1;
      while (PrimeDiveder > 1) {
        if (number % PrimeDiveder == 0)
          return 0;
        PrimeDiveder--;
      }
      if (PrimeDiveder == 1)
        return 1;
    }
    return 0;

  }

  static void calculateNumber() {
    while (input >= 10) {
      number += input % 10;
      input /= 10;
    }
    number += input;
  }

  public static void main(String[] args) {
    Scanner ObjectIn = new Scanner(System.in);
    while (true) {
      System.out.print("---> ");
      input = ObjectIn.nextInt();
      if (input == 42)
        break;
      calculateNumber();
      counter += IsPrime();
    }
    System.out.println("Count of coffe-request : " + counter);
    ObjectIn.close();
  }
}
