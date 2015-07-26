package uebung1.utils;

import java.util.ArrayList;
import java.util.List;


public class FloatingPointNumberUtil {

    public static int mantissa;

    /**
     * Step 1: convert Pre Comma Number
     */
    public static List<String> convertPreCommaNumberToBinary(String preCommaNumber) {
        List<String> numberDigitsPreComma = new ArrayList<>();
        Integer number = Integer.valueOf(preCommaNumber);

        if (number == 0 ) {
            numberDigitsPreComma.add("0");
        } else {
            while(number != 0) {
                Integer t = number / 2;
                Integer t2 = number % 2;
                numberDigitsPreComma.add(String.valueOf(t2));
                number = t;
            }
        }

        return ConvertUtil.swapListElements(numberDigitsPreComma);
    }

    /**
     * Step 2: convert Post Comma Number
     */
    public static List<String> convertPostCommaNumberToBinary(String postCommaNumber, int mantissa) {
        List<String> numberDigitsPostComma = new ArrayList<>();
        Double number = Double.valueOf("0." + postCommaNumber);
        int counter = postCommaNumber.length() * mantissa;

        for (int i = 0; i < counter; i++) {
            Double t = number * 2;

            if (t > 1) {
                numberDigitsPostComma.add("1");
                number = t - 1;
            } else {
                numberDigitsPostComma.add("0");
                number = t;
            }
        }
        return numberDigitsPostComma;
    }

    /**
     * Step 3: normalize binary number
     */
    public static String normalize(String binaryNumber) {
        String normalizeBinaryNumber;
        String[] splitBinaryNumber = binaryNumber.split(",");

        int countPreComma = splitBinaryNumber[0].length();

        if (countPreComma == 1) {
            normalizeBinaryNumber = binaryNumber + " * 2^0";
            mantissa = 0;
        } else {
            int count = 0;
            for (int i = 1; i < countPreComma; i++) {
                count++;
            }
            mantissa = count;

            List<String> t = ConvertUtil.convertStringToList(splitBinaryNumber[0]);
            if (splitBinaryNumber[0].length() - count == 0) {
                t.add(1, ",");
            } else {
                t.add(splitBinaryNumber[0].length() - count, ",");
            }

            normalizeBinaryNumber = buildNormalizedBinaryNumber(t, splitBinaryNumber[1], count);
        }
        return normalizeBinaryNumber;
    }

    /**
     * Step 4: convert exponent
     */
    public static List<String> convertedExponent(int mantisse, boolean use32Bit) {
        int character;

        if (use32Bit) character = mantisse + 127;
        else character = mantisse + 1023;

        return convertPreCommaNumberToBinary(String.valueOf(character));
    }

    /**
     * Step 5: get sign
     */
    public static String getSign(int preCommaNumber) {
        if (preCommaNumber >= 0) return "0";
        else return "1";
    }

    /**
     * Step 6: build formatted floating point number
     */
    public static String buildFormattedFloatingPointNumber(String sign, String exp, String man) {
        return "V: " + sign + " E: " + exp + " M: " + man;
    }

    /**
     * Step 6: build floating point number
     */
    public static String buildFloatingPointNumber(String sign, String exp, String man) {
        return sign + exp + man;
    }

    /**
     * Utils: get Floating Point Number
     */
    public static String getFloatingPointNumber(double number, int mantissa) {
        String numberAsString = String.valueOf(number);
        String numberArray[] = numberAsString.replace(".", ",").split(",");

        List<String> binaryPreCommaNumber = convertPreCommaNumberToBinary(numberArray[0]);
        List<String> binaryPostCommaNumber = convertPostCommaNumberToBinary(numberArray[1], mantissa);

        String binaryNumber = ConvertUtil.convertListToString(binaryPreCommaNumber) + "," + ConvertUtil.convertListToString(binaryPostCommaNumber);
        String normilizedBinaryNumber = normalize(binaryNumber);
        String convertExponent = ConvertUtil.convertListToString(convertedExponent(mantissa, true));
        String sign = getSign(Integer.valueOf(numberArray[0]));
        String mant = buildMantissa(normilizedBinaryNumber, true);

        return buildFloatingPointNumber(sign, convertExponent, mant);
    }

    /**
     * Utils: get formatted Floating Point Number
     */
    public static String getFormattedFloatingPointNumber(double number, int mantissa) {
        String numberAsString = String.valueOf(number);
        String numberArray[] = numberAsString.replace(".", ",").split(",");

        List<String> binaryPreCommaNumber = convertPreCommaNumberToBinary(numberArray[0]);
        List<String> binaryPostCommaNumber = convertPostCommaNumberToBinary(numberArray[1], mantissa);

        String binaryNumber = ConvertUtil.convertListToString(binaryPreCommaNumber) + "," + ConvertUtil.convertListToString(binaryPostCommaNumber);
        String normilizedBinaryNumber = normalize(binaryNumber);
        String convertExponent = ConvertUtil.convertListToString(convertedExponent(mantissa, true));
        String sign = getSign(Integer.valueOf(numberArray[0]));
        String mant = buildMantissa(normilizedBinaryNumber, true);

        return buildFormattedFloatingPointNumber(sign, convertExponent, mant);
    }

    /**
     * Utils: build mantissa
     */
    public static String buildMantissa(String normBinNumber, boolean use32Bit) {
        String[] currMantissa = normBinNumber.split(",");
        String[] useThisMantissa = currMantissa[1].split(" ");

        if (use32Bit) {
            if (useThisMantissa[0].length() == 23) {
                return currMantissa[1];
            } else if (useThisMantissa[0].length() > 23) {
                return useThisMantissa[0].substring(0, 22);
            } else {
                return fillMantissaWithZero(useThisMantissa[0], true);
            }
        } else {
            if (useThisMantissa[0].length() == 52) {
                return currMantissa[1];
            } else if (useThisMantissa[0].length() > 52) {
                return useThisMantissa[0].substring(0, 51);
            } else {
                return fillMantissaWithZero(useThisMantissa[0], false);
            }
        }
    }

    /**
     * Utils: build normalized binary number
     */
    private static String buildNormalizedBinaryNumber (List<String> list, String postComma, int mantisse) {
        return ConvertUtil.convertListToString(list) + postComma + " * 2^" + mantisse;
    }

    /**
     * Utils: fill mantissa with zero
     */
    private static String fillMantissaWithZero(String mantissa, boolean use32Bit) {
        StringBuilder sb = new StringBuilder();

        if (use32Bit) {
            for (int i = 0; i < mantissa.length(); i++) {
                sb.append(mantissa.charAt(i));
            }
            int diff = 23 - mantissa.length();
            for (int i = 0; i < diff; i++) {
                sb.append("0");
            }
        } else {
            for (int i = 0; i < mantissa.length(); i++) {
                sb.append(mantissa.charAt(i));
            }
            int diff = 52 - mantissa.length();
            for (int i = 0; i < diff; i++) {
                sb.append("0");
            }
        }
        return sb.toString();
    }
}
