package me.masumhasan.rvms

import com.hmtmcse.gs.GsApiActionDefinition
import com.hmtmcse.gs.GsRequestParamException
import com.hmtmcse.gs.data.ApiHelper
import com.hmtmcse.gs.data.GsApiRequestProperty
import com.hmtmcse.gs.data.GsApiResponseData
import com.hmtmcse.gs.data.GsParamsPairData
import com.hmtmcse.gs.model.CustomProcessor
import com.hmtmcse.gs.model.CustomRequestParamProcessor
import com.sun.org.apache.xpath.internal.operations.And


class UserDefinitionService {

    UserService userService

    GsApiActionDefinition list(){
        GsApiActionDefinition gsApiActionDefinition = new GsApiActionDefinition<User>(User)
        gsApiActionDefinition.includeAllThenExcludeFromResponse(["password", "version"])
        gsApiActionDefinition.successResponseAsData()
        return gsApiActionDefinition

    }

    GsApiActionDefinition create(){
        GsApiActionDefinition gsApiActionDefinition = new GsApiActionDefinition<User>(User)
        gsApiActionDefinition.addRequestProperty("firstName").required()
        gsApiActionDefinition.addRequestProperty("lastName")
        gsApiActionDefinition.addRequestProperty("email").required().customRequestParamProcessor = new CustomRequestParamProcessor() {
            @Override
            Object process(String fieldName, GsParamsPairData gsParamsPairData, GsApiRequestProperty propertyDefinition) throws GsRequestParamException {
                String email = gsParamsPairData.params.email
                if (userService.isEmailExist(email)){
                    throw new GsRequestParamException(email + " Email already exists. Please try with other Email.")
                }
                return email
            }
        }
        gsApiActionDefinition.addRequestProperty("password").required()
        gsApiActionDefinition.addRequestProperty("address")
        gsApiActionDefinition.addRequestProperty("drivingLicense")
        gsApiActionDefinition.addRequestProperty("NID")
        gsApiActionDefinition.addRequestProperty("birthDate")
        gsApiActionDefinition.addRequestProperty("userRole")
        gsApiActionDefinition.addResponseProperty("uuid")
        gsApiActionDefinition.successResponseAsData()
        return gsApiActionDefinition
    }


    GsApiActionDefinition update(){
        GsApiActionDefinition definition = new GsApiActionDefinition<User>(User)
        definition.includeAllThenExcludeFromRequest(["version","NID","dateCreated","lastUpdated"])
        definition.customProcessor = new CustomProcessor() {
            @Override
            GsApiResponseData process(GsApiActionDefinition actionDefinition, GsParamsPairData paramData, ApiHelper apiHelper) {
                User user = User.findById(paramData.params.id)
                if (user){
                    user.firstName = paramData.params.firstName?paramData.params.firstName:user.firstName
                    user.lastName = paramData.params.lastName?paramData.params.lastName:user.lastName
                    if (userService.isEmailExist(paramData.params.email, user.id)){
                        return GsApiResponseData.successMessage("Email already exists. Please try with other Email.")
                    }else if (!paramData.params.email==null){
                        user.email = paramData.params.email
                    }

                    if(paramData.params.userRole.id){
                        user.userRole = UserRole.findById(paramData.params.userRole.id)
                    }
                    if(paramData.params.password){
                        user.password = paramData.params.password
                    }

                    user.address = paramData.params.address?paramData.params.address:user.address
                    user.NID = paramData.params.NID?paramData.params.NID:user.NID
                    user.drivingLicense = paramData.params.drivingLicense?paramData.params.drivingLicense:user.drivingLicense
                    user.birthDate = paramData.params.birthDate?paramData.params.birthDate:user.birthDate
                    user.save(flush: true)

                    return GsApiResponseData.successMessage("User updated successfully")
                }
                return GsApiResponseData.failed("Unable to update user")
            }
        }
        definition.includeAllThenExcludeFromResponse(["version","password"])
        definition.successResponseAsData()
        return  definition
    }

    GsApiActionDefinition details(){
        GsApiActionDefinition definition = new GsApiActionDefinition<User>(User)
        definition.includeInWhereFilter(["id"])
        definition.includeAllThenExcludeFromResponse(["version","password"])
        definition.successResponseAsData()
        return  definition
    }

    GsApiActionDefinition customList(){
        GsApiActionDefinition definition = new GsApiActionDefinition<User>(User)
//        definition.includeInWhereFilter(["userRole"])
        definition.addRequestProperty("userRole")
        definition.customProcessor = new CustomProcessor() {
            @Override
            GsApiResponseData process(GsApiActionDefinition actionDefinition, GsParamsPairData paramData, ApiHelper apiHelper) {
                def user = User.findAllByUserRoleAndUserRole(UserRole.findById(paramData.params.userRole.id),UserRole.findById(paramData.params.userRole.id))
                if (user){

                    return apiHelper.help.responseToApi(actionDefinition, user)
                }
                return GsApiResponseData.failed("Unable to fetch user")
            }
        }
        definition.includeAllThenExcludeFromResponse(["version","password"])
        definition.successResponseAsData()
        return  definition
    }

    GsApiActionDefinition delete(){
        GsApiActionDefinition gsApiActionDefinition = new GsApiActionDefinition<User>(User)
        gsApiActionDefinition.includeInWhereFilter(["id"])
        gsApiActionDefinition.successResponseAsData()
        return gsApiActionDefinition
    }

    GsApiActionDefinition count(){
        GsApiActionDefinition definition = new GsApiActionDefinition<User>(User)
        return  definition
    }
}
