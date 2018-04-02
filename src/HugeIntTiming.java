public class HugeIntTiming {
    // alia78 400075937

    public static void main(String[] args) {

        HugeInteger huge1, huge2, huge3;
        long startTime, endTime;
        double runTime = 0.0;

        int MAXNUMINTS = 100;
        int MAXRUN = 50;
        for (int numInts = 1; numInts < MAXNUMINTS; numInts++) {
            huge1 = new HugeInteger(numInts);        //creates a random integer of n digits
            huge2 = new HugeInteger(numInts);        //creates a random integer of n digits
            startTime = System.currentTimeMillis();
            for (int numRun = 0; numRun < MAXRUN; numRun++) {
                huge3 = huge1.add(huge2);
            }
            endTime = System.currentTimeMillis();
            runTime += (double) (endTime - startTime) / ((double) MAXRUN);
        }
        runTime = runTime / ((double) MAXNUMINTS);

        System.out.println("time to add is: " + runTime);

        for (int numInts = 1; numInts < MAXNUMINTS; numInts++) {
            huge1 = new HugeInteger(numInts);        //creates a random integer of n digits
            huge2 = new HugeInteger(numInts);        //creates a random integer of n digits
            startTime = System.currentTimeMillis();
            for (int numRun = 0; numRun < MAXRUN; numRun++) {
                huge3 = huge1.subtract(huge2);
            }
            endTime = System.currentTimeMillis();
            runTime += (double) (endTime - startTime) / ((double) MAXRUN);
        }
        runTime = runTime / ((double) MAXNUMINTS);

        System.out.println("time to subtract is: " + runTime);

        for (int numInts = 1; numInts < MAXNUMINTS; numInts++) {
            huge1 = new HugeInteger(numInts);        //creates a random integer of n digits
            huge2 = new HugeInteger(numInts);        //creates a random integer of n digits
            startTime = System.currentTimeMillis();
            for (int numRun = 0; numRun < MAXRUN; numRun++) {
                huge1.compareTo(huge2);
            }
            endTime = System.currentTimeMillis();
            runTime += (double) (endTime - startTime) / ((double) MAXRUN);
        }
        runTime = runTime / ((double) MAXNUMINTS);

        System.out.println("time to compare is: " + runTime);

        for (int numInts = 1; numInts < MAXNUMINTS; numInts++) {
            huge1 = new HugeInteger(numInts);        //creates a random integer of n digits
            huge2 = new HugeInteger(numInts);        //creates a random integer of n digits
            startTime = System.currentTimeMillis();
            for (int numRun = 0; numRun < MAXRUN; numRun++) {
                huge3 = huge1.multiply(huge2);
            }
            endTime = System.currentTimeMillis();
            runTime += (double) (endTime - startTime) / ((double) MAXRUN);
        }
        runTime = runTime / ((double) MAXNUMINTS);

        System.out.println("time to multiply is: " + runTime);
    }
}
