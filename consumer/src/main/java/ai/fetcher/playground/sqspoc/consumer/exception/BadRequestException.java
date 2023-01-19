package ai.fetcher.playground.sqspoc.consumer.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(final String message, final Throwable e) {
        super(message, e);
    }
}
