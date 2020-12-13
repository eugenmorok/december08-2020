import java.util.Scanner;

public class r1
{
    public static Scanner in = new Scanner(System.in);


    public static void main(String[] args)
    {

        System.out.println( "\nWork with Strings\n" );

        System.out.println( "\nEnter string = " );
        String enteredString = "";



        System.out.print("\nEnter one character = ");
        char enteredChar = in.next().charAt(0);


        System.out.println
                (
                        "\nThe entered character belongs to the entered string: " +
                                isTheCharacter ( enteredString, enteredChar )
                );



        in.close ();

        System.out.println("\nThe end of the program");
    }


    static boolean isTheCharacter ( String inputString, char theCharacter )
    {
        for ( int i = 0; i < inputString.length(); i++ )

            if ( inputString.charAt(i) == theCharacter )

                return true;

        return false;
    }

}