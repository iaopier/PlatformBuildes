package com.service.pb.project.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class ClientController {

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ResponseEntity<?> home() {
		return new ResponseEntity<>("Everything is fine!", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/clients", method = RequestMethod.GET)
	public ResponseEntity<?> clients(@RequestParam("query") String query) {
		return new ResponseEntity<>(query,HttpStatus.OK);
	}
	
	
}
