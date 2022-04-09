package org.hackujbrno.digibrno.servicebackend.events;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

@JsonIgnoreProperties(ignoreUnknown = true)
public class EventEnvelopeJSON {
    public EventJSON attributes;
}
