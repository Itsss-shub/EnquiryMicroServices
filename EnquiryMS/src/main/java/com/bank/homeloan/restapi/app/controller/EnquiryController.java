package com.bank.homeloan.restapi.app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.homeloan.restapi.app.model.Enquiry;
import com.bank.homeloan.restapi.app.servicei.EnquiryServicei;

import lombok.extern.slf4j.Slf4j;
@CrossOrigin("*")
@Slf4j
@RestController
@RequestMapping("/bank/homeloan/restapi/enquiry")
public class EnquiryController {
	
	@Autowired  EnquiryServicei es;

	
	@PostMapping("/saveEnquiry")
	public ResponseEntity<Enquiry> saveEnquiry(@RequestBody Enquiry e){
		Enquiry enq= es.saveEnquiry(e);
	
		log.info("enquiry data saved.. and response mail send..!!");
		return new ResponseEntity<Enquiry> (enq, HttpStatus.OK);
		
	}
	
	
	@GetMapping("/getBYId/{enquiryId}")
	public ResponseEntity<Enquiry> getEnquiry(@PathVariable Integer enquiryId){
		Enquiry enq = es.getById(enquiryId);
		return new ResponseEntity<Enquiry>(enq,HttpStatus.OK);
	}
	

}
