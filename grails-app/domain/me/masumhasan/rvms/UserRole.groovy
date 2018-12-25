package me.masumhasan.rvms

class UserRole {

    Long id
    String role

    static hasMany = [user: User]

    static constraints = {
    }
}
