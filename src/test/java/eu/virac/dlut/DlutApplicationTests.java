package eu.virac.dlut;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class DlutApplicationTests {

	@InjectMocks
	private DlutApplication dlutApplication;

	@Test
	void contextLoads() {
		assertNotNull(dlutApplication);
	}

}
