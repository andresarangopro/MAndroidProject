package android.extend.util;

import android.extend.util.epc_sku_convert.AsciiAndHexConvert;
import android.extend.util.epc_sku_convert.HexAndIntConvertToEach;

import com.orhanobut.logger.Logger;

public class EpcAndSkuUtil {
    /**
     * 返回唯一码   长的
     */
    public static String EpcToSkuUnique(String epc) {
        StringBuilder sku_unique = null;              //唯一码
        int indexOfVersion = 0;                                // 版本号所在索引
        int PRE_N_EPC = 0;                                // 根据版本号截取位数
        int versionNum = 0;                                // 版本号
        String Sku_preN = "";                               // SKU唯一码最前段
        AsciiAndHexConvert connto = new AsciiAndHexConvert();
        HexAndIntConvertToEach hexAndIntConvert = new HexAndIntConvertToEach();
        try {
            // 1.获取epc最后一位判断是不是为0
            int lastChar = Integer.parseInt(epc.charAt(epc.length() - 1) + "");
            if (lastChar == 0) {
                // 为0
                indexOfVersion = epc.lastIndexOf("0") - 1;
                versionNum = Integer.parseInt(epc.charAt(indexOfVersion) + "");
                // 确定版本号以后可以确定截取的前N位进行转换
                switch (versionNum) {
                    case 1: // 转换位数6
                        PRE_N_EPC = 6;
                        break;
                    case 2:// 转换位数4
                        PRE_N_EPC = 4;
                        break;
                    case 3:// 转换位数2
                        PRE_N_EPC = 2;
                        break;
                    case 4:// 转换位数0
                    case 5:// 转换位数0
                        PRE_N_EPC = 0;
                        break;
                }

            } else {
                // 不为0则最后一位为版本号
                indexOfVersion = epc.length() - 1;
                versionNum = Integer.parseInt(epc.charAt(epc.length() - 1) + "");
                // 确定版本号以后可以确定截取的前N位进行转换
                switch (versionNum) {
                    case 1: // 转换位数8
                        PRE_N_EPC = 8;
                        break;
                    case 2:// 转换位数6
                        PRE_N_EPC = 6;
                        break;
                    case 3:// 转换位数4
                        PRE_N_EPC = 4;
                        break;
                    case 4:// 转换位数2
                        PRE_N_EPC = 2;
                        break;
                    case 5:// 转换位数0
                        PRE_N_EPC = 0;
                        break;
                }
            }
            // 获取epc前PRE_N_EPC位转为十六进制
            if (PRE_N_EPC != 0) {
                String s = epc.substring(0, PRE_N_EPC).trim();
                Sku_preN = connto.convertStringToHex(s);
            } else {
                return null;
            }



            // 获取序列号“版本号”左边第2-6位转换回十进制 String
            StringBuilder serialNumToString = new StringBuilder().append(hexAndIntConvert.HexToInt(epc.substring(
                    indexOfVersion - 6, indexOfVersion - 1)));

            while (serialNumToString.length() < 6) {
                serialNumToString.insert(0, "0");
            }

            // 获取分割符转ascii码 //
            // * 分割符：“版本号”左边第7-8位数。“2d” 转换回“减号-”
            String divider = connto.convertStringToHex(epc.substring(indexOfVersion - 8, indexOfVersion - 6));

            // SKU条码：“版本号”左边第9位数之后的剩余位数。
            String skuSmall = epc.substring(PRE_N_EPC, indexOfVersion - 8);

            sku_unique = new StringBuilder().append(Sku_preN).append(skuSmall).append(divider).append(serialNumToString);
            // */
            if (!sku_unique.toString().contains("-"))
                return null;
            return sku_unique.toString();
        } catch (Exception e) {
            Logger.e(e, e.getMessage());
//            e.printStackTrace();
        }
        return sku_unique == null ? null : sku_unique.toString();
    }

