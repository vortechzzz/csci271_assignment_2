/*************************************************************************
* Assignment 2 for CSCI 271-001 Spring 2026
*
* Author: Zach Allman
* OS: Ubuntu 24.04.3 LTS
* Compiler: javac 21.0.9
* Date: February 6 2026
*
* Purpose
* This program...
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

	public Fraction(long num)
        {
                numerator = num;
                denominator = 1;
        }

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

		//Test Case: Zero as the numerator
		System.out.println("new Fraction(0, 8) = " + new Fraction(0, 8));

		//Test Case: Verify GCD (greatest common denominator) works correctly
		System.out.println("new Fraction(9, 12) = " + new Fraction(9, 12));

		//Test Case: Negative denominator + GCD
		System.out.println("new Fraction(6, -24) = " + new Fraction(6, -24));

		//Test Case: Whole number
		System.out.println("new Fraction(67) = " + new Fraction(67));

		//Test Case: Negative Whole number
		System.out.println("new Fraction(-67) = " + new Fraction(-67));

		//Task 2:

		//Task 3:
	}
}





