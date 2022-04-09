package org.hackujbrno.digibrno.servicebackend.events;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.hackujbrno.digibrno.servicebackend.common.DataFetching;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;
import java.util.function.Predicate;

@RestController
public class EventProviderService {
    @Autowired
    EventDataRegistry dataRegistry;

    @GetMapping("/events")
    public EventEnvelopeJSON[] getEvents() throws Exception{
        return dataRegistry.events;
    }


    private EventEnvelopeJSON[] getEventsFromRemote() throws Exception{
        var response = DataFetching.getDataFromRemote("https://services6.arcgis.com/fUWVlHWZNxUvTUh8/arcgis/rest/services/Events/FeatureServer/0/query?where=1%3D1&outFields=*&outSR=4326&f=json");
        EventsFullObjectJSON fullObjectJSON = new ObjectMapper().readValue(response.body(),EventsFullObjectJSON.class);
                return fullObjectJSON.features;
    }

    @GetMapping("/eventsToday")
    public List<EventEnvelopeJSON> getEventsToday() throws Exception{
         return Arrays.stream(dataRegistry.events).filter(this::eventIsHappeningToday).toList();
    }

    private boolean eventIsHappeningToday(EventEnvelopeJSON input){
        var fromTs =  input.attributes.getDate_from() ;
        var toTs =  input.attributes.getDate_to();
       var fromDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(fromTs),
                TimeZone.getDefault().toZoneId());
        var toDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(toTs),
                TimeZone.getDefault().toZoneId());
        var now = LocalDateTime.now();
        return now.isAfter(fromDate) && now.isBefore(toDate);
    }






}
