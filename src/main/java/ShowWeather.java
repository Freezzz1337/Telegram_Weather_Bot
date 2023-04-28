import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import emoticons.WeatherEmoticons;
import dto.EnterInJson;
import dto.HttpResponse;
import org.apache.commons.lang3.StringUtils;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import util.GetInformFromApplication;

import java.io.FileNotFoundException;
import java.util.Arrays;


public class ShowWeather {

    private final GetInformFromApplication getInformFromApplication = new GetInformFromApplication();

    private final String cityName;
    private String amountOfDays;
    private final RestTemplate restTemplate = new RestTemplate();

    public ShowWeather(String cityName, String amountOfDays) {
        this.cityName = cityName;
        this.amountOfDays = amountOfDays;
    }

    public ShowWeather(String cityName) {
        this.cityName = cityName;
    }

    public void checkCity() {
        try {
            getWeatherInJsonFormat();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public String getResult() throws HttpClientErrorException {
        try {
            return getWeatherByNumberOfDays(Integer.parseInt(amountOfDays));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

    private String getWeatherByNumberOfDays(int numberOfDays) throws JsonProcessingException {
        HttpResponse httpResponse = getWeatherInJsonFormat();
        EnterInJson enterInJson;
        StringBuilder result = new StringBuilder();

        int count = 0;
        String forCycle = "";
        result.append("Погода у ").append(cityName).append(" на ").append(numberOfDays)
                .append(switch (numberOfDays) {
                    case 1 -> " cьогодні";
                    case 5 -> " днів";
                    default -> " дні";
                })
                .append(":\n");
        for (int i = 0; i < numberOfDays; i++) {
            int j = 0;
            result.append("\nДата : ").append(getData(httpResponse.getEnterInJsons().get(count).getDataAndTime()))
                    .append("\n")
                    .append("--------------------------------------------------------------")
                    .append("\n");

            while (j < 8) {
                enterInJson = httpResponse.getEnterInJsons().get(count);
                forCycle = enterInJson.getDataAndTime();

                result.append(getTime(forCycle)).append(" ")
                        .append(kelvinsToCelsius(enterInJson.getMain().getTemp())).append(" ℃, ")

                        .append(StringUtils.capitalize(enterInJson.getWeatherList().get(0).getDescription()))
                        .append(getWeatherEmoticons(enterInJson.getWeatherList().get(0).getMain()))

                        .append(", ")

                        .append(enterInJson.getWind().getSpeed()).append(" м/с")
                        .append("\n");

                j++;
                count++;

                if (!timeCheck(forCycle))
                    break;
            }
        }
        return result.toString();
    }


    private String getWeatherEmoticons(String weather) {
        return switch (weather) {
            case "Rain" -> WeatherEmoticons.RAIN.get();
            case "Clouds" -> WeatherEmoticons.CLOUDS.get();
            case "Snow" -> WeatherEmoticons.SNOW.get();
            case "Clear" -> WeatherEmoticons.CLEAR.get();
            default -> "";
        };
    }

    private int kelvinsToCelsius(double kelvins) {
        return (int) Math.ceil(kelvins - 273.15);
    }

    private String getData(String dataAndTime) {
        return Arrays.stream(dataAndTime.split(" ")).findFirst().get();
    }

    private String getTime(String dataAndTime) {
        String[] timeWithSeconds = (Arrays.stream(dataAndTime.split(" ")).toList()).get(1).split("");
        String result = "";

        for (int i = 0; i < 5; i++) {
            result += timeWithSeconds[i];
        }
        return result;
    }

    private boolean timeCheck(String str) {
        boolean result = true;
        String lastTime = "21:00:00";
        String timeNow = Arrays.stream(str.split(" "))
                .reduce((a, a1) -> a1)
                .get();

        if (timeNow.equals(lastTime)) {
            result = false;
        }
        return result;
    }


    private HttpResponse getWeatherInJsonFormat() throws JsonProcessingException {

        String everythingThatWasInJson = restTemplate.getForObject(getInformFromApplication.getFirstPartOFLink() + cityName + getInformFromApplication.getLastPartOFLink(), String.class);
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper.readValue(everythingThatWasInJson, HttpResponse.class);
    }

}
