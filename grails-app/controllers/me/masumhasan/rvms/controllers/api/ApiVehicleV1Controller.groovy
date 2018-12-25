package me.masumhasan.rvms.controllers.api

import com.hmtmcse.gs.GsRestProcessor
import me.masumhasan.rvms.VehicleDefinitionService

class ApiVehicleV1Controller extends GsRestProcessor{

    VehicleDefinitionService vehicleDefinitionService
    
    def getList() {
        return list(vehicleDefinitionService.list())
    }

    def getDetails() {
        return customProcessor(vehicleDefinitionService.details())
    }

    def getDetailsByUser() {
        return customProcessor(vehicleDefinitionService.detailsByUser())
    }

    def postCreate() {
        return customProcessor(vehicleDefinitionService.create())
    }

    def postUpdate() {
        return customProcessor(vehicleDefinitionService.update())
    }

    def deleteDelete() {
        return customProcessor(vehicleDefinitionService.delete())
    }

    def getCount(){
        return count(vehicleDefinitionService.count())
    }
}
