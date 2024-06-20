package com.bank.homeloan.restapi.app.serviceimpl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.bank.homeloan.restapi.app.exception.EnquiryNotFoundException;
import com.bank.homeloan.restapi.app.model.Enquiry;
import com.bank.homeloan.restapi.app.repository.EnquiryRepository;
import com.bank.homeloan.restapi.app.servicei.EnquiryServicei;
@Service
public class EnquiryServiceImpl implements EnquiryServicei{
	
	@Autowired EnquiryRepository er;
	
	@Autowired private JavaMailSender sender;
	
	@Autowired@Value("$spring.mail.username") String mymail;
	
	
	@Override
	public Enquiry saveEnquiry(Enquiry e) {
		
		
		SimpleMailMessage msg= new SimpleMailMessage();
		String sub = "Thank You for Your Enquiry  - Apna Finance Corp Ltd”";
				String TextMsg= "Dear "+ e.getName()+"\n\n" +
                "Thank you for reaching out to Apna Finance Corp Ltd. We have successfully received your enquiry form.\n\n" +
                "Your form has been passed to our Customer Relationship Manager for further processing. We will notify you once"
                + " your CIBIL score/credit score verification is completed to determine your loan eligibility.\n\n" +
                "If you have any further questions or need assistance, please feel free to contact our support team.\n\n" +
                "Best Regards,\n" +
                "Apna Finance Corp Ltd\n" +
                "Customer Support Team\n" +
                "Email: support@apnafinance.com\n" +
                "Phone: +91 8088080880";
		msg.setFrom(mymail);
		msg.setSubject(sub);
		msg.setText(TextMsg);
		msg.setTo(e.getEmail());
		
		sender.send(msg);
		
		
		e.setStatus("pending");
		e.setCibilScore(null);
		e.setDob(new Date());
		
		
		return er.save(e);
	}


	@Override
	public Enquiry getById(Integer enquiryId) {
		Optional<Enquiry> enq = er.findById(enquiryId);
		if(enq!=null) {
			
			return enq.get();
		}
		else {
			throw new EnquiryNotFoundException("No Enquiry found on this id..!!!");
		}
		
	}


}
