package me.masumhasan.rvms

class BootStrap {

    AppInitService appInitService

    def init = { servletContext ->
        appInitService.initUserRole()
        appInitService.initUser()
        appInitService.initDriver()
        appInitService.initRoute()
        appInitService.initVehicle()
    }
    def destroy = {
    }
}
