package entity;

import java.time.ZonedDateTime;

/**
 * What the user asks for: where and when they want to observe.
 */
public abstract class QueryObject {
    private final ObserverLocation observerLocation;
    private final ZonedDateTime observerTime;

    protected QueryObject(ObserverLocation observerLocation, ZonedDateTime observerTime) {
        this.observerLocation = observerLocation;
        this.observerTime = observerTime;
    }

    public ObserverLocation getObserverLocation() {
        return observerLocation;
    }

    public ZonedDateTime getObserverTime() {
        return observerTime;
    }
}