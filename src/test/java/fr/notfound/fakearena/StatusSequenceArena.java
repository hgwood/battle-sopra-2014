package fr.notfound.fakearena;

import java.util.Iterator;

import fr.notfound.domain.GameStatus;
import fr.notfound.domain.MoveResult;
import fr.notfound.http.TextArena;

/**
 * An arena used in tests to check how the application reacts to different
 * sequences of game statuses. It:
 * <ul>
 * <li>supports only one team</li>
 * <li>supports only one versus game and no practice games</li>
 * <li>supports querying for status, following those rules:
 *   <ul><li>the status changes when {@link #status(String, String)} is called, 
 *   unless the current status is {@link GameStatus#YourTurn}, in which 
 *   case the status remains {@link GameStatus#YourTurn}</li>
 *   <li>the status changes when {@link #play(String, String, String, String)}
 *   is called if and only if the status is {@link GameStatus#YourTurn}</li>
 *   <li>"the status changes" means the current status takes on whatever the 
 *   next value is according to the iterator specified in the constructor</li>
 *   </ul>
 * </ul>
 */
public class StatusSequenceArena implements TextArena {

    private final String teamId;
    private final String versusId;
    private final Iterator<GameStatus> statusSequence;
    private GameStatus currentStatus;

    public StatusSequenceArena(String teamId, String versusId, Iterator<GameStatus> statusSequence) {
        this.teamId = teamId;
        this.versusId = versusId;
        this.statusSequence = statusSequence;
        this.currentStatus = statusSequence.next();
    }

    @Override public String ping() {
        return "pong";
    }

    @Override public String teamId(String teamName, String password) {
        return teamId;
    }

    @Override public String currentVersus(String teamId) {
        return versusId;
    }

    @Override public String newPractice(String level, String teamId) {
        throw new UnsupportedOperationException();
    }

    @Override public String currentPractice(String teamId) {
        throw new UnsupportedOperationException();
    }

    @Override public String status(String gameId, String teamId) {
        if (currentStatus == GameStatus.NotYourTurn)
            currentStatus = statusSequence.next();
        return currentStatus.wireValue();
    }

    @Override public String board(String gameId) {
        throw new UnsupportedOperationException();
    }

    @Override public String lastMove(String gameId) {
        throw new UnsupportedOperationException();
    }

    @Override public synchronized String play(String gameId, String teamId, String x, String y) {
        if (currentStatus == GameStatus.YourTurn) {
            currentStatus = statusSequence.next();
            return MoveResult.Approved.wireValue();
        } else {
            return MoveResult.NotYourTurn.wireValue();
        }
    }

}