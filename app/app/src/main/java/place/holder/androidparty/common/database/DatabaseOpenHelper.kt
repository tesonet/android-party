package place.holder.androidparty.common.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class DatabaseOpenHelper(context: Context) : ManagedSQLiteOpenHelper(context, DB_NAME, null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(
            TABLE_SERVER, true,
            SERVER_ID to INTEGER + PRIMARY_KEY + UNIQUE,
            SERVER_NAME to TEXT,
            SERVER_DISTANCE to INTEGER
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(TABLE_SERVER)
    }

    companion object {
        private const val DB_NAME = "Servers"
        const val TABLE_SERVER = "Server"
        const val SERVER_ID = "id"
        const val SERVER_NAME = "name"
        const val SERVER_DISTANCE = "distance"

        private var instance: DatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(context: Context): DatabaseOpenHelper {
            if (instance == null) {
                instance = DatabaseOpenHelper(context)
            }
            return instance!!
        }
    }
}

val Context.database: DatabaseOpenHelper
    get() = DatabaseOpenHelper.getInstance(applicationContext)