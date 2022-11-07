package telran.java2022.forum.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.java2022.forum.dao.ForumRepository;
import telran.java2022.forum.dto.CommentDto;
import telran.java2022.forum.dto.CreatePostDto;
import telran.java2022.forum.dto.PeriodDto;
import telran.java2022.forum.dto.PostDto;
import telran.java2022.forum.dto.UpdatePostDto;
import telran.java2022.forum.dto.exceptions.PostNotFoundException;
import telran.java2022.forum.post.model.Post;
import telran.java2022.forum.comment.model.Comment;

@Service
@RequiredArgsConstructor
public class ForumServiceImpl implements ForumService {
	final ForumRepository forumRepository;
	final ModelMapper modelMapper;

	@Override
	public PostDto addPost(CreatePostDto createPostDto, String author) {
		Post post = modelMapper.map(createPostDto, Post.class);
		post.setAuthor(author);
		forumRepository.save(post);
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostDto findPostById(String id) {
		Post post = forumRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostDto deletePost(String id) {
		Post post = forumRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
		forumRepository.deleteById(id);
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostDto updatePost(UpdatePostDto updatePostDto, String id) {
		Post post = forumRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));	
		post.setTitle(updatePostDto.getTitle());
		post.setTags(updatePostDto.getTags());
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public void addLike(String id) {
		Post post = forumRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
		post.setLikes(post.getLikes() + 1);
		forumRepository.save(post);
	}

	@Override
	public PostDto addComment(CommentDto commentDto, String id, String user) {
		Post post = forumRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
		Comment comment = new Comment(user, commentDto.getMessage());
        comment.setUser(user);
		post.addComment(comment);
		forumRepository.save(post);
		return modelMapper.map(post, PostDto.class);		
	}

	@Override
	public List<PostDto> findPostsByAuthor(String author) {
		return forumRepository.findByAuthorIgnoreCase(author)
				.map(p -> modelMapper.map(p, PostDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<PostDto> findPostsByTags(List<String> tags) {
		return forumRepository.findByTagsInIgnoreCase(tags)
				.map(p -> modelMapper.map(p, PostDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<PostDto> findPostsByPeriod(PeriodDto periodDto) {
		LocalDate fromDate = periodDto.getDateFrom();
		LocalDate toDateTime = periodDto.getDateTo();
		return forumRepository.findByDateCreatedBetween(fromDate, toDateTime)
				.map(p -> modelMapper.map(p, PostDto.class))
				.collect(Collectors.toList());
	}

}
