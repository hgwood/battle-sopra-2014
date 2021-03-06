package fr.notfound.fakearena;

import static fr.notfound.TestUtils.localhost;

import java.net.URI;

import fr.notfound.composition.CompositionRoot;
import fr.notfound.http.TextArena;
import fr.notfound.http.server.Jetty;
import fr.notfound.http.uri.ArenaUriCatalog;

/**
 * Wraps a {@link TextArena} in a HTTP server.
 */
public class ArenaServer {

    private static final int port = 8084;
    private static final ArenaUriCatalog uris = new CompositionRoot().uris("");
    
    public static ArenaServer start(final TextArena arena, 
        final String teamName, final String password, final String teamId, final String gameId) {
        return new ArenaServer(teamName, password, Jetty.onPort(port)
            .handle(uris.ping(), new Jetty.StringHandler() {
                @Override public String handle() {
                    return arena.ping();
                }
            })
            .handle(uris.teamId(teamName, password), new Jetty.StringHandler() {
                @Override public String handle() {
                    return arena.teamId(teamName, password);
                }
            })
            .handle(uris.currentVersus(teamId), new Jetty.StringHandler() {
                @Override public String handle() {
                    return arena.currentVersus(teamId);
                }
            })
            .handle(uris.newPractice("0", teamId), new Jetty.StringHandler() {
                @Override public String handle() {
                    return arena.newPractice("0", teamId);
                }
            })
            .handle(uris.currentPractice(teamId), new Jetty.StringHandler() {
                @Override public String handle() {
                    return arena.currentPractice(teamId);
                }
            })
            .handle(uris.opponent(gameId, teamId), new Jetty.StringHandler() {
                @Override public String handle() {
                    return arena.opponent(gameId, teamId);
                }
            })
            .handle(uris.status(gameId, teamId), new Jetty.StringHandler() {
                @Override public String handle() {
                    return arena.status(gameId, teamId);
                }
            })
            .handle(uris.board(gameId), new Jetty.StringHandler() {
                @Override public String handle() {
                    return arena.board(gameId);
                }
            })
            .handle(uris.lastMove(gameId), new Jetty.StringHandler() {
                @Override public String handle() {
                    return arena.latestMove(gameId);
                }
            })
            .handle(uris.play(gameId, teamId, "0", "0"), new Jetty.StringHandler() {
                @Override public String handle() {
                    return arena.play(gameId, teamId, "0", "0");
                }
            })
            .start());
    }
    
    public final URI uri = localhost(port);
    public final String teamName;
    public final String password;
    
    private final Jetty server;
    
    public ArenaServer(String teamName, String password, Jetty server) {
        this.teamName = teamName;
        this.password = password;
        this.server = server;
    }
    
    public void stop() {
        server.stop();
    }

}
