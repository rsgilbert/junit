public class Calculator {
    public double add(double number1, double number2) {
        return number1 + number2;
    }

    public static void main(String[] args) {
        System.out.println(new Calculator().add(1, 4));
        System.out.println(new Calculator().add(81.7, 24.5));
    }
}
