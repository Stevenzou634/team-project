package entity;

import java.time.ZonedDateTime;

/**
 * "Where is this object from this location at this time?"
 */
public class ObjectQuery extends QueryObject {
    private final String celestialBody;

    public ObjectQuery(ObserverLocation observerLocation, ZonedDateTime observerTime, String celestialBody) {
        super(observerLocation, observerTime);
        this.celestialBody = celestialBody;
    }

    public String getCelestialBody() {
        return celestialBody;
    }
}
