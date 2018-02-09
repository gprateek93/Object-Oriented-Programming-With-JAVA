import java.util.Scanner;

public class FractionCalculator {
    public static void main(String[] args){
        calculator();
    }
    //Method to get from the user which operation he/she wants to perform.
    public static String getOperation(){
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter an operation (+,-,/,*,= and q or Q to quit)");
        String op = input.next();
        return op;
    }
    //Method to check the validity of the fraction, i.e whether the entered string is a valid number of p/q form or not.
    public static boolean validFraction(String s){
        if(s.indexOf('/')==-1){
            if(isNumber(s))
                return true;
            else
                return false;
        }
        else{
            String num = s.substring(0,s.indexOf('/'));
            String den = s.substring(s.indexOf('/')+1);
            if(num.equals("") || den.equals("") || !isNumber(num) || !isNumber(den) || Integer.parseInt(den)==0)
                return false;
            else
                return true;
        }

    }
    //Helper function of the validFraction function to check if the given string is made of numbers only or not.
    public static boolean isNumber(String s){
        try{
            int i = Integer.parseInt(s);
            return true;
        }
        catch(NumberFormatException er){
            return false;
        }
    }
    //Method to get the fractions on which he/she wants to perform the operation.
    public static Fraction getFraction(){
        Scanner input = new Scanner(System.in);
        while(true) {
            System.out.print("Please enter a fraction(a/b) or integer(a)");
            String s = input.next();
            if(validFraction(s)){
                int numerator, denominator;
                if(s.indexOf('/')==-1){
                    numerator = Integer.parseInt(s);
                    denominator = 1;
                }
                else{
                    String num = s.substring(0,s.indexOf('/'));
                    String den = s.substring(s.indexOf('/')+1);
                    numerator = Integer.parseInt(num);
                    denominator = Integer.parseInt(den);
                }
                Fraction frac = new Fraction(numerator,denominator);
                return frac;
            }
            else{
                System.out.println("Invalid fraction. Please enter (a/b) or(a), where a and b are integers and b is not zero");
            }
        }
    }
    //The main driver function which puts all the above functions of the fraction calculator together to perform arithmetic operations on the given fractions.
    public static void calculator(){
        System.out.println("This program is a fraction calculator.\nIt will add,subtract,multiply,divide fractions until you type q or Q to quit\nPlease enter your fractions in the form a/b, where a and b are integers");
        while(true){
            System.out.println("------------------------------------------------------------------------------");
            String operation = getOperation();
            if(operation.equals("q") || operation.equals("Q"))
                break;
            Fraction a = getFraction();
            Fraction b = getFraction();
            Fraction res;
            if(operation.equals("+")) {
                res = a.add(b);
                System.out.println(a.toString() + " + "+b.toString()+ " = "+res.toString());
            }
            else if(operation.equals("-")) {
                res = a.subtract(b);
                System.out.println(a.toString() + " - "+b.toString()+ " = "+res.toString());
            }
            else if(operation.equals("*")) {
                res = a.multiply(b);
                System.out.println(a.toString() + " * " + b.toString() + " = " + res.toString());
            }
            else if(operation.equals("/")) {
                res = a.divide(b);
                System.out.println(a.toString() + " / "+b.toString()+ " = "+res.toString());
            }
            else if(operation.equals("=")){
                if(a.equals(b))
                    System.out.println(a.toString()+ " = "+ b.toString());
                else
                    System.out.println(a.toString()+ " != "+ b.toString());
            }
        }
    }
}
