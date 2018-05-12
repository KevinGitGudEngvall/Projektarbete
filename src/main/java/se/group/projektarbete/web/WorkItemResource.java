package se.group.projektarbete.web;


import org.springframework.stereotype.Component;
import se.group.projektarbete.service.WorkItemService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Component
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
@Path("workitems")
public class WorkItemResource {

    private final WorkItemService workItemService;


    public WorkItemResource(WorkItemService workItemService) {
        this.workItemService = workItemService;
    }


    @GET
    public Response getWorkItemByUserId(@QueryParam("workItemId") Long workItemId,
                                        @QueryParam("userId") Long userId) {
            workItemService.addWorkItemByUserId(workItemId, userId);
            return Response.status(Response.Status.CREATED).build();
    }



}
