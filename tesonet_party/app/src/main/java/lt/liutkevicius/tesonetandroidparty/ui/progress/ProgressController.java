package lt.liutkevicius.tesonetandroidparty.ui.progress;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import com.bluelinelabs.conductor.RouterTransaction;
import lt.liutkevicius.tesonetandroidparty.R;
import lt.liutkevicius.tesonetandroidparty.ui.base.BaseController;
import lt.liutkevicius.tesonetandroidparty.ui.servers.ServersController;

public class ProgressController extends BaseController {

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.screen_progress, container, false);
    }

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);
        Log.d("hereeeeeeee", "askdfjlkasdjflksdjf");
        final Handler handler = new Handler();
        handler.postDelayed(() -> getRouter().pushController(RouterTransaction.with(new ServersController())), 1500);
    }
}
