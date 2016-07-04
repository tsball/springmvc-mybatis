package com.springmvc.util;

import java.io.UnsupportedEncodingException;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class EncoderUtil {

	public static String encodePassword(String password, String username) {
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		md5.setEncodeHashAsBase64(false);
		return md5.encodePassword(password, username);
	}

	public static boolean isPasswordValid(String encodePwd, String password,
			String username) {
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		md5.setEncodeHashAsBase64(false);
		return md5.isPasswordValid(encodePwd, password, username);
	}

	public static String md5Encode(String password) {
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		md5.setEncodeHashAsBase64(false);
		return md5.encodePassword(password, null);
	}

	public static String urlDecode(String url, String charset) {
		try {
			url = java.net.URLDecoder.decode(url, charset);
		} catch (UnsupportedEncodingException e) {
			return url;
		}
		return url;
	}

}
