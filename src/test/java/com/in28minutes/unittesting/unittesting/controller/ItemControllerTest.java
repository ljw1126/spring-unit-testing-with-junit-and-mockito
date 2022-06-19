package com.in28minutes.unittesting.unittesting.controller;

import com.in28minutes.unittesting.unittesting.business.ItemBusinessService;
import com.in28minutes.unittesting.unittesting.model.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.junit.matchers.JUnitMatchers.containsString;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = ItemController.class)
public class ItemControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ItemBusinessService businessService;

	@Test
	public void dummyItem_basic() throws Exception {
		
		RequestBuilder request = MockMvcRequestBuilders
				.get("/dummy-item")
				.accept(MediaType.APPLICATION_JSON);

		// response 결과는 정확히 일치해야 한다. / content().string("") 말고 content().json() 사용
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().json("{\"id\": 1,\"name\":\"Ball\",\"price\":10,\"quantity\":100}"))
				.andReturn();

		//JSON 검사 위한 유용한 프레임워크(spring boot test에 포함)
		//JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

	}

	/**
	 * 에러1. ItenBusinessService를 주입해줘야 테스트 가능
	 *
	 * @MockBean
	 * private ItemBusinessService businessService;
	 *
	 * 에러2. org.json.JSONException: Unparsable JSON string
	 * Mockito.when() 으로 service 호출시 임의 결과값 리턴하도록 설정
	 * @throws Exception
	 */
	@DisplayName("service test (DB까지는 가지 않음)")
	@Test
	public void itemFromBusinessService() throws Exception {

		when(businessService.retreiveHardcodedItem())
				.thenReturn(new Item(2, "Item2", 10, 10));

		RequestBuilder request = MockMvcRequestBuilders
				.get("/item-from-business-service")
				.accept(MediaType.APPLICATION_JSON);

		// response 결과는 정확히 일치해야 한다. / content().string("") 말고 content().json() 사용
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().json("{id:2,name:Item2,price:10,quantity:10}"))
				.andReturn();

	}

	@DisplayName("AllItems 호출 기본 테스트 (DB 데이터 x)")
	@Test
	public void retrieveAllItems_basic() throws Exception {
		/**
		 * new Item(10001, "Item1", 10, 20),
		 * new Item(10002, "Item2", 5, 10),
		 * -new Item(10003, "Item3", 15, 2)
		 */
		when(businessService.retrieveAllItems()).thenReturn(
				Arrays.asList(new Item(10001, "Item1", 10, 20), new Item(10002, "Item2", 5, 10))
		);

		RequestBuilder request = MockMvcRequestBuilders
				.get("/all-items-from-database")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().json("[{id:10001,name:Item1,price:10,quantity:20},{id:10002,name:Item2,price:5,quantity:10}]"))
				.andReturn();
	}

	/**
	 * Response Status Sample
	 * 200 - success
	 * 404 - resource not found
	 * 400 - bad request
	 * 201 - created
	 * 401 - unauthorized
	 * 500 - server error
	 */
	@DisplayName("post 방식 테스트 샘플")
	@Test
	public void post_request_test_sample() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/items")
				.accept(MediaType.APPLICATION_JSON)
				.content("{id:1, name:Ball, price:10, quantity:100}")
				.contentType(MediaType.APPLICATION_JSON);

		// @Deprecated 된 메소드가 있네
		MvcResult mvcResult = mockMvc.perform(requestBuilder)
				.andExpect(status().isCreated())
				.andExpect(header().string("location", contains("/item/")))
				.andReturn();
	}

}
