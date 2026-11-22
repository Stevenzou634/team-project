package entity;

/**
 * The weather at one location and time.
 * Percentages are 0-100, temperature is in Celsius, wind speed is in km/h.
 */
public class Weather {
    private final double cloudCover;
    private final double precipitationProbability;
    private final double humidity;
    private final double temperature;
    private final double windSpeed;

    public Weather(double cloudCover, double precipitationProbability, double humidity,
                   double temperature, double windSpeed) {
        this.cloudCover = cloudCover;
        this.precipitationProbability = precipitationProbability;
        this.humidity = humidity;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
    }

    public double getCloudCover() {
        return cloudCover;
    }

    public double getPrecipitationProbability() {
        return precipitationProbability;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getWindSpeed() {
        return windSpeed;
    }
}
