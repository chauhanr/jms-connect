package com.hcl.ms.jms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.ms.jms.clients.RequestReplyHandler;

@RestController
public class MessageHandlerController {

	@Autowired
	private RequestReplyHandler msgHandler; 
	
	@PostMapping(value="/message") 
	public ResponseEntity<String> sendReceiveMessage(@RequestBody String message){	
		
		String reply = msgHandler.sendReceiveMessages(message);
		return new ResponseEntity<String>(reply, HttpStatus.OK);
	}
	
}
