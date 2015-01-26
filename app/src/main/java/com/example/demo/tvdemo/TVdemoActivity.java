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
	private static final String A1_MP4_FILE  = "video/a1.MP4";
	private static final String A2_MP4_FILE  = "video/a2.mp4";
	private static final String A3_MP4_FILE  = "video/a3.mp4";
	private static final String B1_MP4_FILE  = "video/b1.MP4";
	private static final String B2_MP4_FILE  = "video/b2.mp4";
	private static final String B3_MP4_FILE  = "video/b3.mp4";
	private static final String C1_MP4_FILE  = "video/c1.MP4";
	private static final String C2_MP4_FILE  = "video/c2.mp4";
	private static final String C3_MP4_FILE  = "video/c3.mp4";

	private SurfaceHolder	holder1,holder2,holder3,
							holder4,holder5,holder6,
							holder7,holder8,holder9;
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
		//holder1.setFixedSize(10,10);
		holder1.addCallback(this);

		mPreview = (SurfaceView) findViewById(R.id.surfaceView2);
		//mPreview.setSecure(true);
		holder2 = mPreview.getHolder();
		holder2.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		holder2.addCallback(this);

		mPreview = (SurfaceView) findViewById(R.id.surfaceView3);
		//mPreview.setSecure(true);
		holder3 = mPreview.getHolder();
		holder3.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		holder3.addCallback(this);

		mPreview = (SurfaceView) findViewById(R.id.surfaceView4);
		//mPreview.setSecure(true);
		holder4 = mPreview.getHolder();
		holder4.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		holder4.addCallback(this);

		mPreview = (SurfaceView) findViewById(R.id.surfaceView5);
		//mPreview.setSecure(true);
		holder5 = mPreview.getHolder();
		holder5.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		holder5.addCallback(this);

		mPreview = (SurfaceView) findViewById(R.id.surfaceView6);
		//mPreview.setSecure(true);
		holder6 = mPreview.getHolder();
		holder6.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		holder6.addCallback(this);

		mPreview = (SurfaceView) findViewById(R.id.surfaceView7);
		//mPreview.setSecure(true);
		holder7 = mPreview.getHolder();
		holder7.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		holder7.addCallback(this);

		mPreview = (SurfaceView) findViewById(R.id.surfaceView8);
		//mPreview.setSecure(true);
		holder8 = mPreview.getHolder();
		holder8.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		holder8.addCallback(this);

		mPreview = (SurfaceView) findViewById(R.id.surfaceView9);
		//mPreview.setSecure(true);
		holder9 = mPreview.getHolder();
		holder9.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		holder9.addCallback(this);

		PowerManager pm = (PowerManager)getSystemService(Context.POWER_SERVICE);
		lock = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "TVdemo");

		Log.i(TAG, "Create");
	}

	@Override
	public void surfaceCreated(SurfaceHolder paramSurfaceHolder) {
		Log.i(TAG, "Enter");

		MediaPlayer mp = new MediaPlayer();
		String mediaPath;

		if(paramSurfaceHolder==holder9){
			Log.i(TAG, "holder9");
			mediaPath = System.getenv("EXTERNAL_STORAGE") + "/" + C3_MP4_FILE;
			//mp.setSurface(paramSurfaceHolder.getSurface());
			mp.setDisplay(paramSurfaceHolder);
			mp.setVolume((float)0.1,(float)0.2);
		} else
		if(paramSurfaceHolder==holder8){
			Log.i(TAG, "holder8");
			mediaPath = System.getenv("EXTERNAL_STORAGE") + "/" + C2_MP4_FILE;
			mp.setDisplay(paramSurfaceHolder);
			mp.setVolume((float)0.3,(float)0.3);
		} else
		if(paramSurfaceHolder==holder7){
			Log.i(TAG, "holder7");
			mediaPath = System.getenv("EXTERNAL_STORAGE") + "/" + C1_MP4_FILE;
			mp.setDisplay(paramSurfaceHolder);
			mp.setVolume((float)0.2,(float)0.1);
		} else
		if(paramSurfaceHolder==holder6){
			Log.i(TAG, "holder6");
			mediaPath = System.getenv("EXTERNAL_STORAGE") + "/" + B3_MP4_FILE;
			mp.setDisplay(paramSurfaceHolder);
			mp.setVolume((float)0.2,(float)0.4);
		} else
		if(paramSurfaceHolder==holder5){
			Log.i(TAG, "holder5");
			mediaPath = System.getenv("EXTERNAL_STORAGE") + "/" + B2_MP4_FILE;
			mp.setDisplay(paramSurfaceHolder);
			mp.setVolume((float)0.9,(float)0.9);
		} else
		if(paramSurfaceHolder==holder4){
			Log.i(TAG, "holder4");
			mediaPath = System.getenv("EXTERNAL_STORAGE") + "/" + B1_MP4_FILE;
			mp.setDisplay(paramSurfaceHolder);
			mp.setVolume((float)0.4,(float)0.2);
		} else
		if(paramSurfaceHolder==holder3){
			Log.i(TAG, "holder3");
			mediaPath = System.getenv("EXTERNAL_STORAGE") + "/" + A3_MP4_FILE; 
			mp.setDisplay(paramSurfaceHolder);
			mp.setVolume((float)0.1,(float)0.2);
		} else
		if(paramSurfaceHolder==holder2){
			Log.i(TAG, "holder2");
			mediaPath = System.getenv("EXTERNAL_STORAGE") + "/" + A2_MP4_FILE; 
			mp.setDisplay(paramSurfaceHolder);
			mp.setVolume((float)0.3,(float)0.3);
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
			mediaPath = System.getenv("EXTERNAL_STORAGE") + "/" + A1_MP4_FILE; 
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
