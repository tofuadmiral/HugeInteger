
public class Test
{
    public static void main(String args[])
    {
        HugeInteger A= new HugeInteger("46");
        HugeInteger B= new HugeInteger("55");
        HugeInteger C= new HugeInteger("-10");
        HugeInteger D= new HugeInteger("-7");

        System.out.println(A.toString()+ " + " + B.toString() + " = " + A.add(B).toString());
        System.out.println(B.toString()+" + " + A.toString() + " = " + B.add(A).toString());
        System.out.println(C.toString()+" + " + A.toString()+ " = " + C.add(A).toString());
        System.out.println(D.toString()+" + " + A.toString() +" = " + D.add(A).toString());

        A=  new HugeInteger("146");
        B=  new HugeInteger("9");
        C=  new HugeInteger("-10");
        D=  new HugeInteger("-7");


        System.out.println(A.toString()+" - " + B.toString()+ " = " + A.subtract(B).toString());
        A=  new HugeInteger("146");
        B=  new HugeInteger("9");
        C=  new HugeInteger("-10");
        D=  new HugeInteger("-7");
        System.out.println(B.toString()+" - " + A.toString()+ " = " + B.subtract(A).toString());
        A=  new HugeInteger("146");
        B=  new HugeInteger("9");
        C=  new HugeInteger("-10");
        D=  new HugeInteger("-7");
        System.out.println(C.toString()+" - " + A.toString()+ " = " + C.subtract(A).toString());
        A=  new HugeInteger("6");
        B=  new HugeInteger("9");
        C=  new HugeInteger("-10");
        D=  new HugeInteger("-7");
        System.out.println(D.toString()+" - " + A.toString()+ " = " + D.subtract(A).toString());

        A=  new HugeInteger("146");
        B=  new HugeInteger("10");
        C=  new HugeInteger("-10");
        D=  new HugeInteger("-7");

        System.out.println(A.toString()+" x " + B.toString()+ " = " + A.multiply(B).toString());
        System.out.println(B.toString()+" x " + A.toString()+ " = " + B.multiply(A).toString());
        System.out.println(C.toString()+" x " + A.toString()+ " = " + C.multiply(A).toString());
        System.out.println(D.toString()+" x " + A.toString()+ " = " + D.multiply(A).toString());
    }
}
