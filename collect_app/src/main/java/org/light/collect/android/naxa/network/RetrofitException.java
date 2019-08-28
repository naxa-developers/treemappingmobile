package org.light.collect.android.naxa.network;

import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import timber.log.Timber;

public class RetrofitException extends RuntimeException {
    static RetrofitException httpError(String url, Response response, Retrofit retrofit) {
        String message = response.code() + " " + response.message();

        return new RetrofitException(message, url, response, Kind.HTTP, null, retrofit);
    }


    private static String getReadableErrorMessage(ResponseBody responseBody) {
        String message = "";
        try {
            message = responseBody.string();
        } catch (NullPointerException | IOException e) {
            e.printStackTrace();
        }

        return message;
    }


    static RetrofitException networkError(IOException exception) {
        return new RetrofitException(exception.getMessage(), null, null, Kind.NETWORK, exception, null);
    }

    static RetrofitException unexpectedError(Throwable exception) {
        return new RetrofitException(exception.getMessage(), null, null, Kind.UNEXPECTED, exception, null);
    }

    /**
     * Identifies the event kind which triggered a {@link RetrofitException}.
     */
    public enum Kind {
        /**
         * An {@link IOException} occurred while communicating to the server.
         */
        NETWORK("An occurred while communicating to the server."),
        /**
         * A non-200 HTTP status code was received from the server.
         */
        HTTP("A non-200 HTTP status code was received from the server"),
        /**
         * An internal error occurred while attempting to execute a request. It is best practice to
         * re-throw this exception so your application crashes.
         */
        UNEXPECTED("An internal error occurred while attempting to execute a request.");

        private String message;

        Kind(String message) {
            this.message = message;
        }

        @Override
        public String toString() {
            return message+"";
        }
    }

    private final String url;
    private final Response response;
    private final Kind kind;
    private final Retrofit retrofit;

    private RetrofitException(String message, String url, Response response, Kind kind, Throwable exception, Retrofit retrofit) {
        super(message, exception);
        this.url = url;
        this.response = response;
        this.kind = kind;
        this.retrofit = retrofit;
    }

    /**
     * The request URL which produced the error.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Response object containing status code, headers, body, etc.
     */
    public Response getResponse() {
        return response;
    }

    /**
     * The event kind which triggered this error.
     */
    public Kind getKind() {
        return kind;
    }

    /**
     * The Retrofit this request was executed on
     */
    public Retrofit getRetrofit() {
        return retrofit;
    }

    /**
     * HTTP response body converted to specified {@code type}. {@code null} if there is no
     * response.
     *
     * @throws IOException if unable to convert the body to the specified {@code type}.
     */
    public <T> T getErrorBodyAs(Class<T> type) throws IOException {
        if (response == null || response.errorBody() == null) {
            return null;
        }
        Converter<ResponseBody, T> converter = retrofit.responseBodyConverter(type, new Annotation[0]);
        return converter.convert(response.errorBody());
    }

    public static String getMessage(Throwable e) {
        String[] message = new String[]{e.getMessage(), e.getMessage()};

        if (e instanceof RetrofitException) {
            RetrofitException retrofitException = ((RetrofitException) e);
            switch (retrofitException.getKind()) {
                case NETWORK:

                    message = new String[]{"Connection lost", String.format("A %s occurred while communicating to the server", retrofitException.getCause().getMessage())};
                    break;
                case HTTP:
                    message = new String[]{"", e.getMessage()};
                    break;
                case UNEXPECTED:
                    break;
            }
        }
        return message[1];
    }
}

