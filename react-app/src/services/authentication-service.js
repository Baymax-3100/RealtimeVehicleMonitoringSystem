import {RaUrlUtil} from "../artifacts/ra-url-util";
import {AppConstant} from "../app/app-constant";
import RaBrowserStorage from "../artifacts/ra-browser-storage";
import RaHttpUtil from "../artifacts/ra-http-util";
import {ApiURL} from "../app/api-url";

const login = (loginData) => {
    if (loginData !== undefined && loginData instanceof Object){
        loginData.sessionExpire = (+ new Date());
    }
    RaBrowserStorage.addAsJSONStringInSession(AppConstant.USER_AUTH_INFO, loginData);
};


const logout = () => {
    RaHttpUtil.getRequest(ApiURL.BaseURL + ApiURL.Logout, success =>{
        RaUrlUtil.redirectTo(AppConstant.loginUrl);
    });
    RaBrowserStorage.clearSession();
};

const isAuthenticated = () => {
    let loginData = RaBrowserStorage.getAsJSONFromSession(AppConstant.USER_AUTH_INFO);
    if (!loginData){
        return false;
    }
    let checkToApiMilliseconds = 900000; //15m
    let currentTimestamp = (+ new Date());
    if (checkToApiMilliseconds < (currentTimestamp - loginData.sessionExpire)){
        RaHttpUtil.getRequest(ApiURL.BaseURL + ApiURL.isSessionExist, success =>{
            login(loginData)
        },failed =>{
            logout();
        })
    }
    return true
};


export const AuthenticationService = {
    login,
    logout,
    isAuthenticated,
};