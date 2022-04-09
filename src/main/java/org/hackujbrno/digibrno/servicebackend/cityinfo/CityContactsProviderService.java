package org.hackujbrno.digibrno.servicebackend.cityinfo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CityContactsProviderService {

    @GetMapping("/contacts")
    public List<Contact> getContacts(){
        return new ArrayList<Contact>();
    }

}
