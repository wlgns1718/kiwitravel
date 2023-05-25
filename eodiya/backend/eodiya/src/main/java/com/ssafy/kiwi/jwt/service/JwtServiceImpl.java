package com.ssafy.kiwi.jwt.service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ssafy.kiwi.exception.UnAuthorizedException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtServiceImpl implements JwtService {

	public static final Logger logger = LoggerFactory.getLogger(JwtServiceImpl.class);

	private static final String SALT = "ssafySecret";

	private static final int ACCESS_TOKEN_EXPIRE_MINUTES = 1; // 분단위
	private static final int REFRESH_TOKEN_EXPIRE_MINUTES = 2; // 주단위

	@Override
	public <T> String createAccessToken(String key, T data) {
		return create(key, data, "access-token", 1000 * 60 * ACCESS_TOKEN_EXPIRE_MINUTES);
	}

//	AccessToken에 비해 유효기간을 길게...
	@Override
	public <T> String createRefreshToken(String key, T data) {
		return create(key, data, "refresh-token", 1000 * 60 * 60 * 24 * 7 * REFRESH_TOKEN_EXPIRE_MINUTES);
	}

	// Token 발급
	/**
	 * key : Claim에 셋팅될 key 값 data : Claim에 셋팅 될 data 값 subject : payload에 sub의
	 * value로 들어갈 subject값 expire : 토큰 유효기간 설정을 위한 값 jwt 토큰의 구성 : header + payload +
	 * signature
	 */
	@Override
	public <T> String create(String key, T data, String subject, long expire) {
		// Payload 설정 : 생성일 (IssuedAt), 유효기간 (Expiration)
		Claims claims = Jwts.claims().setSubject(subject).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + expire));

		claims.put(key, data);

		String jwt = Jwts.builder().setHeaderParam("typ", "JWT").setClaims(claims)
				.signWith(SignatureAlgorithm.HS256, this.generateKey()).compact(); // 직렬화

		return jwt;
	}

	// Signature 설정에 들어갈 key 생성.
	private byte[] generateKey() {
		byte[] key = null;
		try {
			key = SALT.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			if (logger.isInfoEnabled()) {
				e.printStackTrace();
			} else {
				logger.error("Making JWT Key Error ::: {}", e.getMessage());
			}
		}

		return key;
	}

	@Override
	public boolean checkToken(String jwt) {
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(this.generateKey()).parseClaimsJws(jwt);
			logger.debug("claims: {}", claims);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}

	@Override
	public Map<String, Object> get(String key) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		String jwt = request.getHeader("access-token");
		Jws<Claims> claims = null;
		try {
			claims = Jwts.parser().setSigningKey(SALT.getBytes("UTF-8")).parseClaimsJws(jwt);
		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				e.printStackTrace();
			} else {
				logger.error(e.getMessage());
			}
			throw new UnAuthorizedException();
		}
		Map<String, Object> value = claims.getBody();
		logger.info("value : {}", value);
		return value;
	}

	@Override
	public String getUserId() {
		return (String) this.get("user").get("userid");
	}

}
