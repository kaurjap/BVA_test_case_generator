import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main class
 * 
 * Includes the beginning point of the program, and is the class that interacts directly with the user 
 */
public class Main {

    // scanner instance for input
    Scanner input = new Scanner (System.in);

    /**
     * main method
     */
    public static void main (String[] args) {

        Main program = new Main ();

        // to store the list of variables that the user enters
        ArrayList <Variable> vars = new ArrayList <Variable> ();

        // ask for user's testing choice
        int test_choice = program.ask_test_choice ();
        // ask for number of variables
        int n = program.ask_num_variables ();
        
        // ask for the range of variables
        for (int i = 0; i < n; i++) {
            System.out.println ("Please enter the range for variable " + i + ": ");
            System.out.println ("Min value: ");
            int min = program.get_integer_input();
            System.out.println ("Max value: ");
            int max = program.get_integer_input();
            
            // create a variable with these properties
            Variable var = new Variable (min, max);
            var.setTestValues (test_choice);
            vars.add (var);
        } // end for


        int boundary_value_index = 0;
        for (int i = 0; i < 4*n; i++) {
            // i(th) variable here is the one that changes in each iteration
            ArrayList <Integer> test_case = new ArrayList <Integer> ();
            if (i != 0 && i % 3 == 1) {
                boundary_value_index = boundary_value_index + 1;
            } // end if
            
            // now that we know the variable index that uses a boundary value
            // just add all the nominals first, and then add the boundary value at the index known
            for (int j = 0; j < n; j++) {
                // j = test case number
                if (j != boundary_value_index) {
                    // this variable uses its nominal value
                    test_case.add (vars.get (j).nominal ());
                } else if (j == boundary_value_index) {
                    // this variable uses one of its boundary values i.e. min, min+, max-, max
                    // that boundary value gets added to the test_case array at position i because it is the i'th variable
                    test_case.add (vars.get (j).get_test_value_at (boundary_value_index));
                } // end if-else
            } // end inner for

            // print the current test_case
            String output_string = "";
            for (int k = 0; k < n; k++) {
                output_string += test_case.get (k) + " ";
            } // end for
            System.out.println (output_string);

        } // end inner for



        // output the test cases using standard output
        for (int i = 0; i < n; i++) {
            // i(th) variable here is also the one that changes in each iteration
            //String test_case = "";
            
            for (int j = 0; j < n; j++) {
                if (j == i) {
                    // j(th) variable's value is the one changing
                    // meaning, for each test case, change j'th input value
                    for (int k = 0; k < 4; k++) {
                        
                    } // end for
                }
            } // end inner for

        } // end for





    } // end main


    // constructor for the Main class
    public Main () {
        // do nothing
    } 

    /**
     * method that asks the user for the type of testing to perform
     * @return 
     *      0   int    Boundary Value Testing
     *      1   int    Robustness Testing
     *      2   int    QUIT
     */
    public int ask_test_choice () {
        int choice = -1;
        boolean keepgoing = true;
        while (keepgoing) {
            System.out.println ("Please choose the type of testing: \n(0) Boundary Value Testing \n(1) Robustness Testing \n(2) QUIT");
            choice = this.get_integer_input ();
            if (choice != 0 || choice != 1 || choice != 2) {
                System.out.println ("Please enter a valid input.");
            } else {
                keepgoing = false;
            } // end if-else
        } // end while
        return choice;
    } // end getTestingType



    public int ask_num_variables () {
        int n = 0;
        System.out.println ("Please enter the number of variables: ");
        boolean keepgoing = true;
        while (keepgoing) {
            n = this.get_integer_input ();
            // if an exception does not occur
            if (n > 0) {
                keepgoing = false;
            } else {
                System.out.println ("The number of variables to test has to be greater than zero.");
            } // end if-else
        } // end while
        return n;
    } // end method get_input_num_variables 


    public int get_integer_input () {
        int val = -1;
        try {
            val = input.nextInt ();
        } catch (Exception e) {
            System.out.println ("An exception occurred in getting input: " + e.getMessage ());
        } // end try-catch
        return val;
    } // end method get_integer_input ()

} // end class Main