package se.group.projektarbete.web;

import org.springframework.stereotype.Component;
import se.group.projektarbete.data.Issue;
import se.group.projektarbete.service.IssueService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.CREATED;

@Component
@Path("issues")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)

public final class IssueResource {

    private final IssueService issueService;

    public IssueResource(IssueService issueService) {
        this.issueService = issueService;
    }

   /* @POST
    public Response createIssue (Issue issue){
        issueService.createIssue(issue);
        return Response.status(CREATED).build();

        }*/



    }

