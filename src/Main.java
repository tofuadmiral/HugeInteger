public class Main {

    public static void main(String[] args) {
        HugeInteger num1 = new HugeInteger(2);
        HugeInteger num2 = new HugeInteger("-3");

        System.out.println (" Number 1: " + num1.toString());
        System.out.println (" Number 2: " + num2.toString());

        HugeInteger num3;

        num3 = num1.add(num2);

        System.out.println ("add 1 & 2: " + num3.toString());

        System.out.println (num1.compareTo(num2));

        num3 = num1.multiply(num2);



        System.out.println("\n multiplied: " + num3.toString());



        HugeInteger num4 = new HugeInteger(2);
        HugeInteger num5 = new HugeInteger("-3");
        HugeInteger num6;


        System.out.println (" Number 4: " + num4.toString());
        System.out.println (" Number 5: " + num5.toString());
        num6 = num4.subtract(num5);

        System.out.println ("subtract 4 & 5: " + num6.toString());


        System.out.println("\n negative or positive: " + num3.array[0]);

    }
}
