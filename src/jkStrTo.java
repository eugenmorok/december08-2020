public class jkStrTo {

    public static int strToInt(String str) {
        int i = 0;
        int num = 0;
        boolean isNeg = false;

        //----------------------------------------------------------------------------check for negative
        if (str.charAt(0) == '-') {

            isNeg = true;
            i = 1;

        }

        // Process each character of the string;
        while (i < str.length()) {

            num *= 10;
            num += str.charAt(i++) - '0';
            //------------------------------------------get the value of the charAt
        }

        if (isNeg)

            num = -num;

        return num;
    }

}
