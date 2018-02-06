package aurimas.garuolis.tesonetparty.data;

import android.provider.BaseColumns;

/**
 * Created by Aurimas on 2018.02.05.
 */

public final class PartyContract {
    public static abstract class ServerEntry implements BaseColumns {
        public final static String TABLE_NAME   = "servers";
        public final static String COL_NAME     = "server";
        public final static String COL_DISTANCE = "distance";
    }
}
