package ru.alishev.springcourse.controller;


import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.alishev.springcourse.models.Postuniv;
import ru.alishev.springcourse.models.Themes;
import ru.alishev.springcourse.models.User;
import ru.alishev.springcourse.repo.PostunivRepository;
import ru.alishev.springcourse.repo.UserRepo;

@Controller
public class GreetingController {

	@Autowired
	private PostunivRepository postunivRepository;
	@Autowired
    private UserRepo userRepo;
	@Autowired
	private Themes th;
	
	@GetMapping("/home")
	public String greeting(Model model) {
		return "index";
	}
	@GetMapping("/")
	public String greetin(Model model) {
		return "index";
	}
	@GetMapping("/post/{theme}")
	public String post(@PathVariable(value="theme") String theme, Model model)
	{
		model.addAttribute("text",theme);
		return "post";
	}
	@PostMapping("/post/{theme}")
	public String postP(@PathVariable(value="theme") String theme,@AuthenticationPrincipal User user,@RequestParam String text, Model model)
	{
		
		if(th.isThemes(theme)) 
		{
		System.out.println("kek");
		System.out.println(text);
		Postuniv post=new Postuniv(text,user,theme);
		User usr=userRepo.findById(user.getId()).orElseThrow();
		usr.addSet(post);
		userRepo.save(usr);
		postunivRepository.save(post);
		}
		return "redirect:/home";
		
	}
	
	
	@GetMapping("/{tema}")
	public String postCaiet(@PathVariable(value="tema") String tema, Model model)
	{
		//List<Postuniv> posts=postunivRepository.findTop5ByTheme(tema);
		List<Postuniv> posts=postunivRepository.ownFindTeme(tema,0);
		model.addAttribute("var",posts);
		model.addAttribute("page",1);
		model.addAttribute("tema",tema);
		return tema;
	}
	@GetMapping("/{tema}/{page}")
	public String postPage(@PathVariable(value="tema") String tema,@PathVariable(value="page") int page, Model model)
	{
		if(page<=1)
		{
			return "redirect:/"+tema;
		}
		//List<Postuniv> posts=postunivRepository.findTop5ByTheme(tema);
		List<Postuniv> posts=postunivRepository.ownFindTeme(tema,(page-1)*5);
		model.addAttribute("var",posts);
		model.addAttribute("page",page);
		model.addAttribute("tema",tema);
		model.addAttribute("var",posts);
		return tema;
	}
	
	
	@GetMapping("/user/{id}")
	public String user_info(@PathVariable(value="id") Long id,Model model)
	{
		User usr = userRepo.findById(id).orElseThrow();
		Set<Postuniv> posts=usr.getPosts();
		model.addAttribute("user",usr);
		model.addAttribute("posts",posts);
		return "user";
	}
	@GetMapping("/mypage")
	public String curent_user(@AuthenticationPrincipal User usr, Model model)
	{
		System.out.println(usr.getUsername());
		System.out.println(usr.getId());
		Set<Postuniv> posts=usr.getPosts();
		model.addAttribute("user",usr);
		model.addAttribute("posts",posts);
		return "user";
	}

}
