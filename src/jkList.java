public class jkList<MEMORY_SIZE> {

    //--------------------------------------------------------------------------------------------final constants block

    static final int MEMORY_SIZE = 256; // capacity of the MEMORY

    private static final int[] MEMORY = new int[MEMORY_SIZE]; // initial MEMORY array

    static final int FIRST_MEMORY_ADDRESS = 0; // set the number on the MEMORY

    static final int NULL_MEMORY_FILLER = -7; // set the filler for the empty MEMORY

    static final int OUT_OF_MEMORY_ADDRESS = MEMORY_SIZE + 1;


    //private static final int DEFAULT_LIST_CAPACITY = 0; // default initial capacity

    private int listSize; // the size of the list (the number of it elements)

    //-------------------------------------------------------------------------------------------/final constants block








    //------------------------------------------------------------------------------------------------create a new list

    /*
    The null(head) node address is the name of the list
    The head node stores information about the number of nodes in the list
     */

    static void createANewList() {

        totalClearMemory();     //  memory preparation

        //----------------------------------------------------------------------------------head node

        MEMORY[FIRST_MEMORY_ADDRESS] = 0;      // add initial nodes amount of the list to the head node

        /*
        The address of the head node must get the address from the method issuing free memory
         */

        MEMORY[FIRST_MEMORY_ADDRESS + 1] = getFreeMemoryAddress();     // add the next address to the head node
        System.out.println("::the head node is created::");

        //---------------------------------------------------------------------------------/head node

    }

    //-----------------------------------------------------------------------------------------------/create a new list








    //-------------------------------------------------------------------------------------add a First Node To The List

    static void addFirstNodeToTheList() {

        totalClearMemory();     //  memory preparation

        //----------------------------------------------------------------------------------head node

        MEMORY[FIRST_MEMORY_ADDRESS] = 0;      // add initial nodes amount of the list to the head node
        MEMORY[FIRST_MEMORY_ADDRESS + 1] = getFreeMemoryAddress();     // add the next address to the head node
        System.out.println("::the head node is created::");

        //---------------------------------------------------------------------------------/head node

    }

    //------------------------------------------------------------------------------------/add a First Node To The List








    //-------------------------------------------------------------------------------------------free address generator

    static int getFreeMemoryAddress() {

        int k = 0; // the var of the generation result
        boolean isSecondCellFree = false; //  check for the second cell free space


        do {

            //-----------------------------------------------------------mini random seed generator

            long v = System.currentTimeMillis(); // seed value
            long switcher = v % 2;

            int isSeed = (int) v % 1000;
            isSeed = (isSeed ^ (isSeed >> 31)) - (isSeed >> 31); // abs

            v = v / 100 * v * 1234987 % 100000 + switcher;
            v = ((v ^ (v >> 31)) - (v >> 31));

            //System.out.println("the switcher: " + switcher);
            //System.out.println("the gen seed v: " + v);

            //----------------------------------------------------------/mini random seed generator

            k = ((int) v + (int) switcher) % MEMORY_SIZE;

            if ((k & 1) == 1) k--; //   select only even numbers

            if (MEMORY[k + 1] == NULL_MEMORY_FILLER) {

                isSecondCellFree = true; //  check for the second cell free space

            }

        } while (MEMORY[k] != NULL_MEMORY_FILLER && isSecondCellFree && k != 0 && k != 1);


        return k;

    }

    //-------------------------------------------------------------------------------------/free address generator






    //------------------------------------------------------------------------------------------clear all of the memory

    static void totalClearMemory() {

        int memoryLen = MEMORY.length;


        for (int i = 0; i < memoryLen; i++) {

            MEMORY[i] = NULL_MEMORY_FILLER;

        }


    }

    //-----------------------------------------------------------------------------------------/clear all of the memory










        //---------------------------------------------------------------------------------------------------------checkers

    public static void main(String[] args) {

        totalClearMemory();

        System.out.println("The MEMORY array:");
        System.out.print("--: ");
        jkPrintArray.jkPrintArrOneInt(MEMORY);

        System.out.print("The free gen addres (== -7) --: ");
        System.out.println(getFreeMemoryAddress());

        /*
        int[] testArray = new int[25];


        for (int i = 0; i < testArray.length; i++) {

            testArray[i] = getFreeMemoryAddress();

        }


        jkPrintArray.jkPrintArrOneInt(testArray);
        */

        System.out.println();
        createANewList();

        System.out.println("The MEMORY array with the head node:");
        System.out.print("--: ");
        jkPrintArray.jkPrintArrOneInt(MEMORY);


    }


    //--------------------------------------------------------------------------------------------------------/checkers
}
