package com.service.pb.project.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.service.pb.project.Models.Client;
import com.service.pb.project.Service.ClientService;

@RestController
@RequestMapping("")
public class ClientController {

	@Autowired
	ClientService clientService;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ResponseEntity<?> home() {
		return new ResponseEntity<>(clientService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/clients", method = RequestMethod.GET)
	public ResponseEntity<?> clients(
			@RequestParam(defaultValue = "0") Integer pageNo, 
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "") String name,
            @RequestParam(defaultValue = "") String cpf) throws JsonProcessingException {		
		return new ResponseEntity<>(clientService.getAllClients(pageNo, pageSize, name, cpf), HttpStatus.OK);
	}

	@RequestMapping(value = "/clients", method = RequestMethod.POST)
	public ResponseEntity<?> clientsPost(@RequestBody Client client) {
		return new ResponseEntity<>(clientService.save(client), HttpStatus.OK);
	}	
	
	@RequestMapping(value = "/clients", method = RequestMethod.DELETE)
	public ResponseEntity<?> clientsDelete(@RequestParam String cpf) {
		return new ResponseEntity<>(clientService.deleteByCpf(cpf), HttpStatus.OK);
	}	
}
