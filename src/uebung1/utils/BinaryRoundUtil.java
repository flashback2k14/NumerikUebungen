package uebung1.utils;

import java.util.ArrayList;
import java.util.List;

public class BinaryRoundUtil {
    /**
     *
     */
    public static String roundBinary(String normBinNumber, int mantissa) {
        String[] cuttedNormBinNumber = normBinNumber.split(",");
        String[] cuttedPostCommaNumber = cuttedNormBinNumber[1].split(" ");

        int lenPreCommaNumber = cuttedNormBinNumber[0].length();
        int lenPostCommaNumber = cuttedPostCommaNumber[0].length();

        int digitsForPostCommaNumber = mantissa - lenPreCommaNumber;

        if (digitsForPostCommaNumber <= 0) {
            return cuttedNormBinNumber[0];
        } else {
            String roundingPart = cuttedPostCommaNumber[0].substring(0, digitsForPostCommaNumber);
            List<String> lRoundingPart = ConvertUtil.convertStringToList(roundingPart);

            String cuttedPart = cuttedPostCommaNumber[0].substring(digitsForPostCommaNumber, lenPostCommaNumber);
            List<String> lCuttedPart = ConvertUtil.convertStringToList(cuttedPart);

            if ("0".equals(lCuttedPart.get(0))) {
                return cuttedNormBinNumber[0] + "," + roundingPart;
            } else {

                if ("0".equals(lRoundingPart.get(lRoundingPart.size() - 1))) {
                    return cuttedNormBinNumber[0] + "," + roundingPart.substring(0, roundingPart.length() - 1) + "1";
                } else {
                    return cuttedNormBinNumber[0] + "," + roundingPart.substring(0, roundingPart.length() - 1) + "0";
                }
            }
        }
    }

    /**
     *
     */
    public static String absoluteRoundingError(String roundedNumber, String normBinNumber) {
        String[] cuttedRoundedNumber = roundedNumber.split(",");
        String[] cuttedNormBinNumber = normBinNumber.split(" ");
        String[] cutCuttedNormBinNumber = cuttedNormBinNumber[0].split(",");

        List<String> lRoundedPostCommaNumber = ConvertUtil.convertStringToList(cuttedRoundedNumber[1]);
        List<String> lNormBinPostCommaNumber = ConvertUtil.convertStringToList(cutCuttedNormBinNumber[1]);

        List<String> lmodRoundedPostCommaNumber = new ArrayList<>();

        int lenRoundedPostCommaNumber = lRoundedPostCommaNumber.size();
        int lenNormBinPostCommaNumber = lNormBinPostCommaNumber.size();

        if (lenRoundedPostCommaNumber < lenNormBinPostCommaNumber) {
            lmodRoundedPostCommaNumber = ConvertUtil.fillListWithZeros(lRoundedPostCommaNumber, (lenNormBinPostCommaNumber - lenRoundedPostCommaNumber));
        }

        Integer i = Integer.parseInt(ConvertUtil.convertListToString(lmodRoundedPostCommaNumber), 2);
        Integer j = Integer.parseInt(ConvertUtil.convertListToString(lNormBinPostCommaNumber), 2);
        Integer r = i - j;

        Double d = Math.signum(r.doubleValue());

        if (d == -1d) {
            r = r * -1;
        }

        String result = Integer.toBinaryString(r);
        return cuttedRoundedNumber[0] + "," + result;
    }

    /**
     *
     */
    public static String relativeRoundingError(String roundedNumber, String normBinNumber) {
        String[] cuttedRoundedNumber = roundedNumber.split(",");
        String[] cuttedNormBinNumber = normBinNumber.split(" ");
        String[] cutCuttedNormBinNumber = cuttedNormBinNumber[0].split(",");

        List<String> lRoundedPostCommaNumber = ConvertUtil.convertStringToList(cuttedRoundedNumber[1]);
        List<String> lNormBinPostCommaNumber = ConvertUtil.convertStringToList(cutCuttedNormBinNumber[1]);

        List<String> lmodRoundedPostCommaNumber = new ArrayList<>();

        int lenRoundedPostCommaNumber = lRoundedPostCommaNumber.size();
        int lenNormBinPostCommaNumber = lNormBinPostCommaNumber.size();

        if (lenRoundedPostCommaNumber < lenNormBinPostCommaNumber) {
            lmodRoundedPostCommaNumber = ConvertUtil.fillListWithZeros(lRoundedPostCommaNumber, (lenNormBinPostCommaNumber - lenRoundedPostCommaNumber));
        }

        Integer i = Integer.parseInt(ConvertUtil.convertListToString(lmodRoundedPostCommaNumber), 2);
        Integer j = Integer.parseInt(ConvertUtil.convertListToString(lNormBinPostCommaNumber), 2);
        Integer r = (i - j) / j;

        Double d = Math.signum(r.doubleValue());

        if (d == -1d) {
            r = r * -1;
        }

        String result = Integer.toBinaryString(r);
        return cuttedRoundedNumber[0] + "," + result;
    }

    /**
     *
     */
    public static String machineAccuracy(int mantissa) {
        double d = 0.5 * Math.pow(2.0, (-mantissa + 1));

        String s = String.valueOf(d);
        String[] st = s.replace(".",",").split(",");

        return "1," + Integer.toBinaryString(Integer.parseInt(st[1]));
    }
}
