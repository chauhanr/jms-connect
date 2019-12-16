package com.hcl.ms.jms.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration 
public class XmlMarshallerConfig {

	@Bean
	public Jaxb2Marshaller getJaxb2Marshaller() {
		 Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
		 jaxb2Marshaller.setPackagesToScan("com.hcl.ms");
		 Map<String,Object> map = new HashMap<String,Object>();
		  map.put("jaxb.formatted.output", true);
		  jaxb2Marshaller.setMarshallerProperties(map);
	          return jaxb2Marshaller;
	}
}
