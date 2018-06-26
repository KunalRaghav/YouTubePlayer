package com.example.kunalraghav.youtubeplayer;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class YoutubeActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    
    //TAG
    private static final String TAG = "YoutubeActivity";
    
    //API KEY and other references
    static final String API_KEY="AIzaSyBZNPPhtkJqi7XrhIP-_19BcFQdhZPwAw0";
    static final String PLAYLIST_ID="PL6gx4Cwl9DGBsvRxJJOzG4r4k_zLKrnxl";
    //static final String VIDEO_ID="DMJEgRH3Dys";
    static final String VIDEO_ID="X32dce7_D48";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ConstraintLayout layout = (ConstraintLayout) getLayoutInflater().inflate(R.layout.activity_youtube,null);
        setContentView(layout);
        YouTubePlayerView playerView = new YouTubePlayerView(this);
        playerView.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
        layout.addView(playerView);

        //Initializing the player
        playerView.initialize(API_KEY,this);

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        Log.d(TAG, "onInitializationSuccess: provider "+ provider.getClass().toString());
//        Toast.makeText(this,"Initialized successfully",Toast.LENGTH_LONG).show();
        if(!wasRestored){
            Log.d(TAG, "onInitializationSuccess: loading a new video");
            youTubePlayer.setPlaybackEventListener(playbackEventListener);
            youTubePlayer.setPlayerStateChangeListener(playerStateChangeListener);
            // loading new video from video id
            youTubePlayer.cueVideo(VIDEO_ID.toString());
            Log.d(TAG, "onInitializationSuccess: loading complete");
        }

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        final int REQUEST_CODE=1;

        if(youTubeInitializationResult.isUserRecoverableError()){
            youTubeInitializationResult.getErrorDialog(this,REQUEST_CODE).show();
        }else{
            String ErrorMessage=String.format("There was a problem in initializing the youtube player %1$s",youTubeInitializationResult.toString());
            Toast.makeText(this,ErrorMessage,Toast.LENGTH_LONG);
        }
    }



    private YouTubePlayer.PlaybackEventListener playbackEventListener = new YouTubePlayer.PlaybackEventListener() {
        @Override
        public void onPlaying() {
            Toast.makeText(YoutubeActivity.this,"Playing Video",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPaused() {
            Toast.makeText(YoutubeActivity.this,"Video Paused",Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onStopped() {
            Toast.makeText(YoutubeActivity.this,"Video Stopped",Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onBuffering(boolean b) {
            Toast.makeText(YoutubeActivity.this,"Buffering",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onSeekTo(int i) {
            Toast.makeText(YoutubeActivity.this,"Seeking to "+ i,Toast.LENGTH_SHORT).show();
        }
    };
    private YouTubePlayer.PlayerStateChangeListener playerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {
        @Override
        public void onLoading() {

        }

        @Override
        public void onLoaded(String s) {

        }

        @Override
        public void onAdStarted() {
            Toast.makeText(YoutubeActivity.this,"Click the AD to support the creator",Toast.LENGTH_LONG);

        }

        @Override
        public void onVideoStarted() {
            Toast.makeText(YoutubeActivity.this,"Video Started",Toast.LENGTH_SHORT);
        }

        @Override
        public void onVideoEnded() {
            Toast.makeText(YoutubeActivity.this,"Video Ended",Toast.LENGTH_SHORT);
        }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) {

        }
    };


}
