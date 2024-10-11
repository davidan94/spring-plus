package org.example.expert.domain.comment.repository;

import org.example.expert.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    // 기존에 JOIN만 사용했던 쿼리를 fetch join으로 변경하여 N+1 문제 해결(7단계)
    @Query("SELECT c FROM Comment c JOIN FETCH c.user u JOIN FETCH c.todo t WHERE t.id = :todoId")
    List<Comment> findByTodoIdWithUser(@Param("todoId") Long todoId);
}
