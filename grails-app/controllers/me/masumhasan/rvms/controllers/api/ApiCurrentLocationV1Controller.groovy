package me.masumhasan.rvms.controllers.api

import com.hmtmcse.gs.GsRestProcessor
import me.masumhasan.rvms.CurrentLocationDefinitionService

class ApiCurrentLocationV1Controller extends GsRestProcessor {

    CurrentLocationDefinitionService currentLocationDefinitionService

    def getDetails() {
        return customProcessor(currentLocationDefinitionService.details())
    }

    def postCreate() {
        return customProcessor(currentLocationDefinitionService.update())
    }

    def postUpdate() {
        return customProcessor(currentLocationDefinitionService.update())
    }

    def getCount(){
        return count(currentLocationDefinitionService.count())
    }
}
