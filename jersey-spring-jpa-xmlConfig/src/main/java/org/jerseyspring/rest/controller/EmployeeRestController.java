package org.jerseyspring.rest.controller;

import org.jerseyspring.service.EmployeeService;
import org.jerseyspring.util.PaginationUtil;
import org.jerseyspring.vm.EmployeeVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 *
 * @author Andrey <Andrey at andrew.my@yahoo.com>
 */

@Component
@Path("/api")
public class EmployeeRestController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    @Qualifier(value = "employeeService")
    private EmployeeService employeeService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/employee/{id}")
    public Response getEmployeeById(@PathParam("id") Long id) {
        EmployeeVM employeeVM = employeeService.findById(id);
        if (employeeVM != null) {
            return Response.ok(employeeVM, MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for id: " + id).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/employee")
    public Response getAllEmployee() {
        List<EmployeeVM> list = employeeService.getAll();
        return Response.ok(list, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/employee-p")
    public Response getAllEmployeeP(@QueryParam("page") int page, @QueryParam("size") int size) {
        Page<EmployeeVM> pageEmployee = employeeService.getAllPage(PageRequest.of(page, size));
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(pageEmployee, "/api/employee-p");
        return Response.ok(pageEmployee.getContent()).header("X-Total-Count", headers).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/employee-age")
    public Response getAllEmployeeAge(@QueryParam("from") int from, @QueryParam("to") int to) {
        List<EmployeeVM> list = employeeService.getAllAge(from, to);
        return Response.ok(list, MediaType.APPLICATION_JSON).build();
    }

}
