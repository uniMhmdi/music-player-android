package com.example.musicplayer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSeekBar;

import com.example.musicplayer.api.RetrofitClient;
import com.example.musicplayer.api.RetrofitInterface;
import com.example.musicplayer.models.Song;
import com.example.musicplayer.utils.Constant;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SongDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private RetrofitInterface retrofitInterface;
    private String lyrics;
    private LyricBottomSheet bottomSheet;

    private ImageView backIv;
    private ImageView coverIV;
    private TextView songNameTv;
    private TextView artistNameTv;
    private TextView totalPlayTv;
    private TextView releaseDateTv;
    private TextView durationTv;
    private TextView currentDurationTv;
    private AppCompatSeekBar appCompatSeekBar;
    private ImageButton prevIv;
    private ImageButton nextIv;
    private ImageButton repeatIv;
    private ImageButton shuffleIv;
    private ImageButton playPauseIb;
    private ImageButton lyricsIb;
    private MediaPlayer mediaPlayer;
    private Timer timer;

    private List<Song> songList;
    private Song currentSong;
    private int currentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_detail);

        if (getIntent().getParcelableArrayListExtra(Constant.SONG_LIST_EXTRA_KEY) != null) {
            songList = getIntent().getParcelableArrayListExtra(Constant.SONG_LIST_EXTRA_KEY);
            currentPosition = getIntent().getIntExtra(Constant.SONG_POSITION_EXTRA_KEY, -1);
            currentSong = songList.get(currentPosition);
            init();
            onClick();
        }


    }

    private void onClick() {
        backIv.setOnClickListener(this);
    }

    private void init() {
        retrofitInterface = RetrofitClient.getClient().create(RetrofitInterface.class);
        backIv = findViewById(R.id.iv_back);
        coverIV = findViewById(R.id.iv_cover);
        songNameTv = findViewById(R.id.tv_song_name);
        artistNameTv = findViewById(R.id.tv_artist_name);
        totalPlayTv = findViewById(R.id.tv_total_play);
        releaseDateTv = findViewById(R.id.tv_release_date);
        durationTv = findViewById(R.id.tv_total_time);
        currentDurationTv = findViewById(R.id.tv_current_time);
        appCompatSeekBar = findViewById(R.id.seekbar_player);
        nextIv = findViewById(R.id.ib_next);
        prevIv = findViewById(R.id.ib_prev);
        shuffleIv = findViewById(R.id.ib_shuffle);
        repeatIv = findViewById(R.id.ib_repeat);
        playPauseIb = findViewById(R.id.ib_play_puase);
        lyricsIb = findViewById(R.id.ib_lyrics);

        lyricsIb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lyrics != null && lyrics.length() >= 1) {
                    new LyricBottomSheet(lyrics).show(getSupportFragmentManager(), "TAG");
                } else {
                    Toast.makeText(SongDetailActivity.this, "متن آهنگ موجود نیست", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mediaPlayer = new MediaPlayer();
        setupSongPlayer();
    }

    private void setupSongPlayer() {
        try {
            lyrics = null;
            retrofitInterface.getLyricSong(currentSong.getId()).enqueue(new Callback<Song>() {
                @Override
                public void onResponse(@NonNull Call<Song> call, @NonNull Response<Song> response) {
                    if (response.body() != null) {
                        lyrics = response.body().getLyrics();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<Song> call, @NonNull Throwable t) {
                    Toast.makeText(SongDetailActivity.this, "خطا در گرفتن متن موزیک", Toast.LENGTH_SHORT).show();
                }
            });

            mediaPlayer.setDataSource(currentSong.getAudio().getMedium().getUrl());
            mediaPlayer.prepareAsync();

            artistNameTv.setText(currentSong.getArtistList().get(0).getFullName());
            songNameTv.setText(currentSong.getSongName());
            totalPlayTv.setText(currentSong.getPlayCount());
            releaseDateTv.setText(currentSong.getReleaseDate().substring(0, 11));
            Picasso.get().load(currentSong.getCoverArt().getBigCover().getUrl()).into(coverIV);

            timer = new Timer();

            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    appCompatSeekBar.setMax(mediaPlayer.getDuration());

                    updateSeekBarTime(mediaPlayer.getDuration(), durationTv);

                    timer.scheduleAtFixedRate(new TimerTask() {
                        @Override
                        public void run() {
                            runOnUiThread(() -> {
                                appCompatSeekBar.setProgress(mediaPlayer.getCurrentPosition());

                                updateSeekBarTime(mediaPlayer.getCurrentPosition(), currentDurationTv);

                                if (durationTv.getText().equals(currentDurationTv.getText())) {
                                    playNextSong(mediaPlayer);
                                }
                            });

                        }
                    }, 1000, 1000);

                    mediaPlayer.start();
                    playPauseIb.setImageResource(R.drawable.ic_pause);

                    appCompatSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                            if (fromUser) {
                                mediaPlayer.seekTo(progress);
                                updateSeekBarTime(mediaPlayer.getCurrentPosition(), currentDurationTv);

                            }
                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {

                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {

                        }
                    });

                    prevIv.setOnClickListener(v -> {
                        playPrevSong(mediaPlayer);
                    });

                    nextIv.setOnClickListener(v -> {
                        playNextSong(mediaPlayer);
                    });

                    playPauseIb.setOnClickListener(v -> {
                        if (mediaPlayer.isPlaying()) {
                            mediaPlayer.pause();
                            playPauseIb.setImageResource(R.drawable.ic_play);
                        } else {
                            mediaPlayer.start();
                            playPauseIb.setImageResource(R.drawable.ic_pause);
                        }
                    });

                }
            });
        } catch (IOException e) {
            Toast.makeText(this, "خطایی رخ داد ، بعدا تلاش کنید", Toast.LENGTH_SHORT).show();
            finish();
            e.printStackTrace();
        }
    }


    private void playNewSong(MediaPlayer mediaPlayer) {
        currentSong = songList.get(currentPosition);
        checkPlaying(mediaPlayer);
        mediaPlayer.reset();
        releaseTimerTask();
        setupSongPlayer();
    }

    private void checkPlaying(MediaPlayer mediaPlayer) {
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
    }

    private void releaseTimerTask() {
        timer.cancel();
        timer.purge();
    }

    private void playPrevSong(MediaPlayer mediaPlayer) {

        if ((currentPosition - 1) >= 0) {
            currentPosition--;
            playNewSong(mediaPlayer);
        } else {
            Toast.makeText(this, "ابتدای پلی لیست هستیم!", Toast.LENGTH_SHORT).show();
        }
    }

    private void playNextSong(MediaPlayer mediaPlayer) {

        if ((currentPosition + 1) < songList.size()) {
            currentPosition++;
            playNewSong(mediaPlayer);
        } else {
            playPauseIb.setImageResource(R.drawable.ic_play);
            appCompatSeekBar.setProgress(0);
            updateSeekBarTime(0, currentDurationTv);
//            releaseTimerTask();
            Toast.makeText(this, "به انتهای لیست رسیدیم،هیچ آهنگی در لیست نیست", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateSeekBarTime(int timeInMilliSec, TextView view) {
        int timeInSec = timeInMilliSec / 1000;
        String min = String.valueOf(timeInSec / 60);
        String second = String.valueOf(timeInSec % 60);

        if (min.length() == 1) {
            min = "0" + min;
        }
        if (second.length() == 1) {
            second = "0" + second;
        }

        view.setText(min + ":" + second);
    }


    @Override
    public void onClick(View view) {
        if (view == backIv) {
            releaseResource();
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        releaseResource();
        super.onBackPressed();

    }

    public void releaseResource() {
        mediaPlayer.stop();
        releaseTimerTask();
    }
}