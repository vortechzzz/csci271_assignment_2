/*************************************************************************
* Assignment 2 for CSCI 271-001 Spring 2026
*
* Author: Zach Allman
* OS: Ubuntu 24.04.3 LTS
* Compiler: javac 21.0.9
* Date: February 6 2026
*
* Purpose
* This program implements a Fraction data structure that gives exact calculations by storing values as fractions
* instead of floating-point numbers. The Fraction class has addition, subtraction, multiplication, division, negation
* and exponentation while keeping the fractions in normalized and reduced form.
*
*************************************************************************/
/*************************************************************************
*
* I declare and confirm the following:
* - I have not discussed this program code with anyone other than my
* instructor or the teaching assistants assigned to this course.
* - I have not used programming code obtained from someone else,
* or any unauthorised sources, including the Internet, either
* modified or unmodified.
* - If any source code or documentation used in my program was
* obtained from other sources, like a text book or course notes,
* I have clearly indicated that with a proper citation in the
* comments of my program.
* - I have not designed this program in such a way as to defeat or
* interfere with the normal operation of the supplied grading code.
*
* Zach Allman
********************************************************************/


class Fraction
{
	private long numerator;
	private long denominator;

/****************************Fraction(long, long)***********************
* Description: Constructor that creates a new Fraction from a numerator
*              and denominator, and automatically reduces it to the lowest terms
*              and normalizes the sign
*
* Parameters: num: the numerator of the fraction
*             denom: the denominator of the fraction
*
* Pre: None
*
* Post: A new Fraction object is created in reduced, normalized form
*       where the denominator is non-negative and the fraction is
*       reduced by their GCD
*
* Returns: N/A
*
* Called by: main, add, subtract, multiply, divide, pow, negate
* Calls: gcd
*******************************************************************/

	public Fraction(long num, long denom)
	{
		//Handles 0/0 (NaN (not a number))
		if(num == 0 && denom == 0)
		{
			numerator = 0;
			denominator = 0;
			return;
		}

		//Handles n/0 (Infinity or -Infinity)
		if (denom == 0)
		{
			//Keeps the sign on the numerator, which is set to 1 or -1
			numerator = (num > 0) ? 1 : -1;
			denominator = 0;
			return;
		}

		//Handles 0/n (zero)
		if (num == 0)
		{
			numerator = 0;
			denominator = 1;
			return;
		}

		//Normalizes the sign, if denom is - then flip both
		if (denom < 0)
		{
			num = -num;
			denom = -denom;
		}

		//Finds the GCD and reduces the fraction
		long divisor = gcd(num, denom);
		numerator = num / divisor;
		denominator = denom / divisor;
	}

/****************************Fraction(long)****************************
* Description: Constructor that creates a new Fraction from a whole
*              number (denominator defaults to 1)
*
* Parameters: num: the whole number value
*
* Pre: None
*
* Post: A new Fraction object is created with the given numerator
*       and denominator of 1
*
* Returns: N/A
*
* Called by: main
* Calls: None
*******************************************************************/

	public Fraction(long num)
        {
                numerator = num;
                denominator = 1;
        }

/****************************gcd****************************************
* Description: Calculates the greatest common divisor of two numbers
*              using Euclid's algorithm
*
* Parameters: a: first number
*             b: second number
*
* Pre: None (handles negative numbers and zero)
*
* Post: Returns the GCD of the absolute values of a and b
*       Returns 1 if both inputs are 0
*
* Returns: long: the greatest common divisor
*
* Called by: Fraction(long, long)
* Calls: None
*******************************************************************/

	private static long gcd(long a, long b)
	{
		if(a < 0)
		{
			a = -a;
		}

		while (b != 0)
		{
			long remainder = a % b;
			a = b;
			b = remainder;
		}

		if(a == 0)
		{
			a = 1;
		}

		return a;
	}

	public long getNumerator()
        {
                return numerator;
        }

        public long getDenominator()
        {
                return denominator;
        }

        public Fraction add(Fraction inFraction)
        {
                //adding the fractions occurs as follows:
		// a/b + c/d = (a*d + c*b) / (b*d)
		long newNumerator = this.getNumerator() * inFraction.getDenominator()
				  + inFraction.getNumerator() * this.getDenominator();

		long newDenominator = this.getDenominator() * inFraction.getDenominator();

		return new Fraction(newNumerator, newDenominator);
        }

        public Fraction subtract(Fraction inFraction)
        {
                //subtracting the fractions occurs as follows:
		// a/b - c/d = (a*d - c*b) / (b*d)
		long newNumerator = this.getNumerator() * inFraction.getDenominator()
				  - inFraction.getNumerator() * this.getDenominator();

		long newDenominator = this.getDenominator() * inFraction.getDenominator();

		return new Fraction(newNumerator, newDenominator);
        }

