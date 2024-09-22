package com.timskiproekt.pagepicks.controller;

import com.timskiproekt.pagepicks.domain.model.Blog;
import com.timskiproekt.pagepicks.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blogs")
@RequiredArgsConstructor
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class BlogController {

    private final BlogService blogService;

    @GetMapping
    public ResponseEntity<List<Blog>> getAllBlogs() {
        List<Blog> blogs = blogService.getAllBlogs();
        return ResponseEntity.ok(blogs);
    }

    @PostMapping
    public ResponseEntity<Blog> createBlog(
            @RequestParam String title,
            @RequestParam String content,
            @RequestParam Integer authorId,
            @RequestParam(required = false) List<String> tags) {

        Blog newBlog = blogService.createBlog(title, content, authorId, tags);
        return ResponseEntity.ok(newBlog);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Blog> updateBlog(
            @PathVariable Long id,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String content,
            @RequestParam(required = false) List<String> tags) {

        Blog updatedBlog = blogService.updateBlog(id, title, content, tags);
        return ResponseEntity.ok(updatedBlog);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlog(@PathVariable Long id) {
        blogService.deleteBlog(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public Blog getBlogById(@PathVariable Long id) {
        return blogService.getBlogById(id);
    }
}

