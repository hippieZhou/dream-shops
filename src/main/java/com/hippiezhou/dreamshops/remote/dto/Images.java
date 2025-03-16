package com.hippiezhou.dreamshops.remote.dto;

import java.util.List;

public record Images(
    String startdate,
    String fullstartdate,
    String enddate,
    String url,
    String urlbase,
    String copyright,
    String copyrightlink,
    String title,
    String quiz,
    boolean wp,
    String hsh,
    int drk,
    int top,
    int bot,
    List<String> hs) {
}
