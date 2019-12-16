package com.hcl.ms.jms.tranformers;

import org.springframework.stereotype.Service;

@Service
public interface Transformer {
	
	public String transform(String message); 

}
