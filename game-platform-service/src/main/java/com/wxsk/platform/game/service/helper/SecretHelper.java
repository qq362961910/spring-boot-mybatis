package com.wxsk.platform.game.service.helper;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

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
