package org.hackujbrno.digibrno.servicebackend.brnoid;

import java.time.LocalDateTime;

public record TransportTicket(TransportTicketType type, long validUntilTimeStamp) {
}
