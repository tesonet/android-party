package com.baruckis.androidparty.local.preferences

/**
 * Storage for app and user preferences.
 */
interface PreferenceStorage {
    var token: String?
    var username: String?
}