package pl.tr;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("")
public class Foo {

    @GET
    @Path("foo")
    public String getFoo() {
        return "foo";
    }

}
