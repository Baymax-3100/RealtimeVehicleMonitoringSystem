package me.masumhasan.rvms

import com.hmtmcse.gs.GsApiActionDefinition

class NotificationDefinitionService {


    GsApiActionDefinition list(){
        GsApiActionDefinition gsApiActionDefinition = new GsApiActionDefinition<Notification>(Notification)
        gsApiActionDefinition.includeInResponse(["id","message","type"])
        gsApiActionDefinition.successResponseAsData()
        return gsApiActionDefinition
    }

    GsApiActionDefinition create(){
        GsApiActionDefinition gsApiActionDefinition = new GsApiActionDefinition<Notification>(Notification)
        gsApiActionDefinition.addRequestProperty("type").required()
        gsApiActionDefinition.addRequestProperty("message").required()
        gsApiActionDefinition.addRequestProperty("vehicle")
        return gsApiActionDefinition
    }
}
