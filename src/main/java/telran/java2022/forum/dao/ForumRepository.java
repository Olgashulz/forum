package telran.java2022.forum.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.repository.CrudRepository;
import telran.java2022.forum.post.model.Post;

public interface ForumRepository extends CrudRepository<Post, String> {
	Stream<Post> findByAuthorIgnoreCase(String author);

	Stream<Post> findByTagsInIgnoreCase(List<String> tags);

	Stream<Post> findByDateCreatedBetween(LocalDate fromDate, LocalDate toDateTime);

}
