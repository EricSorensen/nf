package com.es.nf.commons.ws;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class Auth0RestTemplate extends RestTemplate implements InitializingBean {

    @Autowired
    private Auth0RestTemplateInterceptor interceptor;

    public Auth0RestTemplate () {
        super();

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        List<ClientHttpRequestInterceptor> interceptors
                = getInterceptors();
        interceptors = new ArrayList<>();
        interceptors.add(interceptor);
        setInterceptors(interceptors);

    }

}
