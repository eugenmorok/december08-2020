public class jkList {

    //--------------------------------------------------------------------------------------------constants block

    private static final int MEMORY_SIZE = 256; // capacity of the MEMORY

    private static final int[] MEMORY = new int[MEMORY_SIZE]; // initial MEMORY array

    private static final int FIRST_MEMORY_ADDRESS = 0; // set the number on the MEMORY

    private static final int NULL_MEMORY_FILLER = -7; // set the filler for the empty MEMORY

    private static final int OUT_OF_MEMORY_ADDRESS = MEMORY_SIZE + 1;

    private static final int REFERENCE_ARRAY_CAPACITY = 4; // array of the reference elements capacity

    //private static final int DEFAULT_LIST_CAPACITY = 0; // default initial capacity

    /*
    The name of the list is an int type variable that stores the address of the head node.
    When the list is deleted, a list name must be stored value over of memory.
    The max list capacity is 127 elements;
     */

    private static int[] referenceArray = new int[REFERENCE_ARRAY_CAPACITY];

    private static int listName = OUT_OF_MEMORY_ADDRESS; // the name of the list

    //-------------------------------------------------------------------------------------------/constants block


    //-----------------------------------------------------------mini random seed generator

    private static int miniRandomGen(int range) {


        long v = System.currentTimeMillis(); // seed value
        long switcher = v % 2;

        int isSeed = (int) v % 1000;
        isSeed = (isSeed ^ (isSeed >> 31)) - (isSeed >> 31); // abs

        v = v / 100 * v * 1234987 % 100000 + switcher;
        v = ((v ^ (v >> 31)) - (v >> 31)); // abs

        //System.out.println("the switcher: " + switcher);
        //System.out.println("the gen seed v: " + v);

        int k = ((int) v + (int) switcher) % range;

        return k;

    }

    //----------------------------------------------------------/mini random seed generator


    //------------------------------------------------------------------------------------------------create a new list

    /*
    The null(head) node address is the name of the list
    The head node stores information about the number of nodes in the list
     */

    private static void createANewList() {

        totalClearMemory();     //  memory preparation

        listName = FIRST_MEMORY_ADDRESS;

        //----------------------------------------------------------------------------------head node

        MEMORY[FIRST_MEMORY_ADDRESS] = 0;      // add initial nodes amount of the list to the head node

        /*
        The address of the head node must get the address from the method issuing free memory
         */

        MEMORY[FIRST_MEMORY_ADDRESS + 1] = OUT_OF_MEMORY_ADDRESS;     // add the next address to the head node
        System.out.println("::the head node is created::");

        //---------------------------------------------------------------------------------/head node

    }

    //-----------------------------------------------------------------------------------------------/create a new list


    //------------------------------------------------------------------------------------------last node search method

    static int lastNodeNextAddressSearch() {  // search end node address

        int lastNodeNextAddress = FIRST_MEMORY_ADDRESS + 2;


        for (int i = FIRST_MEMORY_ADDRESS; i < OUT_OF_MEMORY_ADDRESS - 1; i++) {

            if (MEMORY[i] == OUT_OF_MEMORY_ADDRESS) {

                lastNodeNextAddress = i;

                //System.out.println("gg" + lastNodeNextAddress);

                break;

            }

        }


        return lastNodeNextAddress;
    }

    //-----------------------------------------------------------------------------------------/last node search method

    /*
    3) добавление одного узла вручную
    - в конец -- addNextValToTheList
    - в начало
    - после N-ого узла
    - после последнего добавленного узла
    */

    //-------------------------------------------------------------------------------------add a next Node To The List

    static void addNextValToTheList(int valueToAddToTheList) { // adding a node to the end of the list


        //----------------------------------------------------------------------------------next node

        int newAddressToTheNextNode = getFreeMemoryAddress();
        int lastNodeAddress = lastNodeNextAddressSearch();

        if (MEMORY[FIRST_MEMORY_ADDRESS + 1] == OUT_OF_MEMORY_ADDRESS) {       // case when the list is empty

            // adding first here

            MEMORY[FIRST_MEMORY_ADDRESS + 1] = newAddressToTheNextNode;  // add the the next link to the prev node
            MEMORY[newAddressToTheNextNode] = valueToAddToTheList;   // add the new value for new node
            MEMORY[newAddressToTheNextNode + 1] = OUT_OF_MEMORY_ADDRESS;  // add the stop link to the new node

            System.out.println("::the first node is created::");

        } else {

            // looking for the last node address
            // System.out.println("no first, next link on the address: " + lastNodeAddress);


            MEMORY[lastNodeAddress] = newAddressToTheNextNode;  // add the the next link to the prev node
            MEMORY[newAddressToTheNextNode] = valueToAddToTheList;   // add the new value for new node
            MEMORY[newAddressToTheNextNode + 1] = OUT_OF_MEMORY_ADDRESS;  // add the stop link to the new node

            //System.out.println("::the next node is created::");

        }

        MEMORY[FIRST_MEMORY_ADDRESS] = MEMORY[FIRST_MEMORY_ADDRESS] + 1;  // iterate the list length


        //---------------------------------------------------------------------------------/next node

    }

    //------------------------------------------------------------------------------------/add a next Node To The List


    //-------------------------------------------------------------------------------------------free address generator

    private static int getFreeMemoryAddress() {

        int k = 0; // the var of the generation result
        boolean isSecondCellFree = false; //  check for the second cell free space

        /*
        do {

            k = miniRandomGen(MEMORY_SIZE); //  used generator here

            if ((k & 1) == 1) k--; //  select only even numbers

            if (MEMORY[k + 1] == NULL_MEMORY_FILLER) {

                isSecondCellFree = true; //  check for the second cell free space

            }

        } while (MEMORY[k] == NULL_MEMORY_FILLER && isSecondCellFree && k != 0 && k != 1);
       */

        while (true) {

            k = miniRandomGen(MEMORY_SIZE);

            if ((k & 1) == 1) k--; //  select only even numbers
            if (MEMORY[k + 1] == NULL_MEMORY_FILLER) isSecondCellFree = true; //  check for the second cell free space

            if (MEMORY[k] == NULL_MEMORY_FILLER && isSecondCellFree && k != 0 && k != 1) break;

        }


        //System.out.println("gens k " + k);
        return k;

    }

    //-------------------------------------------------------------------------------------/free address generator


    //------------------------------------------------------------------------------------------clear all of the memory

    private static void totalClearMemory() {

        int memoryLen = MEMORY.length;


        for (int i = 0; i < memoryLen; i++) {

            MEMORY[i] = NULL_MEMORY_FILLER;

        }


    }

    //-----------------------------------------------------------------------------------------/clear all of the memory


    /*
    1) удаление тела списка. Нужно вычистить всю память от узлов, имя списка должно указывать на адрес,
    которого нет в памяти.
     */

    static void clearListBody() {

        totalClearMemory();
        createANewList();

    }

    //-------------------------------------------------------------------------------------------------

    static void longLine() {  // print a long long line here

        System.out.println();


        for (int i = 0; i < 250; i++) {

            System.out.print("-");

        }

        System.out.println();

    }

    static void shortLine() {  // print a short line here

        System.out.println();


        for (int i = 0; i < 50; i++) {

            System.out.print("-");

        }

        System.out.println();

    }

    /*
    2) Вывод списка должен быть в виде:

    - Полный вывод. Выводится содержимое всей памяти.(,1)

    - Вывод списка значений и количество всех узлов. Выводится только список в виде(,2)
    6 nodes: 3 -> 32 ->  55 ->  7 ->  21 ->  88 -> null

    - Вывод списка значений, и номеров узлов. Выводятся узлы в виде(,3)
    [1] 3 -> [2] 32 ->  [3] 55 ->  [4] 7 ->  [5] 21 ->  [6] 88 -> null
     */

    static void printList(int printListName, int option) {

        int printArrLen = MEMORY.length;

        switch (option) {

            case 1: {


                for (int printCount = printListName; printCount < printArrLen; printCount++) {

                    System.out.print(MEMORY[printCount]);
                    if (printCount != printArrLen - 1) System.out.print(", ");

                }


                System.out.println();

                break;

            }

            case 2: {

                System.out.print(MEMORY[printListName] + " nodes: ");  // print the amount of the list nodes

                int firstLinkIs = MEMORY[printListName + 1];  // get the link from the list head


                for (int printCount = printListName; printCount < printArrLen; printCount++) {

                    System.out.print(MEMORY[firstLinkIs] + " -> "); // print linked values here

                    if (MEMORY[firstLinkIs + 1] == 257) break;  // check for the end value

                    else {

                        firstLinkIs = MEMORY[firstLinkIs + 1];

                    }

                }


                System.out.print("null");

                break;

            }

            case 3: {

                System.out.print(MEMORY[printListName] + " nodes: ");  // print the amount of the list nodes

                int firstLinkIs = MEMORY[printListName + 1];  // get the link from the list head


                for (int printCount = printListName; printCount < printArrLen; printCount++) {

                    // System.out.printf("[%d] %d -> ", firstLinkIs, MEMORY[firstLinkIs]); //if you want see the links
                    System.out.printf("[%d] %d -> ", printCount, MEMORY[firstLinkIs]); // print linked values here

                    if (MEMORY[firstLinkIs + 1] == 257) break;  // check for the end value

                    else {

                        firstLinkIs = MEMORY[firstLinkIs + 1];

                    }

                }


                System.out.print("null");

                break;

            }


            default: {

                System.out.println("printList - method: some error here");
            }


        }

    }

    //--the method helps to get the value of the list by its ordinal number--------------------------------------------

    static int getTheNodeIdVal(int listName, int nodeId) {

        int nextAddress = MEMORY[listName + 1];
        int i = 0;
        int cnt = 0;

        for(; i < nodeId; i++) {

            if (nextAddress == OUT_OF_MEMORY_ADDRESS) break;

            System.out.println(i);

            nextAddress = MEMORY[nextAddress + 1];

        }

        if (nextAddress == OUT_OF_MEMORY_ADDRESS) return NULL_MEMORY_FILLER;
        else return MEMORY[nextAddress];

    }

    //-----------------------------------------------------------------------------------------------------------------

    //--the method helps to change the value of the list by its ordinal number-----------------------------------------

    static void changeTheNodeIdVal(int listName, int nodeId, int newValue) {


        int nextAddress = MEMORY[listName + 1];
        int i = 0;


        while (i < nodeId) {

            nextAddress = MEMORY[nextAddress + 1];

            i += 2;

        }

        System.out.println(nextAddress);
        if (nextAddress == OUT_OF_MEMORY_ADDRESS) System.out.println(NULL_MEMORY_FILLER);
        else System.out.println(MEMORY[nextAddress]);


    }

    //----------------------------------------------------------------------------------------------------------------

    /*
    11) Создание списка максимальной длины, заполненным значениями

    - константной последовательности -- newListConstSequence

    - арифметической прогрессии

    - периодической последовательности

     */

    static void newListConstSequence(int constValue, int size) {

        createANewList();

        for (int i = 0; i < size; i++) {

            addNextValToTheList(constValue);

        }

    }

    static void newListArithmeticSequence(int firstElement, int d, int size) {

        // a(n) = a(n - 1) + d

        createANewList();
        // TODO: finish it, i need the getTheNodeIdVal - method here
        int elemenToAdding = 0;

        addNextValToTheList(firstElement);

        for (int i = 1; i < size; i++) {

            addNextValToTheList(MEMORY[i - 1] + d);

        }

    }


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

        System.out.println();
        System.out.println("The last Node Next Address Search:");
        System.out.print("--: ");
        System.out.println("1_ " + lastNodeNextAddressSearch());

        System.out.println();
        System.out.println("There are adding a first value to the list:");
        addNextValToTheList(7001);
        jkPrintArray.jkPrintArrOneInt(MEMORY);

        System.out.println();
        System.out.println("The last Node Next Address Search:");
        System.out.print("--: ");
        System.out.println("2_ " + lastNodeNextAddressSearch());

        System.out.println();
        System.out.println("There are adding a new value to the list:");
        addNextValToTheList(7002);
        jkPrintArray.jkPrintArrOneInt(MEMORY);
        System.out.println("2_ " + lastNodeNextAddressSearch());

        System.out.println();
        System.out.println("There are adding a new value to the list:");
        addNextValToTheList(7003);
        jkPrintArray.jkPrintArrOneInt(MEMORY);

        System.out.println();
        System.out.println("There are adding a new value to the list:");
        addNextValToTheList(7004);
        jkPrintArray.jkPrintArrOneInt(MEMORY);

        System.out.println();
        System.out.println("There are adding a new value to the list:");
        addNextValToTheList(7005);
        jkPrintArray.jkPrintArrOneInt(MEMORY);
        printList(0, 2);
        shortLine();
        printList(0, 3);
        System.out.println();
        System.out.println(getTheNodeIdVal(0, 6));
        //changeTheNodeIdVal(0, 3, 888);


        System.out.println();
        longLine();

        clearListBody();
        printList(0, 1);

        longLine();
        System.out.println("check the newListConstSequence:");
        newListConstSequence(13, 127);
        jkPrintArray.jkPrintArrOneInt(MEMORY);
        printList(0, 3);
        System.out.println();
        System.out.println(getTheNodeIdVal(0, 7));

        System.out.println(getTheNodeIdVal(0, 7));
        printList(0, 3);


    }


    //--------------------------------------------------------------------------------------------------------/checkers
}
