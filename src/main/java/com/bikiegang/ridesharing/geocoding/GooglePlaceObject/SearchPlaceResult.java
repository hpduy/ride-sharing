package com.bikiegang.ridesharing.geocoding.GooglePlaceObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 8/11/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchPlaceResult {
    private String [] html_attributions;
    private String next_page_token;
    private Place[] results;
    private String status;

    public String[] getHtml_attributions() {
        return html_attributions;
    }

    public void setHtml_attributions(String[] html_attributions) {
        this.html_attributions = html_attributions;
    }

    public String getNext_page_token() {
        return next_page_token;
    }

    public void setNext_page_token(String next_page_token) {
        this.next_page_token = next_page_token;
    }

    public Place[] getResults() {
        return results;
    }

    public void setResults(Place[] results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
