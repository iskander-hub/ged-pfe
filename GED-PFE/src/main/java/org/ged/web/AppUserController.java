package org.ged.web;

import javax.validation.Valid;

import org.ged.dao.RoleRepository;
import org.ged.dao.UserRepository;
import org.ged.entities.AppRole;
import org.ged.entities.AppUser;
import org.ged.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
public class AppUserController {
	@Autowired
	private UserRepository userRepository;
	 @Autowired
	   private BCryptPasswordEncoder bCryptPasswordEncoder;
	  @Autowired 
	   private RoleRepository roleRepository;
	  @Autowired
		private AccountService accountService;
	  @RequestMapping(value="/login")
		public String login() {
		return "login";
		}
		@RequestMapping(value="/403",method = RequestMethod.GET)	
		public String accessDnied() {
			return "403";
		}
		@RequestMapping(value="/",method = RequestMethod.GET)	
		public String home() {
			return "redirect:/users";
		}
	@RequestMapping(value="/users")
	public String index(Model model,
			@RequestParam (name="page",defaultValue = "0")int p,
			@RequestParam (name="size",defaultValue = "5")int s,
			@RequestParam (name="mc",defaultValue = "")String mc) {
		PageRequest pageable = PageRequest.of(p, s);
		Page<AppUser> pageUsers=userRepository.chercher("%"+mc+"%",pageable);
		model.addAttribute("users", pageUsers.getContent());
		int[] pages= new int[pageUsers.getTotalPages()];
		model.addAttribute("pages", pages);
		model.addAttribute("size", s);
		model.addAttribute("pageCourante", p);
		model.addAttribute("mc", mc);
		
		return "users";
	}
	@RequestMapping(value="/deleteUser",method=RequestMethod.GET)
	public String delete(Long id, String mc, int page, int size) {
		userRepository.deleteById(id);
		return "redirect:/users?page="+page+"&size="+size+"&mc="+mc;
	}
	
	@RequestMapping(value="/formUser",method = RequestMethod.GET)
	public String formUser(Model model) {
		model.addAttribute("user",new AppUser());
		model.addAttribute("roles", roleRepository.findAll());
		
		
		return "formUser";
	}
	@RequestMapping(value="/editUser",method = RequestMethod.GET)
	public String editUser(Model model,Long id) {
		AppUser p = userRepository.getById(id);
		model.addAttribute("user",p);
		model.addAttribute("roles", roleRepository.findAll());
		return "editUser";
	}
	@ModelAttribute("user")
	public AppUser construct() {
		return new AppUser();
	}
	@RequestMapping(value="/saveUser",method = RequestMethod.POST)
	public String saveUser(Model model,@ModelAttribute("userForm") UserForm userForm ,@Validated AppUser user, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) 
			return "formUser";
		
			
	System.out.println("roleName"+userForm.getRole().getRoleName());
		AppRole role= roleRepository.findByRoleName(userForm.getRole().getRoleName());
		accountService.saveUser(user);
		
		accountService.addRoleToUser(user.getUsername(),role.getRoleName());
		
		model.addAttribute("user", user);
		return "confirmationUser";
	}
	/*@RequestMapping(value="/",method = RequestMethod.GET)	
	public String home() {
		return "redirect:/index";
	}
	@RequestMapping(value="/login")
	public String login() {
	return "login";
	}
	@RequestMapping(value="/403",method = RequestMethod.GET)	
	public String accessDnied() {
		return "403";
	}*/

}
