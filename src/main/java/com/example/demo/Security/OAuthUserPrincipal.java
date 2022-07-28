package com.example.demo.Security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;

import java.util.Collection;

public class OAuthUserPrincipal  {

    DefaultOidcUser userPrincipal;
    public OAuthUserPrincipal(DefaultOidcUser user) {
           this.userPrincipal = user;
    }
public String getUsername() {
        return userPrincipal.getGivenName();
    }
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userPrincipal.getAuthorities();
    }

    //email
    public String getEmail() {
        return userPrincipal.getEmail();
    }

}
