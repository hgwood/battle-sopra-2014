package fr.notfound.cli;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static fr.notfound.cli.Matchers.*;

import org.junit.Ignore;
import org.junit.Test;

/**
 * Call every {@link TextArena} method on the official arena through the
 * {@link ReflectCliToOfficialTestArena} and check the content of the response is of
 * the expected form. <strong>Requires the official test arena to be 
 * reachable!</strong>
 * <p>
 * Note that these tests are not designed to work against the real API 
 * (parameters are completely random).
 */
@Ignore
public class OfficialTestArenaHealthCheck {

    private final CliRunner application = new CliRunner(ReflectCliToOfficialTestArena.class);

    @Test public void pingPong() throws Exception {
        assertThat(application.run("ping"), hasItem("pong"));
    }

    @Test public void teamId() throws Exception {
        assertThat(application.run("teamId", "0", "0"), hasItem(that(is(anId()))));
    }

    @Test public void currentVersus() throws Exception {
        assertThat(application.run("currentVersus", "0"), hasItem(that(is(anId()))));
    }

    @Test public void newPractice() throws Exception {
        assertThat(application.run("newPractice", "0", "0"), hasItem(that(is(anId()))));
    }

    @Test public void currentPractice() throws Exception {
        assertThat(application.run("currentPractice", "0"), hasItem(that(is(anId()))));
    }
    
    @Ignore // not implemented on the test API
    @Test public void opponent() throws Exception {
        assertThat(application.run("opponent", "0", "0"), hasItem(that(is(aStatus()))));
    }

    @Test public void status() throws Exception {
        assertThat(application.run("status", "0", "0"), hasItem(that(is(aStatus()))));
    }

    @Test public void board() throws Exception {
        assertThat(application.run("board", "0"), hasItem(that(is(aBoard()))));
    }

    @Test public void latestMove() throws Exception {
        assertThat(application.run("latestMove", "0"), hasItem(that(is(aMove()))));
    }

    @Test public void play() throws Exception {
        assertThat(application.run("play", "0", "0", "0", "0"), hasItem(that(is(aPlayResponse()))));
    }

}
