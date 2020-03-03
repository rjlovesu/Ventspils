package o1_intro;

import java.util.Scanner;

public class Demo1 
{
	public static void main(String[] args) 
	{
		try (Scanner in = new Scanner(System.in)) {
			System.out.println("Please enter a number: ");
			int a = in.nextInt();

			if (a % 2 == 0) {
				System.out.printf("%d is even", a);
			} else {
				System.out.printf("%d is odd", a);
			}

			System.out.println();
			System.out.println("done");
		}
	}

}
