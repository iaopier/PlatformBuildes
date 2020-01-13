package com.service.pb.project;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
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
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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
	public void testAGetHome() throws Exception {
		mockMvc.perform(get("http://localhost:8080/home")).andExpect(status().isOk());
	}
	
	@Test
	public void testBPostValue() throws Exception {
		Client cl = new Client();
		cl.setCpf("123456");
		cl.setDataNascimento(Date.valueOf("1990-02-30"));
		cl.setName("Fulano de Tal");
		ObjectMapper mapper = new ObjectMapper();
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson=ow.writeValueAsString(cl);
	    mockMvc.perform(post("http://localhost:8080/clients").contentType("application/json")
	            .content(requestJson))
	            .andExpect(status().isOk());
	}
	
	@Test
	public void testCGetClientCpf() throws Exception {
		mockMvc.perform(get("http://localhost:8080/clients?cpf=123456")).andExpect(status().isOk());
	}
	
	@Test
	public void testDGetClientName() throws Exception {
		mockMvc.perform(get("http://localhost:8080/clients?name=Fulano de Tal")).andExpect(status().isOk());
	}
	
	@Test
	public void testEDeleteClient() throws Exception {
		mockMvc.perform(delete("http://localhost:8080/clients?cpf=123456")).andExpect(status().isOk());
	}
	
	@Test
	public void testFGetClientCpfAfterDelete() throws Exception {
		mockMvc.perform(get("http://localhost:8080/clients?cpf=123456")).andExpect(status().isOk());
	}
	
	@Test
	public void testGDeleteClientAfterPreviouseDelete() throws Exception {
		mockMvc.perform(delete("http://localhost:8080/clients?cpf=123456")).andExpect(content().string("false"));
	}
	
	@Test
	public void testHPostValueToTestPut() throws Exception {
		Client cl = new Client();
		cl.setCpf("123456");
		cl.setDataNascimento(Date.valueOf("1990-02-30"));
		cl.setName("Fulano de Tal");
		ObjectMapper mapper = new ObjectMapper();
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson=ow.writeValueAsString(cl);
	    mockMvc.perform(post("http://localhost:8080/clients").contentType("application/json")
	            .content(requestJson))
	            .andExpect(status().isOk());
	}
	
	@Test
	public void testIPutValue() throws Exception {
		Client cl = new Client();
		cl.setCpf("1234567");
		cl.setDataNascimento(Date.valueOf("1990-10-30"));
		cl.setName("Fulano de Tal do Put");
		ObjectMapper mapper = new ObjectMapper();
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson=ow.writeValueAsString(cl);
	    mockMvc.perform(put("http://localhost:8080/clients?cpf=123456").contentType("application/json")
	            .content(requestJson))
	            .andExpect(status().isOk());
	}
	
	@Test
	public void testJGetClientCpfAfterPut() throws Exception {
		mockMvc.perform(get("http://localhost:8080/clients?cpf=1234567")).andExpect(status().isOk());
	}
	
	@Test
	public void testKGetClientNamerAfterPut() throws Exception {
		mockMvc.perform(get("http://localhost:8080/clients?name=Fulano de Tal do Put")).andExpect(status().isOk());
	}
	
	
	@Test
	public void testLPatchValue() throws Exception {
		Client cl = new Client();
		cl.setCpf("12345");
		cl.setDataNascimento(Date.valueOf("1990-10-30"));
		cl.setName("Fulano de Tal do Put");
		ObjectMapper mapper = new ObjectMapper();
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson=ow.writeValueAsString(cl);
	    mockMvc.perform(put("http://localhost:8080/clients?cpf=1234567").contentType("application/json")
	            .content(requestJson))
	            .andExpect(status().isOk());
	}
	
	@Test
	public void testMGetClientCpfAfterPatch() throws Exception {
		mockMvc.perform(get("http://localhost:8080/clients?cpf=12345")).andExpect(content().string("[{\"name\":\"Fulano de Tal do Put\",\"cpf\":\"12345\",\"dataNascimento\":\"1990-10-29\",\"idade\":29.0}]"));
	}
}




