package com.saccess.forumservice.services;

import com.saccess.forumservice.Entities.Topic;
import com.saccess.forumservice.Repository.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class GestionPostImpl implements IGestionPost{
    @Autowired
    IPostRepository postRep;

    @Override
    public Post retreivePost(Long idPost) {
        return postRep.findById(idPost).get();
    }

    @Override
    public List<Post> retrieveAllPosts() {
        return postRep.findAll();
    }

    @Override
    public Post addPost(Post post) {
        return postRep.save(post);
    }

    @Override
    public Post updatePost(Post post) {
        return postRep.save(post);
    }

    @Override
    public void removePost(Long idPost) {
        postRep.deleteById(idPost);
    }

    @Override
    public Post ApprovePost(Long idPost) {
        if (!postRep.existsById(idPost)) {
            throw new RuntimeException("Post with ID " + idPost + " does not exist.");
        }
        Post post=postRep.findById(idPost).get();
        post.setApproved(true);
        return postRep.save(post);
    }

    @Override
    public List<Post> getAllApprovedPostsOrderByDateCreationDesc() {
        return postRep.findByIsApprovedTrueOrderByCreationDatePostDesc();
    }

    @Override
    public Post AddPostAndAssignToUser(Post post, Long userId) {
        post.setAuteurId(userId);
        post.setCreationDatePost(LocalDate.now());
        return postRep.save(post);
    }

    @Override
    public Post toggleLikeDislike(Long idPost, Long userId) {
        Post post = retreivePost(idPost);

        if (post.getLikedBy().contains(userId)) {
            post.setLikes(post.getLikes() - 1);
            post.getLikedBy().remove(userId);
        } else if (post.getDislikedBy().contains(userId)) {
            post.setDislikes(post.getDislikes() - 1);
            post.getDislikedBy().remove(userId);
        } else {
            post.getLikedBy().add(userId);
            post.setLikes(post.getLikes() + 1);
        }

        postRep.save(post);

        return post;
    }

    @Override
    public List<Post> getPostsByTopic(Topic topic) {
        return postRep.findByTopic(topic);
    }

    @Override
    public Post reportPost(Long idPost, Long userId) {
        Post post = retreivePost(idPost);

        if (!post.getReportedBy().contains(userId)) {
            post.setReport(post.getReport() + 1);
            post.getReportedBy().add(userId);

            if (post.getReport() >= 5) {
                postRep.deleteById(idPost);
                return null;
            }
            else {
                postRep.save(post);
            }

        }

        return post;
    }

    @Override
    public List<Post> getPostsByAuthor(Long authorId) {
        return postRep.findByAuteurId(authorId);
    }
}
