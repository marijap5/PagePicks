package com.timskiproekt.pagepicks.service;

import com.timskiproekt.pagepicks.domain.model.Blog;
import com.timskiproekt.pagepicks.domain.model.User;
import com.timskiproekt.pagepicks.repository.BlogRepository;
import com.timskiproekt.pagepicks.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;
    private final UserRepository userRepository;

    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    public Blog createBlog(String title, String content, Integer authorId, List<String> tags) {
        User author = userRepository.findById(authorId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Blog blog = Blog.builder()
                .title(title)
                .content(content)
                .author(author)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .tags(tags)
                .likes(0)
                .build();

        return blogRepository.save(blog);
    }

    public Blog updateBlog(Long id, String title, String content, List<String> tags) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Blog not found"));

        blog.setTitle(title);
        blog.setContent(content);
        blog.setTags(tags);
        blog.setUpdatedAt(LocalDateTime.now());

        return blogRepository.save(blog);
    }

    public void deleteBlog(Long id) {
        if (!blogRepository.existsById(id)) {
            throw new IllegalArgumentException("Blog not found");
        }
        blogRepository.deleteById(id);
    }
    public Blog getBlogById(Long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Blog not found"));
    }

}
