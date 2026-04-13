package com.example.singersgallery.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.singersgallery.R;
import com.example.singersgallery.beans.Singer;
import com.example.singersgallery.service.SingerService;
import de.hdodenhof.circleimageview.CircleImageView;
import java.util.ArrayList;
import java.util.List;

public class SingerAdapter extends RecyclerView.Adapter<SingerAdapter.SingerViewHolder> implements Filterable {

    private List<Singer> singers;
    private List<Singer> singersFilter;
    private Context context;
    private NewFilter mFilter;

    public SingerAdapter(Context context, List<Singer> singers) {
        this.context = context;
        this.singers = singers;
        this.singersFilter = new ArrayList<>(singers);
        this.mFilter = new NewFilter(this);
    }

    @NonNull
    @Override
    public SingerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.singer_item, parent, false);
        SingerViewHolder holder = new SingerViewHolder(v);

        holder.itemView.setOnClickListener(view -> {
            View popup = LayoutInflater.from(context).inflate(R.layout.singer_edit_item, null, false);
            CircleImageView imgPopup = popup.findViewById(R.id.imgPopup);
            RatingBar popupRatingBar = popup.findViewById(R.id.popupRatingBar);
            TextView tvPopupId = popup.findViewById(R.id.tvPopupId);
            TextView tvPopupName = popup.findViewById(R.id.tvPopupName);

            Singer s = singersFilter.get(holder.getAdapterPosition());
            Glide.with(context).load(s.getImg()).into(imgPopup);
            popupRatingBar.setRating(s.getRating());
            tvPopupId.setText(String.valueOf(s.getId()));
            tvPopupName.setText(s.getName());

            new AlertDialog.Builder(context)
                    .setTitle("Rate Singer")
                    .setMessage("Update the rating for " + s.getName())
                    .setView(popup)
                    .setPositiveButton("Save", (dialog, which) -> {
                        float newRating = popupRatingBar.getRating();
                        int id = Integer.parseInt(tvPopupId.getText().toString());
                        Singer singer = SingerService.getInstance().findById(id);
                        singer.setRating(newRating);
                        SingerService.getInstance().update(singer);
                        notifyItemChanged(holder.getAdapterPosition());
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SingerViewHolder holder, int position) {
        Singer s = singersFilter.get(position);
        holder.tvName.setText(s.getName().toUpperCase());
        holder.tvGenre.setText(s.getGenre());
        holder.ratingBar.setRating(s.getRating());
        holder.tvId.setText(String.valueOf(s.getId()));
        Glide.with(context).load(s.getImg()).into(holder.imgSinger);
    }

    @Override
    public int getItemCount() { return singersFilter.size(); }

    @Override
    public Filter getFilter() { return mFilter; }

    public class SingerViewHolder extends RecyclerView.ViewHolder {
        CircleImageView imgSinger;
        TextView tvName, tvGenre, tvId;
        RatingBar ratingBar;

        public SingerViewHolder(@NonNull View itemView) {
            super(itemView);
            imgSinger = itemView.findViewById(R.id.imgSinger);
            tvName = itemView.findViewById(R.id.tvName);
            tvGenre = itemView.findViewById(R.id.tvGenre);
            tvId = itemView.findViewById(R.id.tvId);
            ratingBar = itemView.findViewById(R.id.ratingBar);
        }
    }

    public class NewFilter extends Filter {
        RecyclerView.Adapter mAdapter;

        public NewFilter(RecyclerView.Adapter mAdapter) {
            this.mAdapter = mAdapter;
        }

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Singer> filtered = new ArrayList<>();
            if (charSequence == null || charSequence.length() == 0) {
                filtered.addAll(singers);
            } else {
                String pattern = charSequence.toString().toLowerCase().trim();
                for (Singer s : singers) {
                    if (s.getName().toLowerCase().startsWith(pattern)) {
                        filtered.add(s);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filtered;
            results.count = filtered.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            singersFilter = (List<Singer>) filterResults.values;
            mAdapter.notifyDataSetChanged();
        }
    }
}