package eu.perfesof.whitenoise;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void definition(View view) {
        Toast.makeText(getApplicationContext(), R.string.definition, Toast.LENGTH_LONG).show();
    }

    public void sound(View view) {
        Log.d(TAG, "sound begins");
        int idView = view.getId();
        Log.d(TAG, "idView = " + idView);
        int sound;
        switch (idView){
            case R.id.buttonB:
                sound=R.raw.blizzard_in_forest;
                break;
            case R.id.buttonCA:
                sound=R.raw.cave_ambience;
                break;
            case R.id.buttonOW:
                sound=R.raw.ocean_waves;
                break;
            case R.id.buttonR:
                sound=R.raw.rain;
                break;
            case R.id.buttonTLR:
                sound=R.raw.thunder_and_light_rain;
                break;
            case R.id.buttonW:
                sound=R.raw.strong_wind_in_trees_005;
                break;
            default:
                sound=R.raw.rain;
        }
        Log.d(TAG, "sound selected = " + sound);
        Intent soundPlayerIntent = new Intent(this, eu.perfesof.whitenoise.SoundPlayer.class);
        soundPlayerIntent.putExtra("soundToPlay", sound);
        Log.d(TAG, "sound start activity SoundPlayer");
        startActivity(soundPlayerIntent);
        Log.d(TAG, "sound ends");
    }

    }
