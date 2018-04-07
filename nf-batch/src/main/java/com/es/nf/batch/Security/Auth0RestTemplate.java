package com.es.nf.batch.Security;

import com.auth0.client.auth.AuthAPI;
import com.auth0.exception.APIException;
import com.auth0.exception.Auth0Exception;
import com.auth0.json.auth.TokenHolder;
import com.auth0.net.AuthRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.lang.Nullable;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


public class Auth0RestTemplate extends RestTemplate {

    @Value("${com.es.nf.batch.oauth2.clientId}")
    private String clientId;

    @Value("${com.es.nf.batch.oauth2.clientSecret}")
    private String clientSecret;

    @Value("${com.es.nf.batch.oauth2.domain}")
    private String idpDomain;

    @Value("${com.es.nf.batch.oauth2.audience}")
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

    public <T> ResponseEntity<T> exchange(String url, HttpMethod method, ParameterizedTypeReference<T> responseType, Object... uriVariables) throws RestClientException {

        HttpHeaders headers = null;
        try {
            headers = createHttpHeaders();
        } catch (Auth0Exception e) {
            throw new RestClientException("Error duing Bearer injection",e);
        }
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        return super.exchange(url,method, entity,responseType, uriVariables);
    }

    private HttpHeaders createHttpHeaders() throws Auth0Exception {
        String bearer = "Bearer " + getAccessToken();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", bearer);
        return headers;
    }


}
