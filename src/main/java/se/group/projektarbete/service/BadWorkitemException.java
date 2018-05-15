package se.group.projektarbete.service;

import javax.ws.rs.ext.Provider;

@Provider
public class BadWorkitemException extends RuntimeException {
    public BadWorkitemException(String message) {
        super(message);
    }
}
