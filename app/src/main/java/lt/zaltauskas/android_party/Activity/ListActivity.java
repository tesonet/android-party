package lt.zaltauskas.android_party.Activity;

import android.os.Bundle;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import lt.zaltauskas.android_party.Model.Server;
import lt.zaltauskas.android_party.R;
import lt.zaltauskas.android_party.Adapter.ServerAdapter;

public class ListActivity extends AppCompatActivity {

    public static final String KEY_SERVER_LIST = "server_list";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        ImageView logout = findViewById(R.id.button_logout);
        List<Server> value = (ArrayList<Server>) getIntent().getSerializableExtra(KEY_SERVER_LIST);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        ServerAdapter adapter = new ServerAdapter();
        DividerItemDecoration itemDecorator = new DividerItemDecoration(getBaseContext(), DividerItemDecoration.VERTICAL);
        adapter.setItems(value);
        itemDecorator.setDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.content_divider, null));
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(itemDecorator);
        recyclerView.setAdapter(adapter);
        logout.setOnClickListener(view -> finish());
    }
}
