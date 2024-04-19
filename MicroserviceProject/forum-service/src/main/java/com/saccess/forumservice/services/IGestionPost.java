package com.saccess.forumservice.services;

import com.saccess.forumservice.Entities.Post;
import com.saccess.forumservice.Entities.Topic;

import java.util.List;

public interface IGestionPost {
    Post retreivePost(Long idPost);
    //    Post retreiveApprovedPost(Long idPost);
    List<Post> retrieveAllPosts();
    public List<Post> getAllApprovedPostsOrderByDateCreationDesc();
    Post addPost(Post post);
    Post updatePost(Post post);
    void removePost(Long idPost);
    Post AddPostAndAssignToUser(Post post, Long userId);
    Post ApprovePost(Long idPost);
    Post toggleLikeDislike(Long idPost, Long userId);
    List<Post> getPostsByTopic(Topic topic);
    Post reportPost(Long idPost, Long userId);
    List<Post> getPostsByAuthor(Long authorId);
}
