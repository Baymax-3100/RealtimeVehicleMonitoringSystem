package me.masumhasan.rvms

class CurrentLocation {

    Integer id
    String latitude
    String longitude

    Vehicle vehicle

    Date lastUpdated

    static constraints = {
        latitude(nullable: false)
        longitude(nullable: false)
    }

    def beforeInsert (){
    }

    def beforeUpdate(){

    }
}
