package org.ged.web;



import org.ged.entities.AppUser;
import org.ged.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;





@Controller
public class AccountRestController {
    
	@Autowired
	private AccountService accountService;
	
	@RequestMapping(value="/register",method = RequestMethod.POST)
	public String register(Model model, @RequestParam("username") String username,
			@RequestParam("password") String password,
			@RequestParam("repassword") String repassword) {
		System.out.println("username"+username);
		System.out.println("password"+password);
		System.out.println("repassword"+repassword);
		if(!password.equals(repassword))
				throw new RuntimeException("You must confirm your password");
		AppUser user= accountService.findUserByUsername(username);
		if(user!=null) throw new RuntimeException("this user already exists");
		AppUser appUser = new AppUser();
		appUser.setUsername(username);
		appUser.setPassword(password);
		
		 accountService.saveUser(appUser);
		 accountService.addRoleToUser(username, "USER");
		 System.out.println("appuser"+appUser);
		 
		 model.addAttribute("msg","Registation avec Succès!!!!"+appUser.getUsername());
		 return "redirect:/login";
		
	}
	@RequestMapping("/registerForm")
	public String registerForm ()  {
		
		return "registerForm";
	}
}
