import java.util.Random;

public class HugeInteger{
    public int[] array;

    //CONSTRUCTOR 1: make a huge integer from a string
    public HugeInteger(String val){

        // check if it's negative first
        if(val.charAt(0)=='-'){

            int leadingzero=0;
            while(val.charAt(leadingzero+1)=='0'){
                // eliminate leading zeros
                leadingzero++;
            }
            array= new int[val.length()-leadingzero];
            array[0]=11;
            for(int i=1; i < val.length()-leadingzero; i++){
                array[i]=val.charAt(i+leadingzero)-48;
            }
        }
        else
        {
            int leadingzero=0;
            while(val.charAt(leadingzero)=='0'){
                // get rid of the leading zeros
                leadingzero++;
            }

            // now allocate space
            array= new int[val.length()+1-leadingzero];

            // by convention, 10 will be a positive number, 11 is a negative
            array[0]=10; // this means a positive number

            // now get the values and put into the array
            for (int i = 0; i < val.length()-leadingzero; i++){
                array[i+1]=val.charAt(i+leadingzero)-48;
            }
        }
    }


    // constructor 2: random number with n digits

    public HugeInteger(int n)
    {
        array= new int[n+1];
        array[0]=10;
        array[1] = new Random().nextInt(9) + 1;
        for(int i=2;i<n+1;i++)
        {
            array[i]= new Random().nextInt(10);
        }
    }

    //METHOD 1: ADD Huge Integers
    public HugeInteger add(HugeInteger h)
    {
        if((this.array[0]==10 && h.array[0]==10) || (this.array[0]==11 && h.array[0]==11)) //Use ADD method if same sign; Substract Otherwise
        {
            int hi_digits, lo_digits; //Determines digits of each HugeInt
            if (this.array.length >= h.array.length) //Case 'this' HugeInteger is Larger
            {
                hi_digits= this.array.length -1;
                lo_digits= h.array.length -1;
                HugeInteger sum = new HugeInteger(hi_digits + 1); //Extra digit allocated in case of final carryover
                int carryover=0,i; //Create variables for i to iterate and carryover to store varry over values
                for(i=lo_digits;i>=1;i--) //Iterates sum from least sig to most sig digit of smaller number
                {
                    carryover=this.array[i+(hi_digits-lo_digits)]+h.array[i]+carryover; //Stores sum, including carryover bit
                    sum.array[i+(hi_digits-lo_digits)+1]=carryover%10; //Stores ones digit result of sum
                    carryover/=10; //Stores tens digit of result of sum, if none then will = 0
                }
                for(i=hi_digits-lo_digits;i>=1;i--) //Iterates for number of digits large number has more than smaller #
                {
                    carryover=this.array[i]+carryover;
                    sum.array[i+1]=carryover%10;
                    carryover/=10;
                }
                sum.array[0]=this.array[0]; //Stores 11 if both neg or 10 if both pos
                if(carryover==0) //Reduce size of HugeInt array to fit sum
                {
                    HugeInteger newSum= new HugeInteger(hi_digits);
                    newSum.array[0]=sum.array[0];
                    for(i=1;i<=hi_digits;i++)
                    {
                        newSum.array[i]=sum.array[i+1];
                    }
                    return newSum;
                }
                sum.array[1]=carryover; //Sign equals the sign of the 2 numbers added together
                return sum;
            }
            else //Other case: integer 'h' is larger than 'this'. Same exact algorithm and implementation as above.
            {
                hi_digits= h.array.length -1;
                lo_digits=this.array.length -1;
                HugeInteger sum = new HugeInteger(hi_digits + 1);
                int carryover=0,i;
                for(i=lo_digits;i>=1;i--)
                {
                    carryover=h.array[i+(hi_digits-lo_digits)]+this.array[i]+carryover;
                    sum.array[i+(hi_digits-lo_digits)+1]=carryover%10;
                    carryover/=10;
                }
                for(i=hi_digits-lo_digits;i>=1;i--)
                {
                    carryover=h.array[i]+carryover;
                    sum.array[i+1]=carryover%10;
                    carryover/=10;
                }
                sum.array[1]=carryover;
                sum.array[0]=this.array[0];
                if(carryover==0)
                {
                    HugeInteger newSum= new HugeInteger(hi_digits);
                    newSum.array[0]=sum.array[0];
                    for(i=1;i<=hi_digits;i++)
                    {
                        newSum.array[i]=sum.array[i+1];
                    }
                    return newSum;
                }
                sum.array[1]=carryover;
                return sum;
            }

        }
        else //Case that one integer is postive and another negative
        {
            if(this.array[0]==11) //Case: this integer is negative
            {
                this.array[0]=10; //Change sign to positive and subtract
                return h.subtract(this);
            }
            else if(h.array[0]==11) //Case: h integer is negative
            {
                h.array[0]=10; //Change to positive and subtract
                return this.subtract(h);
            }
            else
                return this;
        }

    }

