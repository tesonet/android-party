package lt.bulevicius.tessonetapp.ui.progress;

import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import lt.bulevicius.tessonetapp.R;
import lt.bulevicius.tessonetapp.ui.BaseView;

/**
 * The type Progress view.
 */
public final class ProgressView extends BaseView {

    private String title;

    /**
     * The Progress label.
     */
    @SuppressWarnings("all")
    @BindView(R.id.progressTitleLabel)
    AppCompatTextView progressLabel;

    /**
     * Instantiates a new Progress view.
     *
     * @param title the title
     */
    public ProgressView(String title) {
        this.title = title;
    }

    /**
     * Instantiates a new Progress view. Needs to have empty constructor
     * for conductor api.
     */
    @SuppressWarnings("unused")
    public ProgressView() {
    }

    @Override
    public View getContentView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.view_progress, container, false);
    }

    /**
     * Sets new progress title.
     *
     * @param title the title
     */
    public void setNewProgressTitle(String title) {
        this.title = title;
        setTitle(title);
    }

    @Override
    public void doInject() {

    }

    @Override
    public void doBindViews(View view) {
        ButterKnife.bind(this, view);
        setTitle(title);
    }

    private void setTitle(String title) {
        if(title != null)
            progressLabel.setText(title);
    }
}
