package fr.notfound.http.uri;

import java.net.URI;

public class UncheckedUriFactory implements UriFactory {

    @Override public URI get(String uri) {
        return URI.create(uri);
    }

}
