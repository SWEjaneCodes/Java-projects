import java.util.Objects;

/*
this class describes and defines a new type to represent a rational number
a rational number is one that can written as the ratio of two integers, a numerator and a denominator
such that the denominator is non-zero
this class can be used by "clients" to create Rational number objects for their programs
 */
public class Rational implements Comparable<Rational>{
    //******Data variables********
    //these are typically "instance" variables that don't exist until individual
    //objects are created.  each object gets its own copy of each of these variable
    //these variables are usually declared as private, which means they are only accessible to code
    //within this class.  the client code can only work through public methods that are provided
    //the variables in this case are final, which means they cannot be changed once they are set
    //this makes the Rational number objects immutable
    private final int numerator;
    private final int denominator;


    //******Methods**************
    //these are typically public methods that client code can call to create, access, and manipulate
    //the Rational number objects.  the client can only do what the methods provide

    //we usually start with "constructor" methods.  these are the methods called, using the new keyword,
    //to create a new object, to make a specific instance from this class
    //constructor methods have no return type, not even void (they return the new object itself), and
    //are always named using the name of the class

    //this constructor takes in two int parameters and uses them to initialize the numerator
    //and denominator of a new Rational object
    public Rational(int n, int d) {
        //need to ensure the denominator is not 0
        //if it is, immediately terminate the constructor with an exception
        if (d == 0) {
            throw new IllegalArgumentException("can't construct a new rational number with denominator 0");
        }

        //check for negative sign in the denominator
        //"moves" the negative to the numerator and cancels the negative if its
        //present in both numerator and denominator
        if (d < 0) {
            n = -n;
            d = -d;
        }


        /*first reduce n and d to "lowest terms" by dividing out their greatest common divisor
        "moves" the negative
         */
        int divisor = gcd(Math.abs(n), Math.abs(d));
        n /= divisor;
        d /= divisor;
        numerator = n;
        denominator = d;
    }

    /*this is a private "helper" method
    this method can only be called from within
    this Rational class
    the method computes the greatest common
    divisor of the given ints a and b
    returns the divisor. note: the divisor
    should always be at least 1
    this method uses the euclidean algorithm recursively to find the GCD
    note: we assume both a and b are non-negative with b non-zero
    */
    private int gcd(int a, int b) {
        int r = a % b; //find the remainder after dividing a by b
        if (r == 0) {
            return b; //base case
        }
        else {
            return gcd(b, r);
        }
    }

    public double toDouble() {
        return (double)numerator / (double)denominator; //force floating point division using casting
    }
    @Override
    //this method overrides the default version which just gives back the memory address
    public String toString() {
        if (denominator != 1) {
            return numerator + "/" + denominator; //create a printable String version of the fraction
        }
        else {
            return Integer.toString(numerator);
        }
    }

    //getter methods, these can be used by the client to "read" the current numerator and denominator values
    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    //this is what a typical setter method looks like.  this is used by the client to "write" data, to change
    //a value in the object
    //we don't actually include this method, because we want our Rational objects to be "immutable"
//    public void setNumerator(int numerator) {
//        this.numerator = numerator;
//    }


    //some basic arithmetic methods

    //this method will be used to add two Rational numbers together and return
    //the sum as a new Rational number object
    public Rational plus(Rational that) {
        int answerNumerator = this.numerator * that.denominator +
                that.numerator * this.denominator;
        int answerDenominator = this.denominator * that.denominator;
        //create and return a new Rational object containing these computed pieces
        return new Rational(answerNumerator,answerDenominator);
    }
    //method to subtract the given rational numbers
    public Rational minus(Rational that) {
        int answerNumerator = this.numerator*that.denominator -
                that.numerator*this.denominator;
        int answerDenominator = this.denominator * that.denominator;
        //create and return a new Rational object containing these computed pieces
        return new Rational(answerNumerator,answerDenominator);
    }
//computes the product of the given rational numbers
    public Rational times(Rational that) {
        int answerNumerator = this.numerator*that.numerator;
        int answerDenominator = this.denominator * that.denominator;
        //create and return a new Rational object containing these computed pieces
        return new Rational(answerNumerator,answerDenominator);
    }

    //computes the quotient of the given rational number
    public Rational dividedBy(Rational that) {
        int answerNumerator = this.numerator*that.denominator;
        int answerDenominator = this.denominator * that.numerator;
        //create and return a new Rational object containing these computed pieces
        return new Rational(answerNumerator,answerDenominator);
    }

    @Override
    //this method is the implementation of the method specified n the comparable interface
    //this method will allow us to "sort" Rational number objects
    public int compareTo(Rational o) {
        //find the numerator fr each rational after converting to common denominator
        int newNumerator1 = this.numerator * o.denominator;
        int newNumerator2 = o.numerator * this.denominator;
        //if this comes before o return a negative
        if (newNumerator1 < newNumerator2) {
            return -1;
        }
        //if this comes after o return a positive
        else if (newNumerator1 > newNumerator2) {
            return 1;
        }
        //itherwise, they must be equal, so return 0
        else {
            return 0;
        }
    }

    //these methods overrides the default versions that Java provodes
    //that just look at memory addresses instead of what is inside the object
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Rational rational = (Rational) o;
        return numerator == rational.numerator && denominator == rational.denominator;
    }

    //hash code are used in Hash Tables...take CS 2 and you will learn all
    //about them...
    @Override
    public int hashCode() {
        return Objects.hash(numerator, denominator);
    }

    //computes the quotient of two rational numbers

} //end of the Rational class
