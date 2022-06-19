package com.in28minutes.unittesting.unittesting;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 참고
 * https://tecoble.techcourse.co.kr/post/2020-09-21-application-properties/
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations= {"classpath:application.properties"}) // test/resource/에 있는 configuration.properties 파일
public class UnitTestingApplicationTests {

	@Test
	public void contextLoads() {
	}

}
