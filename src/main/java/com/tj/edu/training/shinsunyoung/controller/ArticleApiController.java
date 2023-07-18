package com.tj.edu.training.shinsunyoung.controller;

import com.tj.edu.training.shinsunyoung.model.Article;
import com.tj.edu.training.shinsunyoung.model.dto.AddArticleRequest;
import com.tj.edu.training.shinsunyoung.model.dto.ArticleResponse;
import com.tj.edu.training.shinsunyoung.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ArticleApiController {

    private final ArticleService articleService;

    /**
     *  Desc : 블로그 글 등록 api
     * @param request
     * @return
     */

    // 블로그 글 추가하기
    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request) {
        Article savedArticle = articleService.save(request);   // 글 저장

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle);
    }
    // 블로그 글 목록
    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles() {
        List<Article> articleList = articleService.getArticleAll();

        // stream 형식을 List 형식으로 바꿔주는 코드
        List<ArticleResponse> articleResponseList = articleList.stream()
                .map(ArticleResponse::new)
                .toList();

//        List<ArticleResponse> articleResponseList1 = new ArrayList<>();   // 위에 코드를 이런식으로도 표현함
//        for (Article article : articleList){
//            ArticleResponse articleResponse = new ArticleResponse(article);
//            articleResponseList1.add(articleResponse);
//
//        }

        return ResponseEntity.ok().body(articleResponseList);
    }

    // 블로그 글 상세보기
    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable long id){
        Article article = articleService.getArticle(id);

        return ResponseEntity.ok().body(new ArticleResponse(article));
    }
    
    // 블로그 글 삭제하기
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<String> deleteArticle(@PathVariable long id) {
        articleService.delete(id);

        return ResponseEntity.ok().body(new String("삭제한 블로그 글 번호 : " + id));
    }
    // 블로그 글 수정하기
    @PutMapping("/api/articles/{id}")
    public ResponseEntity<String> updateArticle(@PathVariable long id) {
        articleService.delete(id);

        return ResponseEntity.ok().body(new String("삭제한 블로그 글 번호 : " + id));
    }

}
