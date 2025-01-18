package com.example.clever_app;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import de.hdodenhof.circleimageview.CircleImageView;

public class StoriesAdapter extends RecyclerView.Adapter<StoriesAdapter.StoryViewHolder> {

    private List<Story> stories;

    public StoriesAdapter() {
        stories = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            stories.add(new Story("Story " + (i + 1)));
        }
    }

    @NonNull
    @Override
    public StoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stories, parent, false);
        return new StoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoryViewHolder holder, int position) {
        Story story = stories.get(position);
        holder.bind(story);
    }

    @Override
    public int getItemCount() {
        return stories.size();
    }

    public class StoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView storyTitle;
        private CircleImageView circleImageView;
        private Story currentStory;

        public StoryViewHolder(@NonNull View itemView) {
            super(itemView);
            storyTitle = itemView.findViewById(R.id.story_text);
            circleImageView = itemView.findViewById(R.id.circle_story);
            itemView.setOnClickListener(this);
        }

        public void bind(Story story) {
            currentStory = story;
            storyTitle.setText(story.getTitle());
        }

        @Override
        public void onClick(View v) {
            // Toggle viewed state
            currentStory.setViewed(true);
            // Update UI based on new state
            circleImageView.setBorderColor(Color.TRANSPARENT);
            storyTitle.setTypeface(null, Typeface.NORMAL);
            // Load the image view holder
            //Picasso.get().load(R.drawable.placeholder_image).into(circleImageView); // Placeholder image
            // Open the image associated with the story
            // Intent intent = new Intent(this, ImageViewActivity.class);
        }
    }
}
