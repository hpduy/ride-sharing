package com.bikiegang.ridesharing.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;

/**
 * Created by hpduy17 on 8/13/15.
 */
public class PopularLocation extends LatLng implements PojoBase {
    private long id;
    private String name;
    private String address;
    private String backgroundImageLink = "";
    private HashSet<String> searcher = new HashSet<>();
    private int type;

    @JsonIgnore
    public static final int POPULAR_LOCATION = 0;
    @JsonIgnore
    public static final int EVENT_LOCATION = 1;

    public PopularLocation() {
    }

    public PopularLocation(String coordinate) {
        super(coordinate);
    }

    public PopularLocation(LatLng that, long id, String name, String address, HashSet<String> searcher, String backgroundImageLink) {
        super(that);
        this.id = id;
        this.name = name;
        this.address = address;
        this.searcher = searcher;
        this.backgroundImageLink = backgroundImageLink;
    }

    public PopularLocation(LatLng that, long id, String name, String address, String backgroundImageLink, HashSet<String> searcher, int type) {
        super(that);
        this.id = id;
        this.name = name;
        this.address = address;
        this.backgroundImageLink = backgroundImageLink;
        this.searcher = searcher;
        this.type = type;
    }

    public PopularLocation(PopularLocation that) {
        super(that);
        this.id = that.id;
        this.name = that.name;
        this.address = that.address;
        this.searcher = that.searcher;
        this.backgroundImageLink = that.backgroundImageLink;
        this.type = that.type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public HashSet<String> getSearcher() {
        return searcher;
    }

    public void setSearcher(HashSet<String> searcher) {
        this.searcher = searcher;
    }

    public String getBackgroundImageLink() {
        return backgroundImageLink;
    }

    public void setBackgroundImageLink(String backgroundImageLink) {
        this.backgroundImageLink = backgroundImageLink;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
