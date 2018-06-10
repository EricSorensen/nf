package com.es.nf.commons.ws;

import com.auth0.client.auth.AuthAPI;
import com.auth0.exception.Auth0Exception;
import com.auth0.json.auth.TokenHolder;
import com.auth0.net.AuthRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Auth0RestTemplateInterceptor implements ClientHttpRequestInterceptor {

    @Value("${com.es.nf.ws.oauth2.clientId}")
    private String clientId;

    @Value("${com.es.nf.ws.oauth2.clientSecret}")
    private String clientSecret;

    @Value("${com.es.nf.ws.oauth2.domain}")
    private String idpDomain;

    @Value("${com.es.nf.ws.oauth2.audience}")
    private String audience;

    private TokenHolder tokenHolder;

    public String getClientId() {
        return clientId;
    }
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }
    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getIdpDomain() {
        return idpDomain;
    }

    public String getAudience() {
        return audience;
    }


    private String getAccessToken() throws Auth0Exception {

        if ((tokenHolder == null) || (tokenHolder.getExpiresIn() <=0)) {
            obtainAccessToken();
        }
        return tokenHolder.getAccessToken();
    }

    private void obtainAccessToken() throws Auth0Exception {
        AuthAPI auth0API = null;

        auth0API = new AuthAPI(getIdpDomain(), getClientId(), getClientSecret());
        AuthRequest request = auth0API.requestToken(getAudience()).setScope("personnage.read personnage.write");
        tokenHolder = request.execute();
    }


    private HttpHeaders addAuthenticationHttpHeaders(HttpHeaders pHeader) throws Auth0Exception {
        String bearer = "Bearer " + getAccessToken();
        pHeader.setContentType(MediaType.APPLICATION_JSON);
        pHeader.add("Authorization", bearer);
        return pHeader;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request,
                                        byte[] body,
                                        ClientHttpRequestExecution execution) throws IOException {

        HttpHeaders headers = request.getHeaders();

        addAuthenticationHttpHeaders(headers);
        ClientHttpResponse response = execution.execute(request, body);
        return response;
    }


}
