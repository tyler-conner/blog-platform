package wcci.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PostController {

	@Autowired
	private PostRepository postRepo;

	@RequestMapping("/posts")
	public String getAllPosts(Model model) {
		model.addAttribute("postsAttribute", postRepo.findAll());
		return "postsTemplate";
	}

	@RequestMapping("/posts/{id}")
	public String getOnePost(@PathVariable ("id") long id, Model model) {
		model.addAttribute("postAttribute", postRepo.findById(id).get());
		return "postTemplate";
	}

	@PostMapping("/add")
	public String addPost(String title, String authorString, String categoryString, String content, String tagsString) {
		Category category = new Category(categoryString);
		Author author = new Author(authorString);
		Tag tag = new Tag(tagsString);
		postRepo.save(new Post(title, author, category, content, tag));
		return "redirect:/posts";
	}

}
