package com.example.broadcast;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.nfc.Tag;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v4.os.IResultReceiver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

//将一个普通的Service转换成远程Service其实非常简单，只需要在注册Service的时候将它的android:process属性指定成:remote


//所有的Activity都可以与Service进行关联，然后可以很方便地操作其中的方法，即使Activity被销毁了，
// 之后只要重新与Service建立关联，就又能够获取到原有的Service中Binder的实例
public class MyService extends Service {//继承Service，创建Service子类

    private boolean serviceRunning = false;
    public static final String TAG = "MyService";


    private MyBinder mBinder = new MyBinder();


    class MyBinder extends Binder {
        public void startDownload() {
            Log.i(TAG, "srartDownload() executed");
        }

        public void getProgress() {
            Log.i(TAG, "getProgress:");
        }

    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "---onCreate()---");

        //让服务以notification的形式，显示在前台，不容易被杀死
        //
        //只需在Service的inCreate里面，构建notification，不是用NotificationManager启动，而是用startForeground来启动即可
        //
        //构造pengdingIntent使前台可以打开原来的activity
        Intent intent = new Intent(this, ServiceMain.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        Notification notify = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher) // 设置状态栏中的小图片，尺寸一般建议在24×24，这个图片同样也是在下拉状态栏中所显示，如果在那里需要更换更大的图片，可以使用setLargeIcon(Bitmap
                // icon)
                .setTicker("Service")// 设置在status

                // bar上显示的提示文字
                .setContentTitle("Notification Title")// 设置在下拉status
                // bar后Activity，本例子中的NotififyMessage的TextView中显示的标题
                .setContentText("This is Service")// TextView中显示的详细内容

                .setContentIntent(pendingIntent) // 关联PendingIntent
                .setNumber(1) // 在TextView的右方显示的数字，可放大图片看，在最右侧。这个number同时也起到一个序列号的左右，如果多个触发多个通知（同一ID），可以指定显示哪一个。
                .build();

        startForeground(1, notify);
//
//        try{
//            Thread.sleep(60000);
//        }catch (InterruptedException e){
//            e.printStackTrace();
//        }


    }


    //    //创建Servoce时调用该方法，只调用一次
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        System.out.println("--onCreate()--");
//        serviceRunning = true;
//        new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//                while (serviceRunning) {
//                    System.out.println("---Service运行中---");
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();
//    }


//    @Override
//    public void onCreate() {
//        super.onCreate();
//        System.out.println("---onCreate()---");
//        serviceRunning = true;
//        new Thread(){
//            @Override
//            public void run() {
//                while (serviceRunning){
//                    System.out.println("---Service运行中---");
//                    try {
//                        sleep(1000);
//                    }catch (InterruptedException e){
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }.start();
//    }


    //每次启动Service时都会调用该方法
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("---onStartCommand");

        return super.onStartCommand(intent, flags, startId);


    }


    //解帮Service调用该方法

    @Override
    public boolean onUnbind(Intent intent) {
        System.out.println("---onUnbind()---");

        return super.onUnbind(intent);
    }


    //退出或者销毁时调用该方法


    @Override
    public void onDestroy() {
        serviceRunning = false;
        System.out.println("---onDestory()---");

        super.onDestroy();
    }

    @Nullable
    @Override
    //如果把Service在xml文件中修改为远程Service
    //在Bind Service按钮的点击事件里面我们会让MainActivity和MyService建立关联，
    // 但是目前MyService已经是一个远程Service了，Activity和Service运行在两个不同的进程当中，
    // 这时就不能再使用传统的建立关联的方式，程序也就崩溃了。
    public IBinder onBind(Intent intent) {//用户返回Binder对象
        System.out.println("---onBind()---");
    //    return mBinder;
        return mBindeer;//将MyAIDLInterface.Stub的实现返回
    }

    MyAIDLInterface.Stub mBindeer = new MyAIDLInterface.Stub() {
        //Stub其实就是Binder的子类，
        // 所以在onBind()方法中可以直接返回Stub的实现。

        //重写toUpperCase()和plus()方法
        @Override
        public String toUpperCase(String str) throws RemoteException {//将一个字符串全部转换成大写格式
            if (str != null) {
                return str.toUpperCase();
            }
            return null;
        }

        @Override
        public int plus(int a, int b) throws RemoteException {//将两个传入的整数进行相加
            return a + b;
        }
    };
}

