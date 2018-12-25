package me.masumhasan.rvms.controllers.api


import com.hmtmcse.gs.GsRestProcessor
import me.masumhasan.rvms.UserDefinitionService

class ApiUserV1Controller extends GsRestProcessor {

    UserDefinitionService userDefinitionService

    def getList() {
        return list(userDefinitionService.list())
    }

    def postList() {
        return customProcessor(userDefinitionService.customList())
    }

    def getDetails() {
        return details(userDefinitionService.details())
    }

    def postCreate() {
        return create(userDefinitionService.create())
    }

    def postUpdate() {
        return customProcessor(userDefinitionService.update())
    }

    def deleteDelete() {
        return delete(userDefinitionService.delete())
    }


}
