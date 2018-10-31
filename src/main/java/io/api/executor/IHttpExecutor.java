package io.api.executor;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 31.10.2018
 */
public interface IHttpExecutor {

    /**
     * Performs a Http GET request
     *
     * @param url as string
     * @return result as string
     */
    String get(String url);
}
