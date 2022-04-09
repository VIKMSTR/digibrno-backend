package org.hackujbrno.digibrno.servicebackend.cityboard;

public record CityBoardEventCustom(String url, int id, long createdTimeStamp, long expiresTimeStamp, String title) {
}
