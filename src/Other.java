public class Other {
    public double minus(double number1, double number2) {
        return number1 - number2;
    }

    public static void main(String[] args) {
        System.out.println(new Other().minus(1, 4));
        System.out.println(new Other().minus(81.7, 24.5));
    }
}
