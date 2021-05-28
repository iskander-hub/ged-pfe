package org.ged;

import org.ged.entities.AppRole;
import org.ged.entities.AppUser;
import org.ged.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



@SpringBootApplication
public class GedPfeApplication implements CommandLineRunner {
	@Autowired
	private AccountService accountService;

	public static void main(String[] args) {
		SpringApplication.run(GedPfeApplication.class, args);
	}
	@Bean
    public BCryptPasswordEncoder getBCPE() {
    	return new BCryptPasswordEncoder();
    }

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		accountService.saveUser(new AppUser(null, "admin", "1234",null));
		accountService.saveUser(new AppUser(null, "user", "1234",null));
		accountService.saveRole(new AppRole(null,"ADMIN"));
		accountService.saveRole(new AppRole(null,"USER"));
		accountService.addRoleToUser("admin", "ADMIN");
		accountService.addRoleToUser("admin", "USER");
		accountService.addRoleToUser("user", "USER");
		
	}

}
