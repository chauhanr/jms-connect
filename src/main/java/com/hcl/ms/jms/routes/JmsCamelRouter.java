package com.hcl.ms.jms.routes;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.hcl.ms.jms.processors.EndProcessor;
import com.hcl.ms.jms.processors.ReceiveProcessor;

@Slf4j
@Component
public class JmsCamelRouter extends RouteBuilder {
	static final Logger logger = LoggerFactory.getLogger(JmsCamelRouter.class);
	
	@Value("${input.queue}")
	private String inputQueue;
	
	@Value("${output.queue}")
	private String outputQueue;
	
	@Autowired
	private ReceiveProcessor inputProcessor;
	
	@Autowired
	private EndProcessor endProcessor; 
	
    @Override
    public void configure() throws Exception {
    	logger.info("Configuring route");
       
        from(inputQueue)
                .process(inputProcessor)
                .to(outputQueue)
                //.log(LoggingLevel.INFO, log, "Message enriched and sent to the OutputQueue: "+outputQueue)
                .end();
        
        from(outputQueue)
               .process(endProcessor)
               .end();
    }
}
