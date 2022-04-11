package org.hackujbrno.digibrno.servicebackend.events;

public record EventEntityEnvelope(EventEntity eventEntity) {

    public static EventEntityEnvelope fromEventEntityEnvelopeJSON(EventEnvelopeJSON envelopeJSON){
        return new EventEntityEnvelope(EventEntity.fromEventJSON(envelopeJSON.attributes));
    }
}
