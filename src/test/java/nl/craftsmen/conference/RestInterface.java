package nl.craftsmen.conference;

import java.util.Map;


public interface RestInterface {

    void setURIParameters(Map<String,String> paramValues);
    void callRestService();
    int getStatusCode();
    String getJSON();
}
