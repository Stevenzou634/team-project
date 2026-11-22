package entity;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The result of a query: all tracked objects for one location and time, plus the weather there.
 * For an ObjectQuery, the list contains a single object.
 */
public class TrackedLocation {
    private final ObserverLocation location;
    private final ZonedDateTime time;
    private final Weather weather;
    private final List<TrackedObject> trackedObjects;

    public TrackedLocation(ObserverLocation location, ZonedDateTime time, Weather weather,
                           List<TrackedObject> trackedObjects) {
        this.location = location;
        this.time = time;
        this.weather = weather;
        this.trackedObjects = new ArrayList<>(trackedObjects);
    }

    public ObserverLocation getLocation() {
        return location;
    }

    public ZonedDateTime getTime() {
        return time;
    }

    public Weather getWeather() {
        return weather;
    }

    public List<TrackedObject> getTrackedObjects() {
        return Collections.unmodifiableList(trackedObjects);
    }

    /**
     * Returns only the objects at least minAltitude degrees above the horizon.
     */
    public List<TrackedObject> getVisibleObjects(double minAltitude) {
        List<TrackedObject> visible = new ArrayList<>();
        for (TrackedObject trackedObject : trackedObjects) {
            if (trackedObject.isVisible(minAltitude)) {
                visible.add(trackedObject);
            }
        }
        return visible;
    }
}