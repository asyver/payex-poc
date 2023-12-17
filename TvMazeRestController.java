package no.syver.tvmaze.rest;

import no.syver.tvmaze.serviceimpl.TvMazeServiceImpl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class TvMazeRestController {

	Logger logger = LoggerFactory.getLogger(TvMazeRestController.class);
	
    @Autowired
	TvMazeServiceImpl tvMazeClient;

    ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping(value =  "tvmaze/showlist")
    public String getTrimmedShowList(@RequestParam String showname) {
    	logger.info("showname=" + showname);
        String jsonBlobOfShows = "";
		try {
			jsonBlobOfShows = tvMazeClient.getShows(showname);
			
			Map<String, Object> showList = objectMapper.readValue(jsonBlobOfShows, Map.class);
			
			System.out.println("Hei=" + showList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("FaNT none programmer:");
        return jsonBlobOfShows;
    }

}