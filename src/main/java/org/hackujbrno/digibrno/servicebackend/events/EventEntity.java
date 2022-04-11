package org.hackujbrno.digibrno.servicebackend.events;

import java.util.Arrays;
import java.util.List;

public record EventEntity(
        String name,
        String text,
        String tickets,
        String tickets_info,
        String url,
        String name_en,
        String text_en,
        String url_en,
        long date_from,
        long date_to,
        String categories,
        List<String> categories_arr,
        String organizer_email,
        String first_image
) {
    public static EventEntity fromEventJSON(EventJSON eventJSON){
        return new EventEntity(
                unescapeNullable(eventJSON.getName()),
                unescapeNullable(eventJSON.getText()),
                unescapeNullable(eventJSON.getTickets()),
                unescapeNullable(eventJSON.getTickets_info()),
                eventJSON.getUrl(),
                unescapeNullable(eventJSON.getName_en()),
                unescapeNullable(eventJSON.getText_en()),
                eventJSON.getUrl_en(),
                eventJSON.getDate_from(),
                eventJSON.getDate_to(),
                unescapeNullable(eventJSON.getCategories()),
                parseCategoryString(eventJSON.getCategories()),
                eventJSON.getOrganizer_email(),
                eventJSON.getFirst_image()
        );
    }

    public static String unescapeNullable(String input){
        return input == null ? null : org.jsoup.parser.Parser.unescapeEntities(input, true).replace("\n"," ");
    }
    public static List<String> parseCategoryString(String input){
        if (input == null) {
            return List.of();
        }
        return Arrays.stream(input.split(",")).map(String::trim).toList();
    }
}
