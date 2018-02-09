//Class Fraction represents those rational numbers of the form p/q where p and q are integers and q is not 0.
public class Fraction {
    //fields of the class viz. numerator and denominator.
    private int numerator;
    private int denominator;
    //Methods of the class
    //Parameterized constructor of the class which constructs an object of class with the given numerator and denominator.
    public Fraction(int numerator, int denominator){
        if(denominator == 0){
            throw new IllegalArgumentException("Argument 'Denominator' is 0");
        }
        else{
            if(denominator<0){
                if(numerator<0){
                    this.numerator = Math.abs(numerator);
                    this.denominator = Math.abs(denominator);
                }
                else{
                    this.numerator = -1*numerator;
                    this.denominator = Math.abs(denominator);
                }
            }
            else {
                this.numerator = numerator;
                this.denominator = denominator;
            }
        }
    }
    //Parameterized constructor(overloaded) for fractions with denominator = 1
    public Fraction(int numerator){
        this.numerator = numerator;
        this.denominator = 1;
    }
    //Default overloaded constructor for initializing an object of class with no specified numerator and denominator.
    public Fraction(){
        this.numerator = 0;
        this.denominator = 1;
    }
    //Accessor method to get the private member i.e numerator of the object.
    public int getNumerator(){
        return this.numerator;
    }
    //Accessor method to get the private member i.e denominator of the object.
    public int getDenominator(){
        return this.denominator;
    }
    //Method to return the string representation of the given fraction object.
    public String toString(){
        String fraction = String.valueOf(this.numerator)+"/"+String.valueOf(this.denominator);
        return fraction;
    }
    //Method to get the decimal representation of the fraction object.
    public double toDouble(){
        double result = (double)(this.numerator)/this.denominator;
        return result;
    }
    //Method to add two fractions.
    public Fraction add(Fraction other){
        int numerator = this.numerator * other.getDenominator() + other.getNumerator()*this.denominator;
        int denominator = this.denominator* other.getDenominator();
        Fraction result = new Fraction(numerator,denominator);
        result.toLowestTerms();
        return result;
    }
    //Method to subtract two fractions.
    public Fraction subtract(Fraction other){
        int numerator = this.numerator * other.getDenominator() - other.getNumerator()*this.denominator;
        int denominator = this.denominator * other.getDenominator();
        Fraction result = new Fraction(numerator,denominator);
        result.toLowestTerms();
        return result;
    }
    //Method to multiply two fractions.
    public Fraction multiply(Fraction other){
        int numerator = this.numerator * other.getNumerator();
        int denominator = this.denominator * other.getDenominator();
        Fraction result = new Fraction(numerator,denominator);
        result.toLowestTerms();
        return result;
    }
    //Method to divide two fractions.
    public Fraction divide(Fraction other){
        if(other.denominator == 0 || !(other instanceof Fraction)){
            throw  new IllegalArgumentException("Argument 'divisor' equal to 0 or not a valid Fraction");
        }
        int numerator = this.numerator / other.getNumerator();
        int denominator = this.denominator / other.getDenominator();
        Fraction result = new Fraction(numerator,denominator);
        result.toLowestTerms();
        return result;
    }
    //Method to find the highest common factor(Greatest common divisor) of two numbers.
    public static int gcd(int num, int den){
        if(num % den == 0)
            return den;
        else
            return gcd(den,num%den);
    }
    //Method to simplify the given fractions in lowest terms.
    public void toLowestTerms(){
        int hcf = gcd(this.numerator, this.denominator);
        this.numerator /=Math.abs(hcf);
        this.denominator /=Math.abs(hcf);
    }
    //Method to see if two fractions are equal.
    public boolean equals(Fraction other){
        if(other instanceof Fraction){
            Fraction a = new Fraction(this.numerator,this.denominator);
            Fraction b = new Fraction(other.getNumerator(),other.getDenominator());
            a.toLowestTerms();
            b.toLowestTerms();
            if(a.numerator == b.numerator && a.denominator == b.denominator)
                return true;
            else
                return false;
        }
        else
            return false;
    }
}
