public class jkEasyRandomGen {

    static void jkFillTheArrOfGen(int[] inputArray, int bottom, int top, int isSeed) {

        //------------------------------------------------------------------------------ mini random seed generator

        int v = (int) System.currentTimeMillis(); // seed value
        int switcher = v % 2;

        isSeed += 4987;
        isSeed = (isSeed ^ (isSeed >> 31)) - (isSeed >> 31);//--------------------------------------------------abs

        v = v / 100 * v * 1234987 % 100000 + switcher;
        v = ((v ^ (v >> 31)) - (v >> 31));

        //System.out.println("the switcher: " + switcher);
        //System.out.println("the gen seed v: " + v);

        //------------------------------------------------------------------------------/mini random seed generator

        int k = 0;
        int b = 0;

        if (isSeed != 4987) {

            k = isSeed % 256;
            b = isSeed % 1024 + switcher + 1;
            v = isSeed % 127;

        } else {

            k = (v + 1) % 256;
            b = (v % (k + 1)) * 349 + switcher + 9;

        }

        System.out.println("seed k: " + k);
        System.out.println("seed b: " + b);
        System.out.println("seed v: " + v);

        int arrLen = inputArray.length;
        int firstElem = ((k * v * v + b) % arrLen);
        firstElem = (firstElem ^ (firstElem >> 31)) - (firstElem >> 31); //----------------------------------abs

        firstElem = (firstElem * v) & top;

        if (firstElem < bottom) firstElem = bottom + (k % 10);

        inputArray[0] = firstElem;


        int i = 1;
        double e = 0;
        int element = 0;
        int memo = 0;


        while (i < arrLen) {


            if ((i & 1) == 1) {

                int j = 0;

                e = (double) i;
                e = 4. / (8. * e + 1.) - 2. / (8. * e + 4.) - 1. / (8. * e + 5.) - 1. / (8. * e + 6.);
                memo = ((int) (e * k * (k + j) * 100000 + b));

                if (i % ((b % 10) + 1) == 0) memo = memo ^ (1 << (i % 15));


                if (memo > top + 1) memo &= top;


                inputArray[i] = memo;


            } else {

                memo = (k * inputArray[i - 1] + v) % b;
                memo = (memo ^ (memo >> 31)) - (memo >> 31);

                if (i % ((b % 10) + 2) == 0) memo = memo ^ (1 << (i % 18));


                if (memo > top + 1) memo %= top;


                inputArray[i] = memo;

            }


            i++;

        }


        //System.out.println("The array is full, the end of the bits method");

    }


    public static void main(String[] args) {

        int[] newArr = new int[55];

        jkFillTheArrOfGen(newArr, 48, 57, 0);

        jkPrintArray.jkPrintArrOneInt(newArr);

    }
}