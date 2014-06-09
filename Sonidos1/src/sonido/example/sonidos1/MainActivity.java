package sonido.example.sonidos1;





import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {
	private Button btnplay;
	MediaPlayer mp;
	Button btnLoop,btnPlay;
	int pos = 0;
	private TextView timerValue;
	private long startTime = 0L;
	private Handler customHandler = new Handler();
	   long timeInMilliseconds = 0L;
	    long timeSwapBuff = 0L;
	    long updatedTime = 0L;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		timerValue = (TextView) findViewById(R.id.timerValue);

		btnLoop = (Button)findViewById(R.id.btnLoop);
		btnPlay = (Button)findViewById(R.id.btnPlay);
		
		if(pos==20){
			pos = mp.getCurrentPosition();
			mp.pause();
			
	}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		
			
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	
	}

public void apretar(View v) {
	 mp = MediaPlayer.create(this, R.raw.leon);
	mp.start();
}


public void play(View v) {
	 startTime = SystemClock.uptimeMillis();
     customHandler.postDelayed(updateTimerThread, 0);
     
    Intent i = new Intent(this, Uno.class);
    startActivity(i);
	destruir();
	 mp = MediaPlayer.create(this, R.raw.tv);
	mp.start();
	String texto = btnLoop.getText().toString();
	if(texto.equals("No Loop")){
		mp.setLooping(false);
		mp.setLooping(true);
		mp.stop();
		
	}
	}
	 private Runnable updateTimerThread = new Runnable() {
		 
	        public void run() {
	 
	            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
	 
	            updatedTime = timeSwapBuff + timeInMilliseconds;
	 
	            int secs = (int) (updatedTime / 1000);
	            int mins = secs / 60;
	            secs = secs % 60;
	            int milliseconds = (int) (updatedTime % 1000);
	            timerValue.setText("" + mins + ":"
	                    + String.format("%02d", secs) + ":"
	                    + String.format("%03d", milliseconds));
	            customHandler.postDelayed(this, 0);
	        }
	 
	    };
	 
	

	


public void destruir(){
	if (mp !=null){
		mp.release();
	}
}

public void pausar(View v) {
    timeSwapBuff += timeInMilliseconds;
    customHandler.removeCallbacks(updateTimerThread);
	if(mp != null && mp.isPlaying()) {
	pos = mp.getCurrentPosition();
	mp.pause();
	
}}
	public void continuar(View v) {
		 startTime = SystemClock.uptimeMillis();
	     customHandler.postDelayed(updateTimerThread, 0);
	if(mp != null && mp.isPlaying()==false) {
		mp.seekTo(pos);
		mp.start();
	}
	}
	public void detener(View v) {
	    timeSwapBuff += timeInMilliseconds;
        customHandler.removeCallbacks(updateTimerThread);
		if (mp != null) {
			mp.stop();
			pos = 0;
		}
	}
		public void loop(View v) {
			detener(null);
			String texto = btnLoop.getText().toString();
			if (texto.equals("No Loop"))
				btnLoop.setText("Loop");
			else
				btnLoop.setText("No loop");
		
	}
	}



