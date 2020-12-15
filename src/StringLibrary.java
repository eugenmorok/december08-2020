public class StringLibrary {


    /*
    1) инвертирование (разворот). Первый символ исходной строки должен быть последним в инверсионной строке,
    второй символ исходной строки должен быть предпоследним в развёрнутой строке, третий символ должен быть третьим
    с конца в инверсионной строке и т. д.
     */

    static void reversingString(String inputString) {


        String newString = "";

        for (int i = inputString.length() - 1; i > -1; i--) {

            newString += inputString.charAt(i);

        }


        System.out.println(newString);

    }
    //--------------------------------------------------------------------------------------------------------



    /*
    2)  поиск по значению: есть ли конкретный символ в строке.
    Сложность 1.
    Сигнатура метода static boolean isTheCharacter ( String inputString, char theCharacter ).
    Пример: вручную вводится строка: «safety» и символ ‘f’, вывод должен быть: «Entered character is included in
    inputted string». Или если символ был '3', то вывод тогда будет «Entered character is NOT included in inputted
    string».
     */

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

    //--------------------------------------------------------------------------------------------------------



    /*
    3) нахождение всех уникальных символов в строке.
    Сложность 3.
    Сигнатура метода static String uniqueCharacters ( String inputString ).
    Пример. Вручную вводится строка: «dd21rtyr1», вывод должен быть: «2ty».
     */

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

    //--------------------------------------------------------------------------------------------------------




    /*
    4) нахождение всех разных символом в строке.
    Сложность 3.
    Сигнатура метода static String variousCharacters ( String inputString ).
    Пример. Вручную вводится строка: «dd21rtyr1», вывод должен быть: «d21rty».
     */


    static String variousCharacters(String inputString) {

        String outputString = "";

        int inLen = inputString.length();
        int count = 0;

        for (int i = 0; i < inLen; i++) {

            count = 0;

            for (int j = i + 1; j < inLen; j++) {

                if (inputString.charAt(i) == inputString.charAt(j)) {

                    count++;

                }

            }

            if (count == 0) {

                outputString += inputString.charAt(i);

            }

        }

        //outputString = outputString + inputString.charAt(0);
        return outputString;

    }

    //--------------------------------------------------------------------------------------------------------




    /*
    4,1) нахождение подстроки всех повторённых и повторяющихся символов исходной строки.
    Сложность 4.
    Сигнатура метода static String subStringOfRepeatingCharacters ( String inputString ).
    Возвращаемая подстрока должна добавлять повторённые и повторяющиеся символы по мере того,
    как они встречаются в исходной строке.
    Пример. Вручную вводится строка: «dd211rtyr1», вывод должен быть: «d1r1».
     */

    static String subStringOfRepeatingCharacters(String inputString) {


        String outputString = "";

        int inLen = inputString.length();
        int count = 0;

        for (int i = 0; i < inLen; i++) {

            count = 0;

            for (int j = 0; j < inLen; j++) {

                if (inputString.charAt(i) == inputString.charAt(j)) {

                    count++;

                }

            }

            if (count > 1) {

                outputString += inputString.charAt(i);

            }

        }


        outputString = variousCharacters(outputString);

        return outputString; //TODO: to finish this


    }

    //--------------------------------------------------------------------------------------------------------



    /*
    4,2) нахождение одинаковых символов исходной строки.
    Сложность 4.
    Сигнатура метода static [ ] int dataAboutSameCharacters ( String inputString ).
    Возвращаемый массив содержит чётное количество элементов. В каждой паре первый элемент – это индекс первого
    символа, который имеет двойников, второй элемент должен хранить количество одинаковых символов.
    Пример. Вручную вводится строка: «dd211rtyr1», вывод должен быть: { 0, 2, 3, 3, 5, 2 }.
     */


    static int[] dataAboutSameCharacters(String inputString) {

        int count = 0;

        int[] outputArray = new int[inputString.length()];
        String[] workArray = new String[inputString.length()];


        for (int k = 0; k < inputString.length(); k++) {

            workArray[k] = "" + inputString.charAt(k);

        }

        jkPrintArray.jkPrintArrOneStr(workArray);

        for (int i = 0; i < inputString.length(); i++) {

            count = 0;

            for (int j = i + 1; j < inputString.length(); j++) {

                if (workArray[i].charAt(0) == workArray[j].charAt(0)) {

                    count++;
                    workArray[i + 1] = "" + count;

                }

            }

        }

        //jkPrintArray.jkPrintArrOneStr(workArray);

        return outputArray;
    }


    //--------------------------------------------------------------------------------------------------------





    /*
    6) нахождение всех цифр в строке. Цифры сохранить в отдельном массиве целых чисел.
    */


    static int[] digitsOfString(String inputString) {

        int arrLen = 0;
        int[] digits = new int[inputString.length()];


        for (int i = 0; i < inputString.length(); i++) {

            if (inputString.charAt(i) > 47 && inputString.charAt(i) < 58) {

                digits[arrLen] = inputString.charAt(i);

                arrLen++;

            }

        }


        int[] outputArray = new int[arrLen];

        for (int j = 0; j < arrLen; j++) {

            outputArray[j] = digits[j] - '0';

        }

        return outputArray;

    }

    //--------------------------------------------------------------------------------------------------------


    /*
    5) нахождение символов одной строки, которых нет у другой строки.
    Сложность 3.
    Сигнатура метода static String differenceOfTwoStrings ( String firstString, String secondString ).
    Пример. Вручную вводятся две строки: «stringOne» и «stringTwo». Вывод должен быть: «Oe». ‘O’ и  ‘o’
    – разные символы.
    Ещё пример: Вручную вводятся две строки: «father» и «mother». Вывод должен быть: «fa».
    */

    static String differenceOfTwoStrings(String firstString, String secondString) {

        String outStr = "";
        int cnt = 0;

        for (int i = 0; i < firstString.length(); i++) {

            for (int j = 0; j < secondString.length(); j++) {

                if (firstString.charAt(i) == secondString.charAt(j)) {

                    cnt++;

                }

            }

            if (cnt == 0) {

                outStr += "" + firstString.charAt(i);
            }

            cnt = 0;

        }

        return outStr;

    }

    //--------------------------------------------------------------------------------------------------------




    /*
    7) нахождение всех чисел в строке. Числа сохранить в отдельном массиве целых чисел.
    Сложность 5.
    Сигнатура метода static [ ] int numbersOfString ( String inputString ).
    Пример. Вручную вводится строка: «dd21rtyr1», вывод должен быть: «arrayOfNumbers[0] = 21; arrayOfNumbers[1] =1».
    */


    static int[] numbersOfString_wrongwrong(String inputString) {

        int arrLen = 0;
        String[] digits = new String[inputString.length()];


        for (int k = 0; k < inputString.length(); k++) {

            if (inputString.charAt(k) < 47 && inputString.charAt(k) > 58) {

                digits[k] = "s";

            } else if (inputString.charAt(k) > 47 && inputString.charAt(k) < 58) {

                digits[k] = inputString.charAt(k) + "";

            }

        }


        int i = 0;
        int j = 0;
        String first = "G:";


        while (i < digits.length) {


            System.out.println(digits[i]);

            if (digits[i] == "s") {

                i++;
                continue;

            } else {

                first += digits[i];

            }

            i++;
        }

        int[] outArr = new int[digits.length];

        for (int s = 0; s < digits.length; s++) {

            outArr[s] = 4;

        }

        System.out.println(first);


        return outArr;

    }


    //--------------------------------------------------------------------------------------------------------



    /*
    15) удаление всех пробелов в строке.
    Сложность 1.
    Сигнатура метода static void clearingAllSpaces ( String inputString ).
    Пример. Вручную вводится строка: « back to reality    », вывод должен быть: «backtoreality».
    */

    static void clearingAllSpaces(String inputString) {

        String output = "";


        for (int i = 0; i < inputString.length(); i++) {

            if (inputString.charAt(i) != 32) output += inputString.charAt(i) + "";

        }


        System.out.println(output);

    }

    //--------------------------------------------------------------------------------------------------------





    /*
    14) удаление всех пробелов в начале и конце строки. string.trim() название метода из класса String,
    реализующий подобное.
    Сложность 2.
    Сигнатура метода static void clearingSpacesOfBeginAndEnd ( String inputString ).
    Пример. Вручную вводится строка: « back to reality    », вывод должен быть: «back to reality».
     */

    static void clearingSpacesOfBeginAndEnd(String inputString) {

        String[] workArr = jkStringToArray.stringToArrayStr(inputString);

        String output = "";


        for (int i = 0; i < inputString.length(); i++) {

            if (inputString.charAt(i) == 32) {

                workArr[i] = "";

            } else break;

        }


        //jkPrintArray.jkPrintArrOneStr(workArr);


        for (int j = inputString.length() - 1; j > 0; j--) {

            if (inputString.charAt(j) == 32) {

                workArr[j] = "";

            } else break;

        }


        //jkPrintArray.jkPrintArrOneStr(workArr);


        for (int k = 0; k < workArr.length; k++) {

            output += workArr[k];

        }


        System.out.println(output);

    }


    static String jkTrim(String inputString) {

        String[] workArr = jkStringToArray.stringToArrayStr(inputString);

        String output = "";


        for (int i = 0; i < inputString.length(); i++) {

            if (inputString.charAt(i) == 32) {

                workArr[i] = "";

            } else break;

        }


        //jkPrintArray.jkPrintArrOneStr(workArr);


        for (int j = inputString.length() - 1; j > 0; j--) {

            if (inputString.charAt(j) == 32) {

                workArr[j] = "";

            } else break;

        }


        //jkPrintArray.jkPrintArrOneStr(workArr);


        for (int k = 0; k < workArr.length; k++) {

            output += workArr[k];

        }


        return output;

    }

    //--------------------------------------------------------------------------------------------------------




    /*
    18) удаление из строки определённого символа.
    Сложность 1.
    Сигнатура метода static void removingTheCharacter ( String inputString, char deletedCharacter ).
    Пример. Пример: вручную вводится строка: «safety» и символ ‘f’, вывод должен быть: «saety». Или если символ был
    '3', то вывод тогда будет «safety».
    */


    static void removingTheCharacter(String inputString, char deletedCharacter) {

        String[] workArr = jkStringToArray.stringToArrayStr(inputString);

        String out = "";

        for (int i = 0; i < workArr.length; i++) {

            if (workArr[i].charAt(0) != deletedCharacter) {

                out += workArr[i];

            }
        }


        System.out.println(out);


    }

//--------------------------------------------------------------------------------------------------------




    /*
    19) удаление из строки всех цифр.
    Сложность 2.
    Сигнатура метода static void removingAllDigits ( String inputString ).
    Пример. Вручную вводится строка: «hardFAT34get», вывод должен быть: «hardFATget».
    Вручную вводится строка: «how to create», вывод должен быть: «how to create».
     */


    static void removingAllDigits(String inputString) {

        String[] workArr = jkStringToArray.stringToArrayStr(inputString);

        String out = "";


        for (int i = 0; i < workArr.length; i++) {

            if (workArr[i].charAt(0) < 47 || workArr[i].charAt(0) > 58) {

                out += workArr[i];

            }

        }


        System.out.println(out);


    }

    //--------------------------------------------------------------------------------------------------------




    /*
    20) удаление из строки всех букв.
    Сложность 2.
    Сигнатура метода static void removingAllLetters ( String inputString ).
    Пример. Вручную вводится строка: «hardFAT34get», вывод должен быть: «34».
    Вручную вводится строка: «how to create», вывод должен быть: «  ». Тут два пробела.
    Вручную вводится строка: «145+5=150», вывод должен быть: «145+5=150».
     */

    static void removingAllLetters(String inputString) {

        String[] workArr = jkStringToArray.stringToArrayStr(inputString);

        String out = "";


        for (int i = 0; i < workArr.length; i++) {

            if (
                    (workArr[i].charAt(0) < 65 || workArr[i].charAt(0) > 122) ||
                            (workArr[i].charAt(0) < 97 && workArr[i].charAt(0) > 90)
            ) {

                out += workArr[i];

            }

        }


        System.out.println(out);

    }
    //--------------------------------------------------------------------------------------------------------





    /*
    27) Сравнение на идентичность двух строк.
    Сложность 1.
    Сигнатура метода static boolean identityOfTwoStrings ( String firstString, String secondString ).
    Пример. Вручную вводятся две строки: «stringOne» и «ing». Вывод должен быть: «false».
    Вручную вводятся две строки: «stringOne» и «stringOne». Вывод должен быть: «true».
    Вручную вводятся две строки: «ing» и «in». Вывод должен быть: «false».
     */

    static boolean identityOfTwoStrings(String firstString, String secondString) {

        boolean areIdentity = true;

        if (firstString.length() == secondString.length()) {


            for (int i = 0; i < firstString.length(); i++) {

                if (firstString.charAt(i) != secondString.charAt(i)) areIdentity = false;

            }


        } else areIdentity = false;

        return areIdentity;

    }

    //--------------------------------------------------------------------------------------------------------





    /*
   28) Проверка строки на палиндромность (строка читается и слева направо и справа налево одинаково), то есть
   совпадает ли строка и её развёрнутая модификация. Такое изменение исходной строки называется разворот или инверсия.
   Первый символ исходной строки должен быть последним в инверсионной строке, второй символ исходной строки должен
   быть предпоследним в развёрнутой строке, третий символ должен быть третьим с конца в инверсионной строке и т. д.
    Сложность 1.
    Сигнатура метода static boolean isPalyndrom ( String inputString ).
    Пример. Вручную вводится строка: «rakkar». Вывод должен быть: «true».
    Вручную вводится строка: «connor». Вывод должен быть: «false».
    Вручную вводится строка: «123321». Вывод должен быть: «true».
    Вручную вводится строка: «12312». Вывод должен быть: «false».
     */

    static boolean isPalyndrom(String inputString) {


        String revString = "";


        for (int i = inputString.length() - 1; i > -1; i--) {

            revString += inputString.charAt(i);

        }


        return identityOfTwoStrings(inputString, revString);


    }


//--------------------------------------------------------------------------------------------------------





    /*22) транслитерация с русского в английский.
    Сложность 4.
    Сигнатура метода static void transliterationFromRussianToEnglish (String inputString ).
    Спецификация:
    а - a	б - b	в - v	г - g	д - d	е - e	ё – yo
    ж – zh	з – z	и – i	й – j	к – k	л – l	м - m
    н – n	о – o	п – p	р – r	с – s	т – t	у - u
    ф – f	х – h	ц – ts	ч – ch	ш – sh	щ – shch	ъ - ie
    ы – y	ь - -	э – e	ю – ju	я –ja.

    Регистр букв сохраняется, небуквенные символы сохраняются. В этом методе, естественно, можно вводить русские буквы.
    Пример. Вручную вводится строка «Мартобрь15нас привет 33!!». Вывод должен быть: «Martobr-15nas privet 33!!».
    */

    static void transliterationFromRussianToEnglish(String inputString) {

        int inputStrLen = inputString.length();

        String[][] dict = {

                {"192", "А", "A"},
                {"193", "Б", "B"},
                {"194", "В", "V"},
                {"195", "Г", "D"},
                {"197", "Е", "E"},
                {"198", "Ж", "ZH"},
                {"199", "З", "Z"},
                {"200", "И", "I"},
                {"201", "Й", "J"},
                {"202", "К", "K"},
                {"203", "Л", "L"},
                {"204", "М", "M"},
                {"205", "Н", "N"},
                {"206", "О", "O"},
                {"207", "П", "P"},
                {"208", "Р", "R"},
                {"209", "С", "S"},
                {"210", "Т", "T"},
                {"211", "У", "U"},
                {"212", "Ф", "F"},
                {"213", "Х", "H"},
                {"214", "Ц", "TS"},
                {"215", "Ч", "CH"},
                {"216", "Ш", "SH"},
                {"217", "Щ", "SHCH"},
                {"218", "Ъ", "IE"},
                {"219", "Ы", "Y"},
                {"220", "Ь", "-"},
                {"221", "Э", "E"},
                {"222", "Ю", "JU"},
                {"223", "Я", "JA"},
                {"224", "а", "a"},
                {"225", "б", "b"},
                {"226", "в", "v"},
                {"227", "г", "g"},
                {"228", "д", "d"},
                {"229", "е", "e"},
                {"230", "ж", "zh"},
                {"231", "з", "z"},
                {"232", "и", "i"},
                {"233", "й", "j"},
                {"234", "к", "k"},
                {"235", "л", "l"},
                {"236", "м", "m"},
                {"237", "н", "n"},
                {"238", "о", "o"},
                {"239", "п", "p"},
                {"240", "р", "r"},
                {"241", "с", "s"},
                {"242", "т", "t"},
                {"243", "у", "u"},
                {"244", "ф", "f"},
                {"245", "х", "h"},
                {"246", "ц", "ts"},
                {"247", "ч", "ch"},
                {"248", "ш", "sh"},
                {"249", "щ", "shch"},
                {"250", "ъ", "ie"},
                {"251", "ы", "y"},
                {"252", "ь", "-"},
                {"253", "э", "e"},
                {"254", "ю", "ju"},
                {"255", "я", "ja"},

        };

        String[] stringArr = jkStringToArray.stringToArrayStr(inputString);


        for (int i = 0; i < inputStrLen; i++) {


            for (int j = 0; j < dict.length; j++) {

                if (stringArr[i].charAt(0) == dict[j][1].charAt(0)) {

                    //System.out.println(stringArr[i] + "....." + dict[j][1] + "...>>" + dict[j][2]);

                    stringArr[i] = dict[j][2];

                }

            }


        }

        //jkPrintArray.jkPrintArrOneStr(stringArr);

        System.out.println(jkArrayTo.stringOne(stringArr));

    }


    //--------------------------------------------------------------------------------------------------------





    /*
    23) замена всех цифр на слова, их обозначающие.
    Сложность 4.
    Сигнатура метода static void directTranslatingDigitsInWords (String inputString ).
    Спецификация:
    1 - one	2 - two	3 - three	4 - four	5 - five	6 - six	7 – seven
    8 – eight	9 – nine	0 – zero.
    Регистр букв сохраняется, небуквенные символы сохраняются, добавляемые числительные вместо цифровой записи чисел
    обособляются единичными пробелами с обеих сторон. Если несколько цифр идут последовательно, пробел между ними
    должен быть также один.
    Пример. Вручную вводится строка «?G1h3sj77^Df42q009^&».
    Вывод должен быть: «?G one h three sj seven seven ^Df four two q zero zero nine ^&».
    */

    static void directTranslatingDigitsInWords(String inputString) {

        int inputStrLen = inputString.length();

        int digitsCnt = 0;

        String out = "";

        String[][] dict = {

                {"192", "0", "zero"},
                {"193", "1", "one"},
                {"194", "2", "two"},
                {"195", "3", "three"},
                {"197", "4", "four"},
                {"198", "5", "five"},
                {"199", "6", "six"},
                {"200", "7", "seven"},
                {"201", "8", "eight"},
                {"202", "9", "nine"},

        };

        String[] stringArr = jkStringToArray.stringToArrayStr(inputString);


        for (int i = 0; i < inputStrLen; i++) {


            for (int j = 0; j < dict.length; j++) {

                if (stringArr[i].charAt(0) == dict[j][1].charAt(0)) {

                    //System.out.println(stringArr[i] + "....." + dict[j][1] + "...>>" + dict[j][2]);

                    stringArr[i] = " " + dict[j][2] + " ";
                    digitsCnt++;

                }

            }


        }

        //jkPrintArray.jkPrintArrOneStr(stringArr);

        System.out.println(jkNoExcSpaces(jkArrayTo.stringOne(stringArr)));


    }


    //--------------------------------------------------------------------------------------------------------





    /*
    12) изменение регистра: все строчные на заглавные, все заглавные на строчные.
    Сложность 3.
    Сигнатура метода static void switcherOfLetterCase ( String inputString ).
    Пример. Вручную вводится строка: «hardFAT34get», вывод должен быть: «HARDfat34GET».
    */

    static void switcherOfLetterCase(String inputString) {

        String output = "";
        char s;

        for (int i = 0; i < inputString.length(); i++) {

            s = inputString.charAt(i);

            if (s > 47 & s < 58) output += s;

            else output += (char) (s ^ ' ');

        }

        System.out.println(output);

    }


    //--------------------------------------------------------------------------------------------------------





    /*
    10) изменение регистра: строчных на заглавные.
    Сложность 3.
    Сигнатура метода static void upperOfLetterCase ( String inputString ).
    Пример. Вручную вводится строка: «hardFAT34get», вывод должен быть: «HARDFAT34GET».

    */

    static void upperOfLetterCase(String inputString) {

        String output = "";
        char s;

        for (int i = 0; i < inputString.length(); i++) {

            s = inputString.charAt(i);

            if (s > 47 & s < 58) output += s;

            else output += (char) (s & '_');

        }

        System.out.println(output);

    }


    //--------------------------------------------------------------------------------------------------------





    /*
    11) изменение регистра: заглавные на строчные.
    Сложность 3.
    Сигнатура метода static void downerOfLetterCase ( String inputString ).
    Пример. Вручную вводится строка: «hardFAT34get», вывод должен быть: «hardfat34get».
    */

    static void downerOfLetterCase(String inputString) {

        String output = "";
        char s;

        for (int i = 0; i < inputString.length(); i++) {

            s = inputString.charAt(i);

            if (s > 47 & s < 58) output += s;

            else output += (char) (s | ' ');

        }

        System.out.println(output);

    }


    //--------------------------------------------------------------------------------------------------------





    /*
    9) подсчёт предложений в строке. Предложения считать непустыми подстроками, оканчивающимися
    только на либо «.», либо «!», либо «?».
    Сложность 2.
    Сигнатура метода static int quantityOfSentencesInString ( String inputString ).
    Пример. Вручную вводится строка: «dd2. 1r! wer? t,r.1:blag; », вывод должен быть: «4».
    */

    static int quantityOfSentencesInString(String inputString) {

        int cnt = 0;
        char c;

        for (int i = 0; i < inputString.length(); i++) {

            c = inputString.charAt(i);

            if (c == 46 | c == 33 | c == 63) cnt++;

        }

        return cnt++;
    }


//--------------------------------------------------------------------------------------------------------





    /*
    15) удаление всех лишних пробелов в строке. Лишние пробелы: в начале и конце строки, все пробелы, большие одного
    между словами. Иными словами, оставить только единичные пробелы между словами.
    Сложность 2.
    Сигнатура метода static void clearingExcessSpaces ( String inputString ).
    Пример. Вручную вводится строка: « back   to    reality    », вывод должен быть: «back to reality».
     */

    static void clearingExcessSpaces(String inputString) {

        String out = "";
        boolean isCatch = false;


        for (int i = 0; i < inputString.length(); i++) {


            if (inputString.charAt(i) == 32) {

                if (isCatch) {

                    continue;

                }

                isCatch = true;

            } else {

                isCatch = false;

            }

            out += inputString.charAt(i);

        }


        System.out.println(jkTrim(out));

    }


    static String jkNoExcSpaces(String inputString) {

        String out = "";
        boolean isCatch = false;


        for (int i = 0; i < inputString.length(); i++) {


            if (inputString.charAt(i) == 32) {

                if (isCatch) {

                    continue;

                }

                isCatch = true;

            } else {

                isCatch = false;

            }

            out += inputString.charAt(i);

        }


        return jkTrim(out);

    }


    //--------------------------------------------------------------------------------------------------------





    /*
    8) подсчёт слов в строке.
    Сложность 2.
    Сигнатура метода static int quantityOfWordsInString ( String inputString ).
    Пример. Вручную вводится строка: «dd2 1r t yr1 », вывод должен быть: «4». Или для ввода «  wall the back»
    должен быть вывод: «3».
    */

    static int quantityOfWordsInString(String inputString) {

        //System.out.println(inputString);

        inputString = jkNoExcSpaces(jkTrim(inputString));

        int cnt = 0;

        for (int i = 0; i < inputString.length(); i++) {

            if (inputString.charAt(i) == 32) cnt++;

        }

        return cnt + 1;

    }


    //--------------------------------------------------------------------------------------------------------





    /*
    16) парсинг строки на слова, каждое из которых сохраняется в массив строк как элемент. Словами считать подстроки
    между пробелами, состоящими только из цифр и букв обоих регистров.
    Сложность 3.
    Сигнатура метода static [ ] String parsingIntoWords ( String inputString ).
    Пример. Вручную вводится строка: «dd 21 r tyr 1», вывод должен быть:

    «arrayOfString[0] = “dd”;
    arrayOfString [1] =”21”;
    arrayOfString [2] =”r”;
    arrayOfString [3] =”tyr”;
    arrayOfString [4] =”1”».

    */

    static String[] parsingIntoWords(String inputString) {

        inputString = jkNoExcSpaces(jkTrim(inputString)) + " ";

        String[] workArr = jkStringToArray.stringToArrayStr(inputString);

        jkPrintArray.jkPrintArrOneStr(workArr);

        int spcCnt = 0;

        for (int i = 0; i < workArr.length; i++) {

            if (workArr[i].charAt(0) == 32) spcCnt++;

        }


        String[] finArr = new String[spcCnt];


        int j = 0;
        String finStr = "";

        for (int s = 0; s < 5; s++) {

            //j = 0;

            while (true) {


                if (workArr[0].charAt(0) != 32) finStr += workArr[j];


                //if (j  == workArr.length) break;
                //else j++;

                j++;

                if (workArr[j].charAt(0) == 32) {

                    finArr[s] = jkTrim(finStr);
                    finStr = "";
                    break;
                }


            }


        }

        return finArr;
    }

    //--------------------------------------------------------------------------------------------------------
    //20201214------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------

    /*
    7) нахождение всех чисел в строке. Числа сохранить в отдельном массиве целых чисел.
    Сложность 5.
    Сигнатура метода static [ ] int numbersOfString ( String inputString ).
    Пример. Вручную вводится строка: «dd21rtyr1», вывод должен быть: «arrayOfNumbers[0] = 21; arrayOfNumbers[1] = 1».
     */

    static int[] numbersOfString(String inputString) {

        int d;
        String digString = "";


        for (int i = 0; i < inputString.length(); i++) {

            d = inputString.charAt(i);

            if (d > 47 & d < 58) digString += inputString.charAt(i);
            else digString += " ";

        }


        digString = jkNoExcSpaces(jkTrim(digString)) + " ";
        System.out.println(digString);

        int cntOfSpc = 0;


        for (int s = 0; s < digString.length(); s++) {

            if (digString.charAt(s) == 32) cntOfSpc++;

        }


        System.out.println("cnt: " + cntOfSpc);

        String[] digArr = new String[cntOfSpc];


        int j = 0;
        int k = 0;

        String temp = "";


        for (int digArrIdx = 0; digArrIdx < cntOfSpc; digArrIdx++) {


            while (true) {

                if (digString.charAt(j) == 32) break;

                temp += digString.charAt(k) + "";
                k++;

                j++;


            }

            digArr[digArrIdx] = temp;

            temp = "";
            j = 0;


        }


        int[] finArr = new int[cntOfSpc];

        for (int q = 0; q < cntOfSpc; q++) {

            finArr[q] = jkStrTo.strToInt(jkTrim(digArr[q]));

        }


        jkPrintArray.jkPrintArrOneStr(digArr);


        //------------------------------------------------------------------------------/parsing


        int[] dd = {1, 2, 4};

        return finArr;

    }


    //--------------------------------------------------------------------------------------------------------





    /*
    21) генерация случайной строки.

    Сложность 5.

    Сигнатура метода static void generationOfSpecifiedRandomString ( ).

    Размер или длина строки спрашивается у пользователя, то есть вводится вручную. Программа должна давать возможность
    выбирать пользователю вид получаемой строки.

    Базовые виды: (b1-3)
    случайная (все символы возможны), пример: «g23yfwbh3»
    только цифры, пример: «632353»
    только буквы (и строчные и заглавные), пример: «erAdTRfs»

    Псевдовиды: (p)
    Математические выражения. Содержат только цифры и символы «+-*'/=><», начинаются с числа и оканчиваются числом.
    Цифры образуют числа длиной от 1 до 5 разрядов, распределение разрядности равномерное. После первого числа идёт
    один случайно выбранный символ математической операции «+-*'/», затем второе число, затем один случайно выбранный
    символ состояния «=><», и в конце число.
    Пример: «5+0013=47», «53411/988<1005»

    Предложение: (s)
    Должно начинаться с заглавной буквы, оканчиваться одним из трёх символов: «.!?», содержать только
    буквы, слова должны иметь длину от 1 до 12 символов и иметь нормальное распределение с максимумом в 6 символов,
    обрамлённые единичными пробелами.

    Пример псевдопредложения: «Kfbej n qbfbbaw eoisvbn sdjb fsbb oaas d!»
    */

    static void generationOfbase1(int stringLen) {

        int[] genArr = new int[stringLen];

        jkEasyRandomGen.jkFillTheArrOfGen(genArr, 33, 126, 0);

        //jkPrintArray.jkPrintArrOneInt(genArr);

        String outString = "";

        for (int i = 0; i < stringLen; i++) {

            outString += (char)genArr[i];

        }

        System.out.println(outString);

    }

    static void generationOfbase2(int stringLen) {

        int[] genArr = new int[stringLen];

        jkEasyRandomGen.jkFillTheArrOfGen(genArr, 48, 57, 16413);

        jkPrintArray.jkPrintArrOneInt(genArr);

        String outString = "";

        for (int i = 0; i < stringLen; i++) {

            outString += (char)genArr[i];

        }

        System.out.println(outString);

    }


    //---------------------------------------------------------------------------checker
    public static void main(String[] args) {

        String someString = "dd21rtyr1";

        reversingString(someString);

        isTheCharacter(someString, '3');

        System.out.println();

        System.out.println(uniqueCharacters(someString));

        System.out.println();

        System.out.println(variousCharacters(someString));

        System.out.println();

        System.out.println(subStringOfRepeatingCharacters(someString));

        System.out.println();

        jkPrintArray.jkPrintArrOneInt(dataAboutSameCharacters(someString));

        System.out.println();

        jkPrintArray.jkPrintArrOneInt(digitsOfString(someString));

        System.out.println();

        System.out.println(differenceOfTwoStrings("peterburg", "gammburg"));

        System.out.println();

        String someString1 = " back to reality    ";

        clearingAllSpaces(someString1);
        clearingSpacesOfBeginAndEnd(someString1);

        System.out.println();

        String someString2 = "safety";

        removingTheCharacter(someString2, '3');

        System.out.println();

        String some3 = "145+5=150";

        removingAllDigits(some3);

        System.out.println();

        removingAllLetters(some3);

        System.out.println();

        String some4 = "stringOne";
        String some5 = "stringOne";

        System.out.println(identityOfTwoStrings(some4, some5));

        System.out.println();

        String some6 = "123321";

        System.out.println(isPalyndrom(some6));

        System.out.println();

        String some7 = "Мартобрь15нас привет 33!!";

        transliterationFromRussianToEnglish(some7);

        String some8 = "?G1h3sj77^Df42q009^&";

        directTranslatingDigitsInWords(some8);

        System.out.println();

        String some9 = "hardFAT34get";

        switcherOfLetterCase(some9);

        upperOfLetterCase(some9);

        downerOfLetterCase(some9);

        System.out.println();

        String some10 = "dd2. 1r! wer? t,r.1:blag; ";

        System.out.println(quantityOfSentencesInString(some10));

        System.out.println();

        String some11 = " back   to    reality    ";

        clearingExcessSpaces(some11);

        System.out.println();

        String some12 = "  wall the back   ";

        System.out.println(quantityOfWordsInString(some12));

        System.out.println();

        String some13 = "dd 21 r tyr 1";

        jkPrintArray.jkPrintArrOneStr(parsingIntoWords(some13));

        System.out.println("\nnumbersOfString\n");

        jkPrintArray.jkPrintArrOneInt(numbersOfString(someString));

        System.out.println();

        System.out.println("generationOfbase1");

        generationOfbase1(50);

        System.out.println();

        System.out.println("generationOfbase2");

        generationOfbase2(50);

    }
    //---------------------------------------------------------------------------/checker
}
