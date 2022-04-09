package org.hackujbrno.digibrno.servicebackend.cityboard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CityBoardEvent {
       String url;
       LocalizedTitle název;
       SpecifiedDate vyvěšení;
       SpecifiedDate relevatní_do;

       public CityBoardEventCustom convertToCustomFormat(){
              var parseid = this.url.split("detailId=");

              var id = Integer.parseInt(parseid[1]);
              var created = LocalDate.parse(vyvěšení.datum(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
              var expires = LocalDate.parse(relevatní_do.datum(),DateTimeFormatter.ofPattern("yyyy-MM-dd"));
              return new CityBoardEventCustom(this.url,id,created.toEpochSecond(LocalTime.of(0,0,0),ZoneOffset.UTC)*1000L,
                      expires.toEpochSecond(LocalTime.of(0,0,0),ZoneOffset.UTC)*1000L, this.název.cs());
       }
}
