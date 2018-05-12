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


    @PUT
    @Path("{id}/users/{userId}")
    public Response addWorkItemByUserId(@PathParam("id")Long workItemId,
                                        @PathParam("userId")Long userId) {
            workItemService.addWorkItemToUser(workItemId, userId);
            return Response.status(Response.Status.CREATED).build();
        }
}
