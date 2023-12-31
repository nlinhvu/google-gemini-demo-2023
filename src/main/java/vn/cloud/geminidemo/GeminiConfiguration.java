package vn.cloud.geminidemo;

import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.generativeai.preview.ChatSession;
import com.google.cloud.vertexai.generativeai.preview.GenerativeModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

import java.io.IOException;

@Configuration(proxyBeanMethods = false)
public class GeminiConfiguration {

    @Bean
    public VertexAI vertexAI() throws IOException {
        return new VertexAI("gemini-test-409710", "asia-southeast1");
    }

    @Bean
    public GenerativeModel geminiProVisionGenerativeModel(VertexAI vertexAI) {
        return new GenerativeModel("gemini-pro-vision", vertexAI);
    }

    @Bean
    public GenerativeModel geminiProGenerativeModel(VertexAI vertexAI) {
        return new GenerativeModel("gemini-pro", vertexAI);
    }

    @Bean
    @SessionScope
    public ChatSession chatSession(@Qualifier("geminiProGenerativeModel") GenerativeModel generativeModel) {
        return new ChatSession(generativeModel);
    }
}
