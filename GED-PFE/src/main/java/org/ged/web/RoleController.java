package org.ged.web;








import javax.validation.Valid;

import org.ged.dao.RoleRepository;
import org.ged.entities.AppRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;






@Controller
public class RoleController {
	@Autowired
	private RoleRepository roleRepository;
	
	@RequestMapping(value="/roles")
	public String index(Model model,
			@RequestParam (name="page",defaultValue = "0")int p,
			@RequestParam (name="size",defaultValue = "5")int s,
			@RequestParam (name="mc",defaultValue = "")String mc) {
		PageRequest pageable = PageRequest.of(p, s);
		Page<AppRole> pageRoles=roleRepository.chercher("%"+mc+"%",pageable);
		model.addAttribute("roles", pageRoles.getContent());
		int[] pages= new int[pageRoles.getTotalPages()];
		model.addAttribute("pages", pages);
		model.addAttribute("size", s);
		model.addAttribute("pageCourante", p);
		model.addAttribute("mc", mc);
		
		return "roles";
	}
	@RequestMapping(value="/deleteRole",method=RequestMethod.GET)
	public String delete(Long id, String mc, int page, int size) {
		roleRepository.deleteById(id);
		return "redirect:/roles?page="+page+"&size="+size+"&mc="+mc;
	}
	
	@RequestMapping(value="/formRole",method = RequestMethod.GET)
	public String formRole(Model model) {
		model.addAttribute("role",new AppRole());
		return "formRole";
	}
	@RequestMapping(value="/editRole",method = RequestMethod.GET)
	public String editRole(Model model,Long id) {
		AppRole p = roleRepository.getById(id);
		AppRole role= roleRepository.findByRoleName(p.getRoleName());
		System.out.println("roleEdit"+role.getId());
		model.addAttribute("role",role);
		return "editRole";
	}
	@RequestMapping(value="/saveRole",method = RequestMethod.POST)
	public String save(Model model, @Valid AppRole role, BindingResult bindingResult) {
		if(bindingResult.hasErrors())
			return "formRole";
		roleRepository.save(role);
		model.addAttribute("role", role);
		System.out.println("roleSave"+role.getId());
		return "confirmationRole";
	}
/*	@RequestMapping(value="/",method = RequestMethod.GET)	
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
