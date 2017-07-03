package com.count;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CharacterCounterApplication.class)
@WebAppConfiguration
public class CharacterCounterApplicationTests {

	@Test
	public void contextLoads() {
		char a='a';
		System.out.println(a-a+1);
	}

}


