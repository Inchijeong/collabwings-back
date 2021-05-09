package com.clb.apis.v1;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Card API 테스트")
public class CardApiTests {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext ctx;
	
	@BeforeEach
	void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
				.addFilter(new CharacterEncodingFilter("UTF-8", true))
				.build();
	}
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	@DisplayName("1. 카드 목록 조회")
	public void _01_getCardListTest() throws Exception {
		// 조회		
		ResultActions result = mockMvc.perform(
				get("/api/v1/cards")
		);
		result.andDo(print())
			.andExpect(status().isOk());
	}
	
}
