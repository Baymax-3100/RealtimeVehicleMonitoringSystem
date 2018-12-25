package me.masumhasan.rvms.controllers.api

import com.hmtmcse.gs.GsRestProcessor
import me.masumhasan.rvms.LocationHistoryDefinitionService

class ApiLocationHistoryV1Controller extends GsRestProcessor {

    LocationHistoryDefinitionService locationHistoryDefinitionService

    def getDetails() {
        return customProcessor(locationHistoryDefinitionService.details())
    }
}
