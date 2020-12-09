public class jkPrintArray {

    public  static void jkPrintArrOneInt(int[] inputArr) {

        //---------------------------- print elements of array

        int printArrLen = inputArr.length;

        for (int printCount = 0; printCount < printArrLen; printCount++) {
            System.out.print(inputArr[printCount]);
            if (printCount != printArrLen - 1) System.out.print(", ");
        }
        System.out.println();
        //---------------------------- /print elements of array

    }


    public  static void jkPrintArrOneLong(long[] inputArr) {

        //---------------------------- print elements of array

        int printArrLen = inputArr.length;

        for (int printCount = 0; printCount < printArrLen; printCount++) {
            System.out.print(inputArr[printCount]);
            if (printCount != printArrLen - 1) System.out.print(", ");
        }
        System.out.println();
        //---------------------------- /print elements of array

    }


    public  static void jkPrintArrTwoInt(int[][] inputArr, int rowLength) {

        int len = inputArr.length;

        int[][] theList = new int[len][2];

        String oneCell = "";


        //System.out.println("\nelem  idx   cnt");

        for (int st = 0; st < len; st++) {  // strings

            for (int rw = 0; rw < 2; rw++) {// rows

                oneCell = " " + inputArr[st][rw];
                System.out.print(oneCell); //outputs

                for (int k = 0; k < rowLength - oneCell.length(); k++) {

                    System.out.print(" ");

                }

            }
            System.out.println();// \n
        }

    }









}
