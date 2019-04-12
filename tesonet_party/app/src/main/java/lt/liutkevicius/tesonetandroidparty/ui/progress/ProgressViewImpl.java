package lt.liutkevicius.tesonetandroidparty.ui.progress;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import butterknife.BindView;
import lt.liutkevicius.tesonetandroidparty.R;
import lt.liutkevicius.tesonetandroidparty.ui.base.BaseView;

public class ProgressViewImpl extends BaseView {

    @BindView(R.id.loading_text)
    AppCompatTextView mLoadingText;

    private String loadingText;

    public ProgressViewImpl(String loadingText) {
        this.loadingText = loadingText;
    }

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.screen_progress, container, false);
    }

    @Override
    protected void onAttach(@NonNull View view) {
        super.onAttach(view);
        mLoadingText.setText(loadingText);
    }

    public void setLoadingText(String text) {
        this.loadingText = text;
        mLoadingText.setText(text);
    }

    @Override
    public void doInjection() {
    }
}
