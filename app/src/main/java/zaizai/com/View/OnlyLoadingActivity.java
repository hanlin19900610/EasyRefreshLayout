package zaizai.com.View;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ajguan.R;
import com.ajguan.library.EasyRefreshLayout;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import zaizai.com.MyAnimation;
import zaizai.com.SimpleAdapter;

public class OnlyLoadingActivity extends AppCompatActivity {

    private EasyRefreshLayout easyRefreshLayout;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private SimpleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_only_loading);
        initView();
        initListener();
        initData();
    }
    private void initData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("EasyRefreshLayout index :" + i);
        }
        adapter.setNewData(list);
    }

    private void initListener() {
        easyRefreshLayout.setEnablePullToRefresh(false);

        easyRefreshLayout.addEasyEvent(new EasyRefreshLayout.EasyEvent() {
            @Override
            public void onLoadMore() {

                final List<String> list = new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    list.add("this is  new load data >>>>" + new Date().toLocaleString());
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        easyRefreshLayout.closeLoadView();
                        adapter.getData().addAll(list);
                        adapter.notifyDataSetChanged();
                    }
                },500);


            }

            @Override
            public void onRefreshing() {

            }
        });
    }

    private void initView() {
        easyRefreshLayout = (EasyRefreshLayout) findViewById(R.id.easylayout);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new SimpleAdapter();
       // adapter.setDuration(1000);
       // adapter.openLoadAnimation(new MyAnimation());
        recyclerView.setAdapter(adapter);
    }
}
