package org.hackujbrno.digibrno.servicebackend.brnoid;

import java.time.LocalDateTime;
import java.util.List;

public record BrnoIdData(String firstName, String surname, String city, Membership membership,TransportTicket transportTicket, List<PaymentRecord> payments) {
}
