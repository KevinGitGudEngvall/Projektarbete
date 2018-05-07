package se.group.projektarbete.web;

import org.springframework.stereotype.Component;
import se.group.projektarbete.data.Team;
import se.group.projektarbete.service.TeamService;

import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import static javax.ws.rs.core.Response.Status.CREATED;
import static javax.ws.rs.core.Response.Status.OK;


@Component
@Path("teams")
public final class TeamResource {

    private final TeamService teamService;

    @Context
    private UriInfo uriInfo;

    public TeamResource(TeamService teamService) {
        this.teamService = teamService;
    }

    @POST
    public Response createTeam(Team team){
        teamService.createTeam(team);
        return Response.status(CREATED).header("Location",
                uriInfo.getAbsolutePathBuilder().path(team.getName())).build();
    }

    @PUT
    public Response updateTeam(Team team){
        teamService.updateTeam(team);
        return Response.status(OK).build();
    }
}
