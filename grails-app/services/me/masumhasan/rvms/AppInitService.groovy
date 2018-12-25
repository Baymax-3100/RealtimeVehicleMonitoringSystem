package me.masumhasan.rvms


class AppInitService {

    def initUser() {
        if (User.count() == 0) {
            User user = new User()
            user.firstName = "Masum"
            user.lastName = "Hasan"
            user.email = "admin@rvms.local"
            user.password = "admin"
            user.userRole = UserRole.findByRole("ADMIN")
            user.save(flash: true)
            return user
        }
    }

    def initDriver() {
        User user = new User()
        user.firstName = "Rahim"
        user.lastName = "Khan"
        user.email = "rahim@rvms.local"
        user.password = "rahim"
        user.userRole = UserRole.findByRole("DRIVER")
        user.save(flash: true)
        return user
    }

    def initRoute() {
        Route route = new Route()
        route.startName = "IUBAT"
        route.destinationName = "Mirpur 10"
        route.startPoint = "-27.846523"
        route.endPoint = "28.88946313"
        route.wayPoints = "[27.96466,29.654646,24.897654,29.654646,24.4457654]"
        route.save(flush: true)
    }

    def initVehicle() {
        Vehicle vehicle = new Vehicle()
        vehicle.vehicleNo = "Bus no 29"
        vehicle.route = Route.findById(1)
        vehicle.user = User.findById(2)
        vehicle.save(flush:true)
    }



    def initUserRole(){
        if(UserRole.count() == 0){
         UserRole adminRole = new UserRole(role: "ADMIN").save(flush: true)
         UserRole userRole = new UserRole(role: "USER").save(flush: true)
         UserRole driverRole = new UserRole(role: "DRIVER").save(flush: true)
        }
    }

    def swaggerConfig(){

    }
}
