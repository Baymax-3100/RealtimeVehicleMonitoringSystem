package me.masumhasan.rvms

class Route {
    Integer id
    String destinationName
    String startName
    String startPoint
    String endPoint
    String wayPoints

    static constraints = {
        startPoint(nullable: false)
        endPoint(nullable: false)
        wayPoints(nullable: false)
    }
}
