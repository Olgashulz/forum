package telran.java2022.forum.dto.exceptions;

public class PostNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 5958056815402737926L;
	
	public PostNotFoundException (String id) {
		super("Post with id: " + id + " not found!");
	}

}
