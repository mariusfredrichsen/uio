public class WeatherTuple {
    private long timestamp;
    private String weatherStation;
    private String stationName;
    private Double averageTemperature;
    private Double minimumTemperature;
    private Double maximumTemperature;
    private Double averageWindSpeed;
    private Double precipitation;
    private String weather;

    public WeatherTuple(long timestamp, String weatherStation, String stationName, Double averageTemperature, Double minimumTemperature, Double maximumTemperature, Double averageWindSpeed, Double precipitation, String weather) {
        this.timestamp = timestamp;
        this.weatherStation = weatherStation;
        this.stationName = stationName;
        this.averageTemperature = averageTemperature;
        this.minimumTemperature = minimumTemperature;
        this.maximumTemperature = maximumTemperature;
        this.averageWindSpeed = averageWindSpeed;
        this.precipitation = precipitation;
        this.weather = weather;
    }

    public WeatherTuple() {
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getWeatherStation() {
        return weatherStation;
    }

    public void setWeatherStation(String weatherStation) {
        this.weatherStation = weatherStation;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public Double getAverageTemperature() {
        return averageTemperature;
    }

    public void setAverageTemperature(Double averageTemperature) {
        this.averageTemperature = averageTemperature;
    }

    public Double getMinimumTemperature() {
        return minimumTemperature;
    }

    public void setMinimumTemperature(Double minimumTemperature) {
        this.minimumTemperature = minimumTemperature;
    }

    public Double getMaximumTemperature() {
        return maximumTemperature;
    }

    public void setMaximumTemperature(Double maximumTemperature) {
        this.maximumTemperature = maximumTemperature;
    }

    public Double getAverageWindSpeed() {
        return averageWindSpeed;
    }

    public void setAverageWindSpeed(Double averageWindSpeed) {
        this.averageWindSpeed = averageWindSpeed;
    }

    public Double getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(Double precipitation) {
        this.precipitation = precipitation;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    @Override
    public String toString()
    {
        return          "Timestamp: " + timestamp + "\t" +
                        "Station: "+ weatherStation + "\t" +
                        "Station name: " + stationName + "\t" +
                        "Average temperature: " + averageTemperature.toString() + "\t" +
                        "Minimum temperature: " + minimumTemperature.toString() + "\t" +
                        "Maximum temperature: " + maximumTemperature.toString() + "\t" +
                        "Wind Speed: " + averageWindSpeed.toString() + "\t" +
                        "Precipitation: " + precipitation.toString() + "\t" +
                        "Weather: " + weather + "\t";
    }
}
