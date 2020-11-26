package com.sju.program;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProgramApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void test(){
		String s="admin";
		//s.replaceAll("/","\\");
		System.out.println(s.startsWith("admin"));
	}


}
