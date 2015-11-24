/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package examples;

import org.apache.camel.CamelContext;
import org.apache.camel.Consumer;
import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * A Camel Router
 */
public class CamelExample {

	private CamelContext camel;
	private ProducerTemplate template;


	public CamelExample() throws Exception {
		init(true);
	}

	public CamelExample(boolean enableConsumer) throws Exception {
		init(enableConsumer);
	}


	private void init(boolean enableConsumer) throws Exception {
		// create the camel context that is the "heart" of Camel
		camel = new DefaultCamelContext();

		// get the ProducerTemplate thst is a Spring'ish xxxTemplate based producer for very
		// easy sending exchanges to Camel.
		template = camel.createProducerTemplate();

		// add router
		addMessagesRoute();

		// add the event driven consumer that will listen for new files and process them
		if(enableConsumer)
			addMessagesConsumer();

		// start Camel
		camel.start();

		// Wait five minutes, then stop
		Thread.sleep (60*5*1000);
		camel.stop();
	}


	private void addMessagesRoute() throws Exception {
		camel.addRoutes(new RouteBuilder()
		{
			public void configure()
			{
				// here is a sample which processes the input files
		        // (leaving them in place - see the 'noop' flag)
		        // then performs content based routing on the message
		        // using XPath
				from("file:src/data?noop=true").
	            choice().
	                when(xpath("/person/city = 'London'")).to("file:target/messages/uk").
	                otherwise().to("file:target/messages/others");
			}
		});
	}


	private void addMessagesConsumer() throws Exception {
		// Grab the endpoint where we should consume. Option - the first poll starts after 2 seconds
		// ?consumer.delay=10000&consumer.initialDelay=2000
	    Endpoint endpoint = camel.getEndpoint("file:target/messages/uk?consumer.delay=5000");

	    // create the event driven consumer
	    // the Processor is the code what should happen when there is an event
	    Consumer consumer = endpoint.createConsumer(new Processor() {
	        public void process(Exchange exchange) throws Exception {
	            // get the file body as a String
	            String textBody = exchange.getIn().getBody(String.class);

	            // okay now we are read to send it as an email
	            System.out.println("Reading file..." + textBody);
	        }
	    });

	    // star the consumer, it will listen for files
	    consumer.start();
	}


	public static void main(String args[]) throws Exception
	{
		CamelExample example = new CamelExample();
	}
}
