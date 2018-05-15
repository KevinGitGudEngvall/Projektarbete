package se.group.projektarbete.service;

import javax.ws.rs.ext.Provider;

@Provider
public class BadUserException extends RuntimeException {
    public BadUserException(String message) {
        super(message);
    }
}