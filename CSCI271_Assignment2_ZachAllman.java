/*************************************************************************
* Assignment 1 for CSCI 271-001 Spring 2026
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
                //logic goes here
        }

        public Fraction subtract(Fraction inFraction)
        {
                //logic goes here
        }

        public Fraction multiply(Fraction inFraction)
        {
                //logic goes here
        }

        public Fraction divide(Fraction inFraction)
        {
                //logic goes here
        }

	public Fraction pow(int n)
        {
                //logic goes here
        }

        public Fraction negate()
        {
                //logic goes here
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

	}
}





