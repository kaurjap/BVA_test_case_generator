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
    // to store the list of variables that the user enters
    ArrayList <Variable> vars = new ArrayList <Variable> ();


    /**
     * main method
     */
    public static void main (String[] args) {

        Main program = new Main ();

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
            program.vars.add (var);
        } // end for

        program.generate_test_cases (test_choice, n);

    } // end main


    // constructor for the Main class
    public Main () {
        // do nothing
    } 


    public int ask_test_choice () {
        int choice = -1;
        boolean keepgoing = true;
        while (keepgoing) {
            System.out.println ("Please choose the type of testing: \n(0) Boundary Value Testing \n(1) Robustness Testing \n(2) QUIT");
            choice = this.get_integer_input ();
            if (choice != 0 && choice != 1 && choice != 2) {
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


    public void print (ArrayList <Integer> test_case) {
        String output_string = "";
        for (int k = 0; k < test_case.size (); k++) {
            output_string += test_case.get (k) + " ";
        } // end for
        System.out.println (output_string);
    } // end print


    /**
     * This method provides the implementation of the BVA and robustness testing algorithms that generate the test cases.
     * 
     * @param test_choice
     * @param n
     */
    public void generate_test_cases (int test_choice, int n) {
        System.out.println ("--------- Test Cases -----------");

        int factor = 0;
        if (test_choice == 0) {
            // bva testing
            factor = 4;
        } else if (test_choice == 1) {
            // robustness testing
            factor = 6;
        } // end if-else

        int boundary_variable = 0;
        int counter = 0;
        for (int i = 0; i < factor*n + 1; i++) {
            // i(th) variable here is the one that changes in each iteration
            ArrayList <Integer> test_case = new ArrayList <Integer> ();
            if (i != 0 && i < factor*n) {
                if (i % factor == 0) {
                    boundary_variable = boundary_variable + 1;
                    counter = 0;
                } // end if
            } // end if

            if (i == factor*n) {
                // reset boundary variable value
                boundary_variable = -1;
            } // end if

            // now that we know the variable index that uses a boundary value
            // just add all the nominals first, and then add the boundary value at the index known
            for (int j = 0; j < n; j++) {
                // j = test case number
                if (j != boundary_variable) {
                    // this variable uses its nominal value
                    test_case.add (vars.get (j).nominal ());
                } else if (j == boundary_variable) {
                    // this variable uses one of its boundary values i.e. min, min+, max-, max
                    // that boundary value gets added to the test_case array at position i because it is the i'th variable
                    test_case.add (vars.get (j).get_test_value_at (counter));
                    counter ++;
                } // end if-else
            } // end inner for

            print (test_case);
        } // end for
    } // end 

} // end class Main