package com.service.pb.project;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.service.pb.project.Models.Client;



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
	
	//@Test
	//public void testGetHomeValue() throws Exception {
	//	mockMvc.perform(get("http://localhost:8080/home")).andExpect(content().string("Everything is fine!"));
	//}
	
	@Test
	public void testPostValue() throws Exception {
		
		Client cl = new Client();
		cl.setCpf("123456");
		cl.setDataNascimento(Date.valueOf("1990-02-30"));
		cl.setName("Fulano de Tal");
		ObjectMapper mapper = new ObjectMapper();
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson=ow.writeValueAsString(cl);
	    System.out.println(requestJson);
	    mockMvc.perform(post("http://localhost:8080/clients").contentType("application/json")
	            .content(requestJson))
	            .andExpect(status().isOk());
	}
	
	@Test
	public void testGetClientCpf() throws Exception {
		mockMvc.perform(get("http://localhost:8080/clients?cpf=123456")).andExpect(status().isOk());
	}
	
	@Test
	public void testGetClientName() throws Exception {
		mockMvc.perform(get("http://localhost:8080/clients?name=Fulano de Tal")).andExpect(status().isOk());
	}
	
	@Test
	public void testDeleteClient() throws Exception {
		mockMvc.perform(delete("http://localhost:8080/clients?cpf=123456")).andExpect(status().isOk());
	}
	
	@Test
	public void testGetClientCpfAfterDelete() throws Exception {
		mockMvc.perform(get("http://localhost:8080/clients?cpf=123456")).andExpect(status().isOk());
	}
	
	@Test
	public void testDeleteClientAfterPreviouseDelete() throws Exception {
		mockMvc.perform(delete("http://localhost:8080/clients?cpf=123456")).andExpect(content().string("false"));
	}
	
}



