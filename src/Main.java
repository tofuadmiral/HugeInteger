public class Main {

    public static void main(String[] args) {
        HugeInteger num1 = new HugeInteger(2);
        HugeInteger num2 = new HugeInteger("99");

        System.out.println (" Number 1: " + num1.toString());
        System.out.println (" Number 2: " + num2.toString());

        HugeInteger num3;

        num3 = num1.add(num2);

        System.out.println ("add 1 & 2: " + num3.toString());

        System.out.println (num1.compareTo(num2));
    }
}
