package com.hcl.ms.jms.clients;

/*import org.apache.camel.ConsumerTemplate;*/
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RequestReplyHandler {
    private static Logger logger = LoggerFactory.getLogger(RequestReplyHandler.class);
	
	@Autowired
	private ProducerTemplate producerTemplate;
	
	/*@Autowired
	private ConsumerTemplate consumerTemplate;*/
	
	@Value("${input.queue}")
	private String requestQueue;
	
	@Value ("${output.queue}")
	private String responseQueue;
	
	
	public String sendReceiveMessages(String xmlMessage) {
		logger.info("Message from url: {}",xmlMessage);
		
		Exchange out = producerTemplate.send(requestQueue, ExchangePattern.InOut, 
				new Processor() {
					@Override
					public void process(Exchange exchange) throws Exception {
						Message msg = exchange.getMessage();
						msg.setBody(xmlMessage);
					}
				});
		
		 logger.info("JMS Headers: {} ", out.getMessage().getHeaders().toString());			 
		 String jmsCorrID = (String) out.getMessage().getHeader("JMSCorrelationID");
		 String res = "Message Correlation ID is "+jmsCorrID;
		 
		 return res; 
		
	}
	
}
