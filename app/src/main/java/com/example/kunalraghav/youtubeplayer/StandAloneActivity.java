package com.example.kunalraghav.youtubeplayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeStandalonePlayer;

public class StandAloneActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "StandAloneActivity";
    String VIDEO_KEY ="NOT_DEFINED";
    String PLAYLIST_KEY = "NOT_DEFINED";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stand_alone);
        Button vidPlay = findViewById(R.id.btnPlayVid);
        Button playlistPlay = findViewById(R.id.btnPlayLst);
        vidPlay.setOnClickListener(this);
        playlistPlay.setOnClickListener(this);
    }
    public String IdExtractor(String url){
        String id="";
        try{

            if(!url.contains("?v=")){
                throw new Exception("Is not a valid video link");
            }else {
                id=url.substring(32);
            }
        }catch (Exception e){
            Toast.makeText(this,"Wrong Link Format",Toast.LENGTH_LONG);
            Log.e(TAG, "IdExtractor: "+e.getStackTrace() +" "+ e.getMessage() );
            id=YoutubeActivity.VIDEO_ID;
        }

        return id;
    }

    public String PlaylistExtractor(String Url){
        String id="";
        try{
            if(!Url.contains("?list=")){
                throw new Exception("Not a valid playlist link");
            }
            else {
                id = Url.substring(38);
            }
        }catch (Exception e){
            Log.e(TAG, "PlaylistExtractor: "+e.getStackTrace()+": "+e.getMessage() );
            id=YoutubeActivity.PLAYLIST_ID;
        }
        return id;
    }

    @Override
    public void onClick(View v) {
        Intent intent=null;
        int id = v.getId();
        switch (id){
            case R.id.btnPlayLst:
                EditText playlist_link =findViewById(R.id.PlaylistLink);
                PLAYLIST_KEY=PlaylistExtractor(playlist_link.getText().toString());
                intent = YouTubeStandalonePlayer.createPlaylistIntent(this,YoutubeActivity.API_KEY,PLAYLIST_KEY,0,0,true,true);
                break;
            case R.id.btnPlayVid:
                EditText video_link = findViewById(R.id.VideoLink);
                VIDEO_KEY=IdExtractor(video_link.getText().toString());
                Log.d(TAG, "onClick: ID:" +VIDEO_KEY);
                intent = YouTubeStandalonePlayer.createVideoIntent(this,YoutubeActivity.API_KEY,VIDEO_KEY,0,true,true);
                break;
        }
        if(intent!=null){
            startActivity(intent);
        }

    }

    
}
