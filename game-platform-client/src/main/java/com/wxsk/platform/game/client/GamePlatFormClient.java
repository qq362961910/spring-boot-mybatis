package com.wxsk.platform.game.client;

import java.io.IOException;
import java.util.*;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * 商户
 */
public abstract class GamePlatFormClient {

	private static final Properties properties = new Properties() ;
	private static final String publicKey ;
	static {
		try {
			properties.load(GamePlatFormClient.class.getResourceAsStream("GamePlatFormConfig.properties")) ;
		} catch (IOException e) {
			e.printStackTrace();
		}
		publicKey = properties.getProperty("publicKey") ;
	}

	/**
	 * 创建签名
	 * @param params
	 * @param secret
	 * @return
	 */
	public static String createSign(Map<String, String> params, String secret) {
		//除去map中的空值和签名参数sign
		Map<String, String> sPara = paraFilter(params);
		//生成签名结果
		String prestr = createLinkString(sPara); //把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
		StringBuilder stringBuilder = new StringBuilder(prestr) ;
		stringBuilder.append("|").append(secret) ;
		stringBuilder.append("|").append(publicKey) ;
		String fromStr = stringBuilder.toString();
		String sign = DigestUtils.md5Hex(fromStr);
		return sign;
	}

	/**
	 * 验证是否正确
	 * @param params(包含sign)
	 * @param secret
	 * @return
	 */
	public static boolean verifySign(Map<String, String> params, String secret) {
		String sign = "";
		if(params.get("sign") != null) {
			sign = params.get("sign");
		}
		//生成签名结果
		String mysign = createSign(params, secret);
		return mysign.equals(sign) ;
	}

	/**
	 * 除去数组中的空值和签名参数
	 * @param sArray 签名参数组
	 * @return 去掉空值与签名参数后的新签名参数组
	 */
	private static Map<String, String> paraFilter(Map<String, String> sArray) {
		Map<String, String> result = new HashMap<String, String>();
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
	public static String createLinkString(Map<String, String> params) {

		List<String> keys = new ArrayList<String>(params.keySet());
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

}
