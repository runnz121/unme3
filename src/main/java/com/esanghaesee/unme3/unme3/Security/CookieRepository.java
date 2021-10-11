package com.esanghaesee.unme3.unme3.Security;

import com.nimbusds.oauth2.sdk.util.StringUtils;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CookieRepository implements AuthorizationRequestRepository<OAuth2AuthorizationRequest> {

    public static final String OAUTH_COOKIE = "oauth2_auth_request";
    public static final String REDIRECT_COOKIE = "redirect_uri";
    public static final int COOKIEEXP = 180;


    @Override
    public OAuth2AuthorizationRequest loadAuthorizationRequest(HttpServletRequest request) {
        return CookiesUtils.getCookie(request, OAUTH_COOKIE)
                .map(cookie -> CookiesUtils.deserialize(cookie, OAuth2AuthorizationRequest.class))
                .orElse(null);
    }

    @Override
    public void saveAuthorizationRequest(OAuth2AuthorizationRequest authorizationRequest, HttpServletRequest request, HttpServletResponse response) {
        if (authorizationRequest == null){
            CookiesUtils.deleteCookie(request, response, OAUTH_COOKIE);
            CookiesUtils.deleteCookie(request, response, REDIRECT_COOKIE);
            return;
        }

        CookiesUtils.addCookie(response, OAUTH_COOKIE, CookiesUtils.serialize(authorizationRequest), COOKIEEXP);
        String redirectUriLogin = request.getParameter(REDIRECT_COOKIE);
        if(StringUtils.isNotBlank(redirectUriLogin)){
            CookiesUtils.addCookie(response, REDIRECT_COOKIE, redirectUriLogin, COOKIEEXP);
        }
    }

    @Override
    public OAuth2AuthorizationRequest removeAuthorizationRequest(HttpServletRequest request) {
        return this.loadAuthorizationRequest(request);
    }


    public void removeAuthorizationRequestCookies(HttpServletRequest request, HttpServletResponse response) {
        CookiesUtils.deleteCookie(request, response, OAUTH_COOKIE);
        CookiesUtils.deleteCookie(request,response,REDIRECT_COOKIE);
    }
}
