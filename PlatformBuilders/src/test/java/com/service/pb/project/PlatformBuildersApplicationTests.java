package com.service.pb.project;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;



@RunWith(SpringRunner.class)
@SpringBootTest
public class PlatformBuildersApplicationTests {
	
	@Autowired
	private WebApplicationContext webApplicationContext;
		
	private MockMvc mockMvc;
	
	@Test
	public void contextLoads() {
	}
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void testGetHome() throws Exception {
		mockMvc.perform(get("http://localhost:8080/home")).andExpect(status().isOk());
	}
	
	@Test
	public void testGetHomeValue() throws Exception {
		mockMvc.perform(get("http://localhost:8080/home")).andExpect(content().string("Everything is fine!"));
	}
	
}



