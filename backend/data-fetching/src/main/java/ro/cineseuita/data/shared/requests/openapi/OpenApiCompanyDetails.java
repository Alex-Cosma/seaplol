package ro.cineseuita.data.shared.requests.openapi;

import ro.cineseuita.data.shared.requests.OpenApiGet;

import javax.ws.rs.core.MediaType;

public class OpenApiCompanyDetails implements OpenApiGet {

    private static final String URL = "https://api.openapi.ro/api/companies/";
    private static final String KEY = "";

    private final String cui;

    public OpenApiCompanyDetails(String cui) {
        this.cui = cui;
    }

    @Override
    public String getUrl() {
        return URL + cui;
    }

    @Override
    public MediaType getMediaType() {
        return MediaType.APPLICATION_JSON_TYPE;
    }

    @Override
    public String getApiKey() {
        return KEY;
    }

}
