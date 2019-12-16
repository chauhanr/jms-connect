package com.hcl.ms.jms.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.hcl.ms.jms.tranformers.Transformer;

@Component
public class ReceiveProcessor implements Processor{
	private static Logger logger = LoggerFactory.getLogger(ReceiveProcessor.class);
	
	@Autowired
	@Qualifier("receiveEnricher")
	private Transformer receiveTransformer;
	
	@Override
	public void process(Exchange exchange) throws Exception {
		String message = exchange.getMessage().getBody().toString();
		    
		String messageId = exchange.getMessage().getMessageId();
	    
	    logger.info(getClass().getSimpleName()+" message with id: {} and body: {}", messageId, message);	    
	    logger.info(getClass().getSimpleName()+ " ExchangePattern {} Headers: {}",  exchange.getPattern().toString() , exchange.getMessage().getHeaders().toString());
	   
	    // enriching message
	    String tMessage = receiveTransformer.transform(message);
	    // processing logic here. 
	    exchange.getMessage().setBody(tMessage);
	}
}
