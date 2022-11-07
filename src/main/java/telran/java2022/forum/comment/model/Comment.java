package telran.java2022.forum.comment.model;

import java.time.LocalDateTime;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@EqualsAndHashCode(exclude = { "author", "dateCreated" })
@Document(collection = "forum")
@NoArgsConstructor
public class Comment {
	@Setter
	String author;
	@Setter
	String user;
	String message;
	LocalDateTime dateCreated = LocalDateTime.now().withNano(0);
	@Setter
	Integer likes = 0;

	public Comment(String user, String message) {
		this.user = user;
		this.message = message;
	}
}
