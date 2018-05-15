package se.group.projektarbete.web.mapper;

import se.group.projektarbete.service.exceptions.InvalidInputException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Collections;


@Provider
public class InvalidInputMapper implements ExceptionMapper<InvalidInputException> {

    @Override
    public Response toResponse(InvalidInputException exception) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(Collections.singletonMap("error", exception.getMessage()))
                .build();
    }
}