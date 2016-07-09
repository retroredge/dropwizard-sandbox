package uk.co.redsoft.ports.http;

import org.apache.http.HttpStatus;
import uk.co.redsoft.domain.GameMetaData;
import uk.co.redsoft.domain.GameService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Path("/games")
@Produces(MediaType.APPLICATION_JSON)
public class GameResource {

    private final GameService gameService;

    public GameResource(GameService gameService) {
        this.gameService = gameService;
    }

    @GET
    @Path("{id}")
    public GetGameResponse getGameMetaData(@PathParam("id") String id) {
        final GameMetaData gameMetaData = gameService.getGameMetaData(id);

        return GetGameResponse.from(gameMetaData);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createGame(CreateGameRequest createGameRequest) {
        UUID gameId = gameService.createGame(createGameRequest.toGame());
        return Response.status(HttpStatus.SC_CREATED).build();
    }
}
