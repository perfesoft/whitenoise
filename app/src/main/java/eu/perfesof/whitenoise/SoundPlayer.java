package eu.perfesof.whitenoise;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;


import java.io.IOException;

import eu.perfesof.whitenoise.util.SystemUiHider;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class SoundPlayer extends Activity implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {

    protected static final String TAG = "SoundPlayer";
    private  MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        int sound;
        try{
            sound = intent.getIntExtra("soundToPlay", R.raw.rain);
        } catch (Exception e){
            sound = R.raw.rain;
        }
        mediaPlayer = MediaPlayer.create(getApplicationContext(), sound);

        Log.d(TAG,"onCreate mediaplayer Instantiated, sound =" +sound);
    }

    // Get ready to play sound effects
    @Override
    protected void onResume() {
        super.onResume();
        mediaPlayer.start();
        mediaPlayer.setLooping(true);
    }

    /** Called when MediaPlayer is ready */
    public void onPrepared(MediaPlayer player) {
        player.setLooping(true);
        player.start();
    }


    @Override
    protected void onStop(){
        super.onStop();
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;
    }


    @Override
    public void onCompletion(MediaPlayer player) {
        player.start();
    }
}
