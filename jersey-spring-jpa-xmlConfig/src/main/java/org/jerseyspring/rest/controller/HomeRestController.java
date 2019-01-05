package org.jerseyspring.rest.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author Andrey <Andrey at andrew.my@yahoo.com>
 */

@Path("/")
public class HomeRestController {

    @GET
    @Path("/")
    @Produces(value = MediaType.TEXT_PLAIN)
    public String greeting() {
        return "Hello, World!";
    }

}
