package se.group.projektarbete.web;


import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.stereotype.Component;
import se.group.projektarbete.data.WorkItem;
import se.group.projektarbete.data.workitemenum.Status;
import se.group.projektarbete.service.WorkItemService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import java.util.List;

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
    public Response addWorkItemByUserId(@PathParam("id") Long workItemId,
                                        @PathParam("userId") Long userId) {
        workItemService.addWorkItemByUserId(workItemId, userId);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("team/{id}")
    public List<WorkItem> getAllWorkItemsForTeam(@PathParam("id") Long teamId) {
        return workItemService.findAllWorkItemsByTeamId(teamId);
    }


    @GET
    @Path("user/{id}")
    public List<WorkItem> getAllWorkItemsForUser(@PathParam("id")Long userId) {
        return workItemService.findAllWorkItemsByUserId(userId);
    }
}

