package me.masumhasan.rvms

class Vehicle {
    Integer id
    String vehicleNo

    Route route
    User user

    static constraints = {
        vehicleNo(nullable: false)
    }
}
