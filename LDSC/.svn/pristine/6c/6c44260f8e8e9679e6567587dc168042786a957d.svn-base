package android.extend.util.epc_sku_convert;

public class AsciiAndHexConvert {

    public static String convertHexToString(String hex) {

        char[] chars = hex.toCharArray();

        StringBuffer str = new StringBuffer();
        for (int i = 0; i < chars.length; i++) {
            str.append(Integer.toHexString((int) chars[i]));
        }

        return str.toString();
    }

    public static String convertStringToHex(String str) {

        StringBuilder sb   = new StringBuilder();
//        StringBuilder temp = new StringBuilder();

        String output;
        int decimal;
        for (int i = 0; i < str.length() - 1; i += 2) {

            output = str.substring(i, (i + 2));
            decimal = Integer.parseInt(output, 16);
            sb.append((char) decimal);
//            temp.append(decimal);
        }

        return sb.toString();
    }

}

