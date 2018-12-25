package me.masumhasan.rvms

class LocationHistory {

    Integer id
    String latitude
    String longitude

    Vehicle vehicle

    Date dateCreated


    static constraints = {
        latitude(nullable: false)
        longitude(nullable: false)
    }
}