        public Fraction multiply(Fraction inFraction)
        {
                //multiplying the fractions occurs as follows:
		// a/b * c/d = (a*c) / (b*d)
		long newNumerator = this.getNumerator() * inFraction.getNumerator();

		long newDenominator = this.getDenominator() * inFraction.getDenominator();

		return new Fraction(newNumerator, newDenominator);
	}

        public Fraction divide(Fraction inFraction)
        {
		//dividing the fractions occurs as follows:
		// (a/b) / (c/d) = (a*d) / (b*c)
		long newNumerator = this.getNumerator() * inFraction.getDenominator();

		long newDenominator = this.getDenominator() * inFraction.getNumerator();

		return new Fraction(newNumerator, newDenominator);
        }

	public Fraction pow(int n)
        {
		Fraction base;
		int exponent;

		//This case handles 0/0 (NaN), which raised to any power is (NaN)
                if(this.getNumerator() == 0 && this.getDenominator() == 0)
		{
			return new Fraction(0, 0);
		}

		//Handles if any number to power of 0 is 1
		if(n == 0)
		{
			return new Fraction(1, 1);
		}

		//Handles if the exponent is negative, the fraction gets inverted
		if(n < 0)
		{
			base = new Fraction(this.getDenominator(), this.getNumerator());
			exponent = -n;
		}

		else
		{
			base = new Fraction(this.getNumerator(), this.getDenominator());
			exponent = n;
		}

		//Figures out the power by repeated multiplication
		Fraction result = new Fraction(1, 1);
		for (int i = 0; i < exponent; i++)
		{
			result = result.multiply(base);
		}

		return result;
	}

        public Fraction negate()
        {
		return new Fraction(-this.getNumerator(), this.getDenominator());
        }


	public String toString()
	{
		String output = "";

		//Handles 0/0 (NaN)
		if (getNumerator() == 0 && getDenominator() == 0)
		{
			output = "NaN";
		}

		//Handles positive num/0 (Infinity)
		else if (getDenominator() == 0 && getNumerator() > 0)
		{
			output = "Infinity";
		}

		//Handles negative num/0 (Negative infinity)
		else if (getDenominator() == 0 && getNumerator() < 0)
		{
			output = "-Infinity";
		}

		//If denom is 1, return num
		else if (getDenominator() == 1)
		{
			output = String.valueOf(getNumerator());
		}

		//Return num/denom
		else
		{
			output = getNumerator() + "/" + getDenominator();
		}

		return output;
	}
}

public class CSCI271_Assignment2_ZachAllman
{

