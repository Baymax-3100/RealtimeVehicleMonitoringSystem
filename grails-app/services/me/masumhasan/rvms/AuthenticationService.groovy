package me.masumhasan.rvms


class AuthenticationService {

    boolean isAuthenticated(){
        def authorization = AppUtil.getAppSession()[GraConstant.AUTHORIZED]
        if (authorization && authorization.isLoggedIn){
            return true
        }
        return false
    }

    def doLogin(String email, String password){
        password = password.encodeAsMD5()
        User user = User.findByEmailAndPassword(email, password)
        if (user){
            setUserAuthorization(user)
            user.insert(["role":user.userRole.role])
            return user
        }
        return null
    }


    def setUserAuthorization(User user) {
        def authorization = [isLoggedIn: true, user: user]
        AppUtil.getAppSession()[GraConstant.AUTHORIZED] = authorization
    }
}
