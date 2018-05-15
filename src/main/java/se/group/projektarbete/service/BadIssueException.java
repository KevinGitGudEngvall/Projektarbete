package se.group.projektarbete.service;

import javax.ws.rs.ext.Provider;

@Provider
public class BadIssueException extends RuntimeException {
    public BadIssueException(String message) {
        super(message);
    }
}