package org.hackujbrno.digibrno.servicebackend.cityinfo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CityContactsProviderService {

    @GetMapping("/contactsPhone")
    public List<Contact> getContacts(){
        var contactList = new ArrayList<Contact>();
        contactList.add(new Contact("+420 545 538 538","Lékařská služba první pomoci pro dospělé"));
        contactList.add(new Contact("+420 532 234 935","Lékařská služba první pomoci pro děti"));
        contactList.add(new Contact("+420 545 538 421","Stomatologická pohotovost"));
        contactList.add(new Contact("+420 542 213 531","Lékárenská pohotovostní služba"));
        contactList.add(new Contact("129","Plyn"));
        contactList.add(new Contact("+420 543 212 537","Voda"));
        contactList.add(new Contact("+420 800 225 577","Elektřina"));
        contactList.add(new Contact("+420 950 630 110","Hasičský záchranný sbor JKM"));
        contactList.add(new Contact("+420 541 594 472","Krajská veterinární správa SVS pro Jihomoravský kraj"));
        contactList.add(new Contact("+420 974 621 111","Odbor cizinecké policie"));
        return contactList;
    }

    @GetMapping("/contactsEmail")
    public List<Contact> getMails() throws IOException {
        var contactList = new ArrayList<Contact>();
        Document doc = Jsoup.connect("https://www.brno.cz/kontakty/kontakty-pro-dotazy-elektronickou-formou/").get();
         var tbody = doc.getElementsByTag("tbody").get(0);
         for (Element row : tbody.getElementsByTag("tr")) {
             var desc = row.getElementsByTag("th").get(0).text();
             var contRaw = row.getElementsByTag("td").get(0).text();
             var contSanitized = contRaw.replace("(zavináč)","@").replace("(tečka)", ".");
             var cont = new Contact(contSanitized,desc);
             contactList.add(cont);
         }
         return contactList;
    }


}
