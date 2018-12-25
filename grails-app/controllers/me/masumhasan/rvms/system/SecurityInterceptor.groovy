package me.masumhasan.rvms.system


import com.hmtmcse.gs.GsConfigHolder
import com.hmtmcse.gs.data.GsApiResponseData
import grails.converters.JSON
import me.masumhasan.rvms.AuthenticationService


class SecurityInterceptor {

    AuthenticationService authenticationService

    SecurityInterceptor() {
        matchAll()
                .excludes(controller: "apiAuthenticationV1")
                .excludes(controller: "reactJs")
    }

    boolean before() {
        if (!authenticationService.isAuthenticated()) {
            if (controllerName.startsWith(GsConfigHolder.controllerStartWithDefault.toLowerCase())){
                response.status = 401
                render(GsApiResponseData.failed("Unauthorized Access").toMap() as JSON)
            }else{
                redirect(controller: "reactJs", action:"index")
            }
            return false
        }
        return true
    }


    boolean after() { true }

    void afterView() {
        // no-op
    }
}
