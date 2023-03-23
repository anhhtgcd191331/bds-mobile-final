package com.example.bds_mobile.model;

public class Like {
    private Long likePostId;

    private boolean like;

    private Long userId;

    private Long postId;

    public Like(Long likePostId, boolean like, Long userId, Long postId) {
        this.likePostId = likePostId;
        this.like = like;
        this.userId = userId;
        this.postId = postId;
    }

    public Long getLikePostId() {
        return likePostId;
    }

    public void setLikePostId(Long likePostId) {
        this.likePostId = likePostId;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }
}
