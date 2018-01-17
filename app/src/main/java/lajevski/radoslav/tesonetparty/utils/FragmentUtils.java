package lajevski.radoslav.tesonetparty.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.widget.Toast;

import lajevski.radoslav.tesonetparty.R;

/**
 * Created by Radoslav on 1/16/2018.
 */

public class FragmentUtils {

    public static final String CLOSE_APP_EXTRA = "close_app";

    // ------------------------ Member variables -----------------

    /**
     * Indicates whether "back pressed" button was pressed once
     */
    private static boolean backToExitPressedOnce;

    /**
     * Whether exiting fragment is allowed
     */
    private static boolean sCanExitFragment = true;



    /**
     * <ul>
     *     Make check before network-related action:
     * </ul>
     * <li>
     *     whether network connection is present,
     * </li>
     * <li>
     *      whether no other downloads or uploads are going on the same time
     * </li>
     * <li>
     *      whether no scanner is running before leaving screen
     * </li>
     * <li>
     *      whether signature is sent before leaving screen
     * </li>
     * @return true if click is allowed, false otherwise
     */
    public static boolean canExitFragment(Context context) {

        boolean shouldExit = sCanExitFragment;

        if (shouldExit) {

            if (!AppNetwork.isNetworkConnectionPresent(context)) {
                // Inform if no network is available

                Toast.makeText(context, "No internet connection", Toast.LENGTH_SHORT).show();

                shouldExit = false;
            }
        }

        return shouldExit;
    }
    /**
     * Exit current fragment on double tap
     * @param context
     */
    public static void BackStackDoubleTapExit(Context context) {
        // If the button has been pressed twice go to the main screen of phone
        if (backToExitPressedOnce) {
            Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            context.startActivity(startMain);

            return;
        }

        backToExitPressedOnce = true;
        Toast.makeText(context, context.getString(R.string.notification_message_exit_app),
                Toast.LENGTH_SHORT).show();

        // 2 seconds delay to reset back pressed counter
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                backToExitPressedOnce = false;
            }

        }, 2000);
    }

    // ------------------------ Getters and setters --------------

    public static void setCanExitFragment(boolean canExitFragment) {
        sCanExitFragment = canExitFragment;
    }
}
