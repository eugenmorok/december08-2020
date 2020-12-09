public class StringLibrary {

    static void reversingString(String inputString) {


        String newString = "";

        for (int i = inputString.length() - 1; i > -1; i--) {

            newString += inputString.charAt(i);

        }


        System.out.println(newString);

    }


    static boolean isTheCharacter(String inputString, char theCharacter) {

        boolean isTheCharacter = false;


        for (int i = 0; i < inputString.length(); i++) {

            if (inputString.charAt(i) == theCharacter) {

                isTheCharacter = true;
                break;

            }

        }

        if (!isTheCharacter) System.out.println("Entered character is NOT included in inputted string");
        else System.out.println("Entered character is included in inputted string");

        return isTheCharacter;

    }


    static String uniqueCharacters(String inputString) {

        int stringLength = inputString.length();

        int[][] workArray = new int[stringLength][2];

        int count = 0;

        String outputString = "";


        for (int i = 0; i < stringLength; i++) {

            count = 0;

            for (int j = 0; j < stringLength; j++) {

                if (inputString.charAt(i) == inputString.charAt(j)) {

                    count++;
                    workArray[i][0] = count;
                    workArray[i][1] = i;

                }

            }

        }


        for (int k = 0; k < stringLength; k++) {

            if (workArray[k][0] == 1) {

                outputString = outputString + inputString.charAt(workArray[k][1]);
            }

        }


        return outputString;


    }

    //---------------------------------------------------------------------------checker
    public static void main(String[] args) {

        String someString = "dd21rtyr1";

        reversingString(someString);

        isTheCharacter(someString, '3');

        System.out.println();

        System.out.println(uniqueCharacters(someString));


    }
    //---------------------------------------------------------------------------/checker
}
