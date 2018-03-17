package com.wxsk.platform.game.service.helper;

import org.junit.Before;
import org.junit.Test;

public class SecretHelperTest {

    private SecretHelper secretHelper;

    @Before
    public void init() {
        secretHelper = new SecretHelper();
    }

    @Test
    public void encryptUserIdTest() throws Exception {
        long userId = 1;
        String secret = "wxsk_wxsj";
        String openId = secretHelper.encryptUserId(userId, secret);
        System.out.println("open id: " + openId);
    }

    @Test
    public void decryptOpenIdTest() throws Exception {
        String openId = "f1594954c1257235373d68ae4ff29530";
        String secret = "wxsk_wxsj";
        Long userId = secretHelper.decryptOpenId(openId, secret);
        System.out.println("userid: " + userId);
    }

    @Test
    public void destroy(){

    }
}
