

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class StartCamel {
    public static void main(String[] args) throws Exception {
        CamelContext ctx = new DefaultCamelContext();
        ctx.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("direct:start")
                        .to("log:end?level=INFO");
            }
        });
        RouteBuilder r2=fileRoute();
       
        
        ctx.addRoutes(r2);
        ctx.start();
        ctx.createProducerTemplate().sendBody("direct:start", "************ Hello, world! ");
        ctx.stop();
    }
    
    //read file
    public static RouteBuilder fileRoute() {
    	
    	RouteBuilder builder = new RouteBuilder() {
    	    public void configure() {
    	        errorHandler(deadLetterChannel("mock:error"));

    	        fromF("file://%s?fileName=%s", "/Users/suhelfirdus/eclipse-workspace/", "a.txt").toF("file://%s?fileName=%s", "/Users/suhelfirdus/eclipse-workspace/", "b.txt");
    	    }
    	};
    	
    	
    	return builder;
    	
    	
    }
    

    
}


