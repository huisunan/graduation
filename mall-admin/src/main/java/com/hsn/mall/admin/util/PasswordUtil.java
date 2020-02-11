package com.hsn.mall.admin.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @author huisunan
 * @date 2020/1/17 16:46
 */
public class PasswordUtil {
    public static String addSalt(final String password,final String salt){
        //加密方式
        String hashAlgorithmName = "MD5";
        //盐：为了即使相同的密码不同的盐加密后的结果也不同
        ByteSource byteSalt = ByteSource.Util.bytes(salt);
        //加密次数
        int hashIterations = 1024;
        SimpleHash result = new SimpleHash(hashAlgorithmName, password, byteSalt, hashIterations);
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(addSalt("123123", "huisunan"));
    }
}