    //METHOD 2: SUBSTRACT HugeIntegers
    public HugeInteger subtract(HugeInteger h)
    {
        if(this.array[0]==10 && h.array[0]==10) //CASE 0: POS - POS
        {
            if(this.compareTo(h)==0){
                // if both same, return 0
                return new HugeInteger("0");
            }
            else if(this.compareTo(h)==1) {

                // normal subtraction
                int digitdiff= this.array.length-h.array.length; //Difference in number of digits between both numbers
                HugeInteger diffR= new HugeInteger(this.array.length-1); //Create new HugeInteger of equal size to this, adjust accordingly after
                diffR.array[0]=10; //Already know substraction will lead to a positive number
                for(int i=h.array.length-1;i>=1;i--) //h[i], this[i+digitdiff], diffR[i+digitdiff]
                {
                    if(h.array[i]>this.array[i+digitdiff])
                    {
                        //Borrowing Block, rearrange values to allow for substraction: End result: Modify more significant values
                        int counter=1; //Counts how many digits involved in borrowing process eg. 1001-2, 3 digits involved in borrowing, all 2 0's and the least sig 1
                        while(this.array[i+digitdiff-counter]==0)
                        {
                            this.array[i+digitdiff-counter]=9; //After borrowing process this digit will ultimately become 9 (10, then 1  borrowed to lead to 9)
                            counter++;
                        }
                        this.array[i+digitdiff-counter]-=1; //Substract 1 from non zero more significant digit
                        this.array[i+digitdiff]+=10; //Increase digit by 10 to allow for borrowing process to be complete

                    }
                    diffR.array[i+digitdiff]=this.array[i+digitdiff]-h.array[i]; //Normal no borrowing needed;
                }
                //For loop to iterate through remaining digits
                for(int i=this.array.length-h.array.length;i>=1;i--)
                {
                    diffR.array[i]=this.array[i];
                }

                //BLOCK for reducing zeros in most significant bit
                if(diffR.array[1]==0)
                {
                    int zeros = 1;
                    while (diffR.array[1 + zeros] == 1) {
                        zeros++; //Counts total zeros in most significant bit
                    }
                    HugeInteger updatedDiffR = new HugeInteger(diffR.toString());
                    return updatedDiffR;
                }
                return diffR;

            }
            else
            {
                //Negative/Reverse substraction
                int digitdiff= h.array.length-this.array.length;
                HugeInteger diffR= new HugeInteger(h.array.length-1);
                diffR.array[0]=11;
                for(int i=this.array.length-1;i>=1;i--){ //h[i], this[i+digitdiff], diffR[i+digitdiff]
                
                    if(this.array[i]>h.array[i+digitdiff]){
                    
                        //Borrowing Block, rearrange values to allow for substraction: End result: Modify more significant values
                        int counter=1; //Counts how many digits involved in borrowing process eg. 1001-2, 3 digits involved in borrowing, all 2 0's and the least sig 1
                        while(h.array[i+digitdiff-counter]==0)
                        {
                            h.array[i+digitdiff-counter]=9; //After borrowing process this digit will ultimately become 9 (10, then 1  borrowed to lead to 9)
                            counter++;
                        }
                        h.array[i+digitdiff-counter]-=1; //Substract 1 from non zero more significant digit
                        h.array[i+digitdiff]+=10; //Increase digit by 10 to allow for borrowing process to be complete

                    }
                    diffR.array[i+digitdiff]=h.array[i+digitdiff]-this.array[i]; //Normal no borrowing needed;
                }
                //For loop to iterate through remaining digits
                for(int i=h.array.length-this.array.length;i>=1;i--)
                {
                    diffR.array[i]=h.array[i];
                }
                //BLOCK for reducing zeros in most significant bit
                if(diffR.array[1]==0)
                {
                    int zeros = 1;
                    while (diffR.array[1 + zeros] == 0) {
                        zeros++; //Counts total zeros in most significant bit
                    }
                    HugeInteger updatedDiffR = new HugeInteger(diffR.toString());
                    return updatedDiffR;
                }
                return diffR;
            }


        }
        else if(this.array[0]==10 && h.array[0]==11) //CASE 1: POS - NEG
        {
            h.array[0]=10; //Converted to POS + POS
            return this.add(h);
        }
        else if(this.array[0]==11 && h.array[0]==10) //CASE 2: NEG - POS
        {
            h.array[0]=11; //Converted to NEG + NEG
            return this.add(h);
        }
        else //CASE 3: NEG - NEG
        {
            h.array[0]=10; //Converted to NEG + POS > Switch order of substraction to POS - POS
            this.array[0]=10;
            return h.subtract(this);
        }
    }

