//package com.wuxianedu.demo;
//
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.view.View;
//
//import com.yolanda.nohttp.NoHttp;
//import com.yolanda.nohttp.RequestMethod;
//import com.yolanda.nohttp.rest.OnResponseListener;
//import com.yolanda.nohttp.rest.Request;
//import com.yolanda.nohttp.rest.RequestQueue;
//import com.yolanda.nohttp.rest.Response;
//
//public class MainActivityNoHttp extends AppCompatActivity implements View.OnClickListener {
//
//    private static final String TAG = "--Main--";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//
//        findViewById(R.id.but_id).setOnClickListener(this);
//
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.but_id:
//                get();
//                break;
//
//        }
//
//    }
//
//    private void get(){
//        String url = "http://192.168.20.96:8080/WxhlServer/LoginServlet?name=%E5%BC%A0%E4%B8%89&pwd=123456&age=12&flag=true";
//        Request<String> request =  NoHttp.createStringRequest(url, RequestMethod.GET);
//        //请求队列
//        RequestQueue requestQueue = NoHttp.newRequestQueue();
//        // 如果要指定并发值，传入数字即可：NoHttp.newRequestQueue(3);
//        requestQueue.add(0, request, onResponseListener);
//    }
//
//    private OnResponseListener onResponseListener = new OnResponseListener(){
//
//        @Override
//        public void onStart(int what) {
//            Log.e(TAG,"-----onStart------");
//        }
//
//        @Override
//        public void onSucceed(int what, Response response) {
//            Log.e(TAG,"-----onSucceed------");
//            switch (what){
//                case 0:
//                    Log.e(TAG,response.);
//                    break;
//            }
//
//        }
//
//        @Override
//        public void onFailed(int what, Response response) {
//            Log.e(TAG,"-----onFailed------");
//        }
//
//        @Override
//        public void onFinish(int what) {
//            Log.e(TAG,"-----onFinish------");
//        }
//    };
//}
