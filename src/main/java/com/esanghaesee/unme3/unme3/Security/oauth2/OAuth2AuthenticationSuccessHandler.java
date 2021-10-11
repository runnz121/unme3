package com.esanghaesee.unme3.unme3.Security.oauth2;

import com.esanghaesee.unme3.unme3.Security.CookieRepository;
import com.esanghaesee.unme3.unme3.Security.TokenProvider;
import com.esanghaesee.unme3.unme3.Security.config.SecProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.Optional;

@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private SecProperties secProperties;

    private TokenProvider tokenProvider;

    private CookieRepository cookieRepository;

    @Autowired
    OAuth2AuthenticationSuccessHandler(TokenProvider tokenProvider, SecProperties secProperties, CookieRepository cookieRepository){
        this.tokenProvider = tokenProvider;
        this.secProperties = secProperties;
        this.cookieRepository =cookieRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String targetUri = determineTargetUrl(request, response, authentication);


        getRedirectStrategy().sendRedirect(request, response, targetUri);
    }

    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

        String targetUri = "http://localhost:3000/oauth2/redirect?";
        String token = tokenProvider.createToken(authentication);
        return UriComponentsBuilder.fromUriString(targetUri).queryParam("token", token).build().toUriString();
    }

    private boolean isAuthorizedRedirectUri(String uri){
        URI redirectURI = URI.create(uri);

        return secProperties.getOauth2().getAuthorizedRedirectUris()
                .stream()
                .anyMatch(authorizedUri -> {
                    URI authorizedURI = URI.create(authorizedUri);
                    if(authorizedURI.getHost().equalsIgnoreCase(redirectURI.getHost()) && authorizedURI.getPort() == redirectURI.getPort()){
                        return true;
                    }
                    return false;
                });
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
        super.clearAuthenticationAttributes(request);
        cookieRepository.removeAuthorizationRequestCookies(request, response);
    }
}
