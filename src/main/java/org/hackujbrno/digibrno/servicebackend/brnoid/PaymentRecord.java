package org.hackujbrno.digibrno.servicebackend.brnoid;

import java.time.LocalDateTime;

public record PaymentRecord(PaymentType type, long paymentDueTimestamp) {
}
