package com.bestar.network;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.net.app.interfaces.ErrorResponse;
import com.net.app.interfaces.RequestInterface;
import com.net.app.request.RequestManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.testRequestBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getListData(20);
            }
        });

    }

    public void getListData(int startIndex) {
        GetProjectDataRequest request = new GetProjectDataRequest(this);
        request.setCityId("1111");
        request.setUserId("2222");
            request.setIndustryId("ssss");
        request.setPage(startIndex);
        request.setPageSize(2);
        request.setType(2);
        request.setCategory(3);
        request.setLevel(4);
        request.setVersion("V4");
        RequestManager.builder()
                .requestByGet(request);

    }
}
