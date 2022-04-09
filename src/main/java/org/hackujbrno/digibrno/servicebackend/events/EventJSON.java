package org.hackujbrno.digibrno.servicebackend.events;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventJSON {
    private String name;
    private String text;
    private String tickets;
    private String tickets_info;
    private String url;
    private String name_en;
    private String text_en;
    private String url_en;
    private long date_from;
    private long date_to;
    private String categories;
    private String organizer_email;
    private String first_image;
    /*
    "ID":2968,
            "name":"Šifrovací hra: Tajemství Staré radnice",
            "text":"Hra, ke které je potřeba mít chytrý telefon, provede návštěvníky sedmi stanovišti. Ta jsou rozmístěna nejen po Staré radnici, ale i&nbsp;ve věži, tak, aby si hráči užili i&nbsp;výhled na Brno. Na každém ze stanovišť musí hráč najít kód. Buďto v&nbsp;podobě čísel nebo jako QR symbol, což je kresba, kterou dekóduje chytrý telefon. Po zadání kódu a&nbsp;zvládnutí úkolu se přesune na další stanoviště. Nikdo se nemusí bát, že něco nevyřeší. Úkoly pracují s&nbsp;tím, co hráči v&nbsp;místě najdou. Nutí je vše si prohlédnout, protože právě v&nbsp;předmětech, obrazech, sochách mohou být skryty šifry. Na konci hry získají heslo, které zadají do systému a&nbsp;zjistí, zda hrou prošli správně. Hrou vede příběh.Hru je možné hrát dle otevírací doby věže.",
            "tickets":"Dospělí - 75Kč,Studenti - 40Kč,Dospělí - 40Kč",
            "tickets_info":"v ceně je i&nbsp;vstup na sálů Staré radnice a&nbsp;na věž",
            "images":"https://www.gotobrno.cz/wp-content/uploads/2017/05/stara-radnice-infocentrum-pod-krokodylem-e1498945693466.jpg,https://www.gotobrno.cz/wp-content/uploads/2017/07/stara-radnice-monika-hlavacova.jpg,https://www.gotobrno.cz/wp-content/uploads/2017/10/stara-radnice-m-ruzicka-e1620633554122.jpg,https://www.gotobrno.cz/wp-content/uploads/2017/10/vez-stare-radnice_-pohled_tic.jpg,https://www.gotobrno.cz/wp-content/uploads/2017/05/jan_caga_stara-radnice-turisti-e1498831073811.jpg,https://www.gotobrno.cz/wp-content/uploads/2017/05/jan_caga_stara-radnice-krokodyl-e1501584742980.jpg,https://www.gotobrno.cz/wp-content/uploads/2017/10/letni-scena_podium.jpg,https://www.gotobrno.cz/wp-content/uploads/2017/07/jan_caga_stara-radnice-kolo-e1498946075708.jpg,https://www.gotobrno.cz/wp-content/uploads/2017/05/stara-radnice-nadvori-brno.jpg,https://www.gotobrno.cz/wp-content/uploads/2017/05/stara-radnice-brno-1-e1503665790233.jpg,https://www.gotobrno.cz/wp-content/uploads/2017/05/radnice-e1498945361824.jpg,https://www.gotobrno.cz/wp-content/uploads/2017/05/infocentrum-pod-krokodylem-2.jpg,https://www.gotobrno.cz/wp-content/uploads/2017/05/infocentrum-pod-krokodylem-1.jpg,https://www.gotobrno.cz/wp-content/uploads/2017/07/jan_caga_stara-radnice-e1498946030170.jpg,https://www.gotobrno.cz/wp-content/uploads/2017/05/piřík_Radnice-9-k-e1498945396605.jpg,https://www.gotobrno.cz/wp-content/uploads/2021/02/stara-radnice-autor-m-ruzicka-e1613127005633.jpg",
            "url":"https://www.gotobrno.cz/akce/sifrovaci-hra-tajemstvi-stare-radnice/",
            "categories":"Pro rodiny, Veletrhy / vzdělávací",
            "parent_festivals":null,
            "organizer_email":"info@ticbrno.cz",
            "tickets_url":null,
            "name_en":null,
            "text_en":null,
            "url_en":"https://www.gotobrno.cz/akce/sifrovaci-hra-tajemstvi-stare-radnice/",
            "tickets_url_en":null,
            "latitude":49.1931161,
            "longitude":16.6088381,
            "date_from":1462406400000,
            "date_to":1672444800000,
            "first_image":"https://www.gotobrno.cz/wp-content/uploads/2017/05/stara-radnice-infocentrum-pod-krokodylem-e1498945693466.jpg",
            "ObjectId":1,
            "GlobalID":"17b409bd-1fc4-4c76-b2de-16002ec4dd28
     */
}
