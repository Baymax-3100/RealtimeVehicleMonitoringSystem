package me.masumhasan.rvms

import com.hmtmcse.gs.GsApiActionDefinition
import com.hmtmcse.gs.data.ApiHelper
import com.hmtmcse.gs.data.GsApiResponseData
import com.hmtmcse.gs.data.GsParamsPairData
import com.hmtmcse.gs.model.CustomProcessor

class CurrentLocationDefinitionService {

    GsApiActionDefinition details(){
        GsApiActionDefinition definition = new GsApiActionDefinition<CurrentLocation>(CurrentLocation)
        definition.addRequestProperty("id")
        definition.customProcessor = new CustomProcessor() {
            @Override
            GsApiResponseData process(GsApiActionDefinition actionDefinition, GsParamsPairData paramData, ApiHelper apiHelper) {
                def currentLocation = CurrentLocation.findAllByVehicle(Vehicle.findById(paramData.params.id))
                if (currentLocation){
                    return apiHelper.help.responseToApi(actionDefinition, currentLocation)
                }
                return GsApiResponseData.failed("Unable to fetch CurrentLocation")
            }
        }
        definition.includeAllThenExcludeFromResponse(["version"])
        definition.successResponseAsData()
        return  definition
    }

    GsApiActionDefinition update(){
        GsApiActionDefinition definition = new GsApiActionDefinition<CurrentLocation>(CurrentLocation)
        definition.includeAllThenExcludeFromRequest(["version","lastUpdated"])
        definition.customProcessor = new CustomProcessor() {
            @Override
            GsApiResponseData process(GsApiActionDefinition actionDefinition, GsParamsPairData paramData, ApiHelper apiHelper) {
                CurrentLocation currentLocation = CurrentLocation.findByVehicle(Vehicle.findById(paramData.params.vehicle.id))?:new CurrentLocation(vehicle: Vehicle.findById(paramData.params.vehicle.id))
                if (currentLocation){
                    currentLocation.latitude = paramData.params.latitude
                    currentLocation.longitude = paramData.params.longitude
                    currentLocation.save(flush: true)

                    LocationHistory locationHistory = new LocationHistory()
                    locationHistory.longitude = paramData.params.longitude
                    locationHistory.latitude = paramData.params.latitude
                    locationHistory.vehicle = Vehicle.findById(paramData.params.vehicle.id)
                    locationHistory.save(flush: true)
                    return GsApiResponseData.successMessage("CurrentLocation updated successfully")
                }
                return GsApiResponseData.failed("Unable to update CurrentLocation")
            }
        }
        definition.includeAllThenExcludeFromResponse(["version"])
        definition.successResponseAsData()
        return  definition
    }

    GsApiActionDefinition count(){
        GsApiActionDefinition definition = new GsApiActionDefinition<CurrentLocation>(CurrentLocation)
        return  definition
    }
}
