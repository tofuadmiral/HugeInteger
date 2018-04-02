
public class Test
{
    public static void main(String args[])

            // alia78 400075937
    {
        HugeInteger int1= new HugeInteger("46");
        HugeInteger int2= new HugeInteger("55");
        HugeInteger int3= new HugeInteger("-10");
        HugeInteger int4= new HugeInteger("-7");

        System.out.println(int1.toString()+ " + " + int2.toString() + " = " + int1.add(int2).toString());
        System.out.println(int2.toString()+" + " + int1.toString() + " = " + int2.add(int1).toString());
        System.out.println(int3.toString()+" + " + int1.toString()+ " = " + int3.add(int1).toString());
        System.out.println(int4.toString()+" + " + int1.toString() +" = " + int4.add(int1).toString());

        int1=  new HugeInteger("146");
        int2=  new HugeInteger("9");
        int3=  new HugeInteger("-10");
        int4=  new HugeInteger("-7");


        System.out.println(int1.toString()+" - " + int2.toString()+ " = " + int1.subtract(int2).toString());
        int1=  new HugeInteger("146");
        int2=  new HugeInteger("9");
        int3=  new HugeInteger("-10");
        int4=  new HugeInteger("-7");
        System.out.println(int2.toString()+" - " + int1.toString()+ " = " + int2.subtract(int1).toString());
        int1=  new HugeInteger("146");
        int2=  new HugeInteger("9");
        int3=  new HugeInteger("-10");
        int4=  new HugeInteger("-7");
        System.out.println(int3.toString()+" - " + int1.toString()+ " = " + int3.subtract(int1).toString());
        int1=  new HugeInteger("6");
        int2=  new HugeInteger("9");
        int3=  new HugeInteger("-10");
        int4=  new HugeInteger("-7");
        System.out.println(int4.toString()+" - " + int1.toString()+ " = " + int4.subtract(int1).toString());

        int1=  new HugeInteger("146");
        int2=  new HugeInteger("10");
        int3=  new HugeInteger("-10");
        int4=  new HugeInteger("-7");

        System.out.println(int1.toString()+" x " + int2.toString()+ " = " + int1.multiply(int2).toString());
        System.out.println(int2.toString()+" x " + int1.toString()+ " = " + int2.multiply(int1).toString());
        System.out.println(int3.toString()+" x " + int1.toString()+ " = " + int3.multiply(int1).toString());
        System.out.println(int4.toString()+" x " + int1.toString()+ " = " + int4.multiply(int1).toString());
    }
}
