package se.group.projektarbete.web;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.sse.*;

@Singleton
@Path("broadcast")
public final class BroadcasterResource {
    private Sse sse;
    private SseBroadcaster broadcaster;

    public BroadcasterResource(@Context final Sse sse) {
        this.sse = sse;
        this.broadcaster = sse.newBroadcaster();
    }

    @POST
    @Path("post")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public String broadcastMessage(String message) {
        final OutboundSseEvent event = sse.newEventBuilder()
                .name("message")
                .mediaType(MediaType.TEXT_PLAIN_TYPE)
                .data(String.class, message)
                .build();

        broadcaster.broadcast(event);

        return "Message '" + message + "' has been broadcast.";
    }

    @GET
    @Path("get")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public void listenToBroadcast(@Context SseEventSink eventSink) {
        this.broadcaster.register(eventSink);
    }
}

