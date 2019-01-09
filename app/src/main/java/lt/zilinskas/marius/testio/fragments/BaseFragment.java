package lt.zilinskas.marius.testio.fragments;

import android.content.Context;
import android.support.v4.app.Fragment;

import lt.zilinskas.marius.testio.TestioApplication;
import lt.zilinskas.marius.testio.activities.BaseActivity;

public class BaseFragment extends Fragment {

    protected TestioApplication testioApplication;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        testioApplication = getBaseActivity().getTestioApplication();
    }

    protected BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
    }
}
