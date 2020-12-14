public class jkEasyRandomGen {

    static void jkFillTheArrOfGen(int[] inputArray, int bottom, int top, int isSeed) {

        //------------------------------------------------------------------------------ mini random seed generator

        int v = (int) System.currentTimeMillis(); // seed value
        int switcher = v % 2;
        v = v / 100 * v * 1234987 % 100000 + switcher;
        v = ((v ^ (v >> 31)) - (v >> 31));


        System.out.println("the switcher: " + switcher);
        System.out.println("the gen seed: " + v);

        //---------------------------------------------------------------------------- /mini random seed generator

        int k = 0;
        int b = 0;

        if (isSeed != 0) {
            k = isSeed % 256;
            b = isSeed / 128;
            v = isSeed % 100;
        } else {
            k = (v + 1) % 256;
            b = (v % k) * 349 + switcher;
        }

        System.out.println("seed k: " + k);
        System.out.println("seed b: " + b);
        System.out.println("yes seed v: " + v);

        int arrLen = inputArray.length;
        int firstElem = ((k * v * v + b) % arrLen);
        firstElem = (firstElem ^ (firstElem >> 31)) - (firstElem >> 31); //----------------------------------abs

        firstElem = (firstElem * v) & top;

        if (firstElem < bottom) firstElem = bottom + (k % 10);

        inputArray[0] = firstElem;


        int i = 1;
        int element = 0;
        int memo = 0;


        while (i < arrLen) {

            if ((i & 1) == 1) {

                element = ((inputArray[i - 1] * inputArray[i - 1] * k + b) / i * (b / k));
                element = (((element ^ (1 << i)) & ~(1 << (k / b))) % k + 3);

                if (element == inputArray[i - 1]) {

                    element = (((element ^ (1 << i)) & ~(1 << (k / b))) % v);

                }

                memo = (((element ^ (element >> 31)) - (element >> 31)) * 100) + bottom;

                if (memo == inputArray[i - 1]) {

                    memo = (((element ^ (1 << i)) & ~(1 << (k / b))) % k + 1);

                }

                memo = memo % top;

                if (memo < bottom) memo = bottom + (k % 10);

                inputArray[i] = memo;

            } else {

                element = ((inputArray[i - 1] * inputArray[i - 1] * k * 3 + b / 22));

                if (element == inputArray[i - 1]) {

                    element = (((element ^ (1 << i)) & ~(1 << (k / b))) % k + 1);

                }

                memo = (((element ^ (element >> 31)) - (element >> 31) * 2 / 3) % 100000);

                if (memo == inputArray[i - 1]) {

                    memo = (((element ^ (1 << i)) & ~(1 << (k / b))) % v);

                }

                memo = memo % top;

                if (memo < bottom) memo = bottom + (k % 10);

                inputArray[i] = memo;


            }

            //inputArray[0] = (i % ((v % 100) + 1) / 2);
            i++;

        }

        //System.out.println("The array is full, the end of the bits method");

    }


    public static void main(String[] args) {

        int[] newArr = new int[55];

        jkFillTheArrOfGen(newArr, 9, 99, 0);

        jkPrintArray.jkPrintArrOneInt(newArr);

    }
}