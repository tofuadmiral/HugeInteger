/*
    Ahmed Fuad Ali
    L02
    January 29th, 2018
    alia78
    400075937
    Lab 1
 */

import java.util.Random;

// class to represent an integer that is larger than the type we can represent with our current capabilities in Java
// arbitrarily large i.e. as large as "n"
public class HugeInteger {

    // Private Instance fields_______________________________________________________________________________________
    private int values[];
    private int numDigits;
    private  boolean isNegative;
    final static int MAXNUMINTS = 35;

    // _____________________________ CONSTRUCTORS _____________________________

    public HugeInteger(String val) throws IllegalArgumentException{
        // Create an integer that has the same value as the string that the user inputs
        // val string has an optional minus sign at the beginning and other than that all characters are digits

        // Check if it's negative
        if ((int)val.charAt(0) == (int)'-') {
            isNegative = true; // set to a negative integer
        }
        else {
            isNegative = false;
        } // end else

        // Find how many digits long the number is
        numDigits = isNegative ? val.length()-1:val.length(); // if negative, numDigits is less than the length
        values = new int[numDigits];

        // Initialize the new array of digits
        short digits[] = new short[numDigits]; // initialize the new array

        // read the string into the digits of the array
        int n = isNegative ? 1:0; // if it's negative, skip the first element

        // iterate through the string, read most significant digit into first pos of array, etc.
        for (int i = 0; i < val.length(); i++){
            if ((int)val.charAt(i)<48 || (int)val.charAt(i)>57)
                throw new IllegalArgumentException("That isn't an integer. Try again next time");
            this.values[i] = Integer.parseInt("" + val.charAt(val.length()-1-i)); // convert the character into a string and then an int
        } // end for

    } // end HugeInteger

    public HugeInteger(int n){
        // create a huge integer that is random and has n digits, n is from the user
        this.numDigits=n;
        values = new int[n];
        // Generate a random number and populate the array with them
        Random rand = new Random();
        for (int i = 0; i < n; i++){
            this.values[i] = rand.nextInt(10);
        } // end for
    } // end HugeInteger

    // _____________________________ OPERATIONS _____________________________

    public HugeInteger add(HugeInteger h){
        // initialize a new big integer
        HugeInteger addedInt = new HugeInteger(0);

        // case of both positive

        if(!this.isNegative && !h.isNegative){
            // check which integer is bigger
            int isthisbigger = 0;
            int smallerDigits = 0;
            int largerDigits = 0;
            int carry = 0;

            if (this.numDigits > h.numDigits){
                isthisbigger = 1;
                smallerDigits = h.numDigits;
                largerDigits = this.numDigits;
            } else if (this.numDigits<h.numDigits){
                isthisbigger = 0;
                smallerDigits = this.numDigits;
                largerDigits = h.numDigits;
            }
            else{
                isthisbigger = 2;
                smallerDigits = this.numDigits;
                largerDigits = this.numDigits;
            }

            // initialize array of new integer
            addedInt.values = new int[largerDigits+1]; // +1 in case there's an end carry
            addedInt.numDigits = largerDigits+1;


            // now iterate over the smaller number and add things up
            for (int i = 0; i <= smallerDigits-1; i++){
                addedInt.values[i] = (carry + this.values[i] + h.values[i])%10; // only add the ones digit
                carry = (carry + this.values[i] + h.values[i])/10; // update the carry for the next addition
            } // end for

            // now iterate over larger number and add, but to do that check which is larger
            if (isthisbigger ==1){
                for (int i = smallerDigits; i<=largerDigits-1; i++){
                    addedInt.values[i] = (carry + this.values[i])%10;
                    carry = (carry + this.values[i])/10; // update the carry for the next addition
                } // end for
            } else { // this means that h is bigger so iterate over that
                for (int i = smallerDigits; i<= largerDigits-1; i++){
                    addedInt.values[i] = (carry + h.values[i])%10;
                    carry = (carry + h.values[i])/10; // update the carry for the next addition
                }
            }

            // check for an end carry
            if (carry ==1){
                addedInt.values[largerDigits] = 1;
                addedInt.numDigits = largerDigits + 1;
            } else {
                addedInt.values[largerDigits] =0;
                addedInt.numDigits = largerDigits;
            }
        }

        // case this positive h negative

        if (!this.isNegative && h.isNegative){


        }

        return addedInt;

    } // end add

    public HugeInteger subtract(HugeInteger h){
        // return a huge integer that is the subtraction of this and h

        // case this isn't negative and the other is, therefore we're adding the second

        if(!this.isNegative && h.isNegative){
            h.isNegative = false;
            return this.add(h);
        }

        // case this is negative, other is too
        // thus effectively taking second number and subtracting from it THIS

        if(this.isNegative && h.isNegative){
            return h.subtract(this);
        }

        // case this is negative, and other isn't
        // i.e we're effectively adding them tgt

        if(this.isNegative && !h.isNegative){
            HugeInteger temp = new HugeInteger();
            temp = this;
            temp.isNegative = false;
            // since taking more away from negative
            temp = temp.add(h);
            temp.isNegative = true;
            return temp;
        }

        return new HugeInteger(3);


    } // end subtract()

    public HugeInteger multiply(HugeInteger h){
        // Multiply two huge integers and return a huge integer
        return new HugeInteger(3);
    } // end multiply

    public int compareTo(HugeInteger h){
        // compare two huge integers, this and h, 0 means equal, 1 means this is larger, -1 means h is larger
        if(this.numDigits>h.numDigits) {
            if(this.isNegative){
                // then this must be smaller bc negative and more digits so more negative either way
                return -1;
            }
            else{
                // must be bigger bc more digits and not negative
                return 1;
            }
        }
        else if(this.numDigits<h.numDigits){
            // that means h has more digits
            if (h.isNegative){
                return 1; // more digits and negative so must be smaller
            }
            else{
                return -1; // h more digits but not negative so must be larger
            }
        }
        else{
            // equal digits
            if(h.isNegative && !this.isNegative){
                // same digits but h is negative therefore this bigger
                return 1;
            }
            else if (!h.isNegative && this.isNegative) {
                // same digits but this is negative so H must be smaller
                return -1;
            }
        }

    } // end compareTo

    public String toString(){
        // make this a string
        String hugeIntStr = "";
        // iterate backwards through the list
        int i = (this.numDigits-1); // start at the end
        while(values[i] == 0){ // check for leading zeros
            i--; // keep going through until you're not at a zero
        } // end while
        while(i>= 0){
            hugeIntStr = hugeIntStr + Integer.toString(this.values[i]);
            i--;
        } // end while
        return hugeIntStr;
    } // end toString
}

