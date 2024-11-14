package com.api.v1;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.modulith.core.ApplicationModule;
import org.springframework.modulith.core.ApplicationModules;

@SpringBootTest
class V1ApplicationTests {

	@Test
	void contextLoads() {
		ApplicationModules.of(V1ApplicationTests.class).verify();
	}

}
