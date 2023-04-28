package dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class HttpResponse {
    @JsonProperty("list")
    List<EnterInJson> enterInJsons;

    public List<EnterInJson> getEnterInJsons() {
        return enterInJsons;
    }

    public void setEnterInJsons(List<EnterInJson> enterInJsons) {
        this.enterInJsons = enterInJsons;
    }
}
