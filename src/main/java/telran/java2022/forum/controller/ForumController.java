package telran.java2022.forum.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import telran.java2022.forum.dto.CommentDto;
import telran.java2022.forum.dto.CreatePostDto;
import telran.java2022.forum.dto.PeriodDto;
import telran.java2022.forum.dto.PostDto;
import telran.java2022.forum.dto.UpdatePostDto;
import telran.java2022.forum.service.ForumService;

@RestController
@RequiredArgsConstructor
public class ForumController {
	final ForumService forumService;

	@PostMapping("/forum/post/{author}")
	public PostDto addPost(@RequestBody CreatePostDto createPostDto, @PathVariable String author) {
		return forumService.addPost(createPostDto, author);
	}

	@GetMapping("/forum/post/{id}")
	public PostDto findPost(@PathVariable String id) {
		return forumService.findPostById(id);
	}

	@DeleteMapping("/forum/post/{id}")
	public PostDto deletePost(@PathVariable String id) {
		return forumService.deletePost(id);
	}

	@PutMapping("/forum/post/{id}")
	public PostDto updatePost(@RequestBody UpdatePostDto updatePostDto, @PathVariable String id) {
		return forumService.updatePost(updatePostDto, id);
	}

	@PutMapping("/forum/post/{id}/like")
	public void addLike(@PathVariable String id) {
		forumService.addLike(id);
	}

	@PutMapping("/forum/post/{id}/comment/{user}")
	public PostDto addComment(@RequestBody CommentDto commentDto, @PathVariable String id, @PathVariable String user) {
		return forumService.addComment(commentDto, id, user);
	}

	@GetMapping("/forum/posts/author/{author}")
	public List<PostDto> postsByAutor(@PathVariable String author) {
		return forumService.findPostsByAuthor(author);
	}

	@PostMapping("/forum/posts/tags")
	public List<PostDto> postsByTags(@RequestBody List<String> tags) {
		return forumService.findPostsByTags(tags);
	}

	@PostMapping("/forum/posts/period")
	public List<PostDto> postsByPeriod(@RequestBody PeriodDto periodDto) {
		return forumService.findPostsByPeriod(periodDto);

	}

}
