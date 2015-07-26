package uebung1.utils;


import java.util.ArrayList;
import java.util.List;

public class ConvertUtil {
    /**
     * Utils: convert list to String
     */
    public static String convertListToString(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
        }
        return sb.toString();
    }

    /**
     * Utils: convert String to list
     */
    public static List<String> convertStringToList(String s) {
        List<String> list = new ArrayList<>();
        for(int i = 0; i < s.length(); i++) {
            list.add(String.valueOf(s.charAt(i)));
        }
        return list;
    }

    /**
     * Utils: convert Array to String
     */
    public static String convertArrayToString(String[] arrStr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arrStr.length; i++) {
            sb.append(arrStr[i]);
        }
        return sb.toString();
    }

    /**
     * Utils: swap List elements
     */
    public static List<String> swapListElements(List<String> list) {
        List<String> returnList = new ArrayList<>();
        for (int i = list.size() - 1; i >= 0; i--) {
            returnList.add(list.get(i));
        }
        return returnList;
    }

    /**
     * Utils: fill List with Zeros
     */
    public static List<String> fillListWithZeros(List<String> list, int lengthOfZeros) {
        List<String> returnList = new ArrayList<>();
        returnList.addAll(list);
        for (int i = 0; i < lengthOfZeros; i++) {
            returnList.add("0");
        }
        return swapListElements(returnList);
    }
}
