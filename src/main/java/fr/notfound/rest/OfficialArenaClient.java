package fr.notfound.rest;

import fr.notfound.rest.uri.ArenaUriCatalog;

public class OfficialArenaClient implements PlainTextArenaClient {
    
    public final ArenaUriCatalog urls;
    public final UriContentReader client;
    
    public OfficialArenaClient(ArenaUriCatalog urls, UriContentReader client) {
        this.urls = urls;
        this.client = client;
    }

    @Override public String ping() {
        return client.read(urls.ping());
    }

    @Override public String teamId(String teamName, String password) {
        return client.read(urls.teamId(teamName, password));
    }

    @Override public String currentVersus(String teamId) {
        return client.read(urls.currentVersus(teamId));
    }

    @Override public String newPractice(String level, String teamId) {
        return client.read(urls.newPractice(level, teamId));
    }

    @Override public String currentPractice(String teamId) {
        return client.read(urls.currentPractice(teamId));
    }

    @Override public String status(String gameId, String teamId) {
        return client.read(urls.status(gameId, teamId));
    }

    @Override public String board(String gameId) {
        return client.read(urls.board(gameId));
    }

    @Override public String lastMove(String gameId) {
        return client.read(urls.lastMove(gameId));
    }

    @Override public String play(String gameId, String teamId, String x, String y) {
        return client.read(urls.play(gameId, teamId, x, y));
    }

}