    /**
     * 返回sku   短的   -前面的
     */
    public static String EpcToSku(String epc) {
        String sku = null;
        int indexOfVersion = 0; // 版本号所在索引
        int PRE_N_EPC = 0; // 根据版本号截取位数
        int versionNum = 0; // 版本号
        String Sku_preN = ""; // SKU唯一码最前段
        AsciiAndHexConvert connto = new AsciiAndHexConvert();
        HexAndIntConvertToEach hexAndIntConvert = new HexAndIntConvertToEach();
        try {
            // 1.获取epc最后一位判断是不是为0
            int lastChar = Integer.parseInt(epc.charAt(epc.length() - 1) + "");
            if (lastChar == 0) {
                // 为0
                indexOfVersion = epc.lastIndexOf("0") - 1;
                versionNum = Integer.parseInt(epc.charAt(indexOfVersion) + "");
                // 确定版本号以后可以确定截取的前N位进行转换
                switch (versionNum) {
                    case 1: // 转换位数8
                        PRE_N_EPC = 6;
                        break;
                    case 2:// 转换位数6
                        PRE_N_EPC = 4;
                        break;
                    case 3:// 转换位数4
                        PRE_N_EPC = 2;
                        break;
                    case 4:// 转换位数2
                        PRE_N_EPC = 0;
                        break;
                    case 5:// 转换位数0
                        PRE_N_EPC = 0;
                        break;
                }

            } else {
                // 不为0则最后一位为版本号
                indexOfVersion = epc.length() - 1;
                versionNum = Integer.parseInt(epc.charAt(epc.length() - 1) + "");
                // 确定版本号以后可以确定截取的前N位进行转换
                switch (versionNum) {
                    case 1: // 转换位数8
                        PRE_N_EPC = 8;
                        break;
                    case 2:// 转换位数6
                        PRE_N_EPC = 6;
                        break;
                    case 3:// 转换位数4
                        PRE_N_EPC = 4;
                        break;
                    case 4:// 转换位数2
                        PRE_N_EPC = 2;
                        break;
                    case 5:// 转换位数0
                        PRE_N_EPC = 0;
                        break;
                }
            }
            // 获取epc前PRE_N_EPC位转为十六进制
            if (PRE_N_EPC != 0) {
                String s = epc.substring(0, PRE_N_EPC).trim();
                Sku_preN = connto.convertStringToHex(s);
            } else {
                return null;
            }

            // 获取序列号“版本号”左边第2-6位转换回十进制 String
            String serialNumToString = hexAndIntConvert.HexToInt(epc.substring(
                    indexOfVersion - 6, indexOfVersion - 1)) + "";
            if (serialNumToString.length() < 6) {
                int dic = 6 - serialNumToString.length();
                switch (dic) {
                    case 1:
                        serialNumToString = "0" + serialNumToString;
                        break;
                    case 2:
                        serialNumToString = "00" + serialNumToString;
                        break;
                    case 3:
                        serialNumToString = "000" + serialNumToString;
                        break;
                    case 4:
                        serialNumToString = "0000" + serialNumToString;
                        break;
                    case 5:
                        serialNumToString = "00000" + serialNumToString;
                        break;
                    case 6:
                        serialNumToString = "000000" + serialNumToString;
                        break;
                    default:
                        break;
                }
                serialNumToString = "" + serialNumToString;
            }
            // 获取分割符转ascii码
            // * 分割符：“版本号”左边第7-8位数。“2d” 转换回“减号-”
            String divider = connto.convertStringToHex(epc.substring(indexOfVersion - 8, indexOfVersion - 6));
            // SKU条码：“版本号”左边第9位数之后的剩余位数。
            String skuSmall = epc.substring(PRE_N_EPC, indexOfVersion - 8);
            sku = Sku_preN + skuSmall;
            // */
            return sku;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sku;
    }

    public static String SkuUniqueToSku(String skuUnique) {
        if(skuUnique == null) return null;
        return skuUnique.split("-")[0];
    }
}
