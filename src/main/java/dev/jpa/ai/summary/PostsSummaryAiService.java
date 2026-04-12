package dev.jpa.ai.summary;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.jpa.posts.Posts;
import dev.jpa.posts.PostsRepository;
import dev.jpa.posts_summary.PostsSummary;
import dev.jpa.posts_summary.PostsSummaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostsSummaryAiService {

    private final PostsRepository postsRepository;
    private final PostsSummaryRepository summaryRepository;
    private final SummaryAiClient aiClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void generateSummary(Long postId) {

        // 1. 게시글 조회
        Posts post = postsRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("게시글 없음: " + postId));

        // 2. 프롬프트 생성
        String prompt = aiClient.buildPrompt(post.getTitle(), post.getContent());

        // 3. OpenAI 호출
        String aiJson = aiClient.requestSummary(prompt);

        // 4. 응답 파싱 (Responses API 구조)
        String summaryText;
        try {
            JsonNode root = objectMapper.readTree(aiJson);

            JsonNode output = root.path("output");
            if (!output.isArray() || output.size() == 0) {
                throw new RuntimeException("AI output 없음: " + aiJson);
            }

            JsonNode content = output.get(0).path("content");
            if (!content.isArray() || content.size() == 0) {
                throw new RuntimeException("AI content 없음: " + aiJson);
            }

            summaryText = content.get(0).path("text").asText();

        } catch (Exception e) {
            throw new RuntimeException("AI 응답 파싱 실패: " + aiJson, e);
        }

        // 5. DB 저장
        PostsSummary ps = new PostsSummary();
        ps.setPostId(postId);
        ps.setSummary(summaryText);

        summaryRepository.save(ps);
    }
}
