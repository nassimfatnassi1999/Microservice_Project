package com.saccess.forumservice.Repository;

import com.saccess.forumservice.Entities.CommentairePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentairePostRepository extends JpaRepository<CommentairePost, Long> {
}