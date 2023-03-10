package com.bptn.feedApp.provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import com.bptn.feedApp.provider.factory.YamlPropertySourceFactory;


	@Component
	@PropertySource(value = "classpath:config.yml", factory = YamlPropertySourceFactory.class)
	public class ResourceProvider {
		
		@Value("${jwt.secret}")
		private String jwtSecret;

		@Value("${jwt.expiration}")
		private long jwtExpiration;

		@Value("${jwt.issuer}")
		private String jwtIssuer;
			
		@Value("${jwt.audience}")
		private String jwtAudience;

		@Value("${jwt.prefix}")
		private String jwtPrefix;

		@Value("${jwt.excluded.urls}")
		private String[] jwtExcludedUrls;

		@Value("${client.url}")
		private String clientUrl;
		    
		@Value("${client.email.verify.param}")
		private String clientVerifyParam;

		@Value("${client.email.verify.expiration}")
		private long clientVerifyExpiration;
		    
		@Value("${client.email.reset.param}")
		private String clientResetParam;

		@Value("${client.email.reset.expiration}")
		private long clientResetExpiration;

		public String getJwtSecret() {
			return jwtSecret;
		}

		public long getJwtExpiration() {
			return jwtExpiration;
		}

		public String getJwtIssuer() {
			return jwtIssuer;
		}

		public String getJwtAudience() {
			return jwtAudience;
		}

		public String getJwtPrefix() {
			return jwtPrefix;
		}

		public String[] getJwtExcludedUrls() {
			return jwtExcludedUrls;
		}

		public String getClientUrl() {
			return clientUrl;
		}

		public String getClientVerifyParam() {
			return clientVerifyParam;
		}

		public long getClientVerifyExpiration() {
			return clientVerifyExpiration;
		}

		public String getClientResetParam() {
			return clientResetParam;
		}

		public long getClientResetExpiration() {
			return clientResetExpiration;
		}
			
		
			
	}
	



