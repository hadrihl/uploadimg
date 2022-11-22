package com.example.uploadimg;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {
	
	@Autowired private UserRepository repo;
	
	@Autowired private TestEntityManager em;
	
	@Test
	void create_new_user() {
		User user = new User();
		user.setUsername("david");
		user.setImageFile("pexels-creation-hill-1681010_person3.jpg");
		
		User savedUser = repo.save(user);
		User existedUser = em.find(User.class, savedUser.getId());
		
		assertThat(existedUser.getUsername()).isEqualTo(savedUser.getUsername());
	}
}

