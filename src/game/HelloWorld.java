package game;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class HelloWorld {

		@GET
		@Produces(MediaType.TEXT_PLAIN)
		public String hello(){
			
			return "HI";
		}
}
