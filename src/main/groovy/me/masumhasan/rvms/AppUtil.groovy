package me.masumhasan.rvms

import org.grails.web.util.WebUtils

class AppUtil {


    static saveResponse(Boolean isSuccess, def model) {
        return [isSuccess: isSuccess, model: model]
    }

    static getAppSession() {
        return WebUtils.retrieveGrailsWebRequest().session
    }

    static invalidateSession() {
        return appSession.invalidate()
    }

    static uuid(){
        return UUID.randomUUID().toString().toUpperCase()
    }

}
