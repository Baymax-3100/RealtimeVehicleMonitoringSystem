package me.masumhasan.rvms.controllers.api

import com.hmtmcse.gs.GsRestProcessor
import me.masumhasan.rvms.RouteDefinitionService

class ApiRouteV1Controller extends GsRestProcessor {

    RouteDefinitionService routeDefinitionService

    def getList() {
        return list(routeDefinitionService.list())
    }

    def getDetails() {
        return customProcessor(routeDefinitionService.details())
    }

    def postCreate() {
        return create(routeDefinitionService.create())
    }

    def postUpdate() {
        return customProcessor(routeDefinitionService.update())
    }

    def deleteDelete() {
        return delete(routeDefinitionService.delete())
    }

    def getCount(){
        return count(routeDefinitionService.count())
    }
}
