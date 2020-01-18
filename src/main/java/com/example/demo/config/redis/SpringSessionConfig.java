package com.example.demo.config.redis;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.DefaultCookieSerializer;


@Configuration

@EnableRedisHttpSession(maxInactiveIntervalInSeconds=1800,redisNamespace="demo:spring:session")
public class SpringSessionConfig {

	@Bean
	public DefaultCookieSerializer defaultCookieSerializer() {
		return new CustomCookieSerializer("SESSIONID");
	}

	// 内部类，用来实现自定义sessionid的名字和处理（flash方式）
	class CustomCookieSerializer extends DefaultCookieSerializer
	{
		private String cookieName = "SESSIONID";

		public CustomCookieSerializer(String cookieName)
		{
			super.setCookieName(cookieName);
			this.cookieName = cookieName;
			this.setUseBase64Encoding(false);
			this.setSameSite(null);
		}

		@Override
		public List<String> readCookieValues(HttpServletRequest request) {
			List<String> cookies = super.readCookieValues(request);
			String reqUri = request.getRequestURI();
			if (reqUri.contains(";")) {
				String s = reqUri.substring(reqUri.indexOf(";") + 1);
				if (s.contains(this.cookieName)) {
					String jssionid = s.substring(s.indexOf(this.cookieName) + this.cookieName.length() + 1);
					if (!cookies.contains(jssionid))
						cookies.add(jssionid);
				}
			}
			return cookies;
		}
	}
}
