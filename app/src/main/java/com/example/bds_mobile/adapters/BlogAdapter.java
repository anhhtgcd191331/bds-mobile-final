package com.example.bds_mobile.adapters;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bds_mobile.api.APIClient;
import com.example.bds_mobile.api.APIInterface;
import com.example.bds_mobile.databinding.ItemContainerPostBinding;
import com.example.bds_mobile.listeners.PostListener;
import com.example.bds_mobile.model.Post;
import com.example.bds_mobile.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.PostsHolder>{
    private final List<Post> posts;
    private PostListener postListener;
    private String token;
    public BlogAdapter(List<Post> posts, PostListener postListener,String token){
        this.posts = posts;
        this.postListener = postListener;
        this.token = token;
    }

    @NonNull
    @Override
    public BlogAdapter.PostsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemContainerPostBinding itemContainerPostBinding = ItemContainerPostBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new BlogAdapter.PostsHolder(itemContainerPostBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BlogAdapter.PostsHolder holder, @SuppressLint("RecyclerView") int position) {
        APIInterface apiInterface = APIClient.getRetrofitInstance().create(APIInterface.class);
        Call<User> call = apiInterface.getUser("Bearer " + token, posts.get(position).getAuthorId());
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                holder.setPostData(posts.get(position), response.body().getFullName());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class PostsHolder extends RecyclerView.ViewHolder{
        ItemContainerPostBinding binding;
        PostsHolder(ItemContainerPostBinding itemContainerPostBinding){
            super(itemContainerPostBinding.getRoot());
            binding = itemContainerPostBinding;
        }

        void setPostData(Post post, String fullName){
            binding.textName.setText(fullName);
            binding.textContent.setText(post.getPostTitle());
//            binding.imageProfile.setImageBitmap(getPostImage(post.getImageUrl()));
            binding.getRoot().setOnClickListener(v -> postListener.onPostClicked(post));
        }

//        private Bitmap getPostImage(String encodedImage){
//            byte[] bytes = Base64.decode(encodedImage,Base64.DEFAULT);
//            return BitmapFactory.decodeByteArray(bytes,0,bytes.length);
//        }
    }
}
