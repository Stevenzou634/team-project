package entity;

/**
 * The calculated position of one celestial object at a given time and location.
 * Elevation: degrees above the horizon (negative = below the horizon).
 * Azimuth: compass direction in degrees (0 = North, 90 = East).
 */
public class TrackedObject {
    private final String objectName;
    private final double objectElevation;
    private final double objectAzimuth;

    public TrackedObject(String objectName, double objectElevation, double objectAzimuth) {
        this.objectName = objectName;
        this.objectElevation = objectElevation;
        this.objectAzimuth = objectAzimuth;
    }

    public String getObjectName() {
        return objectName;
    }

    public double getObjectElevation() {
        return objectElevation;
    }

    public double getObjectAzimuth() {
        return objectAzimuth;
    }

    /**
     * Returns true if the object is at least minAltitude degrees above the horizon.
     */
    public boolean isVisible(double minAltitude) {
        return objectElevation >= minAltitude;
    }
}