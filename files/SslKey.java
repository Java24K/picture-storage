package com.crm.annotation;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
 
import sun.misc.BASE64Encoder;
 
public class SslKey {
 
    public static KeyStore getKeyStore(String keyStorePath, String password) throws Exception {
        FileInputStream is = new FileInputStream(keyStorePath);
        KeyStore ks = KeyStore.getInstance("JKS");// 声明keStore实例JKS文件
        ks.load(is, password.toCharArray());// 通过keyStore文件和keyStore文件密码拿到文件
        is.close();// 关闭流
        return ks;
    }
 
    public static PrivateKey getPrivateKey() {
        try {
            BASE64Encoder encoder = new BASE64Encoder();
            KeyStore ks = getKeyStore("/Users/finup/downloads/https/zhoufeng.keystore", "123456");
            PrivateKey key = (PrivateKey) ks.getKey("zhoufeng", "123456".toCharArray());// 在jks文件中通过别名和私钥密码拿到私钥
            String encoded = encoder.encode(key.getEncoded());// 私钥进行Base64编码
            System.out.println("-----BEGIN RSA PRIVATE KEY-----");
            System.out.println(encoded);// 输出私钥
            System.out.println("-----END RSA PRIVATE KEY-----");
            return key;
        } catch (Exception e) {
            return null;
        }
    }
 
    public static void main(String[] args) {
        getPrivateKey();
    }
 
}
