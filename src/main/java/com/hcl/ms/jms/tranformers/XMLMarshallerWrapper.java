package com.hcl.ms.jms.tranformers;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

import javax.xml.bind.JAXBException;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;

@Component
public class XMLMarshallerWrapper {
	
	@Autowired
	private Jaxb2Marshaller marshaller; 
	
   public <T> String marshallXml(final T obj) throws JAXBException {
      StringWriter sw = new StringWriter();
      Result result = new StreamResult(sw);
      marshaller.marshal(obj, result);
      return sw.toString();
   }

   @SuppressWarnings("unchecked")
   public <T> T unmarshallXml(String xml) throws JAXBException {
	   InputStream in = new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8));
      return (T) marshaller.unmarshal(new StreamSource(in));
   }

}
