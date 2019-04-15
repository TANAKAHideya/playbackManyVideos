package com.example.demo.tvdemo;

import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;

/* http://pentan.info/android/app/sample/mediaplayer.html */
/* http://blog.tappli.com/article/44620525.html */

// Environment.getExternalStorageDirectory();

public class TVdemoActivity extends Activity implements SurfaceHolder.Callback{
	private static final String TAG = "TVdemo";
	private static final String V1_MP4_FILE  = "video/video1.mp4";
	private static final String V2_MP4_FILE  = "video/video2.mp4";
	private static final String V3_MP4_FILE  = "video/video3.mp4";
	private static final String V4_MP4_FILE  = "video/video4.mp4";

	private SurfaceHolder	holder1,holder2,holder3,	holder4;
	private WakeLock lock;
	private int prev_status = -1;

	@SuppressWarnings({ "deprecation", "deprecation" })
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SurfaceView mPreview;

		/* Set Full screen flag */
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        /* Support Transparent color */
		//getWindow().setFormat(PixelFormat.TRANSPARENT);
		getWindow().setFormat(PixelFormat.RGBX_8888);
        /* No window title */
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.main);

        //layout_height
		mPreview = (SurfaceView) findViewById(R.id.surfaceView1);
		//mPreview.setSecure(true);
		holder1 = mPreview.getHolder();
		holder1.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		holder1.addCallback(this);

		mPreview = (SurfaceView) findViewById(R.id.surfaceView2);
		//mPreview.setSecure(true);
		holder2 = mPreview.getHolder();
		holder2.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        //holder2.setFixedSize(500,500);
		holder2.addCallback(this);

		mPreview = (SurfaceView) findViewById(R.id.surfaceView3);
		//mPreview.setSecure(true);
		holder3 = mPreview.getHolder();
		holder3.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        //holder3.setFixedSize(2000,2000);
		holder3.addCallback(this);

		mPreview = (SurfaceView) findViewById(R.id.surfaceView4);
		//mPreview.setSecure(true);
		holder4 = mPreview.getHolder();
		holder4.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		holder4.addCallback(this);


		PowerManager pm = (PowerManager)getSystemService(Context.POWER_SERVICE);
		lock = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "TVdemo");

		Log.i(TAG, "Create");
	}

	@Override
	public void surfaceCreated(SurfaceHolder paramSurfaceHolder) {
		Log.i(TAG, "Enter");

		MediaPlayer mp = new MediaPlayer();
		String mediaPath;

		if(paramSurfaceHolder==holder4){
			Log.i(TAG, "holder4");
			mediaPath = System.getenv("EXTERNAL_STORAGE") + "/" + V4_MP4_FILE;
			mp.setDisplay(paramSurfaceHolder);
			mp.setVolume((float)0.1,(float)0.2);
		} else
		if(paramSurfaceHolder==holder3){
			Log.i(TAG, "holder3");
			mediaPath = System.getenv("EXTERNAL_STORAGE") + "/" + V3_MP4_FILE;
			mp.setDisplay(paramSurfaceHolder);
			mp.setVolume((float)0.2,(float)0.1);
		} else
		if(paramSurfaceHolder==holder2){
			Log.i(TAG, "holder2");
			mediaPath = System.getenv("EXTERNAL_STORAGE") + "/" + V2_MP4_FILE;
			mp.setDisplay(paramSurfaceHolder);
			mp.setVolume((float)0.1,(float)0.2);
		} else {
			Log.i(TAG, "holder1");
//			{
//				//layout_height
//				SurfaceView mPreview;
//				mPreview = (SurfaceView) findViewById(R.id.surfaceView1);
//				if(prev_status == 0) {
//					prev_status = 1;
//					mPreview.setSecure(true);
//					Log.i(TAG, "setSecure(true)");
//				} else {
//					prev_status = 0;
//					mPreview.setSecure(false);
//					Log.i(TAG, "setSecure(false)");
//				}
//			}
			mediaPath = System.getenv("EXTERNAL_STORAGE") + "/" + V1_MP4_FILE;
			mp.setDisplay(paramSurfaceHolder);
			mp.setVolume((float)0.2,(float)0.1);
		}
		lock.acquire();
		mediaPlay(mp,paramSurfaceHolder,mediaPath);
	}
	public void mediaPlay(MediaPlayer mp,SurfaceHolder paramSurfaceHolder,String mediaPath) {
		try {
			mp.setLooping(true);
			mp.setDataSource(mediaPath);
			//mp.setDisplay(paramSurfaceHolder);
			mp.prepare();
			mp.start();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder paramSurfaceHolder, int paramInt1,
			int paramInt2, int paramInt3) {
		Log.i(TAG, "surfaceChanged()");
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder paramSurfaceHolder) {
		Log.i(TAG, "surfaceDestroyed()");
		if(lock.isHeld()) lock.release();
	}
}
