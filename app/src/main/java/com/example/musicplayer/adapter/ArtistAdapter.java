package com.example.musicplayer.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer.HomeActivity;
import com.example.musicplayer.R;
import com.example.musicplayer.models.TrendArtist;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder> {

    private List<TrendArtist> artistList;
    private HomeActivity.OnTrendArtistClicked onTrendArtistClicked;

    public ArtistAdapter(List<TrendArtist> songList, HomeActivity.OnTrendArtistClicked onTrendArtistClicked) {
        this.artistList = songList;
        this.onTrendArtistClicked = onTrendArtistClicked;
    }

    @NonNull
    @Override
    public ArtistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ArtistViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.artist_item_home, parent, false));
    }

    public void addArtist(TrendArtist trendArtist) {
        artistList.add(trendArtist);
        notifyItemInserted(artistList.size() - 1);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistViewHolder holder, int position) {
        TrendArtist artist = artistList.get(position);

        Picasso.get().load(artist.getCover().getSmallCover().getUrl()).into(holder.coverIv);
        holder.artistNameTv.setText(artist.getFullName());
        holder.downloadCountTv.setText(artist.getSumSongsDownloadsCount());
        holder.followersTv.setText(artist.getFollowersCount());

    }

    @Override
    public int getItemCount() {
        return artistList.size();
    }

    public class ArtistViewHolder extends RecyclerView.ViewHolder {
        public ImageView coverIv;
        public TextView artistNameTv;
        public TextView downloadCountTv;
        public TextView followersTv;

        public ArtistViewHolder(@NonNull View itemView) {
            super(itemView);
            coverIv = itemView.findViewById(R.id.iv_cover);
            artistNameTv = itemView.findViewById(R.id.tv_artist_name);
            downloadCountTv = itemView.findViewById(R.id.tv_download_count);
            followersTv = itemView.findViewById(R.id.tv_followers);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onTrendArtistClicked.onClick(artistNameTv.getText().toString());
                }
            });
        }
    }
}
