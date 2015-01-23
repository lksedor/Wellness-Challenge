package com.ritcheydevelopers.wellnesstracker.utility;

import org.scribe.builder.api.DefaultApi10a;
import org.scribe.model.Token;

public class FitBitAPI extends DefaultApi10a {

	public static final String BASE_URL="https://api.fitbit.com/";
	private static final String REQUEST_TOKEN_RESOURCE = "api.fitbit.com/oauth/request_token";
	private static final String AUTHORIZE_URL = "https://api.fitbit.com/oauth/authorize?oauth_token=%s";
	private static final String ACCESS_TOKEN_RESOURCE = "api.fitbit.com/oauth/access_token";
	

	public String getAccessTokenEndpoint() {
		return "https://" + ACCESS_TOKEN_RESOURCE;
	}

	public String getRequestTokenEndpoint() {
		return "https://" + REQUEST_TOKEN_RESOURCE;
	}
	
	public String getAuthorizationUrl(Token requestToken) {
		return String.format(AUTHORIZE_URL, requestToken.getToken());
	}


}
