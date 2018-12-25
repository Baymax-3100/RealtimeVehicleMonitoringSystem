package me.masumhasan.rvms

import com.hmtmcse.gs.GsApiActionDefinition
import com.hmtmcse.gs.data.ApiHelper
import com.hmtmcse.gs.data.GsApiResponseData
import com.hmtmcse.gs.data.GsParamsPairData
import com.hmtmcse.gs.model.CustomProcessor

class LocationHistoryDefinitionService {

    GsApiActionDefinition details(){
        GsApiActionDefinition definition = new GsApiActionDefinition<LocationHistory>(LocationHistory)
        definition.addRequestProperty("id")
        definition.customProcessor = new CustomProcessor() {
            @Override
            GsApiResponseData process(GsApiActionDefinition actionDefinition, GsParamsPairData paramData, ApiHelper apiHelper) {
                def locationHistory = LocationHistory.findAllByVehicle(Vehicle.findById(paramData.params.id))
                if (locationHistory){
                    return apiHelper.help.responseToApi(actionDefinition, locationHistory)
                }
                return GsApiResponseData.failed("Unable to fetch location history")
            }
        }
        definition.includeAllThenExcludeFromResponse(["version"])
        definition.successResponseAsData()
        return  definition
    }
}
