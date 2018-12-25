package me.masumhasan.rvms

import com.hmtmcse.gs.GsApiActionDefinition
import com.hmtmcse.gs.data.ApiHelper
import com.hmtmcse.gs.data.GsApiResponseData
import com.hmtmcse.gs.data.GsParamsPairData
import com.hmtmcse.gs.model.CustomProcessor

class VehicleDefinitionService {


    GsApiActionDefinition list(){
        GsApiActionDefinition gsApiActionDefinition = new GsApiActionDefinition<Vehicle>(Vehicle)
        gsApiActionDefinition.includeAllPropertyToResponse()
        gsApiActionDefinition.successResponseAsData()
        return gsApiActionDefinition
    }

    GsApiActionDefinition details(){
        GsApiActionDefinition definition = new GsApiActionDefinition<Vehicle>(Vehicle)
        definition.addRequestProperty("id")
        definition.customProcessor = new CustomProcessor() {
            @Override
            GsApiResponseData process(GsApiActionDefinition actionDefinition, GsParamsPairData paramData, ApiHelper apiHelper) {
                Vehicle Vehicle = Vehicle.findById(paramData.params.id)
                if (Vehicle){

                    return apiHelper.help.responseToApi(actionDefinition, Vehicle)
                }
                return GsApiResponseData.failed("Unable to fetch Vehicle")
            }
        }
        definition.includeAllThenExcludeFromResponse(["version"])
        definition.successResponseAsData()
        return  definition
    }

    GsApiActionDefinition detailsByUser(){
        GsApiActionDefinition definition = new GsApiActionDefinition<Vehicle>(Vehicle)
        definition.addRequestProperty("id")
        definition.customProcessor = new CustomProcessor() {
            @Override
            GsApiResponseData process(GsApiActionDefinition actionDefinition, GsParamsPairData paramData, ApiHelper apiHelper) {
                Vehicle Vehicle = Vehicle.findByUser(User.findById(paramData.params.id))
                if (Vehicle){
                    return apiHelper.help.responseToApi(actionDefinition, Vehicle)
                }
                return GsApiResponseData.failed("Unable to fetch Vehicle")
            }
        }
        definition.includeAllThenExcludeFromResponse(["version"])
        definition.successResponseAsData()
        return  definition
    }

    GsApiActionDefinition create(){
        GsApiActionDefinition definition = new GsApiActionDefinition<Vehicle>(Vehicle)
        definition.includeAllThenExcludeFromRequest(["version"])
        definition.customProcessor = new CustomProcessor() {
            @Override
            GsApiResponseData process(GsApiActionDefinition actionDefinition, GsParamsPairData paramData, ApiHelper apiHelper) {
                Vehicle vehicle = new Vehicle()
                vehicle.vehicleNo = paramData.params.vehicleNo
                vehicle.route = Route.findById(paramData.params.route.id)
                vehicle.user = User.findById(paramData.params.user.id)
                vehicle.save(flush: true)
                return GsApiResponseData.successMessage("Vehicle saved successfully")
            }
        }
        definition.includeAllThenExcludeFromResponse(["version"])
        definition.successResponseAsData()
        return  definition
    }


    GsApiActionDefinition update(){
        GsApiActionDefinition definition = new GsApiActionDefinition<Vehicle>(Vehicle)
        definition.includeAllThenExcludeFromRequest(["version"])
        definition.customProcessor = new CustomProcessor() {
            @Override
            GsApiResponseData process(GsApiActionDefinition actionDefinition, GsParamsPairData paramData, ApiHelper apiHelper) {
                Vehicle vehicle = Vehicle.findById(paramData.params.id)
                if (vehicle){
                    vehicle.vehicleNo = paramData.params.vehicleNo ? paramData.params.vehicleNo : vehicle.vehicleNo

                    if (paramData.params.route.id && !paramData.params.route.id == null){
                        vehicle.route = Route.findById(paramData.params.route.id)
                    }
                    if (paramData.params.user.id && !paramData.params.user.id == null){
                        vehicle.user = User.findById(paramData.params.user.id)
                    }
                    vehicle.save(flush: true)
                    return GsApiResponseData.successMessage("Vehicle updated successfully")
                }
                return GsApiResponseData.failed("Unable to update Vehicle")
            }
        }
        definition.includeAllThenExcludeFromResponse(["version"])
        definition.successResponseAsData()
        return  definition
    }

    GsApiActionDefinition delete(){
        GsApiActionDefinition definition = new GsApiActionDefinition<Vehicle>(Vehicle)
        definition.addRequestProperty("id")
        definition.customProcessor = new CustomProcessor() {
            @Override
            GsApiResponseData process(GsApiActionDefinition actionDefinition, GsParamsPairData paramData, ApiHelper apiHelper) {
                Vehicle vehicle = Vehicle.findById(paramData.params.id)
                if (vehicle){
                    vehicle.delete(flush: true)
                    return GsApiResponseData.successMessage("Vehicle delete successfully")
                }
                return GsApiResponseData.failed("Unable to delete vehicle")
            }
        }
        definition.includeAllThenExcludeFromResponse(["version"])
        definition.successResponseAsData()
        return  definition
    }

    GsApiActionDefinition count(){
        GsApiActionDefinition definition = new GsApiActionDefinition<Vehicle>(Vehicle)
        return  definition
    }

}
