package com.ranasoftcraft.org.ai.config;

import jakarta.annotation.PostConstruct;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.ssl.SSLContextBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.RestHighLevelClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticClient {

    @Value("${spring.elastic.client.username}")
    private String username;

    @Value("${spring.elastic.client.password}")
    private String password;

    @Value("${spring.elastic.client.host}")
    private String host;

    @Value("${spring.elastic.client.port}")
    private int port;

    private RestHighLevelClient restClient;

    @PostConstruct
    public void init() throws Exception {
        // prepare credential provider
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials(username, password));

        RestClientBuilder builder = RestClient.builder(new HttpHost(host, port, "http"));

        builder.setRequestConfigCallback(requestConfigBuilder -> {
            return RequestConfig.custom()
                    .setContentCompressionEnabled(true)
                    .setConnectTimeout(5000) // 5 seconds
                    .setSocketTimeout(RestClientBuilder.DEFAULT_SOCKET_TIMEOUT_MILLIS);
        });

        builder.setCompressionEnabled(Boolean.TRUE);
        builder.setHttpClientConfigCallback(httpAsyncClientBuilder -> {
            // handle based on enable ssl verification
            try {
                httpAsyncClientBuilder.setSSLContext(new SSLContextBuilder().loadTrustMaterial(null, TrustAllStrategy.INSTANCE).build());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            httpAsyncClientBuilder.setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE);
            httpAsyncClientBuilder.setMaxConnPerRoute(RestClientBuilder.DEFAULT_MAX_CONN_PER_ROUTE); // 10
            httpAsyncClientBuilder.setMaxConnTotal(RestClientBuilder.DEFAULT_MAX_CONN_TOTAL); // 30

            httpAsyncClientBuilder.setDefaultCredentialsProvider(credentialsProvider); // set credentials only when required

            return httpAsyncClientBuilder;
        });
        this.restClient = new RestHighLevelClientBuilder(builder.build()).setApiCompatibilityMode(true).build();
    }

    public RestHighLevelClient getRestClient() {
        return restClient;
    }
}
