# Instructions to run the JMS Connect Project 

1. Download the Apache Active MQ project from the following [site](https://activemq.apache.org/download.html) choose the version 5.15.10 [as this is the version we have tested for] 
2. Extract the activemq binary to a folder any where on the laptop lest call it ACTIVE_MQ_HOME 
3. To start activemq please go to the following location ACTIVE_MQ_HOME\bin and type command `activemq start` 
4. Once the activemq has started go to the activemq concole http://localhost:8161/admin/queues.jsp | the `username/password` is `admin/admin`
5. create two queues `myinput-queue` and `myoutput-queue` so that the usecase can work as this is the name of the queues mentioned in the property file.  
5. Once the activemq has started we need to start the jms-connect app. 
6. Go to the home folder of this application and type `mvn spring-boot:run`. Ensure that you have maven installed as well as have internet access. 
7. Once the project starts we need send a message to the application to see it work. 

Send a POST request to the URL `http://localhost:8080/message` with the following headers: 
* Content-Type: "text/xml"
* body as shown below which is an XML 

```
<employee>
	<name>Ritesh</name>
	<age>30</age>
	<address>address1</address>
</employee>
```  
When you send the message to the application you will have to follow the message in the logs as it flows in the following sequeunce: 
1. published on myinput-queue 
2. consumed by the ReceiveProcessor when then passes it to the ReceiveEnricher(transformer) this will unmarshall the message too. 
3. Once the message is processed by the transformer the ReceiveEnricher will return it back to the apache camel which will then publish to the myoutput-queue 
4. finally the message is consumed by the End Processor to complete the flow. 

To make sure you should check with the http://localhost:8161/admin/queues.jsp URL to see that the messages have indeed gone through the two queues we created earlier. 

