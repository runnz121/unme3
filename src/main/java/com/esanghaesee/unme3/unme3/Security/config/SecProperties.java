package com.esanghaesee.unme3.unme3.Security.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@ConfigurationProperties(prefix = "sec")
public class SecProperties {

    private final Auth auth = new Auth();
    private final OAuth2 oauth2 = new OAuth2();

    public static class Auth {
        private String tokenSecret;
        private long tokenExpiry;

        public String getTokenSecret(){
            return tokenSecret;
        }

        public void setTokenSecret(String tokenSecret){
            this.tokenSecret = tokenSecret;
        }

        public long getTokenExpiry(){
            return tokenExpiry;
        }

        public void setTokenExpiry(long tokenExpiry){
            this.tokenExpiry = tokenExpiry;
        }

    }

    public static final class OAuth2 {
        private List<String> authorizedRedirectUris = new ArrayList<>();

        private List<String> getAuthorizedRedirectUris() {
            return authorizedRedirectUris;
        }

        public OAuth2 authorizedRedirectUris(List<String> authorizedRedirectUris) {
            this.authorizedRedirectUris = authorizedRedirectUris;
            return this;
        }

    }
    public Auth getAuth() {
        return auth;
    }

    public OAuth2 getOauth2() {
        return oauth2;
    }

}
