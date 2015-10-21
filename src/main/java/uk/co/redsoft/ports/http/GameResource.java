package uk.co.redsoft.ports.http;

import uk.co.redsoft.domain.GameMetaData;
import uk.co.redsoft.domain.GameService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/game")
@Produces(MediaType.APPLICATION_JSON)
public class GameResource {

    private final GameService gameService;

    public GameResource(GameService gameService) {
        this.gameService = gameService;
    }

    @GET
    @Path("{id}")
    public GameResponse getGame(@PathParam("id") String id) {

        final GameMetaData gameMetaData = gameService.getGame(id);
        final GameResponse gameResponse = GameResponse.from(gameMetaData);
        return gameResponse;
    }
}
