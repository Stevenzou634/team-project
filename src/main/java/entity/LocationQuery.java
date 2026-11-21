package entity;

import java.time.ZonedDateTime;

/**
 * "What can I see from this location at this time?"
 */
public class LocationQuery extends QueryObject {
    private final double minAltitude;

    public LocationQuery(ObserverLocation observerLocation, ZonedDateTime observerTime, double minAltitude) {
        super(observerLocation, observerTime);
        this.minAltitude = minAltitude;
    }

    public double getMinAltitude() {
        return minAltitude;
    }
}
