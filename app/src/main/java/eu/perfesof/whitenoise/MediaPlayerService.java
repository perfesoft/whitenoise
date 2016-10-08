package eu.perfesof.whitenoise;

import android.app.IntentService;
import android.app.Notification;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.PowerManager;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MediaPlayerService extends IntentService implements MediaPlayer.OnErrorListener{
    protected static final String TAG = "SoundPlayer";
    private MediaPlayer mediaPlayer;
    private static int NOTIFICATION_ID = 12344321;


    public MediaPlayerService() {
        super("MediaPlayerService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        int sound;
        try{
            sound = intent.getIntExtra("soundToPlay", R.raw.rain);
        } catch (Exception e){
            sound = R.raw.rain;
        }
        startSound(sound);
    }

    //starts the sound selected
    private void startSound(int sound) {
        mediaPlayer = MediaPlayer.create(getApplicationContext(), sound);
        mediaPlayer.setOnErrorListener(this);
        mediaPlayer.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);

        mediaPlayer.start();
        mediaPlayer.setLooping(true);
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        mp = null;
        return false;
    }

    private void startForeground(){

        Notification notification = new Notification.Builder(getApplicationContext())
                .setContentTitle("White Noise ")
                .setContentText("Playing")
                .setSmallIcon(R.drawable.wn_waves_ico)
                .setLargeIcon(((BitmapDrawable)getResources().getDrawable(R.drawable.wn_waves_ico)).getBitmap())
                .build();

        startForeground(NOTIFICATION_ID, notification);
    }
}
