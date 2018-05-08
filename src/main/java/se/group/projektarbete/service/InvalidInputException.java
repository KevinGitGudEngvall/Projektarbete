package se.group.projektarbete.service;

import javax.ws.rs.ext.Provider;

@Provider
public class InvalidInputException extends RuntimeException {
    public InvalidInputException(String message) {
        super(message);
    }
}


