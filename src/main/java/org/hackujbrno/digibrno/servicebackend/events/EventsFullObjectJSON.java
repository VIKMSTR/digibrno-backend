package org.hackujbrno.digibrno.servicebackend.events;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventsFullObjectJSON {
    public EventEnvelopeJSON[] features;
    public String objectIdFieldName;
}
