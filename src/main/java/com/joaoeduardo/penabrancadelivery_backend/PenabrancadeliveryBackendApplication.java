package com.joaoeduardo.penabrancadelivery_backend;

import com.joaoeduardo.penabrancadelivery_backend.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
//@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
public class PenabrancadeliveryBackendApplication implements CommandLineRunner {

	private final UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(PenabrancadeliveryBackendApplication.class, args);

	}






	@Override
	public void run(String... args) throws Exception {
//		System.out.println(userRepository.findAll().get(0).getId());
//		UserCreateDTO dto = new UserCreateDTO("joao@email.com", "João", UserRole.ADMIN);
//		this.userRepository.save(new User(dto));
//		UserCreateDTO dto1 = new UserCreateDTO("amanda@email.com", "Amanda", UserRole.CUSTOMER);
//		this.userRepository.save(new User(dto));
//		this.userRepository.save(new User(dto1));
	}


}
