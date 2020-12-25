package com.sju.program;

import com.sju.program.utils.Result;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootTest
@RestController
class ProgramApplicationTests {

	@Test
	void contextLoads() {
	}
	@Test
	@GetMapping("/123")
	public Result<String> test(){
		return Result.success("123");
	}


}
