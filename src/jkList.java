public class jkList {

    //--------------------------------------------------------------------------------------------variables block

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

    //-------------------------------------------------------------------------------------------/variables block


    //-----------------------------------------------------------mini random seed generator

    private static int miniRandomGen(int range) {

        /*
        the method takes as a basis the value in milliseconds from the current time and generates random values
        in the range from 0 to the input parameter
         */


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

        /*
        method creates a new empty list in the MEMORY
         */

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

        /*
        the method searches in the MEMORY area in the list and finds the cell value for the next address
        for adding to the list
        */

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
    3) adding one node manually
      - addNextValToTheList
    */

    //-------------------------------------------------------------------------------------add a next Node To The List

    static void addNextValToTheList(int valueToAddToTheList) { // adding a node to the end of the list

        /*
        method adds a new node to the list with a new value. A stub value is set in the cell of the next node
        address, which marks the end of the list
        */

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

    /*
    the method allocates an address from memory, checks that the address is not occupied by any value, and eventually
    returns this address as a result of the method
     */

    private static int getFreeMemoryAddress() {

        int k = 0; // the var of the generation result
        boolean isSecondCellFree = false; //  check for the second cell free space



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

    /*
    the method fills the entire area of the emulated memory with Null-values
    */

    private static void totalClearMemory() {

        int memoryLen = MEMORY.length;


        for (int i = 0; i < memoryLen; i++) {

            MEMORY[i] = NULL_MEMORY_FILLER;

        }


    }

    //-----------------------------------------------------------------------------------------/clear all of the memory


    /*
    1) deleting the body of the list. It is necessary to clear all memory from the nodes,
    the name of the list should point to the address,
    which is not in memory.
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
    2) The list output:

     - Full print. The contents of all memory are displayed. (, 1)

     - Displaying a list of values and the number of all nodes. Only the list is displayed as (, 2)
     6 nodes: 3 -> 32 -> 55 -> 7 -> 21 -> 88 -> null

     - Displaying a list of values and node numbers. The nodes are displayed in the form (, 3)
     [1] 3 -> [2] 32 -> [3] 55 -> [4] 7 -> [5] 21 -> [6] 88 -> null

     - Display of all basic information (, 4)
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

        /*
        the method allows you to get the value of the list node from its ordinal number
        */

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

    11) Create a list of the maximum length filled with values (the length may be any)

     - constant sequence - newListConstSequence

     - arithmetic progression - newListArithmeticSequence

     - periodic sequence - newListPeriodicSequence

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
    1) method, puts at the reference array of addresses, each every N / 4 nodes,
      where N is the total number of nodes in the list.
    */


    static void toFillTheReferenceArray(int listNameHere) {

        /*
        the method fills the reference array with the values of the addresses of the
        sorted list, based on its capacity
        */

        int firstIdToAAdding = MEMORY[listNameHere] / REFERENCE_ARRAY_CAPACITY - 1;
        int step = firstIdToAAdding + 1;

        //System.out.println("first ID to adding is " + (firstIdToAAdding));

        // TODO: to create the getMeTheLinkFromListByListId - method here

        int firstReferAddress = getTheNodeIdLink(listNameHere, firstIdToAAdding);

        //System.out.println("first ID link is " + firstReferAddress);


        for (int referArrayIncrement = 0;
             referArrayIncrement < REFERENCE_ARRAY_CAPACITY;
             referArrayIncrement++) {

            referenceArray[referArrayIncrement] = getTheNodeIdLink(listNameHere, firstIdToAAdding); // address search

            //referenceArray[referArrayIncrement] = firstIdToAAdding;

            firstIdToAAdding += step + 1;

        }

    }

    //----------------------------------------------------------------------------------------------swap the list nodes

    /*
    the method allows you to exchange between two list nodes.

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
    the method takes an incoming list and sorts it in ascending order.

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

        //System.out.println("the nextAddress to value set is: " + nextAddress);

        MEMORY[nextAddress] = newNodeValue;

    }


    //-------------------------------------------------------------------------------------------/set Node Values By Id

    //--------------------------------------------------------------------------------------------search by brute force


    static int simpleSearchValueAddress(int listNameHere, int startAddress, int endAddress, int searchValue) {

        /*
        the method allows by simply iterating over the elements of nodes in the list to find
        the address of the value to search.

        the implementation of the method allows you to accept as input arguments the initial and final values of
        the range of addresses for search
        */


        System.out.printf("\n::input start = %d, input end = %d, value to search = %d, list name = %d::\n",
                startAddress, endAddress, searchValue, listNameHere);

        //TODO: 23-12-2020

        int firstAddress = startAddress;

        int firstValByFirstAddress = MEMORY[firstAddress];

        int firstNextLinkAddress = startAddress + 1; // value to go to the next node


        while (MEMORY[firstAddress] != OUT_OF_MEMORY_ADDRESS) {

            //System.out.println("firstAddress (simple method): " + firstAddress);

            firstAddress = MEMORY[firstAddress + 1];

            if (searchValue == MEMORY[firstAddress]) { // if the value is found then break

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

        /*
        the final method allows you to search for the address of the value to search in the list
        using the capabilities of the support array
        */

        // TODO: it is possible to implement for other values of the reference array capacity in future


        int halfOfTheRefArray; // start search position
        int outListAddress = OUT_OF_MEMORY_ADDRESS; // default value is over the MEMORY


        if ((REFERENCE_ARRAY_CAPACITY & 1) == 1) halfOfTheRefArray = (REFERENCE_ARRAY_CAPACITY / 2) + 1;
        else halfOfTheRefArray = (REFERENCE_ARRAY_CAPACITY / 2);
        // if ref capacity is not even then + 1 for centering of the half position

        halfOfTheRefArray--; //we remember that the ref array starts at the 0 position

        //System.out.println("halfOfTheRefArray ID: " + halfOfTheRefArray);

        //-------------------------------------------------------------------------------the method core

        /*
        System.out.println("MEMORY[referenceArray[halfOfTheRefArray]] is: "
                + MEMORY[referenceArray[halfOfTheRefArray]]);

        System.out.println("MEMORY[referenceArray[halfOfTheRefArray] + 1] is: "
                + MEMORY[referenceArray[halfOfTheRefArray] + 1]);
        */

        // the block for comparing values

        int value0 = (MEMORY[referenceArray[halfOfTheRefArray - 1]]);
        int value1 = (MEMORY[referenceArray[halfOfTheRefArray]]);
        int value2 = (MEMORY[referenceArray[halfOfTheRefArray + 1]]);
        int value3 = (MEMORY[referenceArray[halfOfTheRefArray + 2]]);

        // the block of parameters to transfer to the simple search method

        int param0 = (MEMORY[referenceArray[halfOfTheRefArray - 1] + 1]);
        int param1 = (MEMORY[referenceArray[halfOfTheRefArray] + 1]);
        int param2 = (MEMORY[referenceArray[halfOfTheRefArray + 1] + 1]);
        int param3 = (MEMORY[referenceArray[halfOfTheRefArray + 2] + 1]);
        int param4 = OUT_OF_MEMORY_ADDRESS - 1; // the tail

        if (value0 == searchValue) {

            outListAddress = param0;

        } else if (value1 == searchValue) {

            outListAddress = param1;

        } else if (value2 == searchValue) {

            outListAddress = param2;

        } else if (value3 == searchValue) {

            outListAddress = param3;

        } else {


            if (searchValue > value1) { // go to the ref arr center and start start branch 1 [1]

                if (searchValue < value2) {

                    outListAddress = simpleSearchValueAddress(listNameHere, param1, param2, searchValue); // [2]

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
                    outListAddress = OUT_OF_MEMORY_ADDRESS;

                }


            }
        }

        //------------------------------------------------------------------------------/the method core


        //System.out.println("result of the quasi method is: " + outListAddress);

        return outListAddress; // return 257 if the value not in the list

    }

    //--------------------------------------------------------------------------------/quasi-binary search of the value


    //---------------------------------------------------------------------------------------------------------checkers

    public static void main(String[] args) {

        // Demo: the first method clears all MEMORY nods
        totalClearMemory();

        System.out.println("The first method clears all MEMORY nods\nThe MEMORY array:");
        System.out.print("--: ");
        jkPrintArray.jkPrintArrOneInt(MEMORY);

        // Demo: here is the work of the generator of free memory addresses
        longLine();

        System.out.print("Some free gen address (== -7) --: ");
        System.out.println(getFreeMemoryAddress());

        /*
        int[] testArray = new int[25];


        for (int i = 0; i < testArray.length; i++) {

            testArray[i] = getFreeMemoryAddress();

        }


        jkPrintArray.jkPrintArrOneInt(testArray);
        */

        // Demo: the method of create new list here
        longLine();
        System.out.println();

        createANewList();

        System.out.println("The MEMORY array with the head node:");
        System.out.print("--: ");
        jkPrintArray.jkPrintArrOneInt(MEMORY);

        // Demo: here we will get in which memory cell we can take the address to add a new node
        longLine();

        System.out.println();
        System.out.println("The last Node Next Address Search:");
        System.out.print("--: ");
        System.out.println("Next Address_ " + lastNodeNextAddressSearch());

        // Demo: there are the method for adding any node to the list
        longLine();
        System.out.println("there are the method for adding any node to the list:");

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

        // Demo: here we see the implementation of the method for printing the list in various views
        longLine();
        System.out.println("here we see the implementation of the method for printing the list in various views");

        System.out.println("MEMORY view option:");
        printList(0, 1);
        shortLine();

        System.out.println("linked view option:");
        printList(0, 2);
        shortLine();

        System.out.println("linked view option with the list ID:");
        printList(0, 3);
        shortLine();

        System.out.println("full info view option:");
        printList(0, 4);
        System.out.println();

        //Demo: method for getting the value of a node by its order in the list
        longLine();

        System.out.println("method for getting the value of a node by its order in the list(node ID is 3)");
        System.out.println(getTheNodeIdVal(0, 3));

        //Demo: method for setting a new value of a node in its order in the list
        longLine();

        System.out.println("method for setting a new value (888) of a node in its order in the list(node ID is 3)");

        changeTheNodeIdVal(0, 3, 888);

        System.out.println("the changed list:");

        printList(0, 3);


        // Demo: The clear list method right here
        System.out.println("\nThe clear list method right here:");
        longLine();

        clearListBody();
        printList(0, 1);

        // Demo: constant list method with given parameters
        longLine();

        System.out.println("constant list method with given parameters:");
        System.out.println("check the newListConstSequence:");

        newListConstSequence(13, 127);
        jkPrintArray.jkPrintArrOneInt(MEMORY);
        printList(0, 3);


        //Demo: second method for setting a new value of a node in its order in the list
        longLine();
        System.out.println("second method for setting a new value of a node in its order in the list:");

        System.out.println();
        System.out.println(getTheNodeIdVal(0, 7));

        changeTheNodeIdVal(0, 7, 5555);
        printList(0, 3);

        //Demo: of longLine and shortLine methods
        System.out.println("\n\n");
        System.out.println("longLine and shortLine methods:");
        longLine();
        shortLine();


        //-------------------------------------------------------------- next stage


        // Demo: Periodic sequence with given parameters
        longLine();
        System.out.println("Periodic sequence with given parameters:");
        newListPeriodicSequence(1, 8, 127);
        printList(0, 2);

        // Demo: Arithmetic sequence with given parameters
        longLine();
        System.out.println("check the newListArithmeticSequence:");
        newListArithmeticSequence(7, 1, 2, 127); // 127 elements
        printList(0, 2);

        shortLine();
        System.out.println("check the setNodeValuesById - method:");
        setNodeValuesById(0, 3, 6666);
        //setNodeAddressById(0, 3, 111); it is not possible
        printList(0, 4);


        // Demo: node swap method
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
        System.out.println("and finally the address that we were looking for is : ");
        System.out.println(quasiBinSearchOfValue(0, 1023));

        //
        longLine();
        System.out.println("The end of the demonstration, thank you all!");


    }


    //--------------------------------------------------------------------------------------------------------/checkers
}
