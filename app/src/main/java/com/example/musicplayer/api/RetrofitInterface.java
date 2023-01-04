package mhmdi.uni.musicplayer.api;


import static mhmdi.uni.musicplayer.utils.Constant.COVER_LINK;
import static mhmdi.uni.musicplayer.utils.Constant.DATABASE_KEY_SEARCH;
import static mhmdi.uni.musicplayer.utils.Constant.DURATION;
import static mhmdi.uni.musicplayer.utils.Constant.PLAY_LINK;
import static mhmdi.uni.musicplayer.utils.Constant.SINGER_NAME;
import static mhmdi.uni.musicplayer.utils.Constant.SONG_NAME;

import java.util.List;

import mhmdi.uni.musicplayer.model.Music;
import mhmdi.uni.musicplayer.model.Result;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitInterface {
    @GET("get_music.php")
    Call<List<Music>> getMusics();

    @POST("search.php")
    Call<List<Music>> search(@Query(DATABASE_KEY_SEARCH) String key);

    @POST("add_music.php")
    Call<Result> addMusic(@Query(SONG_NAME) String songName, @Query(SINGER_NAME) String singerName, @Query(COVER_LINK) String coverLink, @Query(PLAY_LINK) String playLink, @Query(DURATION) String duration);
}
