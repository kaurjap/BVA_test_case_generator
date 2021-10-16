import java.util.ArrayList;

/**
 *  @class Variable
 * 
 *  This class holds all the important information about each test variable to be used in the BVA and Robustness Testing techniques.
 */
public class Variable {

    protected int max;
    protected int min;

    // following includes values [min-, min, min+, max-, max, max+] depending on the type of testing performed with the variable
    protected ArrayList<Integer> test_values;        // does not include the nominal as it functions separately in the logic

    // constructor
    public Variable () {
        // do nothing
        test_values = new ArrayList<Integer> ();
    }

    // initializing constructor
    public Variable (int min, int max) {
        this.min = min;
        this.max = max;
        test_values = new ArrayList<Integer> ();
    }

    public int range () {
        return this.max - this.min;
    }

    public int min_minus () {
        return this.min - 1;
    }

    public int min () {
        return this.min;
    }

    public int min_plus () {
        return this.min + 1;
    }

    public int nominal () {
        return (int) (this.max - this.min) / 2;       // DOUBT: what if this is a decimal?
    }

    public int max_minus () {
        return this.max - 1;
    }

    public int max () {
        return this.max;
    }

    public int max_plus () {
        return this.max + 1;
    }

    /**
     * method to populate the test values array based on the test choice
     * 
     * parameter:   test_choice        0    if BVA testing
     *                                 1    Robustness testing    
     *
    */
    public void setTestValues (int test_choice) {
        if (test_choice == 0) {
            // BVA testing
            test_values.add (this.min ());
            test_values.add (this.min_plus ());
            test_values.add (this.max_minus ());
            test_values.add (this.max ());

        } else if (test_choice == 1) {
            // Robustness testing
            test_values.add (this.min_minus ());
            test_values.add (this.min ());
            test_values.add (this.min_plus ());
            test_values.add (this.max_minus ());
            test_values.add (this.max ());
            test_values.add (this.max_plus ());

        } // end if-else
    } // end method setTestValues ()


    public int get_test_value_at (int index) {
        return this.test_values.get (index);
    } // end get_test_value_at

} // end class definition for Variable
