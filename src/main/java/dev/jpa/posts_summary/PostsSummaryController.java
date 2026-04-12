package dev.jpa.posts_summary;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.jpa.ai.summary.PostsSummaryAiService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/posts/summary")
@RequiredArgsConstructor
public class PostsSummaryController {

    private final PostsSummaryService service;
    private final PostsSummaryAiService aiService;   // 🔥 추가

    @GetMapping("/{postId}")
    public PostsSummaryDTO get(@PathVariable("postId") Long postId) {
        return service.getByPostId(postId);
    }

    // 테스트용 수동 저장
    @PostMapping
    public void save(@RequestBody PostsSummaryDTO dto) {
        service.save(dto);
    }

    // 🔥 AI 요약 생성
    @PostMapping("/ai/{postId}")
    public void generate(@PathVariable("postId") Long postId) {
        aiService.generateSummary(postId);
    }
}
