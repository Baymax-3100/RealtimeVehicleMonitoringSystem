package me.masumhasan.rvms.controllers.api


import com.hmtmcse.gs.GsRestProcessor
import me.masumhasan.rvms.AuthenticationDefinitionService

class ApiAuthenticationV1Controller extends GsRestProcessor {

    AuthenticationDefinitionService authenticationDefinitionService

    def postLogin() {
        return customProcessor(authenticationDefinitionService.login())
    }

    def getLogout() {
        return customProcessor(authenticationDefinitionService.logout())
    }

    def getIsSessionExist() {
        return customProcessor(authenticationDefinitionService.isSessionExist())
    }
}
