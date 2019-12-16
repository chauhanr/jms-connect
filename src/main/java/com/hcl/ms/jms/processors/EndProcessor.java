package com.hcl.ms.jms.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class EndProcessor implements Processor{
	private static Logger logger = LoggerFactory.getLogger(EndProcessor.class);
	
	
	@Override
	public void process(Exchange exchange) throws Exception {
		String finalMsg = exchange.getMessage().getBody().toString();
		String id = exchange.getMessage().getMessageId();
		
	    logger.info(getClass().getSimpleName()+" message with id: {} and body: {}", id, finalMsg);
	    logger.info(getClass().getSimpleName()+ "Headers: "+exchange.getMessage().getHeaders().toString());
	}

}
