/**
 * @Copyright: Herb2Sy
 */
package com.herb2sy.utils;

import com.herb2sy.exception.EncryptionException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Base64;


/**
 * @des: 加密工具类
 * @author: HerbLee
 * @email: herb2sy@gmail.com
 * @data: 2019/4/27 
 */
public class EncryptionUtils {


    private static byte[] md5(String s) {
        MessageDigest algorithm;
        try {
            algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(s.getBytes("UTF-8"));
            byte[] messageDigest = algorithm.digest();
            return messageDigest;
        } catch (Exception e) {
        }
        return null;
    }

    private static final String toHex(byte hash[]) {
        if (hash == null) {
            return null;
        }
        StringBuffer buf = new StringBuffer(hash.length * 2);
        int i;

        for (i = 0; i < hash.length; i++) {
            if ((hash[i] & 0xff) < 0x10) {
                buf.append("0");
            }
            buf.append(Long.toString(hash[i] & 0xff, 16));
        }
        return buf.toString();
    }

    /**
     * 对密码按照用户名，密码，盐进行加密
     * @param s 计算md5的参数
     * @return
     */
    public static String hMd5(String s) {
        try {
            return new String(toHex(md5(s)).getBytes("UTF-8"), "UTF-8");
        } catch (Exception e) {
            return s;
        }
    }

    /**
     * 对密码按照用户名，密码，盐进行加密
     * @param username 用户名
     * @param password 密码
     * @param salt 盐
     * @return
     */
    public static String hMd5(String username, String password, String salt) {
        return hMd5(username + password, salt);
    }

    /**
     * 对密码按照密码，盐进行加密
     * @param password 密码
     * @param salt 盐
     * @return
     */
    public static String hMd5(String password, String salt) {
        return hMd5(password + salt);
    }


    /**
     * 使用base64编码
     * @param str 待编码的字符
     * @return
     */
    public static String hBase64Encoder(String str){
        Base64.Encoder encoder = Base64.getEncoder();
        try {
            return encoder.encodeToString(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throwException(e.getMessage());
        }
        return null;


    }

    /**
     * 使用base64解码
     * @param str 待解码的字符
     * @return
     */
    public static String hBase64Decoder(String str){
        Base64.Decoder decoder = Base64.getDecoder();
        try {
            return new String(decoder.decode(str));
        } catch (Exception e) {
            throwException(e.getMessage());
        }
        return null;

    }



    private static void throwException(String msg){
        try {
            throw new EncryptionException(StringUtils.obj2Str(msg,"数据错误"));
        } catch (EncryptionException e1) {
            e1.printStackTrace();
        }
    }




}
