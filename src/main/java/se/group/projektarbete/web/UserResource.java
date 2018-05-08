package se.group.projektarbete.web;

import org.springframework.stereotype.Component;
import se.group.projektarbete.data.User;
import se.group.projektarbete.service.UserService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import java.util.Optional;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.CREATED;

@Component
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
@Path("users")
public final class UserResource {

    private final UserService userService;

    @Context
    private UriInfo uriInfo;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @POST
    public Response createUser(User user) {
       User createdUser = userService.createUser(user);

        return Response.status(CREATED).header("Location",
                uriInfo.getAbsolutePathBuilder().path(createdUser.getUserNumber().toString())).build();
    }


    @GET
    @Path("{userNumber}")
    public Response getUserByUserNumber(@PathParam("userNumber") Long id) {
       Optional<User> result = userService.getUserByUsernumber(id);

        return Response.ok(result).build();
    }


}
