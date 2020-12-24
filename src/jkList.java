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

    - Вывод всей основной информации (, 4)
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


            case 4: {

                System.out.print(MEMORY[printListName] + " nodes: ");  // print the amount of the list nodes

                int firstLinkIs = MEMORY[printListName + 1];  // get the link from the list head


                for (int printCount = printListName; printCount < printArrLen; printCount++) {

                    System.out.printf("[%d] [%d] %d -> ", printCount, firstLinkIs, MEMORY[firstLinkIs]); // full out
                    // System.out.printf("[%d] %d -> ", printCount, MEMORY[firstLinkIs]); // print linked values here

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


        for (; i < nodeId; i++) {

            if (nextAddress == OUT_OF_MEMORY_ADDRESS) break;

            //System.out.println(i);

            nextAddress = MEMORY[nextAddress + 1];

        }


        if (nextAddress == OUT_OF_MEMORY_ADDRESS) return NULL_MEMORY_FILLER;
        else return MEMORY[nextAddress];

    }


    //--the method helps to get the value of the list by its ordinal number--------------------------------------------

    static int getTheNodeIdLink(int listName, int nodeId) {

        int nextAddress = MEMORY[listName + 1];
        int i = 0;


        for (; i < nodeId; i++) {

            if (nextAddress == OUT_OF_MEMORY_ADDRESS) break;

            //System.out.println(i);

            nextAddress = MEMORY[nextAddress + 1];

        }


        if (nextAddress == OUT_OF_MEMORY_ADDRESS) return OUT_OF_MEMORY_ADDRESS;
        else return nextAddress;

    }


    //-----------------------------------------------------------------------------------------------------------------

    //--the method helps to change the value of the list by its ordinal number-----------------------------------------

    static void changeTheNodeIdVal(int listName, int nodeId, int newValue) {


        int nextAddress = MEMORY[listName + 1];
        int i = 0;


        for (; i < nodeId; i++) {

            if (nextAddress == OUT_OF_MEMORY_ADDRESS) break;

            //System.out.println(i);

            nextAddress = MEMORY[nextAddress + 1];

        }


        if (nextAddress == OUT_OF_MEMORY_ADDRESS) System.out.println(NULL_MEMORY_FILLER +
                " some value error here (changeTheNodeIdVal - method)");
        else MEMORY[nextAddress] = newValue;


    }

    //---------------------------------------------------------------------------------------------------filler methods

    /*
    11) Создание списка максимальной длины, заполненным значениями (the length may be )

    - константной последовательности -- newListConstSequence

    - арифметической прогрессии -- newListArithmeticSequence

    - периодической последовательности -- newListPeriodicSequence

     */

    static void newListConstSequence(int constValue, int size) {

        createANewList();

        for (int i = 0; i < size; i++) {

            addNextValToTheList(constValue);

        }

    }

    static void newListArithmeticSequence(int firstElement, int a, int d, int size) {

        // a(n) = a(n - 1) + d

        createANewList();
        // TODO: do not forget for myself that to finish it, I will need the getTheNodeIdVal - method here
        // int elementToAdding = 0;

        addNextValToTheList(firstElement);


        for (int i = 1; i < size; i++) {

            addNextValToTheList(a * (firstElement - 1) + d);
            firstElement = getTheNodeIdVal(listName, i);

            //System.out.print(getTheNodeIdVal(listName, i) + ", "); // print the list here

        }

    }


    static void newListPeriodicSequence(int k, int period, int size) {

        // a(n + p) = a(n)

        createANewList();

        //System.out.print(getTheNodeIdVal(listName, i) + ", "); // print the list here

        int i = 1;
        int temp = k;

        //addNextValToTheList(k);


        while (i < size + 1) {

            addNextValToTheList(k);

            k++;

            if (i % period == 0) k = temp;

            i++;

        }


    }

    //--------------------------------------------------------------------------------------------------/filler methods



    /*
    1) Написать метод, который помещает во вспомогательный массив адреса узлов, расположенных через каждые N / 4 узлов,
    где N – общее число узлов списка. Например, если в списке 100 узлов, тогда во вспомогательном массиве будут
    храниться адреса 25, 50, 75 и 100 узлов. Если в списке 77 узлов, тогда во вспомогательном массиве будут храниться
    адреса 19, 38, 57 и 76 узлов.
    */

    static void toFillTheReferenceArray(int listNameHere) {

        int firstIdToAAdding = MEMORY[listNameHere] / REFERENCE_ARRAY_CAPACITY - 1;
        int step = firstIdToAAdding + 1;

        System.out.println("first ID to adding is " + (firstIdToAAdding));

        // TODO: to create the getMeTheLinkFromListByListId - method here

        int firstReferAddress = getTheNodeIdLink(listNameHere, firstIdToAAdding);

        System.out.println("first ID link is " + firstReferAddress);


        for (int referArrayIncrement = 0;
             referArrayIncrement < REFERENCE_ARRAY_CAPACITY;
             referArrayIncrement++) {

            referenceArray[referArrayIncrement] = getTheNodeIdLink(listNameHere, firstIdToAAdding); // address search

            //referenceArray[referArrayIncrement] = firstIdToAAdding;

            firstIdToAAdding += step;

        }

    }

    //----------------------------------------------------------------------------------------------swap the list nodes

    /*
    the method exchanges the values of the specified nodes of the specified list by node id
    */

    static void swapListNodes(int listNameHere, int firstNodeId, int secondNodeId) {

        int valueBuffer;

        //addressBuffer = getTheNodeIdLink(listNameHere, firstNodeId); not need

        valueBuffer = getTheNodeIdVal(listNameHere, firstNodeId);

        //TODO: there are the setNodeValuesById for realisation

        setNodeValuesById(listNameHere, firstNodeId, getTheNodeIdVal(listNameHere, secondNodeId));
        setNodeValuesById(listNameHere, secondNodeId, valueBuffer);


    }

    //---------------------------------------------------------------------------------------------/swap the list nodes

    //---------------------------------------------------------------------------------------------sort the list values

    static void sortListValues(int listNameHere) {

    /*
    There is the easy bubble sort method for the some list.
    The first loop shrinks the array fragment each pass, because the second loop always
    puts at the end fragment maximum element (the little optimization here)
    */

        int listLength = MEMORY[listNameHere];
        int firstVal, secondVal;
        int tempVal = 0;

        for (int i = listLength - 1; i > 0; i--) {

            for (int j = 0; j < i; j++) {



            /*at this point we compare the elements in pairs, if they are in the wrong order,
            then we swap them (TODO: maybe I can use the list swap method as the option here)
            */
                firstVal = getTheNodeIdVal(listNameHere, j);
                secondVal = getTheNodeIdVal(listNameHere, (j + 1));

                if (firstVal > secondVal) {

                    tempVal = firstVal;
                    setNodeValuesById(listNameHere, j, secondVal);
                    setNodeValuesById(listNameHere, (j + 1), tempVal);

                }
            }
        }
    }


    //--------------------------------------------------------------------------------------------/sort the list values


    //--------------------------------------------------------------------------------------------set Node Values By Id

    static void setNodeValuesById(int listNameHere, int nodeIdHere, int newNodeValue) {

        int nextAddress = MEMORY[listNameHere + 1]; // set pointer to the head position


        for (int i = 0; i < nodeIdHere; i++) {

            if (nextAddress == OUT_OF_MEMORY_ADDRESS) {

                System.out.println("::we have reached the end of the list here::");
                break;
            }

            //System.out.println(i);

            nextAddress = MEMORY[nextAddress + 1];

        }

        System.out.println("the nextAddress to value set is: " + nextAddress);

        MEMORY[nextAddress] = newNodeValue;

    }


    //-------------------------------------------------------------------------------------------/set Node Values By Id

    //--------------------------------------------------------------------------------------------search by brute force


    static int simpleSearchValueAddress(int listNameHere, int startAddress, int endAddress, int searchValue) {

        System.out.printf("\n::input start = %d, input end = %d, value to search = %d, list name = %d::\n",
                startAddress, endAddress, searchValue, listNameHere);

        //TODO: do not apply this method yet

        int firstAddress = startAddress;

        int firstValByFirstAddress = MEMORY[firstAddress];

        int firstNextLinkAddress = startAddress + 1; // значение для перехода к след узлу


        while (MEMORY[firstAddress] != OUT_OF_MEMORY_ADDRESS) {

            System.out.println("firstAddress (simple method): " + firstAddress);

            firstAddress = MEMORY[firstAddress + 1];

            if (searchValue == MEMORY[firstAddress]) { // если значение найдено, то выход

                firstValByFirstAddress = MEMORY[firstAddress];

                break;

            }

            if (firstAddress == endAddress) {

                System.out.println("the end address in this place");

                if (firstValByFirstAddress != searchValue) {

                    System.out.println("the search value is not find");

                    //the search value not within the search range, assign the address out of memory
                    firstAddress = OUT_OF_MEMORY_ADDRESS;
                    firstValByFirstAddress = NULL_MEMORY_FILLER;

                }

                break; // the end address in this place

            }

        }

        /*
        for (; i < MEMORY[listNameHere] - 1; i++) {

            if (nextAddress == endAddress) break;
            if (nextAddress > OUT_OF_MEMORY_ADDRESS - 1) break;
            if (MEMORY[nextAddress] == searchValue) break;

            //System.out.println(i);

            nextAddress = MEMORY[nextAddress + 1];

        }
         */

        /*
        //--------------------------------------------------------------------------check out
        System.out.println("\nThe firstAddress__: " + firstAddress);
        System.out.println("The firstValByFirstAddress: " + firstValByFirstAddress);
        System.out.println("The firstNextLinkAddress: " + firstNextLinkAddress);
        //-------------------------------------------------------------------------/check out
        */

        return firstAddress; // return 257 if the value not find here in the range (if memory cap = 256)

    }

    static void simpleSearchValueById(int listNameHere, int startID, int endID, int searchValue) {

        //TODO: do not apply this method yet

        // use there the list Id

        int nextAddress = MEMORY[listNameHere];

        int i = 0;


        while (i < 127) {

            nextAddress = MEMORY[nextAddress + 1];
            i++;

        }


    }

    //-------------------------------------------------------------------------------------------/search by brute force

    //---------------------------------------------------------------------------------quasi-binary search of the value

    static int quasiBinSearchOfValue(int listNameHere, int searchValue) {


        int halfOfTheRefArray; // start search position
        int outListAddress = OUT_OF_MEMORY_ADDRESS; // default value is over the MEMORY


        if ((REFERENCE_ARRAY_CAPACITY & 1) == 1) halfOfTheRefArray = (REFERENCE_ARRAY_CAPACITY / 2) + 1;
        else halfOfTheRefArray = (REFERENCE_ARRAY_CAPACITY / 2);
        // if ref capacity is not even then + 1 for centering of the half position

        halfOfTheRefArray--; //we remember that the ref array starts at the 0 position

        System.out.println("halfOfTheRefArray ID: " + halfOfTheRefArray);

        //-------------------------------------------------------------------------------the method core

        System.out.println("MEMORY[referenceArray[halfOfTheRefArray]] is: "
                + MEMORY[referenceArray[halfOfTheRefArray]]);

        System.out.println("MEMORY[referenceArray[halfOfTheRefArray] + 1] is: "
                + MEMORY[referenceArray[halfOfTheRefArray] + 1]);


        int value0 = (MEMORY[referenceArray[halfOfTheRefArray - 1]]);
        int value1 = (MEMORY[referenceArray[halfOfTheRefArray]]);
        int value2 = (MEMORY[referenceArray[halfOfTheRefArray + 1]]);
        int value3 = (MEMORY[referenceArray[halfOfTheRefArray + 2]]);

        int param0 = (MEMORY[referenceArray[halfOfTheRefArray - 1] + 1]);
        int param1 = (MEMORY[referenceArray[halfOfTheRefArray] + 1]);
        int param2 = (MEMORY[referenceArray[halfOfTheRefArray + 1] + 1]);
        int param3 = (MEMORY[referenceArray[halfOfTheRefArray + 2] + 1]);
        int param4 = OUT_OF_MEMORY_ADDRESS - 1; // the tail


        if (searchValue > value1) { // go to the ref arr center and start start branch 1 [1]

            if (searchValue < value2) {

                outListAddress = simpleSearchValueAddress(listNameHere, param1 , param2, searchValue); // [2]

            } else { // [3]

                if (searchValue < value3) { // [4]

                    outListAddress = simpleSearchValueAddress(listNameHere, param2, param3, searchValue); // [4]

                } else { // [5]

                    outListAddress = simpleSearchValueAddress(listNameHere, param3, param4, searchValue); // [5]

                }
            }

        } else { // start branch 2 [1']

            if (searchValue > value0) { // [2']

                outListAddress = simpleSearchValueAddress(listNameHere, param0, param1, searchValue); // [2']

            } else { // [3']

                // the value is out of range here
                outListAddress = OUT_OF_MEMORY_ADDRESS + 10;

            }


        }

        //------------------------------------------------------------------------------/the method core


        return outListAddress; // return -7 if the value not in the list

    }

    //--------------------------------------------------------------------------------/quasi-binary search of the value


    //---------------------------------------------------------------------------------------------------------checkers

    public static void main(String[] args) {

        totalClearMemory();

        System.out.println("The MEMORY array:");
        System.out.print("--: ");
        jkPrintArray.jkPrintArrOneInt(MEMORY);

        System.out.print("The free gen address (== -7) --: ");
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
        changeTheNodeIdVal(0, 3, 888);
        printList(0, 3);


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

        changeTheNodeIdVal(0, 7, 5555);
        printList(0, 3);


        longLine();
        shortLine();

        // Demo: Periodic sequence
        longLine();
        System.out.println("check the newListPeriodicSequence:");
        newListPeriodicSequence(1, 8, 127);
        printList(0, 3);


        System.out.println("check the newListArithmeticSequence:");
        newListArithmeticSequence(7, 1, 2, 127); // 127 elements
        printList(0, 4);

        shortLine();
        System.out.println("check the setNodeValuesById - method:");
        setNodeValuesById(0, 3, 6666);
        //setNodeAddressById(0, 3, 111); it is not possible
        printList(0, 4);

        shortLine();
        System.out.println("to node values swap use the swapListNodes - method(id 6 swap id 7): ");
        swapListNodes(0, 6, 7);
        printList(0, 4);

        // Demo: sort the list method
        shortLine();
        System.out.println("sort the list method: ");
        sortListValues(0);
        System.out.println("there is the sorted list:");
        printList(0, 4);

        // Demo: get the node id Link method
        shortLine();
        System.out.println("get the node id Link method: " + getTheNodeIdLink(0, 4));
        printList(0, 4);
        shortLine();

        // Demo: fill reference array method
        System.out.println("use the fill method now and print the result");
        toFillTheReferenceArray(0);
        System.out.println("TADAM, the reference address array of the sorted list is on the bottom:");
        jkPrintArray.jkPrintArrOneInt(referenceArray); // print the created ref array

        // Demo: check the simple search method
        longLine();

        System.out.println("check the brut force search method: ");

        totalClearMemory();
        newListArithmeticSequence(1, 2, 3, 15);
        changeTheNodeIdVal(0, 3, 777);

        System.out.println("the test list: ");

        printList(0, 4);
        System.out.println();
        printList(0, 1);

        sortListValues(0);

        shortLine();

        System.out.println("the sorted test list: ");

        printList(0, 4);
        System.out.println();
        printList(0, 1);

        toFillTheReferenceArray(0);
        System.out.println("this is the ref array of the sorted list:");
        jkPrintArray.jkPrintArrOneInt(referenceArray);

        int out = simpleSearchValueAddress(0, referenceArray[1], referenceArray[2], 511);
        System.out.println("\nthe find MEMORY address for val 511 is: " + out);
        System.out.println();

        // Demo: quasi-binary search method
        longLine();
        System.out.println("check the quasi-binary search method(we search the list address of the value \"1023\"): ");
        System.out.println("there are the sorted test array: ");
        printList(0, 4);
        System.out.println();
        printList(0, 1);

        toFillTheReferenceArray(0);
        System.out.println("this is the ref array of the sorted list:");
        jkPrintArray.jkPrintArrOneInt(referenceArray);
        System.out.println();

        System.out.println("and remember that we are looking for the value 1023 at the list MEMORY: ");
        System.out.println(quasiBinSearchOfValue(0, 1023));


    }


    //--------------------------------------------------------------------------------------------------------/checkers
}
