package com.hcl.ms.jms.tranformers.impl;

import javax.xml.bind.JAXBException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hcl.ms.jms.model.Employee;
import com.hcl.ms.jms.tranformers.Transformer;
import com.hcl.ms.jms.tranformers.XMLMarshallerWrapper;


@Component(value = "receiveEnricher")
public class ReceiveEnricher implements Transformer{
    private static Logger logger = LoggerFactory.getLogger(ReceiveEnricher.class);
    
    @Autowired
    private XMLMarshallerWrapper marshaller; 
    
	@Override
	public String transform(String message) {
		
		try {
		   Employee emp = marshaller.unmarshallXml(message); 
		   logger.info("Successfully unmarshalled xml file Emp: {}", emp.toString());
		}catch(JAXBException exp) {
		   logger.info("Error marshalling xml message {} ", exp.getMessage());
		   return "";
		}
		
		/** Transformation logic goes in here. */
		
		return message;
	}
}
