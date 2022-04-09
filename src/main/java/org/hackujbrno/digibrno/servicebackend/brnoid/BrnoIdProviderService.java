package org.hackujbrno.digibrno.servicebackend.brnoid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;
import java.util.List;

@RestController
public class BrnoIdProviderService {

    @GetMapping("/brnoid")
    public BrnoIdData getBrnoIdData(Integer id){
        var waste = new PaymentRecord(PaymentType.WASTE,LocalDateTime.of(2022,Month.AUGUST,24,0,0).toEpochSecond(ZoneOffset.UTC)*1000L);
        var ticket = new TransportTicket(TransportTicketType.YEAR, LocalDateTime.of(2022, Month.APRIL,11,0,0).toEpochSecond(ZoneOffset.UTC)*1000L);
        return new BrnoIdData("Vladislav","Těsto","Brno", Membership.FULL,ticket, List.of(waste));
    }
}
