package lt.liutkevicius.tesonetandroidparty.ui.progress;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;
import butterknife.BindView;
import lt.liutkevicius.tesonetandroidparty.R;
import lt.liutkevicius.tesonetandroidparty.ui.base.BaseController;

public class ProgressController extends BaseController {

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.progress_screen, container, false);
    }
}
