package org.hackujbrno.digibrno.servicebackend.control;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hackujbrno.digibrno.servicebackend.cityboard.CityBoardDataRegistry;
import org.hackujbrno.digibrno.servicebackend.cityboard.CityBoardEvent;
import org.hackujbrno.digibrno.servicebackend.cityboard.CityBoardEventCustom;
import org.hackujbrno.digibrno.servicebackend.cityboard.CityBoardEventFullJSON;
import org.hackujbrno.digibrno.servicebackend.common.DataFetching;
import org.hackujbrno.digibrno.servicebackend.events.EventDataRegistry;
import org.hackujbrno.digibrno.servicebackend.events.EventEnvelopeJSON;
import org.hackujbrno.digibrno.servicebackend.events.EventsFullObjectJSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@EnableAsync
@RestController
public class ControlService {

    @Autowired
    EventDataRegistry dataRegistry;
    @Autowired
    CityBoardDataRegistry cityBoardDataRegistry;

    Logger logger = LoggerFactory.getLogger(ControlService.class);

    @GetMapping("/refresh")
    public void refreshData() throws Exception {
        logger.info("Data refresh requested...");
            dataRegistry.events = getEventsFromRemote();
            cityBoardDataRegistry.data = getCityBoardEvents();
    }

    @EventListener(classes = ApplicationStartedEvent.class )
    public void listenToStart(ApplicationStartedEvent event) throws Exception {
        logger.info("App starting, fetching new data.");
        dataRegistry.events = getEventsFromRemote();
        cityBoardDataRegistry.data = getCityBoardEvents();
        logger.info("Data updated!");
    }

    private EventEnvelopeJSON[] getEventsFromRemote() throws Exception{
        var response = DataFetching.getDataFromRemote("https://services6.arcgis.com/fUWVlHWZNxUvTUh8/arcgis/rest/services/Events/FeatureServer/0/query?where=1%3D1&outFields=*&outSR=4326&f=json");
        EventsFullObjectJSON fullObjectJSON = new ObjectMapper().readValue(response.body(),EventsFullObjectJSON.class);
        return fullObjectJSON.features;
    }

    private List<CityBoardEventCustom> getCityBoardEvents() throws Exception {
        var response = DataFetching.getDataFromRemote("http://edeska.brno.cz/eDeska/opendata");
        CityBoardEventFullJSON fullObjectJSON = new ObjectMapper().readValue(response.body(),CityBoardEventFullJSON.class);
        if (fullObjectJSON == null || fullObjectJSON.getInformace() == null) {
            return new ArrayList<>();
        }
        return Arrays.stream(fullObjectJSON.getInformace()).map(CityBoardEvent::convertToCustomFormat).toList();
    }

    @Async
    @Scheduled( fixedDelay = 2*60*60*1000, initialDelay = 2*60*60*1000) //every 2hrs
    public void scheduleFixedRateTask()  {
        logger.info("Scheduled data update started");
        try {
            dataRegistry.events = getEventsFromRemote();
            cityBoardDataRegistry.data = getCityBoardEvents();
            logger.info("Data updated!");
        } catch (Exception e) {
            logger.error("Error when fetching data.");
        }
    }


}
