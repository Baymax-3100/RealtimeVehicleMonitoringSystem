package me.masumhasan.rvms


class UserService {

    def isEmailExist(String email, Integer userId = null) {
        User user = User.findByEmail(email)
        if (user){
            if(user.id == userId){
                return false
            }
            return true
        }
        return false
    }
}
