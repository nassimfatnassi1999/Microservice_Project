package com.saccess.forumservice.controller;
import com.saccess.forumservice.Entities.Post;
import com.saccess.forumservice.Entities.Topic;
import com.saccess.forumservice.services.IGestionPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Post")
public class PostController {
    @Autowired
    IGestionPost gestionPost;

    @GetMapping("/getall")
    public List<Post> getall() {
        return gestionPost.retrieveAllPosts();}

    @GetMapping("/getallApproved")
    public List<Post> getAllApprovedPostsOrdered() {
        return gestionPost.getAllApprovedPostsOrderByDateCreationDesc();
    }

    @GetMapping("/getId/{id}")
    public Post getId(@PathVariable("id") Long id) {
        return gestionPost.retreivePost(id);}

    @PostMapping("/add")
    public Post add(@RequestBody Post post){
        return gestionPost.addPost(post);
    }

    @DeleteMapping("/deleteID/{id}")
    public void delete(@PathVariable("id") long id){
        gestionPost.removePost(id);
    }

    @PutMapping ("/update")
    public Post update(@RequestBody Post post){
        return gestionPost.updatePost(post);
    }

    @PostMapping("/AddPostAndAssignToUser/{numU}")
    public Post AddPostAndAssignToTopicAndUser(@RequestBody Post post,
                                               @PathVariable("numU") Long userId) {
        return gestionPost.AddPostAndAssignToUser(post, userId);
    }
    @PutMapping ("/ApprovePost/{numP}")
    public Post ApprovePost( @PathVariable("numP") Long idPost) {
        return gestionPost.ApprovePost(idPost);
    }

/*    @PutMapping("/toggleLikeDislike/{id}/{userId}")
    public Post toggleLikeDislike(@PathVariable("id") Long id, @PathVariable("userId") Long userId) {
        return gestionPost.toggleLikeDislike(id, userId);
    }*/
    @GetMapping("/getByTopic/{topic}")
    public List<Post> getByTopic(@PathVariable("topic") Topic topic) {
        return gestionPost.getPostsByTopic(topic);
    }
/*    @PutMapping("/report/{id}/{userId}")
    public Post reportPost(@PathVariable("id") Long id, @PathVariable("userId") Long userId) {
        return gestionPost.reportPost(id, userId);
    }*/
    @GetMapping("/getByAuthor/{authorId}")
    public List<Post> getByAuthor(@PathVariable("authorId") Long authorId) {
        return gestionPost.getPostsByAuthor(authorId);
    }
}

