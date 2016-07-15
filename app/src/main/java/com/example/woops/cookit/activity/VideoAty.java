package com.example.woops.cookit.activity;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.woops.cookit.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class VideoAty extends AppCompatActivity {

    private VideoView vv_video;
    private MediaController mController;

    private List<Integer> mVideoList;

    private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_aty);

        mVideoList = new ArrayList<Integer>();

        mVideoList.add(R.raw.video);
        mVideoList.add(R.raw.video2);
        mVideoList.add(R.raw.video3);
        mVideoList.add(R.raw.video4);



        vv_video = (VideoView) findViewById(R.id.vv_video);
        String uri = "android.resource://" + getPackageName() + "/" + mVideoList.get(index);
        vv_video.setVideoURI(Uri.parse(uri));
        vv_video.start();


        Thread thread = new MyReceiveGestureThread();
        thread.start();
    }


    /**
     * 菜单、返回键响应
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            exitBy2Click();        //调用双击退出函数
        }
        return false;
    }
    /**
     * 双击退出函数
     */
    private static Boolean isExit = false;

    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            finish();
            System.exit(0);
        }
    }
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:

                    index--;
                    if (index < 0){
                        index = 3;
                        String uri = "android.resource://" + getPackageName() + "/" + mVideoList.get(index);
                        vv_video.setVideoURI(Uri.parse(uri));
                        vv_video.start();
                    }
                    String uri = "android.resource://" + getPackageName() + "/" + mVideoList.get(index);
                    vv_video.setVideoURI(Uri.parse(uri));
                    vv_video.start();

                    break;
                case 1:
                    //下一个
                    index++;
                    if (index > 3){
                        index = 0;
                        String uri2 = "android.resource://" + getPackageName() + "/" + mVideoList.get(index);
                        vv_video.setVideoURI(Uri.parse(uri2));
                        vv_video.start();
                    }
                    String uri2 = "android.resource://" + getPackageName() + "/" + mVideoList.get(index);
                    vv_video.setVideoURI(Uri.parse(uri2));
                    vv_video.start();

                    break;



            }
            super.handleMessage(msg);
        }
    };


    class MyReceiveGestureThread extends Thread {

        public void run() {


            while (true) {
                try {
                    ServerSocket serverSocket = new ServerSocket(8877);
                    Socket socket = serverSocket.accept();
                    Log.d("Jay", "连接成功");
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
                    String inputLine = in.readLine();
                    Log.d("Jay", "inputLine is :" + inputLine);

                    if (inputLine.equals("1")) {
                       handler.sendEmptyMessage(1);
                    }else {
                        handler.sendEmptyMessage(0);

                    }
                    in.close();
                    socket.close();
                    serverSocket.close();
                    Log.d("Jay", "连接已关闭");

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

        }


    }


}
