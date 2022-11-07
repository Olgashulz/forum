package telran.java2022.forum.service;

import java.util.List;

import telran.java2022.forum.dto.CommentDto;
import telran.java2022.forum.dto.CreatePostDto;
import telran.java2022.forum.dto.PeriodDto;
import telran.java2022.forum.dto.PostDto;
import telran.java2022.forum.dto.UpdatePostDto;

public interface ForumService {

	PostDto addPost(CreatePostDto createPostDto, String author);

	PostDto findPostById(String id);

	PostDto deletePost(String id);

	PostDto updatePost(UpdatePostDto updatePostDto, String id);

	void addLike(String id);

	PostDto addComment(CommentDto commentDto, String id, String user);

	List<PostDto> findPostsByAuthor(String author);

	List<PostDto> findPostsByTags(List<String> tags);

	List<PostDto> findPostsByPeriod(PeriodDto periodDto);

}
