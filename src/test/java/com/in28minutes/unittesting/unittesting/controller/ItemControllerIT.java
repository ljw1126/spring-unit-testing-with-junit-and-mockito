package com.in28minutes.unittesting.unittesting.controller;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @SpringBootTest 통합테스트
 * https://goddaehee.tistory.com/211
 *
 * 에러. org.junit.runners.model.InvalidTestClassError: Invalid test class
 * 해결. @Test import 잘못됨.. jupiter 말고 org.junit.Test 사용해야 함
 * 참고. https://stackoverflow.com/questions/24319697/java-lang-exception-no-runnable-methods-exception-in-running-junits
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // 임의 포트
@RunWith(SpringRunner.class)
public class ItemControllerIT {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	public void contextLoads() throws JSONException {
		String response = this.testRestTemplate.getForObject("/all-items-from-database", String.class);
		JSONAssert.assertEquals("[{id:10001}, {id:10002}, {id:10003}, {id:10004}]", response, false);
	}

}
