package uebung1;

import uebung1.utils.BinaryRoundUtil;
import uebung1.utils.ConvertUtil;
import uebung1.utils.FloatingPointNumberUtil;

import java.util.List;

public class Task1 {

    public static void main(String[] args) {
        //double number = (1.0 / 17.0);
        double number = 18.4;
        int mantisse = 4;

        System.out.println("---------------------------");
        System.out.println("-       Aufgabe 1         -");
        System.out.println("---------------------------");

        System.out.println("Number: " + number);
        System.out.println("Mantisse: " + mantisse);

        System.out.println("---------------------------");
        String numberAsString = String.valueOf(number);
        String numberArray[] = numberAsString.replace(".", ",").split(",");

        List<String> binaryPreCommaNumber = FloatingPointNumberUtil.convertPreCommaNumberToBinary(numberArray[0]);
        List<String> binaryPostCommaNumber = FloatingPointNumberUtil.convertPostCommaNumberToBinary(numberArray[1], mantisse);

        String binaryNumber = ConvertUtil.convertListToString(binaryPreCommaNumber) + "," + ConvertUtil.convertListToString(binaryPostCommaNumber);
        System.out.println("Binary Number is:\n" + binaryNumber);

        System.out.println("---------------------------");
        String normilizedBinaryNumber = FloatingPointNumberUtil.normalize(binaryNumber);
        System.out.println("Normalized Binary Number is:\n" + normilizedBinaryNumber);

        System.out.println("---------------------------");
        String convertExponent = ConvertUtil.convertListToString(FloatingPointNumberUtil.convertedExponent(FloatingPointNumberUtil.mantissa, true));
        System.out.println("Converted Exponent is:\n" + convertExponent);

        System.out.println("---------------------------");
        String sign = FloatingPointNumberUtil.getSign(Integer.valueOf(numberArray[0]));
        System.out.println("Sign is:\n" + sign);

        System.out.println("---------------------------");
        String mantissa = FloatingPointNumberUtil.buildMantissa(normilizedBinaryNumber, true);
        System.out.println("Mantissa is:\n" + mantissa);

        System.out.println("---------------------------");
        System.out.println("Floating point Number:\n" + FloatingPointNumberUtil.buildFloatingPointNumber(sign, convertExponent, mantissa));

        System.out.println("---------------------------");
        System.out.println("Floating point Number Formatted:\n" + FloatingPointNumberUtil.buildFormattedFloatingPointNumber(sign, convertExponent, mantissa));

        System.out.println("---------------------------");
        System.out.println("-       Aufgabe 2         -");
        System.out.println("---------------------------");
        String roundedNumber = BinaryRoundUtil.roundBinary(normilizedBinaryNumber, mantisse);
        System.out.println("Rounded Number:\n" + roundedNumber);

        System.out.println("---------------------------");
        String absRoundingNumber = BinaryRoundUtil.absoluteRoundingError(roundedNumber, normilizedBinaryNumber);
        System.out.println("Absolute Rounding Error:\n" + absRoundingNumber);

        System.out.println("---------------------------");
        String relRoundingNumber = BinaryRoundUtil.relativeRoundingError(roundedNumber, normilizedBinaryNumber);
        System.out.println("Relative Rounding Error:\n" + relRoundingNumber);

        System.out.println("---------------------------");
        String machineAccuracy = BinaryRoundUtil.machineAccuracy(mantisse);
        System.out.println("Machine Accuracy is:\n" + machineAccuracy);
    }
}
