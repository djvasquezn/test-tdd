package com.springboot.devops.rest.app.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.devops.rest.app.domain.Request;
import com.springboot.devops.rest.app.domain.Response;

@RestController
@RequestMapping("/rest")
public class RestAPIController {

	@RequestMapping(
			path = "/send",
			method = {RequestMethod.POST, RequestMethod.PUT , RequestMethod.PATCH},
			headers = "Accept=application/json", 
			consumes = {"application/JSON"})
	public ResponseEntity<Object> postRequest(@RequestBody Request request, HttpServletRequest httpRequest) {
		if (RequestMethod.POST.name().equals(httpRequest.getMethod())) {
			String message = String.format("Hello %s your message will be send", request.getTo()) ;
			return new ResponseEntity<>(new Response(message),HttpStatus.OK);
		} 
		
		return new ResponseEntity<>("ERROR",HttpStatus.OK);
	}
	
	@RequestMapping(method = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.HEAD}, 
			path = "/send", 
			produces = {"application/JSON"})
	public ResponseEntity<String> getSimpleRequestMessage(@RequestParam("message") String message, @RequestParam("to")  String to, 
			@RequestParam("from") String from, @RequestParam("timeToLifeSec") Long timeToLifeSec ) {
		return new ResponseEntity<>("ERROR", HttpStatus.OK);
	}
}
