package com.dosweedos.myapplication;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class FeedActivity extends FragmentActivity {
    private FirebaseAuth firebaseAuth;
    private RecyclerView mBlogList;
    private DatabaseReference mDatabase;

    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        firebaseAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("users");
        mBlogList = (RecyclerView) findViewById(R.id.users_list);
        mBlogList.setHasFixedSize(true);
        mBlogList.setLayoutManager(new LinearLayoutManager(this));
    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<UserBean, FeedActivity.UsersViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<UserBean, FeedActivity.UsersViewHolder>(
                UserBean.class, R.layout.user_row, FeedActivity.UsersViewHolder.class, mDatabase
        ) {

            @Override
            protected void populateViewHolder(FeedActivity.UsersViewHolder viewHolder, UserBean model, int position) {
                viewHolder.setName(model.getName());
                viewHolder.setImage(getApplicationContext(), model.getPhotourl());
            }
        };

        mBlogList.setAdapter(firebaseRecyclerAdapter);
    }
    public static class UsersViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public UsersViewHolder(View itemView) {
            super(itemView);

            mView = itemView;
        }

        public void setName(String name) {
            TextView user_name = (TextView) mView.findViewById(R.id.user_name);
            user_name.setText(name);
        }


        public void setImage(Context ctx, String image) {
            ImageView user_image = (ImageView) mView.findViewById(R.id.user_image);
            Picasso.with(ctx).load(image).into(user_image);
        }
    }
}
