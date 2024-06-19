package com.bank.homeloan.restapi.app.servicei;


import com.bank.homeloan.restapi.app.model.Enquiry;

public interface EnquiryServicei {

	Enquiry saveEnquiry(Enquiry e);

	Enquiry getById(Integer enquiryId);



}
