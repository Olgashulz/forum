package telran.java2022.forum.post.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import telran.java2022.forum.comment.model.Comment;

@Getter
@EqualsAndHashCode(of = "id")
@Document(collection = "forum")
@NoArgsConstructor
public class Post {
	@Id
	String id;
	@Setter
	String title;
	String content;
	@Setter
	String author;
	LocalDateTime dateCreated = LocalDateTime.now().withNano(0);
	@Setter
	List<String> tags = new ArrayList<>();
	@Setter
	Integer likes = 0;
	@Setter
	List<Comment> comments = new ArrayList<>();

	public Post(String title, String content, String author) {
		this.title = title;
		this.content = content;
		this.author = author;
	}

	public void addTag(String tag) {
		tags.add(tag);
	}

	public void addComment(Comment comment) {
		comments.add(comment);
	}

}
