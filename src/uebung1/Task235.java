package uebung1;

public class Task235 {

	public static double runden(double number, int mantisse) {
		boolean isNegativ = false;
		if (Math.signum(number) < 0) { /*Workaround for negative numbers*/
			number = number * -1;
			mantisse++;
			isNegativ = true;
		}
		double exp = Math.abs(Math.ceil(Math.log10(number)));
		double d = Math.pow(10, mantisse - exp);
		double dd = Math.round(number * d) / d;
		if (isNegativ) dd = dd * -1; /*Workaround for negative numbers*/
		return dd;
	}

	public static double integralFunktion(int n, int mantisse) {
		double d = 0d;
		double ii = 0d;

		for (int i = 0; i <= n; i++) {
			if (i == 0) {
				double x = Math.log(6d/5d);
				d = Task235.runden(x, mantisse);
				ii++;
			} else {
				d = Task235.runden((-5 * d) + (1 / ii), mantisse);
				ii++;
			}
			System.out.println("n" + i + ", d: " + d);
		}
		return d;
	}

	public static double add1WithRound(double a, double b, double c, int mantisse) {
		return Task235.runden(a, mantisse) + (Task235.runden(b, mantisse) + Task235.runden(c, mantisse));
	}

	public static double add1WithoutRound(double a, double b, double c, int mantisse) {
		double d = a + (b + c);
		System.out.println("d1: " + d);
		return Task235.runden(d, mantisse);
	}
	public static double add1WithoutRound(double a, double b, double c) {
		return a + (b + c);
	}

	public static double add2WithRound(double a, double b, double c, int mantisse) {
		return (Task235.runden(a, mantisse) + Task235.runden(b, mantisse)) + Task235.runden(c, mantisse);
	}

	public static double add2WithoutRound(double a, double b, double c, int mantisse) {
		double d = (a + b) + c;
		System.out.println("d2: " + d);
		return Task235.runden(d, mantisse);
	}
	public static double add2WithoutRound(double a, double b, double c) {
		return (a + b) + c;
	}

	public static double absoluterRundungsfehler(double number, double roundedNumber) {
		return Math.abs(roundedNumber - number);
	}

	public static double relativerRundungsfehler(double number, double roundedNumber) {
		return Math.abs(roundedNumber - number) / number;
	}

	public static void main(String[] args) {
		System.out.println("---------------------------");
		System.out.println("-       Aufgabe 2         -");
		System.out.println("---------------------------");

		double n1 = 0.023456589;
		int m1 = 6;

		System.out.println("Number: " + n1 + " , Mantisse: " + m1);
		System.out.println("Rounded Number: " + Task235.runden(n1, m1) + "\n");

		double n2 = 123.023456589;
		int m2 = 5;

		System.out.println("Number: " + n2 + " , Mantisse: " + m2);
		System.out.println("Rounded Number: " + Task235.runden(n2, m2) + "\n");

		double n3 = -123.023456589E-5;
		int m3 = 10;

		System.out.println("Number: " + n3 + " , Mantisse: " + m3);
		System.out.println("Rounded Number: " + Task235.runden(n3, m3) + "\n");

		System.out.println("---------------------------");
		System.out.println("-       Aufgabe 3         -");
		System.out.println("---------------------------");

		int n4 = 20;
		int m4 = 12;

		System.out.println("Result Integral-Funktion: " + Task235.integralFunktion(n4, m4) + "\n");

		System.out.println("---------------------------");
		System.out.println("-       Aufgabe 5         -");
		System.out.println("---------------------------");

		double a = 0.23371258E-4;
		double b = 0.33678429E+2;
		double c = -0.33677811E+2;
		int m5 = 6;

		double resultAdd1WithRound = Task235.add1WithRound(a, b, c, m5);
		double resultAdd1WithoutRound = Task235.add1WithoutRound(a, b, c);

		System.out.println("Method Add #1 with Rounding: " + resultAdd1WithRound);
		System.out.println("Method Add #1 without Rounding: " + resultAdd1WithoutRound + "\n");

		double absoluterRundungsfehler1 = Task235.absoluterRundungsfehler(resultAdd1WithoutRound, resultAdd1WithRound);
		double relativerRundungsfehler1 = Task235.relativerRundungsfehler(resultAdd1WithoutRound, resultAdd1WithRound);

		System.out.println("Absoluter Rundungsfehler Method Add #1: " + absoluterRundungsfehler1);
		System.out.println("Relativer Rundungsfehler Method Add #1: " + relativerRundungsfehler1 + "\n");

		double resultAdd2WithRound = Task235.add2WithRound(a, b, c, m5);
		double resultAdd2WithoutRound = Task235.add2WithoutRound(a, b, c);

		System.out.println("Method Add #2 with Rounding: " + resultAdd2WithRound);
		System.out.println("Method Add #2 without Rounding: " + resultAdd2WithoutRound + "\n");

		double absoluterRundungsfehler2 = Task235.absoluterRundungsfehler(resultAdd2WithoutRound, resultAdd2WithRound);
		double relativerRundungsfehler2 = Task235.relativerRundungsfehler(resultAdd2WithoutRound, resultAdd2WithRound);

		System.out.println("Absoluter Rundungsfehler Method Add #2: " + absoluterRundungsfehler2);
		System.out.println("Relativer Rundungsfehler Method Add #2: " + relativerRundungsfehler2 + "\n");
	}   
}
