package io.api.executor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import static java.net.HttpURLConnection.HTTP_MOVED_PERM;
import static java.net.HttpURLConnection.HTTP_MOVED_TEMP;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 28.10.2018
 */
public class HttpExecutor {

    private final int timeout;

    public HttpExecutor() {
        this(30000);
    }

    public HttpExecutor(int timeout) {
        this.timeout = timeout;
    }

    public String get(final String urlAsString,
                      final Map<String, String> headers) throws IOException {

        final URL url = new URL(urlAsString);
        final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(timeout);
        headers.forEach(connection::setRequestProperty);

        final int status = connection.getResponseCode();
        if (status == HTTP_MOVED_TEMP || status == HTTP_MOVED_PERM) {
            final String location = connection.getHeaderField("Location");
            return get(location, headers);
        }

        final StringBuilder content = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                content.append(inputLine);

            in.close();
        }

        connection.disconnect();
        return content.toString();
    }
}
