//package com.newx.staging.util;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.RequestEntity;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.client.SimpleClientHttpRequestFactory;
//import org.springframework.http.converter.StringHttpMessageConverter;
//import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
//import org.springframework.security.oauth2.client.OAuth2RestTemplate;
//import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
//import org.springframework.security.oauth2.common.AuthenticationScheme;
//import org.springframework.web.client.DefaultResponseErrorHandler;
//import org.springframework.web.client.RestTemplate;
//
//import java.nio.charset.Charset;
//import java.util.Map;
//
///**
// * Created by jiaquan on 2016/12/19.
// */
//@Configuration
//public class HttpClient {
//
//    private static Logger log = LoggerFactory.getLogger(HttpClient.class);
//    private static int CONNECTION_TIMEOUT=15000; //15秒
//    private static int CONNECTION_READ_TIMEOUT=15000; //15秒
//
//    @Value("${security.oauth2.client.access-token-uri}")
//    String accessTokenURI;
//    @Value("${security.oauth2.client.client-id}")
//    String clientID;
//    @Value("${security.oauth2.client.client-secret}")
//    String clientSecret;
//    @Value("${security.oauth2.client.client-authentication-scheme}")
//    String clientAuthenticationScheme;
//    @Value("${security.oauth2.client.grant-type}")
//    String grantType;
//
//    ClientCredentialsResourceDetails resourceDetails() {
//        ClientCredentialsResourceDetails details = new ClientCredentialsResourceDetails();
//        details.setAccessTokenUri(accessTokenURI);
//        details.setClientId(clientID);
//        details.setClientSecret(clientSecret);
//        details.setGrantType(grantType);
//        details.setAuthenticationScheme(AuthenticationScheme.valueOf(clientAuthenticationScheme));
//        return details;
//    }
//
//    /**
//     * 获取OAuth2RestTemplate
//     * @return OAuth2RestTemplate
//     */
//    public OAuth2RestTemplate getHlyOAuth2RestTemplate() {
//        OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resourceDetails(), new DefaultOAuth2ClientContext());
//        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
//        requestFactory.setReadTimeout(CONNECTION_READ_TIMEOUT);
//        requestFactory.setConnectTimeout(CONNECTION_TIMEOUT);
//        restTemplate.setRequestFactory(requestFactory);
//        //处理中文乱码
//        restTemplate.getMessageConverters().add(0,
//            new StringHttpMessageConverter(Charset.forName("UTF-8")));
//        return restTemplate;
//    }
//
//    /**
//     * 获取处理Query Params OAuth2RestTemplate（自动进行xxx的安全认证），会自动解析传入的Query Params参数
//     * @return OAuth2RestTemplate
//     */
//    public OAuth2RestTemplate getHlyQueryParamsOAuth2RestTemplate() {
//        OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resourceDetails(), new DefaultOAuth2ClientContext());
//        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
//        requestFactory.setReadTimeout(CONNECTION_READ_TIMEOUT);
//        requestFactory.setConnectTimeout(CONNECTION_TIMEOUT);
//        restTemplate.setRequestFactory(requestFactory);
//        //处理中文乱码
//        restTemplate.getMessageConverters().add(0,
//            new StringHttpMessageConverter(Charset.forName("UTF-8")));
//        restTemplate.setUriTemplateHandler(new QueryParamsUrlTemplateHandler());
//        return restTemplate;
//    }
//
//    /**
//     * 获取默认配置的RestTemplate
//     * @return 默认RestTemplate
//     */
//    public RestTemplate getRestTemplate() {
//        RestTemplate restTemplate = new RestTemplate();
//        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
//        requestFactory.setReadTimeout(CONNECTION_READ_TIMEOUT);
//        requestFactory.setConnectTimeout(CONNECTION_TIMEOUT);
//        restTemplate.setRequestFactory(requestFactory);
//        //处理中文乱码
//        restTemplate.getMessageConverters().add(0,
//            new StringHttpMessageConverter(Charset.forName("UTF-8")));
//        return restTemplate;
//    }
//
//    /**
//     * 获取默认配置的处理Query Params RestTemplate
//     * @return Query Params RestTemplate
//     */
//    public RestTemplate getQueryParamsRestTemplate() {
//        RestTemplate restTemplate = new RestTemplate();
//        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
//        requestFactory.setReadTimeout(CONNECTION_READ_TIMEOUT);
//        requestFactory.setConnectTimeout(CONNECTION_TIMEOUT);
//        restTemplate.setRequestFactory(requestFactory);
//        //处理中文乱码
//        restTemplate.getMessageConverters().add(0,
//            new StringHttpMessageConverter(Charset.forName("UTF-8")));
//        restTemplate.setUriTemplateHandler(new QueryParamsUrlTemplateHandler());
//        return restTemplate;
//    }
//
//    /**
//     * 可以生成自定义配置的RestTemplate实例
//     * @param requestFactory
//     * @return
//     */
//    public RestTemplate getRestTemplate(SimpleClientHttpRequestFactory requestFactory) {
//        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.setRequestFactory(requestFactory);
//        //处理中文乱码
//        restTemplate.getMessageConverters().add(0,
//            new StringHttpMessageConverter(Charset.forName("UTF-8")));
//        restTemplate.setErrorHandler(new DefaultResponseErrorHandler());
//        return restTemplate;
//    }
//
//    /**
//     * 发送各种http请求，可以通过RequestEntity添加header,可以通过uriVariables添加path params 和query params
//     * @param requestEntity
//     * @param uriVariables path params 和query params的map
//     * @return
//     */
//    public ResponseEntity<String> exchange(RequestEntity<?> requestEntity, Map<String, Object> uriVariables){
//        RestTemplate restTemplate = getQueryParamsRestTemplate();
//        return restTemplate.exchange(requestEntity.getUrl().toString(), requestEntity.getMethod(), requestEntity, String.class, uriVariables);
//    }
//
//
//    /**
//     * 发送http各种请求，可以通过RequestEntity添加header
//     * @param requestEntity
//     * @return
//     */
//    public ResponseEntity<String> exchange(RequestEntity<?> requestEntity){
//        RestTemplate restTemplate = getRestTemplate();
//        return restTemplate.exchange(requestEntity, String.class);
//    }
//
//    /**
//     * 通过OAuth2向xxx发送http各种请求，OAuth2自动进行安全校验，可以通过RequestEntity添加header
//     * @param requestEntity
//     * @return
//     */
//    public ResponseEntity<String> exchangeHly(RequestEntity<?> requestEntity){
//        OAuth2RestTemplate oAuth2RestTemplate = getHlyOAuth2RestTemplate();
//        return oAuth2RestTemplate.exchange(requestEntity, String.class);
//    }
//
//    /**
//     * 通过OAuth2向xxx发送http各种请求，OAuth2自动进行安全校验，可以通过RequestEntity添加header,可以通过uriVariables添加path params 和query params
//     * @param requestEntity
//     * @param uriVariables path params 和query params的map
//     * @return
//     */
//    public ResponseEntity<String> exchangeHly(RequestEntity<?> requestEntity, Map<String, Object> uriVariables){
//        RestTemplate restTemplate = getHlyQueryParamsOAuth2RestTemplate();
//        return restTemplate.exchange(requestEntity.getUrl().toString(), requestEntity.getMethod(), requestEntity, String.class, uriVariables);
//    }
//
//    /**
//     * 发送http各种请求，可以通过RequestEntity添加header
//     * @param requestEntity
//     * @return
//     */
//    public ResponseEntity<String> exchange(SimpleClientHttpRequestFactory requestFactory, RequestEntity<?> requestEntity){
//        RestTemplate restTemplate = getRestTemplate(requestFactory);
//        return restTemplate.exchange(requestEntity, String.class);
//    }
//
//    //带有延时设置的RestTemplate
//    public RestTemplate getTimeoutRestTemplate() {
//        RestTemplate restTemplate = new RestTemplate();
//        //设置超时
//        ((SimpleClientHttpRequestFactory)restTemplate.getRequestFactory()).setReadTimeout(1000*30);
//        //处理中文乱码
//        restTemplate.getMessageConverters().add(0,
//            new StringHttpMessageConverter(Charset.forName("UTF-8")));
//        return restTemplate;
//    }
//
//    //带有延时设置的exchange
//    public ResponseEntity<String> exchangeTimeout(RequestEntity<?> requestEntity){
//        RestTemplate restTemplate = getTimeoutRestTemplate();
//        return restTemplate.exchange(requestEntity, String.class);
//    }
//}
