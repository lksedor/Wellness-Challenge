package com.ritcheydevelopers.wellnesstracker;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ritcheydevelopers.wellnesstracker.model.Weight;
import com.ritcheydevelopers.wellnesstracker.utility.FitBitAPI;

@Controller
public class FitBitController {
	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	private String authToken = "121e130e2ebc9210e80fdb934a463f4b";
	private String authVerifier = "cn1304rn8oe97hu9qjfrr5ponh";

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/fitbit", method = RequestMethod.GET)
	public String oAuth(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("Testing the Fitbit OAuth 1.0");

		OAuthService service = new ServiceBuilder().provider(FitBitAPI.class)
				.apiKey("cc95f37510324f3aa58050ae36569bbe")
				.apiSecret("227b82a2c1bc4a25a16b1caa87efd259")
				.callback("http://fitbit.com/oauth/callback").debug().build();
		Token requestToken = service.getRequestToken();
		logger.info(requestToken.getRawResponse());
		String authUrl = service.getAuthorizationUrl(requestToken);
		logger.info(authUrl);
		request.getSession().setAttribute("token", requestToken);

		// Verifier v = new Verifier("h7hg49m4p0pcb7q3v1bap57ke8");
		// logger.info(v.getValue());

		// Token accessToken = service.getAccessToken(token, v);
		//
		// OAuthRequest request = new OAuthRequest(Verb.GET, FitBitAPI.BASE_URL
		// + "/1/user/-/profile.json");
		// service.signRequest(accessToken, request);
		// Response response = request.send();
		// logger.info(response.getBody());
		// Date date = new Date();
		model.addAttribute("authorizationURL", authUrl);
		return "home";
	}

	@RequestMapping(value = "/authorization/verifier/{verifier}", method = RequestMethod.GET)
	public String oAuthPin(Model model, @PathVariable String verifier,
			HttpServletRequest request, HttpServletResponse response) {

		Verifier v = new Verifier(verifier);
		OAuthService service = new ServiceBuilder().provider(FitBitAPI.class)
				.apiKey("cc95f37510324f3aa58050ae36569bbe")
				.apiSecret("227b82a2c1bc4a25a16b1caa87efd259")
				.callback("http://fitbit.com/oauth/callback").debug().build();
		Token requestToken = (Token) request.getSession().getAttribute("token");
		Token token = new Token("2c5e0a01ab952ddd4df84b8d39772fa7",
				"46e759cab87a30b5e5dec5213fc05fc0");
		Token accessToken = service.getAccessToken(requestToken, v);

		OAuthRequest serviceRequest = new OAuthRequest(
				Verb.GET,
				FitBitAPI.BASE_URL
						+ "1/user/-/body/log/weight/date/2014-01-01/2014-01-20.json");
		service.signRequest(accessToken, serviceRequest);
		Response serviceResponse = serviceRequest.send();
		logger.info(serviceResponse.getBody());

		serviceRequest = new OAuthRequest(Verb.GET, FitBitAPI.BASE_URL
				+ "1/user/-/body/log/weight/date/2014-01-01/2014-01-20.json");
		service.signRequest(accessToken, serviceRequest);
		serviceResponse = serviceRequest.send();
		logger.info(serviceResponse.getBody());
		model.addAttribute("ProfileText", serviceResponse.getBody());
		return "FitbitProfile";

	}

	@RequestMapping(value = "/preauthorized", method = RequestMethod.GET)
	public String preAuthorizedTest(Model model, HttpServletRequest request,
			HttpServletResponse response) {

		Token lauraToken = new Token("f5c36d6d0dffeb338fd9c4e3e2fb6e07",
				"d4c31722da4b84601bba43a275f9298d");
		Token dadToken = new Token("c5b008ae6a99edb38b42b41693812b7a",
				"d47620a84085689b29b7feeb4e97668f");
		Token momToken = new Token("fe28c9ef08ec5e7a4470aba1392e5d7c",
						"a88d926564d31773bad11c040347df88");
		OAuthService service = new ServiceBuilder().provider(FitBitAPI.class)
				.apiKey("cc95f37510324f3aa58050ae36569bbe")
				.apiSecret("227b82a2c1bc4a25a16b1caa87efd259")
				.callback("http://fitbit.com/oauth/callback").debug().build();
		ObjectMapper mapper = new ObjectMapper();
		OAuthRequest serviceRequest = new OAuthRequest(
				Verb.GET,
				FitBitAPI.BASE_URL
						+ "1/user/-/body/log/weight/date/2015-01-01/2015-01-20.json");
		service.signRequest(lauraToken, serviceRequest);
		Response serviceResponse = serviceRequest.send();
		logger.info(serviceResponse.getBody());

		Weight lauraWeight = new Weight();
		try {
			lauraWeight = mapper.readValue(serviceResponse.getBody(),
					Weight.class);
			logger.info(lauraWeight.getWeight().get(0).getWeight().toString());
			model.addAttribute("lauraProfileText", "Laura Sedor's Weight History");
			model.addAttribute("lauraWeight", lauraWeight);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(lauraWeight);
		
		
	
		serviceRequest = new OAuthRequest(
				Verb.GET,
				FitBitAPI.BASE_URL
						+ "1/user/-/body/log/weight/date/2015-01-01/2015-01-20.json");
		service.signRequest(momToken, serviceRequest);
	
		 serviceResponse = serviceRequest.send();
		 Weight momWeight = new Weight();
			try {
				momWeight = mapper.readValue(serviceResponse.getBody(),
						Weight.class);
				logger.info(momWeight.getWeight().get(0).getWeight().toString());
				model.addAttribute("momProfileText", "Mom's Weight History");
				model.addAttribute("momWeight", momWeight);
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println(momWeight);
			serviceRequest = new OAuthRequest(
					Verb.GET,
					FitBitAPI.BASE_URL
							+ "1/user/-/body/log/weight/date/2015-01-01/2015-01-20.json");
			service.signRequest(dadToken, serviceRequest);
			 serviceResponse = serviceRequest.send();
			 Weight dadWeight = new Weight();
				try {
					dadWeight = mapper.readValue(serviceResponse.getBody(),
							Weight.class);
					logger.info(dadWeight.getWeight().get(0).getWeight().toString());
					model.addAttribute("dadProfileText", "Dad's Weight History");
					model.addAttribute("dadWeight", dadWeight);
				} catch (JsonParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JsonMappingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(dadWeight);
		return "FitbitProfile";

	}

}
