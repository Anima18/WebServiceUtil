package com.example.chirs.rxsimpledemo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.chirs.rxsimpledemo.entity.DataObject;
import com.example.chirs.rxsimpledemo.entity.User;
import com.example.requestmanager.NetworkRequest;
import com.google.gson.reflect.TypeToken;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

;

/**
 * Created by jianjianhong on 2016/6/12.
 */
public class GetNestedDataActivity extends BaseActivity implements View.OnClickListener {

    private Button searchBt;
    private TextView resultTv;
    private Subscription subscription;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_object);
        initView();
        initEvent();
    }

    private void initView() {
        searchBt = (Button)findViewById(R.id.goAct_bt);
        resultTv = (TextView)findViewById(R.id.goAct_result);
    }

    public void initEvent() {
        searchBt.setOnClickListener(this);

        progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                NetworkRequest.cancel(subscription);
                //Toast.makeText(GetNestedDataActivity.this, "请求结束", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.goAct_bt:
                subscription = getObjectData2();
                break;
        }
    }

    private Subscription getObjectData2() {
        resultTv.setText("");
        showProgress("正在查询...");

        /*return NetworkRequest.create()
                .setUrl("http://192.168.1.103:8080/webService/userInfo/getAllUserInfo.action")
                .setMethod(Service.GET_TYPE)
                .setDataType(new TypeToken<DataObject<User>>() {
                }.getType())
                .request()
                .flatMap(new Func1<DataObject<User>, Observable<?>>() {
                    @Override
                    public Observable<?> call(DataObject<User> o) {
                        Log.i("WebService", "嵌套请求一成功");
                        Log.i("WebService", o.data.rows.get(0).toString());
                        return NetworkRequest.create().setUrl("http://192.168.1.103:8080/webService/userInfo/getAllUserInfo.action")
                                .setMethod(Service.GET_TYPE)
                                .setDataType(new TypeToken<DataObject<User>>() {
                                }.getType())
                                .request();
                    }
                })
                .flatMap(new Func1<DataObject<User>, Observable<?>>() {
                    @Override
                    public Observable<?> call(DataObject<User> o) {
                        Log.i("WebService", "嵌套请求二成功");
                        Log.i("WebService", o.data.rows.get(0).toString());
                        return NetworkRequest.create().setUrl("http://192.168.1.103:8080/webService/userInfo/getAllUserInfo.action")
                                .setMethod(Service.GET_TYPE)
                                .setDataType(new TypeToken<DataObject<User>>() {
                                }.getType())
                                .request();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber() {
                    @Override
                    public void onCompleted() {
                        Log.i("WebService", "onCompleted");
                        hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("WebService", "onError");
                    }

                    @Override
                    public void onNext(Object o) {
                        Log.i("WebService", "最后请求成功");
                        resultTv.setText("嵌套请求成功");
                        DataObject<User> data = (DataObject<User>) o;
                        Log.i("WebService", data.data.rows.get(0).toString());
                    }
                });*/

        Subscription subscription = new NetworkRequest.Builder()
                .url(BASE_PATH + "userInfo/getAllUserInfo.action")
                .method(NetworkRequest.GET_TYPE)
                .dataType(new TypeToken<DataObject<User>>() {}.getType())
                .request()
                .flatMap(new Func1<DataObject<User>, Observable<?>>() {
                    @Override
                    public Observable<?> call(DataObject<User> o) {
                        Log.i("WebService", "嵌套请求一成功");
                        Log.i("WebService", o.data.rows.get(0).toString());
                        return new NetworkRequest.Builder()
                                .url(BASE_PATH + "userInfo/getAllUserInfo.action")
                                .method(NetworkRequest.GET_TYPE)
                                .dataType(new TypeToken<DataObject<User>>() {}.getType())
                                .request();
                    }
                })
                .flatMap(new Func1<DataObject<User>, Observable<?>>() {
                    @Override
                    public Observable<?> call(DataObject<User> o) {
                        Log.i("WebService", "嵌套请求二成功");
                        Log.i("WebService", o.data.rows.get(0).toString());
                        return new NetworkRequest.Builder()
                                .url(BASE_PATH + "userInfo/getAllUserInfo.action")
                                .method(NetworkRequest.GET_TYPE)
                                .dataType(new TypeToken<DataObject<User>>() {}.getType())
                                .request();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DataObject<User>>() {
                    @Override
                    public void onCompleted() {
                        hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("WebService", e.getMessage());
                    }

                    @Override
                    public void onNext(DataObject<User> o) {
                        resultTv.setText("嵌套请求成功");
                        Log.i("WebService", "最后请求成功");
                        Log.i("WebService", o.data.rows.get(0).toString());
                    }
                });
        return subscription;
    }
}
