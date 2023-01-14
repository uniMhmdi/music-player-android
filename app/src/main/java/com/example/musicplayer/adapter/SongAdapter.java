package com.example.musicplayer.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer.R;
import com.example.musicplayer.SongDetailActivity;
import com.example.musicplayer.models.Song;
import com.example.musicplayer.utils.Constant;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewHolder> {

    private List<Song> songList;

    public SongAdapter(List<Song> songList) {
        this.songList = songList;
    }

    @NonNull
    @Override
    public SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SongViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.song_item_home, parent, false));
    }

    public void addSong(Song song) {
        songList.add(song);
        notifyItemInserted(songList.size() - 1);
    }

    @Override
    public void onBindViewHolder(@NonNull SongViewHolder holder, int position) {
        Song song = songList.get(position);

        Picasso.get().load(song.getCoverArt().getSmallCover().getUrl()).into(holder.coverIv);
        holder.songNameTv.setText(song.getSongName());
        holder.artistNameTv.setText(song.getArtistList().get(0).getFullName());
        holder.durationTv.setText(song.getDurationByFormat());
        holder.downloadCountTv.setText(song.getPlayCount());

    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    public class SongViewHolder extends RecyclerView.ViewHolder {
        public ImageView coverIv;
        public TextView songNameTv;
        public TextView artistNameTv;
        public TextView downloadCountTv;
        public TextView durationTv;

        public SongViewHolder(@NonNull View itemView) {
            super(itemView);
            coverIv = itemView.findViewById(R.id.iv_cover);
            songNameTv = itemView.findViewById(R.id.tv_song_name);
            artistNameTv = itemView.findViewById(R.id.tv_artist_name);
            downloadCountTv = itemView.findViewById(R.id.tv_download_count);
            durationTv = itemView.findViewById(R.id.tv_duration);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), SongDetailActivity.class);
                    intent.putParcelableArrayListExtra(Constant.SONG_LIST_EXTRA_KEY,(ArrayList<Song>)songList) ;
                    intent.putExtra(Constant.SONG_POSITION_EXTRA_KEY,getAdapterPosition()) ;
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}