	public static void main (String[] args) //Main function
	{

		//Task 1:
		System.out.println("Task 1: ");

		//extra space
                System.out.println("***************");

		//Test Case: Zero as the numerator
		System.out.println("new Fraction(0, 8) = " + new Fraction(0, 8)); //answer: 0

		//Test Case: Verify GCD (greatest common denominator) works correctly
		System.out.println("new Fraction(9, 12) = " + new Fraction(9, 12)); //answer: 3/4

		//Test Case: Negative denominator + GCD
		System.out.println("new Fraction(6, -24) = " + new Fraction(6, -24)); //answer: -1/4

		//Test Case: Whole number
		System.out.println("new Fraction(67) = " + new Fraction(67)); //answer: 67

		//Test Case: Negative Whole number
		System.out.println("new Fraction(-67) = " + new Fraction(-67)); //answer: -67

		//extra space
		System.out.println("***************");

		//Task 2:
		System.out.println("Task 2:");

		//extra space
                System.out.println("***************");

		//Test Case: Fraction with a negative
		System.out.println("new Fraction(8, -6) = " + new Fraction(8, -6)); //answer: -4/3

		//Test Case: Infinity
		System.out.println("new Fraction(23, 0) = " + new Fraction(23, 0)); //answer: Infinity

		//Test Case: Negative Infinity
		System.out.println("new Fraction(-6, 0) = " + new Fraction(-6, 0)); //answer: -Infinity

		//Test Case: Whole number
		System.out.println("new Fraction(7, 1) = " + new Fraction(7, 1)); //answer: 7

		//Test Case: Negative Whole number
		System.out.println("new Fraction(-7, 1) = " + new Fraction(-7, 1)); //answer: -7

		//Test Case: NaN (not a number)
		System.out.println("new Fraction(0, 0) = " + new Fraction(0, 0)); //answer: NaN

		//extra space
		System.out.println("***************");

		//Task 3:
		System.out.println("Task 3: ");

 		//extra space
                System.out.println("***************");

		//Adding
		System.out.println("Add test cases: ");

		//Adding test case: Adding 2 regular fractions together
		System.out.println("2/3 + 6/7 = " + new Fraction(2, 3).add(new Fraction(6, 7))); //answer: 32/21

		//Adding test case: Adding a regular fraction and whole number
		System.out.println("4/5 + 10 = " + new Fraction(4, 5).add(new Fraction(10))); //answer: 54/5

		//Adding test case: Adding 2 improper fractions
		System.out.println("9/5 + 8/3 = " + new Fraction(9, 5).add(new Fraction(8, 3))); //answer: 67/15

		//extra space
                System.out.println("***************");

		//Subtracting
		System.out.println("Subtracting test cases: ");

		//Subtracting test case: Subtracting 2 regular fractions
		System.out.println("3/4 - 1/4 = " + new Fraction(3, 4).subtract(new Fraction(1, 4))); //answer: 1/2

		//Subtracting test case: Subtracting a regular fraction and whole number
		System.out.println("1/4 - 7 = " + new Fraction(1, 4).subtract(new Fraction(7))); //answer: -27/4

		//Subtracting test case: Subtracting 2 improper fractions
		System.out.println("9/5 - 8/3 = " + new Fraction(9, 5).subtract(new Fraction(8, 3))); //answer: -13/15

		//extra space
                System.out.println("***************");

		//Multiplying
		System.out.println("Multiplying test cases: ");

		//Multiplying test case: Multiplying two regular fractions
		System.out.println("2/3 * 3/4 = " + new Fraction(2, 3).multiply(new Fraction(3, 4))); //answer: 1/2

		//Multiplying test case: Multiplying a negative regular fraction with an improper fraction
		System.out.println("-3/7 * 9/8 = " + new Fraction(-3, 7).multiply(new Fraction(9, 8))); //answer: -27/56

		//Mutliplying test case: Multiplying a regular fraction and a whole number
		System.out.println("1/2 * 25 = " + new Fraction(1, 2).multiply(new Fraction(25))); //answer: 25/2

		//extra space
                System.out.println("***************");

		//Dividing
		System.out.println("Dividing test cases: ");

		//Dividing test case: Dividing two regular fractions
		System.out.println("(2/4) / (5/15) = " + new Fraction(2, 4).divide(new Fraction(5, 15))); //answer: 3/2

		//Dividing test case: Dividing a fraction by zero
		System.out.println("(5/3) / 0 = " + new Fraction(5, 3).divide(new Fraction(0))); //answer: Infinity

		//Dividing test case: Dividing two improper fractions, one positive and one negative fraction
		System.out.println("(9/5) / (-8/3) = " + new Fraction(9, 5).divide(new Fraction(-8, 3))); //answer: -27/40

		//extra space
                System.out.println("***************");

		//Negation
		System.out.println("Negation test cases: ");

		//Negation test case: Negating a regular positive fraction
		System.out.println("negate(1/2) = " + new Fraction(1, 2).negate()); //answer: -1/2

		//Negation test case: Negating a negative regular fraction
		System.out.println("negate(-1/2) = " + new Fraction(-1, 2).negate()); //answer: 1/2

		//Negation test case: Negating infinity
		System.out.println("negate(Infinity) = " + new Fraction(1, 0).negate()); //answer: -Infinity

		//Negation test case: Negating NaN
		System.out.println("negate(0/0) = " + new Fraction(0, 0).negate()); //answer: NaN

		//extra space
                System.out.println("***************");

		//Pow
		System.out.println("Pow test cases: ");

		//Pow test case: A regular fraction to the power of a positive number (in this case 3)
		System.out.println("(6/7)^3 = " + new Fraction(6, 7).pow(3)); //answer: 216/343

		//Pow test case: A regular fraction to the power of 0
		System.out.println("(5/9)^0 = " + new Fraction(5, 9).pow(0)); //answer: 1

		//Pow test case: A regular fraction to the power of a negative number (in this case -4)
		System.out.println("(2/3)^(-4) = " + new Fraction(2, 3).pow(-4)); //answer: 81/16

		//extra space
                System.out.println("***************");

		//Sample code calculation
		//(16 / (3/5 + 7)) * (6/7))
		System.out.println("Sample code calculation: ");
		System.out.println("Calculating: (16 / (3/5 + 7)) * (6/7)) ");

		Fraction a = new Fraction(16);
		Fraction b = new Fraction(3, 5).add(new Fraction(7));
		Fraction c = new Fraction(6, 7);
		Fraction answer = c.multiply(a.divide(b));

		System.out.println("Answer = " + answer); //answer: 240/133
	}
}





