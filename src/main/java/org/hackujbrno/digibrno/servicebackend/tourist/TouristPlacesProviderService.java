package org.hackujbrno.digibrno.servicebackend.tourist;

import org.hackujbrno.digibrno.servicebackend.events.EventEnvelopeJSON;
import org.springframework.web.bind.annotation.GetMapping;

public class TouristPlacesProviderService {
    @GetMapping("/touristPlaces")
    public EventEnvelopeJSON[] getEvents() throws Exception{
        return null;
    }
}
