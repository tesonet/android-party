package lt.havefun.tesonetfunparty

import lt.havefun.tesonetfunparty.data.IPreferencesManager

class MockedPreferencesManager: IPreferencesManager {
    override fun saveToken(token: String?) {
    }

    override fun getToken(): String? {
        return "test"
    }
}