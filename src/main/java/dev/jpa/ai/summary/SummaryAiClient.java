package dev.jpa.ai.summary;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Map;

@Component
public class SummaryAiClient {

    @Value("${openai.api.key}")
    private String apiKey;

    private final WebClient webClient = WebClient.builder()
            .baseUrl("https://api.openai.com/v1")
            .build();

    /** 1️⃣ 프롬프트 생성 */
    public String buildPrompt(String title, String content) {
        return """
        다음 게시글을 한국어로 요약하세요.

        조건:
        - 3문장
        - 핵심 위주
        - 광고, 반복 제거

        [제목]
        %s

        [내용]
        %s
        """.formatted(title, content);
    }

    /** 2️⃣ OpenAI Responses API 호출 */
    public String requestSummary(String prompt) {
        try {
            Map<String, Object> body = Map.of(
                "model", "gpt-4o-mini",
                "input", prompt,
                "max_output_tokens", 300
            );

            return webClient.post()
                    .uri("/responses")
                    .header("Authorization", "Bearer " + apiKey)
                    .header("Content-Type", "application/json")
                    .bodyValue(body)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

        } catch (WebClientResponseException e) {
            throw new RuntimeException("OpenAI API Error: " + e.getResponseBodyAsString());
        }
    }
}
