package io.api.etherscan.executor.impl;

import io.api.etherscan.error.ConnectionException;
import io.api.etherscan.executor.IHttpExecutor;
import io.api.etherscan.util.BasicUtils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static java.net.HttpURLConnection.HTTP_MOVED_PERM;
import static java.net.HttpURLConnection.HTTP_MOVED_TEMP;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 28.10.2018
 */
public class HttpExecutor implements IHttpExecutor {

    private static final Map<String, String> DEFAULT_HEADERS = new HashMap<>();

    static {
        DEFAULT_HEADERS.put("accept-language", "en,ru;q=0.9");
        DEFAULT_HEADERS.put("accept-encoding", "gzip, deflate, br");
        DEFAULT_HEADERS.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) Chrome/68.0.3440.106");
        DEFAULT_HEADERS.put("cache-control", "max-age=0");
    }

    private final Map<String, String> headers;
    private final int timeout;

    public HttpExecutor() {
        this(30000);
    }

    public HttpExecutor(final int timeout) {
        this(timeout, DEFAULT_HEADERS);
    }

    public HttpExecutor(final int timeout,
                        final Map<String, String> headers) {
        this.timeout = timeout;
        this.headers = headers;
    }

    @Override
    public String get(final String urlAsString) {
        try {
            final URL url = new URL(urlAsString);
            final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(timeout);
            headers.forEach(connection::setRequestProperty);

            final int status = connection.getResponseCode();
            if (status == HTTP_MOVED_TEMP || status == HTTP_MOVED_PERM) {
                final String location = connection.getHeaderField("Location");
                return get(location);
            }

            final String data = readData(connection);
            connection.disconnect();
            return data;
        } catch (IOException e) {
            throw new ConnectionException(e.getLocalizedMessage(), e);
        }
    }

    @Override
    public String post(final String urlAsString, final String dataToPost) {
        try {
            final URL url = new URL(urlAsString);
            final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(timeout);
            headers.forEach(connection::setRequestProperty);

            if(!BasicUtils.isEmpty(dataToPost)) {
                connection.setDoOutput(true);
                DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
                wr.writeBytes(dataToPost);
                wr.flush();
                wr.close();
            }

            final int status = connection.getResponseCode();
            if (status == HTTP_MOVED_TEMP || status == HTTP_MOVED_PERM) {
                final String location = connection.getHeaderField("Location");
                return post(location, dataToPost);
            }

            final String data = readData(connection);
            connection.disconnect();
            return data;
        } catch (IOException e) {
            throw new ConnectionException(e.getLocalizedMessage(), e);
        }
    }

    private String readData(final HttpURLConnection connection) throws IOException {
        final StringBuilder content = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                content.append(inputLine);

            in.close();
        }

        return content.toString();
    }
}
