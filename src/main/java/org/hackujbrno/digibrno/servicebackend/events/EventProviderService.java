package org.hackujbrno.digibrno.servicebackend.events;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.hackujbrno.digibrno.servicebackend.common.DataFetching;
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

@RestController
public class EventProviderService {


    @GetMapping("/events")
    public EventEnvelopeJSON[] getEvents() throws Exception{
        return getEventsFromRemote();
    }


    private EventEnvelopeJSON[] getEventsFromRemote() throws Exception{
        var response = DataFetching.getDataFromRemote("https://services6.arcgis.com/fUWVlHWZNxUvTUh8/arcgis/rest/services/Events/FeatureServer/0/query?where=1%3D1&outFields=*&outSR=4326&f=json");
        EventsFullObjectJSON fullObjectJSON = new ObjectMapper().readValue(response.body(),EventsFullObjectJSON.class);
                return fullObjectJSON.features;
    }




}
