package com.wuxianedu.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "--Main--";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.but_id_1).setOnClickListener(this);
        findViewById(R.id.but_id_2).setOnClickListener(this);
        findViewById(R.id.but_id_3).setOnClickListener(this);
        findViewById(R.id.but_id_4).setOnClickListener(this);
        findViewById(R.id.but_id_5).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.but_id_1:
                syncGet();
                break;
            case R.id.but_id_2:
                asyncGet();
                break;
            case R.id.but_id_3:
                syncPost();
                break;
            case R.id.but_id_4:
                asyncPost();
                break;
            case R.id.but_id_5:
                postCommitJson();
                break;

        }
    }

    /**
     * 同步get
     */
    private void syncGet(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "http://192.168.20.96:8080/WxhlServer/LoginServlet?name=%E5%BC%A0%E4%B8%89&pwd=123456&age=12&flag=true";
                //创建okHttpClient对象
                OkHttpClient okHttpClient = new OkHttpClient();
                //创建一个Request
                Request request = new Request.Builder().url(url).build();

                //方式一：在当前线程中发起请求
                 try {
                 Response response = okHttpClient.newCall(request).execute();
                     //判断是否成功
                     if(response.isSuccessful()){
                        Log.e(TAG,response.body().string());
                     }
                 } catch (IOException e) {
                     e.printStackTrace();
                 } finally {
                 }
            }
        }).start();
    }

    /**
     * 异步 get
     */
    private void asyncGet(){
        String url = "http://192.168.20.96:8080/WxhlServer/LoginServlet?name=%E5%BC%A0%E4%B8%89&pwd=123456&age=12&flag=true";
        //创建okHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        //创建一个Request
        Request request = new Request.Builder().url(url).build();
        //方式二：自动开启一个线程来发起网络请求
        //new call
        Call call = okHttpClient.newCall(request);
        //请求加入调度
        /**
         *
         以上就是发送一个get请求的步骤，首先构造一个Request对象，参数最起码有个url，当然你可以通过Request.Builder设置更多的参数比如：header、method等。
         然后通过request的对象去构造得到一个Call对象，类似于将你的请求封装成了任务，既然是任务，就会有execute()和cancel()等方法。
         最后，我们希望以异步的方式去执行请求，所以我们调用的是call.enqueue，将call加入调度队列，然后等待任务执行完成，我们在Callback中即可得到结果。
         */
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                if(response.isSuccessful()){
                    /**
                     onResponse回调的参数是response，一般情况下，
                     1: 比如我们希望获得返回的字符串，可以通过response.body().string()获取；
                     2: 如果希望获得返回的二进制字节数组，则调用response.body().bytes()；
                     3: 如果你想拿到返回的inputStream，则调用response.body().byteStream(), 这里支持大文件下载，有inputStream我们就可以通过IO的方式写文件。
                     */
                    String message =  response.body().string();
                    Log.e(TAG,message);

                }

                //此方法是非UI线程方法, 如果你希望操作控件，还是需要使用handler等，
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),"完成",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    /**
     * 同步 post POST提交键值对 ， 很多时候我们会需要通过POST方式把键值对数据传送到服务器
     */
    private void syncPost(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();

                //添加post请求参数
                RequestBody formBody = new FormEncodingBuilder()
                        .add("name", "android")
                        .add("pwd", "bug")
                        .add("age", "12")
                        .add("flag", "true")
                        .build();
                //构造请求对象
                Request request = new Request.Builder()
                        .url("http://192.168.20.96:8080/WxhlServer/LoginServlet")
                        .post(formBody).build();
                try {
                    //在当前线程中发起请求
                    Response response = okHttpClient.newCall(request).execute();
                    //判断是否成功
                    if(response.isSuccessful()){
                        Log.e(TAG,response.body().string());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                }
            }
        }).start();
    }

    /**
     * 异步post， POST提交键值对 ， 很多时候我们会需要通过POST方式把键值对数据传送到服务器
     */
    private void asyncPost(){
        OkHttpClient okHttpClient = new OkHttpClient();

        //添加post请求参数
        RequestBody formBody = new FormEncodingBuilder()
                .add("name", "android")
                .add("pwd", "bug")
                .add("age", "12")
                .add("flag", "true")
                .build();
        //构造请求对象
        Request request = new Request.Builder()
                .url("http://192.168.20.96:8080/WxhlServer/LoginServlet")
                .post(formBody).build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                if(response.isSuccessful()){
                    Log.e(TAG,response.body().string());
                }
            }
        });
    }

    public static final MediaType MEDIA_TYPE_TYPE = MediaType.parse("application/json; charset=utf-8");
    /**
     * POST提交Json数据
     */
    private void postCommitJson(){
        Member member = new Member();
        member.setName("张三");
        member.setAge(88);
        member.setFlag(true);
        member.setPwd("android——json");
        String json = JSON.toJSONString(member);
        Log.e(TAG,"json---->"+json);

        OkHttpClient client = new OkHttpClient();
        //设置请求的Body类型
        RequestBody requestBody =RequestBody.create(MEDIA_TYPE_TYPE,json);

        Request request = new Request.Builder()
                .url("http://192.168.20.96:8080/WxhlServer/LoginServlet")
                .post(requestBody)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (response.isSuccessful()){
                    Log.e(TAG,response.body().string());
                }
            }
        });
    }

}
