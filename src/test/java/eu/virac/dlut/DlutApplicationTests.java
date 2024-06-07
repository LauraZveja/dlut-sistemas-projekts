package eu.virac.dlut;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class DlutApplicationTests {

	@Test
	void contextLoads() {
		assertNotNull(SpringApplication.run(DlutApplication.class));
	}

}
