package com.wxsk.platform.game.service.helper;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Component
public class SecretHelper {

    private static final String ALGORITHM = "AES/ECB/PKCS5Padding";

    public String encryptUserId(Long id, String openIdSalt) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(pkcs5Padding(openIdSalt.getBytes()), "AES"));
        byte[] result = cipher.doFinal(String.valueOf(id).getBytes());
        return Hex.encodeHexString(result);
    }
    public Long decryptOpenId(String openId, String openIdSalt) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, DecoderException {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(pkcs5Padding(openIdSalt.getBytes()), "AES"));
        byte[] result = cipher.doFinal(Hex.decodeHex(openId));
        return Long.valueOf(new String(result));
    }

    /**
     * 创建签名
     * @param params 参数
     * @param secret 密钥
     * @return sign
     */
    public String createSign(Map<String, String> params, String secret) {
        //除去map中的空值和签名参数sign
        Map<String, String> sPara = paraFilter(params);
        //生成签名结果
        //把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
        String fromStr = createLinkString(sPara).concat("|").concat(secret);
        return DigestUtils.md5Hex(fromStr);
    }

    /**
     * 验证是否正确
     * @param params 参数
     * @param secret 密钥
     * @return true OK, false not OK
     */
    public boolean verifySign(Map<String, String> params, String secret) {
        String sign = "";
        if(params.get("sign") != null) {
            sign = params.get("sign");
        }
        //生成签名结果
        return createSign(params, secret).equals(sign);
    }

    /**
     * 除去数组中的空值和签名参数
     * @param sArray 签名参数组
     * @return 去掉空值与签名参数后的新签名参数组
     */
    private Map<String, String> paraFilter(Map<String, String> sArray) {
        Map<String, String> result = new HashMap<>();
        if (sArray == null || sArray.size() <= 0) {
            return result;
        }
        for (String key : sArray.keySet()) {
            String value = sArray.get(key);
            if (value == null || value.equals("") || key.equalsIgnoreCase("sign")) {
                continue;
            }
            result.put(key, value);
        }
        return result;
    }

    /**
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public String createLinkString(Map<String, String> params) {

        List<String> keys = new ArrayList<>(params.keySet());
        Collections.sort(keys);
        String prestr = "";
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }
        return prestr;
    }



    public byte[] pkcs5Padding(byte[] keyBytes) {
        int length = keyBytes.length;
        int tailLength = length % 16;
        if(tailLength > 0) {
            int missing = 16 - tailLength;
            keyBytes = Arrays.copyOf(keyBytes, length + missing);
        }
        return keyBytes;
    }

}
