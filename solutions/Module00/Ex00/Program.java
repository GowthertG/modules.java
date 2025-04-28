
public class Program {
  int number;

  public Program() {
    number = 479598;
  }

  public static void main(String[] args) {
    Program Obj = new Program();
    int res = 0;
    while (Obj.number % 10 != 0) {
      res += Obj.number % 10;
      Obj.number /= 10;
    }
    System.out.println(res);
  }
}
