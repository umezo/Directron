package in.tsuyabu.umezo.android.directron;

import java.io.IOException;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.util.Log;

public class MP {
	private static boolean isPrepared = false;
	private static MediaPlayer mp ;//= new MediaPlayer();
	private static MediaPlayer getMediaplayPlayer(){
		if( mp == null ){
			mp = new MediaPlayer();
			mp.setOnPreparedListener( new OnPreparedListener() {
				
				@Override
				public void onPrepared(MediaPlayer mp) {
					isPrepared = true;
				}
			});
		}
		return mp;
	}
	
	public static void reset(){
		
		MediaPlayer mp = getMediaplayPlayer();
		if( mp.isPlaying() ){
			mp.stop();

		}
		mp.reset();
		
		isPrepared = false;
		
	}
	
	public static void setDataSource( Context c , Uri uri ){
		MediaPlayer mp = getMediaplayPlayer();
		reset();

		try {
			mp.setDataSource( c , uri );
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void prepare(){
		MediaPlayer mp = getMediaplayPlayer();
		try {
			mp.prepare();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void start() {
		MediaPlayer mp = getMediaplayPlayer();
		if( mp.isPlaying() ){
			mp.seekTo(0);
		}else{
			mp.start();
		}
	}
	
	public static void pause(){
//		Log.d("UMEZO", "pause");
		if( mp.isPlaying() ){
			mp.pause();
		}
	}
	
	public static void stop(){
		getMediaplayPlayer().stop();
		prepare();
	}
	
	public static void seekTo( int pos ){
		getMediaplayPlayer().seekTo( pos );
	}
	
	public static int getDuration(){
		return isPrepared ? getMediaplayPlayer().getDuration() : 0;
	}
	
	public static int getPosition(){
		return isPrepared ? getMediaplayPlayer().getCurrentPosition() : 0;
	}
	
	public static void setOnCompletionListener( OnCompletionListener l){
		getMediaplayPlayer().setOnCompletionListener(l);
	}
}
