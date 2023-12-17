package no.syver.tvmaze.serviceimpl;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import no.syver.tvmaze.http.TvMazeHttpClient;
import no.syver.tvmaze.service.TvMazeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.micrometer.common.util.StringUtils;

@Service
public class TvMazeServiceImpl implements TvMazeService {

    @Autowired
    TvMazeHttpClient httpClient;

    public String getShows(String showname) throws Exception {
        return httpClient.doGet(showname);
    }

}