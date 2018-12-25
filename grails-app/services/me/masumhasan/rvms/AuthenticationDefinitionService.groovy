package me.masumhasan.rvms

import com.hmtmcse.gs.GsApiActionDefinition
import com.hmtmcse.gs.data.ApiHelper
import com.hmtmcse.gs.data.GsApiResponseData
import com.hmtmcse.gs.data.GsParamsPairData
import com.hmtmcse.gs.model.CustomProcessor


class AuthenticationDefinitionService {

    AuthenticationService authenticationService

    GsApiActionDefinition login() {
        GsApiActionDefinition gsApiActionDefinition = new GsApiActionDefinition<User>(User)
        gsApiActionDefinition.addRequestProperty("email").required()
        gsApiActionDefinition.addRequestProperty("password").required()
        gsApiActionDefinition.customProcessor = new CustomProcessor() {
            @Override
            GsApiResponseData process(GsApiActionDefinition actionDefinition, GsParamsPairData paramData, ApiHelper apiHelper) {
                def response = authenticationService.doLogin(paramData.params.email, paramData.params.password)
                return apiHelper.help.responseToApi(actionDefinition, response)
            }
        }
        gsApiActionDefinition.includeAllThenExcludeFromResponse(["password", "dateCreated", "lastUpdated", "version", "userRole", "address", "NID", "drivingLicense", "birthDate"])
        gsApiActionDefinition.includeInResponse(["userRoleId"])
        gsApiActionDefinition.successResponseAsData()
        gsApiActionDefinition.failedResponseFormat = GsApiResponseData.failed("Invalid email or password !!")
        return gsApiActionDefinition
    }


    GsApiActionDefinition logout() {
        GsApiActionDefinition gsApiActionDefinition = new GsApiActionDefinition<User>(User)
        gsApiActionDefinition.customProcessor = new CustomProcessor() {
            @Override
            GsApiResponseData process(GsApiActionDefinition actionDefinition, GsParamsPairData paramData, ApiHelper apiHelper) {
                AppUtil.invalidateSession()
                return apiHelper.help.responseMessageToApi(actionDefinition, true)
            }
        }
        gsApiActionDefinition.successResponseFormat = GsApiResponseData.successMessage("Logout Success!!")
        gsApiActionDefinition.failedResponseFormat = GsApiResponseData.failed("Logout Failed!!")
        return gsApiActionDefinition
    }

    GsApiActionDefinition isSessionExist() {
        GsApiActionDefinition gsApiActionDefinition = new GsApiActionDefinition<User>(User)
        gsApiActionDefinition.customProcessor = new CustomProcessor() {
            @Override
            GsApiResponseData process(GsApiActionDefinition actionDefinition, GsParamsPairData paramData, ApiHelper apiHelper) {
                return GsApiResponseData.successMessage("Session Exists")
            }
        }
        gsApiActionDefinition.failedResponseFormat = GsApiResponseData.failed("Session Expired!!")
        return gsApiActionDefinition
    }

}
