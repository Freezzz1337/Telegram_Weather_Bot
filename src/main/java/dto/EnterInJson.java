package dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class EnterInJson {
    @JsonProperty("dt_txt")
    private String dataAndTime;
    @JsonProperty("main")
    private Main main;
    @JsonProperty("weather")
    private List<Weather> weatherList;
    @JsonProperty("wind")
    private Wind wind;

    public String getDataAndTime() {
        return dataAndTime;
    }

    public void setDataAndTime(String dataAndTime) {
        this.dataAndTime = dataAndTime;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public List<Weather> getWeatherList() {
        return weatherList;
    }

    public void setWeatherList(List<Weather> weatherList) {
        this.weatherList = weatherList;
    }

}
