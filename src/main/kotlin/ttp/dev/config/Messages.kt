package ttp.dev.config

const val USER_ID_ALREADY_REGISTERED = "Tên đăng nhập bị trùng"
const val USER_REGISTRATION_SUCCESS = "Đăng kí thành công"
const val USER_LOGIN_SUCCESS = "Đăng nhập thành công"
const val USER_LOGIN_FAILURE = "Đăng nhập thất bại "
const val GENERIC_ERROR = "Some error occurred! Please try again later"
const val INVALID_AUTHENTICATION_TOKEN = "Invalid authentication token, please login again"

const val SUCCESS = "SUCCESS"

const val UPDATE_FAILURE = "Đã xảy ra lỗi trong quá trinh cập nhật, vui lòng thử lại sau"
const val DELETE_FAILURE = "Đã xảy ra lỗi trong quá trinh xóa, vui lòng thử lại sau"



object DeleteUserErrorCodes {
    const val SUCCESS = 0
    const val DELETE_ERR = 1
    const val INVALID_USER = 2
}

object UserExist{
    const val EXIST = "User đã tồn tại"
    const val NOT_EXIST = "chưa tồn tại"
}
