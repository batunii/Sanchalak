package com.redmark.Sanchalak.Controllers;

import com.redmark.Sanchalak.Entity.Query;
import com.redmark.Sanchalak.Entity.Request;
import com.redmark.Sanchalak.Services.Service;
import com.redmark.Sanchalak.Services.UniRestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller
 */
@RestController
@RequestMapping("/sanch")
public class Controller {
	@Autowired
	Service service;
	@Autowired
	UniRestService uniService;

	@GetMapping("/health")
	public ResponseEntity<String> checkHealth() {
		return new ResponseEntity<>("Health.OK", HttpStatus.OK);
	}

	@PostMapping("/ask")
	@CrossOrigin
	public ResponseEntity<String> getQuery(@RequestBody Query query) {
		try {
			return new ResponseEntity<>(service.getResponse(query.getMessage()), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/promptFix")
	@CrossOrigin
	public ResponseEntity<String> changePromptPrefixAndPostfix(@RequestBody Request request ) {
		service.updatePostFixAndPrefix(request.getPrompt_prefix(), request.getPrompt_suffix());
		return new ResponseEntity<>("Prompt Parameters Updated", HttpStatus.OK);
	}

}
