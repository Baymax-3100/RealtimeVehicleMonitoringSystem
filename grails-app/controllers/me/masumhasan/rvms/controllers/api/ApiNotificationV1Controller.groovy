package me.masumhasan.rvms.controllers.api

import com.hmtmcse.gs.GsRestProcessor
import me.masumhasan.rvms.NotificationDefinitionService

class ApiNotificationV1Controller extends GsRestProcessor{
    NotificationDefinitionService notificationDefinitionService

    def getList() {
        return list(notificationDefinitionService.list())
    }

    def postCreate() {
        return create(notificationDefinitionService.create())
    }
}
