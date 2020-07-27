package lt.havefun.tesonetfunparty.data

interface IPreferencesManager {
    fun saveToken(token: String?)
    fun getToken(): String?
}