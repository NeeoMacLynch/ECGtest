package com.example.serialcommunicationtest.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataConversionUtils {
    private static String baseHex =  "0123456789ABCDEF";

    /**
     * 十六进制字节数组转字符串
     *
     * @param bytes -目标数组
     * @param dec -起始位置
     * @param length -长度
     * @return
     */
    public static String bytes2HexStr(byte[] bytes, int dec, int length) {
        byte[] temp = new byte[length];
        System.arraycopy(bytes, dec, temp, 0, length);
        return bytes2HexStr(temp);
    }

    /**
     * 字节数组转换成对应的16进制表示的字符串
     *
     * @param bytes -目标数组
     * @return Hex字符串
     */
    public static String bytes2HexStr(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        if (bytes == null || bytes.length <= 0) {
            return "";
        }
        char[] buffer = new char[2];
        for (byte aByte : bytes) {
            buffer[0] = Character.forDigit((aByte >>> 4) & 0x0F, 16);
            buffer[1] = Character.forDigit(aByte & 0x0F, 16);
            builder.append(buffer);
        }
        //toUpperCase()字符串转换为大写
        return builder.toString().toUpperCase();
    }


    /**
     * Hex字符串转byte
     * @param inHex 待转换的Hex字符串
     * @return  转换后的byte
     */
    public static byte hex2Byte(String inHex){
        return (byte)Integer.parseInt(inHex,16);
    }

    /**
     * hex字符串转byte数组
     * @param hexStr 待转换的Hex字符串
     * @return  转换后的byte数组结果
     */
    public static byte[] hex2BinArray(String hexStr){
        //hexString的长度对2取整，作为bytes的长度
        int len = hexStr.length()/2;
        byte[] bytes = new byte[len];
        byte high = 0;//字节高四位
        byte low = 0;//字节低四位
        for(int i=0;i<len;i++){
            //右移四位得到高位
            high = (byte)((baseHex.indexOf(hexStr.charAt(2*i)))<<4);
            low = (byte) baseHex.indexOf(hexStr.charAt(2*i+1));
            bytes[i] = (byte) (high|low);//高地位做或运算
        }
        return bytes;
    }

    /**
     *  将二进制数组转换为十六进制字符串
     *
     * @param bytes -目标数组
     * @return hex字符串结果
     */
    public static String bin2HexStr(byte[] bytes){

        StringBuilder result = new StringBuilder();
        String hex;
        for (byte aByte : bytes) {
            //字节高4位
            hex = String.valueOf(baseHex.charAt((aByte & 0xF0) >> 4));
            //字节低4位
            hex += String.valueOf(baseHex.charAt(aByte & 0x0F));
            result.append(hex);
        }
        return result.toString();
    }

    /**
     * 16进制字符串转十进制数字
     * 每两位转换为十进制
     *
     * @param hexStr -目标HEX字符串
     * @return 由整型数值组成的字符串
     */
    public static String hexStr2IntStr(String hexStr) {

        List<String> hexList = Arrays.asList(hexStr.split(" "));
        List<String> intList = new ArrayList<>();

        for (int position = 1 ; position < hexList.size(); position++) {
            intList.add(
                    String.valueOf(
                            Integer.parseInt(hexList.get(position), 16)
                    ));
        }

        StringBuilder builder = new StringBuilder();
        for(String intItem : intList){
            builder.append(intItem);
            builder.append(" ");
        }

        return builder.toString();
    }

    /**
     * 16进制转ASCII
     * */
    public static String hexStr2Ascii(String hexStr) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < hexStr.length(); i += 2) {
            String str = hexStr.substring(i, i + 2);
            builder.append((char) Integer.parseInt(str, 16));
        }
        return builder.toString();
    }

    /**
     * 十进制转二进制byte数组
     * 只保留四位
     * */
    public static byte[] dec2BinArray(int a) {
        return new byte[] {
                (byte) ((a >> 3) & 0x01),
                (byte) ((a >> 2) & 0x01),
                (byte) ((a >> 1) & 0x01),
                (byte) (a & 0x01)
        };
    }


}
