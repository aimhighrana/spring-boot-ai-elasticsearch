package com.ranasoftcraft.org.ai.play;

import com.ranasoftcraft.org.ai.config.ElasticClient;
import com.ranasoftcraft.org.ai.entity.AiRequest;
import com.ranasoftcraft.org.ai.entity.AiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.json.JSONObject;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Configuration @RequiredArgsConstructor @Slf4j
public class ChatPlay {


    @Value("classpath:/schema.json")
    private Resource ddlResource;

    @Value("classpath:/elastic-prompt-template.st")
    private Resource elasticPromptTemplateResource;

    @Value("${spring.elastic.index-name}")
    private String indexName;

    private final ChatClient chatClient;

    private final ElasticClient elasticClient;

    public AiResponse doChat(AiRequest request) throws IOException {
        log.info("User entered this : {}", request.text());
        String schema = ddlResource.getContentAsString(Charset.defaultCharset());
        String query = chatClient.prompt()
                .advisors(new SimpleLoggerAdvisor())
                .user(userSpec -> userSpec
                        .text(elasticPromptTemplateResource)
                        .param("question", request.text())
                        .param("ddl", schema)
                )
                .call()
                .content();


        JSONObject jsonObject = null;
        try {
            jsonObject  = new JSONObject(query);
        } catch (Exception e) {
            query = query.substring(query.indexOf("{"), query.lastIndexOf("}")+1).replaceAll("\\\\",""); // temp fix
            jsonObject = new JSONObject(query);
        }
        SearchRequest searchRequest = new SearchRequest(indexName);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.wrapperQuery(jsonObject.get("query").toString()));
        searchSourceBuilder.size(100);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse =  elasticClient.getRestClient().search(searchRequest, RequestOptions.DEFAULT);
        List<Map<String, Object>> res = new ArrayList<>();
        for(SearchHit searchHit : searchResponse.getHits().getHits()) {
            res.add(searchHit.getSourceAsMap());
        }
        return new AiResponse(jsonObject.toString(), res);

    }
}
