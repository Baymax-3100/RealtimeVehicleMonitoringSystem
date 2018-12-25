package me.masumhasan.rvms

import com.hmtmcse.gs.GsApiActionDefinition
import com.hmtmcse.gs.data.ApiHelper
import com.hmtmcse.gs.data.GsApiResponseData
import com.hmtmcse.gs.data.GsParamsPairData
import com.hmtmcse.gs.model.CustomProcessor

class RouteDefinitionService {

    GsApiActionDefinition list(){
        GsApiActionDefinition gsApiActionDefinition = new GsApiActionDefinition<Route>(Route)
        gsApiActionDefinition.includeInResponse(["id","destinationName","startName"])
        gsApiActionDefinition.successResponseAsData()
        return gsApiActionDefinition
    }

    GsApiActionDefinition details(){
        GsApiActionDefinition definition = new GsApiActionDefinition<Route>(Route)
        definition.addRequestProperty("id")
        definition.customProcessor = new CustomProcessor() {
            @Override
            GsApiResponseData process(GsApiActionDefinition actionDefinition, GsParamsPairData paramData, ApiHelper apiHelper) {
                Route route = Route.findById(paramData.params.id)
                if (route){

                    return apiHelper.help.responseToApi(actionDefinition, route)
                }
                return GsApiResponseData.failed("Unable to fetch route")
            }
        }
        definition.includeAllThenExcludeFromResponse(["version"])
        definition.successResponseAsData()
        return  definition
    }

    GsApiActionDefinition create(){
        GsApiActionDefinition gsApiActionDefinition = new GsApiActionDefinition<Route>(Route)
        gsApiActionDefinition.includeAllThenExcludeFromRequest(["id","version"])
        gsApiActionDefinition.successResponseAsData()
        return gsApiActionDefinition

    }


    GsApiActionDefinition update(){
        GsApiActionDefinition definition = new GsApiActionDefinition<Route>(Route)
        definition.includeAllThenExcludeFromRequest(["version"])
        definition.customProcessor = new CustomProcessor() {
            @Override
            GsApiResponseData process(GsApiActionDefinition actionDefinition, GsParamsPairData paramData, ApiHelper apiHelper) {
                Route route = Route.findById(paramData.params.id)
                if (route){
                    route.destinationName = paramData.params.destinationName
                    route.startPoint = paramData.params.startPoint
                    route.startName = paramData.params.startName
                    route.endPoint = paramData.params.endPoint
                    route.wayPoints = paramData.params.wayPoints
                    route.save(flush: true)
                    return GsApiResponseData.successMessage("Route updated successfully")
                }
                return GsApiResponseData.failed("Unable to update route")
            }
        }
        definition.includeAllThenExcludeFromResponse(["version"])
        definition.successResponseAsData()
        return  definition
    }

    GsApiActionDefinition delete(){
        GsApiActionDefinition gsApiActionDefinition = new GsApiActionDefinition<Route>(Route)
        gsApiActionDefinition.includeInWhereFilter(["id"])
        gsApiActionDefinition.successResponseAsData()
        return gsApiActionDefinition
    }

    GsApiActionDefinition count(){
        GsApiActionDefinition definition = new GsApiActionDefinition<Route>(Route)
        return  definition
    }
}
