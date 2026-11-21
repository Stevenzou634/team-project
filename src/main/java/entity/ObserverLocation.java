package entity;

/**
 * A place on Earth where the user observes from.
 * Latitude: north is positive. Longitude: east is positive.
 */
public class ObserverLocation {
    private static final double MAX_LATITUDE = 90.0;
    private static final double MAX_LONGITUDE = 180.0;

    private final double latitude;
    private final double longitude;

    public ObserverLocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    /**
     * Returns true if latitude is in [-90, 90] and longitude is in [-180, 180].
     */
    public boolean isValid() {
        return Math.abs(latitude) <= MAX_LATITUDE && Math.abs(longitude) <= MAX_LONGITUDE;
    }
}