    public HugeInteger multiply(HugeInteger h)
    {
        //The following algorithm determines number of digits of product. Number of Digits for product of integers A and B: = log10(A)+log10(B)+1
        //Given this, cannot find exact size of product due to length of numbers. Therefore, both numbers will be rounded for purpose of estimation. Round up to extra array space incase
        // eg. if 1580x840 >> Round to 2000 and 1000 >> 2000x1000 >> 2*10^3 + 1*10^3 # Digits= log10(2)+3*log10(10) + log10(1)+3*log10(10) = log10(2) + 3 + log10(1) + 3
        int digits=1; //Use : Math.log10(x)
        int sigbit1=this.array[1]+1;
        int sigbit2=h.array[1]+1;
        //exponent is equal to length of each HugeInt-2
        digits+= ( Math.log10(sigbit1)+ (this.array.length-2) + Math.log10(sigbit2) + (h.array.length-2) );
        HugeInteger prodInt = new HugeInteger(digits);

        if(this.array[0]==h.array[0]){
            // if both signs the same, then that means it's a positive number +*+ or -*- = +
            prodInt.array[0]=10;
        }
        else {
            // different signs means that the product must be negative
            prodInt.array[0] = 11;
        }

        for(int z=1;z<prodInt.array.length;z++)
            prodInt.array[z]=0;
        //System.out.println(prodInt.toString()); //CHECK
        HugeInteger BigNum = this.compareTo(h)>=0 ? this : h;
        HugeInteger SmolNum = this.compareTo(h)>=0 ? h : this;
        //System.out.println("Smaller is " + SmolNum + " and bigger is " + BigNum); //CHECK

        //For loop multiplying smaller integer to larger integer from least significant bit to most significant bit
        for(int i = SmolNum.array.length-1; i>=1;i--){//Iterates for each digit of smaller number to multiply to bigger number

            // now let's append at k
            int k = i + prodInt.array.length - SmolNum.array.length;
            for (int j = BigNum.array.length - 1; j >= 1; j--) {
                int val = SmolNum.array[i] * BigNum.array[j]; 
                prodInt.array[k] += val % 10;
                val = val / 10;
                prodInt.array[k - 1] += val;
                k--;
            }
        }
        for(int m=prodInt.array.length-1;m>=1;m--)
        {
            if(prodInt.array[m]>9)
            {
                int carry= prodInt.array[m];
                prodInt.array[m]=prodInt.array[m]%10;
                prodInt.array[m-1]+=(carry/10);
            }
        }
        if(prodInt.array[1]==0){
            int zeros = 1;
            while (prodInt.array[1 + zeros] == 1) {
                zeros++; //Counts total zeros in most significant bit
            }
            HugeInteger updatedProd = new HugeInteger(prodInt.toString());
            return updatedProd;
        }
        return prodInt;
        //Within loop, multiply and add each segment into produced prodArray
        //After adding is complete, perform carryover for indexes exceeding size of 9 (eg. if one sum index holds 81, transfer 8 to next one, and then onwards..)
        //Final sum created >> Resize array to be appropriate size
        //Convert array to string
        //String to HugeInteger
    }


    public int compareTo(HugeInteger h)
    {
        int aisnegative=this.array[0];
        int bisnegative=h.array[0];
        int digA=this.array.length;
        int digB=h.array.length;
        // if this is smaller, these conditions must be met
        if((aisnegative==11 && bisnegative==10) || (aisnegative==11 && bisnegative==11 && digA>digB) || (aisnegative==10 && bisnegative==10 && digB>digA) ){

            return -1;
        }
        else if((aisnegative==10 && bisnegative==11) || (aisnegative==11 && bisnegative==11 && digB>digA) || (aisnegative==10 && bisnegative==10 && digA>digB) ){
            return 1;
        }
        else if((aisnegative==bisnegative) && this.toString()==h.toString()){
            return 0;
        }
        else{
            for(int i=1;i<digA;i++){
                if(this.array[i]>h.array[i]){
                    if(aisnegative==10){
                        return 1;
                    }
                    else{
                        return -1;
                    }
                }
                else if(this.array[i]<h.array[i]){
                    if(aisnegative==10){
                        return -1;
                    }
                    else{
                        return 1;
                    }
                }
            }
        }
        return 0;
    }


    // return a string representation of this HugeInteger
    public String toString(){
        String stringrepresentation="";
        if(this.array[0]==11)
            stringrepresentation+="-";
        for(int i=1;i<this.array.length;i++)
        {
            stringrepresentation+=this.array[i];
        }
        //System.out.println(stringrepresentation);
        return stringrepresentation;
    }
}

