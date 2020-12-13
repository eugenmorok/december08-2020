public class jkStringToArray {

    public static int[] stringToArrayInt(String inputString) {

        int[] outputArr = new int[inputString.length()];


        for (int i = 0; i < inputString.length(); i++) {

            outputArr[i] = inputString.charAt(i);

        }


        return outputArr;
    }

    //-----------------------------------------------------------------------------


    public static String[] stringToArrayStr(String inputString) {

        String[] outputArr = new String[inputString.length()];


        for (int i = 0; i < inputString.length(); i++) {

            outputArr[i] = String.valueOf(inputString.charAt(i));

        }


        return outputArr;
    }
}
