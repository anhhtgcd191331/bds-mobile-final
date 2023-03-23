package com.example.bds_mobile.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bds_mobile.R;
import com.example.bds_mobile.api.APIClient;
import com.example.bds_mobile.api.APIInterface;
import com.example.bds_mobile.databinding.ItemContainerPostBinding;
import com.example.bds_mobile.listeners.PostListener;
import com.example.bds_mobile.model.Post;
import com.example.bds_mobile.model.User;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.PostsHolder>{
    private final List<Post> posts;
    private PostListener postListener;
    private String token;
    private String username;
    public BlogAdapter(List<Post> posts, PostListener postListener,String token,String username){
        this.posts = posts;
        this.postListener = postListener;
        this.token = token;
        this.username = username;
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
                holder.likePost(posts.get(position), username);
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
            binding.progressBar.setVisibility(View.INVISIBLE);
            binding.textName.setText(fullName);
            binding.textContent.setText(post.getPostTitle());
//            binding.imageProfile.setImageBitmap(getPostImage(post.getImageUrl()));
            binding.textLike.setText(post.getTotalLike()+ " Likes");
            binding.getRoot().setOnClickListener(v -> postListener.onPostClicked(post));
        }

//        void setListeners(Long idPost){
//            binding.imageLike.setOnClickListener(v-> likePost(idPost));
//        }
        void likePost(Post post, String username){
            binding.imageLike.setOnClickListener(v-> {
                binding.textLike.setText(post.getTotalLike()+1 + " Likes");
            APIInterface apiInterface = APIClient.getRetrofitInstance().create(APIInterface.class);
            Call<User> call = apiInterface.getUserByUsername("Bearer " + token, username);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful()){
                            JsonObject jsonObject = new JsonObject();
                        jsonObject.addProperty("likePostId", 0);
                            jsonObject.addProperty("like", true);
                            jsonObject.addProperty("userId", response.body().getUserId());
                        jsonObject.addProperty("postId", post.getPostId());
                            setApiLike(jsonObject);
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {

                }
            });
            });
        }

        void setApiLike(JsonObject like){
            APIInterface apiInterface = APIClient.getRetrofitInstance().create(APIInterface.class);
            Call<JsonObject> call = apiInterface.likePost("Bearer " + token, like);
            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    if(response.isSuccessful()){
                        Boolean isLike = response.body().get("like").getAsBoolean();
                            binding.imageLike.setImageResource(
                                    isLike?R.drawable.ic_like_red:R.drawable.ic_like
                            );
                    }
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {

                }
            });
        }

//        private Bitmap getPostImage(String encodedImage){
//            byte[] bytes = Base64.decode(encodedImage,Base64.DEFAULT);
//            return BitmapFactory.decodeByteArray(bytes,0,bytes.length);
//        }
    }
}
