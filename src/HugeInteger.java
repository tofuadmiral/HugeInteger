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
                int digitdiff= this.array.length-h.array.length;
                HugeInteger difference= new HugeInteger(this.array.length-1);
                difference.array[0]=10;
                for(int i=h.array.length-1;i>=1;i--){
                    // this means that we have to borrow
                    if(h.array[i]>this.array[i+digitdiff]){

                        int counter=1;
                        // see how many digits we have to borrow from
                        while(this.array[i+digitdiff-counter]==0){

                            this.array[i+digitdiff-counter]=9; //After borrowing process this digit will ultimately become 9 (10, then 1  borrowed to lead to 9)
                            counter++;
                        }
                        this.array[i+digitdiff-counter]-=1;
                        // if we have an end borrow increase by ten
                        this.array[i+digitdiff]+=10;

                    }
                    // this means we don't need to borrow, so just subtract as with the digits
                    difference.array[i+digitdiff]=this.array[i+digitdiff]-h.array[i];
                }

                // go through digits that are left, and just read those into the new array
                for(int i=this.array.length-h.array.length;i>=1;i--){
                    difference.array[i]=this.array[i];
                }

                // count the leading zeros in the new array
                if(difference.array[1]==0)
                {
                    int zeros = 1;
                    while (difference.array[1 + zeros] == 1) {
                        zeros++; //Counts total zeros in most significant bit
                    }
                    // after updating how many leading zeros there are and then get rid of them
                    HugeInteger updatedDiffR = new HugeInteger(difference.toString());
                    return updatedDiffR;
                }
                return difference;
            }
            else{
                // this is subtraction where the first is smaller, so get's more negative
                int digitdiff= h.array.length-this.array.length;
                HugeInteger difference= new HugeInteger(h.array.length-1);
                difference.array[0]=11;
                for(int i=this.array.length-1;i>=1;i--){
                
                    if(this.array[i]>h.array[i+digitdiff]){
                        // this mean's that we need to borrow
                        int counter=1;{
                            // same as before, count number needed to implement
                            h.array[i+digitdiff-counter]=9;
                            counter++;
                        }
                        h.array[i+digitdiff-counter]-=1;
                        // last digit needs to be incremented if there is an end borroq
                        h.array[i+digitdiff]+=10;

                    }
                    difference.array[i+digitdiff]=h.array[i+digitdiff]-this.array[i]; //Normal no borrowing needed;
                }

                for(int i=h.array.length-this.array.length;i>=1;i--){
                    // go through the rest and just read them into the difference array
                    difference.array[i]=h.array[i];
                }
                //BLOCK for reducing zeros in most significant bit
                if(difference.array[1]==0){
                    int zeros = 1;
                    while (difference.array[1 + zeros] == 0) {
                        zeros++; //Counts total zeros in most significant bit
                    }
                    HugeInteger updatedDiffR = new HugeInteger(difference.toString());
                    return updatedDiffR;
                }
                return difference;
            }


        }
        else if(this.array[0]==10 && h.array[0]==11){
            // positive minus negative so just add them
            h.array[0]=10;
            return this.add(h);
        }
        else if(this.array[0]==11 && h.array[0]==10){
            // negative minus positive so just adding negatives
            h.array[0]=11;
            return this.add(h);
        }
        else {
            // subtracting two negatives, so we switch order and then continue as normal
            h.array[0]=10;
            this.array[0]=10;
            return h.subtract(this);
        }
    }

    public HugeInteger multiply(HugeInteger h)
    {
        // get num digits i.e. log10(integer1)+log10(integer2)+1
        int digits=1;
        int sigbit1=this.array[1]+1;
        int sigbit2=h.array[1]+1;
        digits+= ( Math.log10(sigbit1)+ (this.array.length-2) + Math.log10(sigbit2) + (h.array.length-2) );
        HugeInteger product = new HugeInteger(digits);

        if(this.array[0]==h.array[0]){
            // if both signs the same, then that means it's a positive number +*+ or -*- = +
            product.array[0]=10;
        }
        else {
            // different signs means that the product must be negative
            product.array[0] = 11;
        }

        // intialize the array
        for(int z=1;z<product.array.length;z++)
            product.array[z]=0;

        HugeInteger biggernumber = this.compareTo(h)>=0 ? this : h;
        HugeInteger smallernumber = this.compareTo(h)>=0 ? h : this;

        // go through smaller number, and multiply by each digit, starting from least significant
        for(int i = smallernumber.array.length-1; i>=1;i--){

            // now let's append at k
            int k = i + product.array.length - smallernumber.array.length;
            for (int j = biggernumber.array.length - 1; j >= 1; j--) {
                int val = smallernumber.array[i] * biggernumber.array[j];
                product.array[k] += val % 10;
                val = val / 10;
                product.array[k - 1] += val;
                k--;
            }
        }
        for(int m=product.array.length-1;m>=1;m--){
            // this means we have to carry the value
            if(product.array[m]>9){
                int carry= product.array[m];
                product.array[m]=product.array[m]%10; // mod by ten to get how much we need to carry
                product.array[m-1]+=(carry/10);
            }
        }
        if(product.array[1]==0){
            int zeros = 1;
            // just like before, want to get rid of leading zeros
            // our toString will take care of this and so will our constructor if we do it properly
            while (product.array[1 + zeros] == 1) {
                zeros++;
            }
            HugeInteger updatedProd = new HugeInteger(product.toString());
            return updatedProd;
        }
        return product;
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

