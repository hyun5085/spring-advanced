package org.example.expert.domain.todo.repository;

import org.example.expert.domain.todo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {

//    @Query("SELECT t FROM Todo t LEFT JOIN FETCH t.user u ORDER BY t.modifiedAt DESC")
//    방지 내용은 Fetch Join 을 사용하지 않고 코드를 실행했을 때 Todo 전체 조회를 하고
//    User가 새로 나올 때 마다 새로운 쿼리가 동작하여 N+1 문제가 발생
//    Fetch Join 을 사용함으로써 연관된 모든 자료를 한번에 조회함으로써 쿼리 실행을 여러번 하지 않게 됨

    @EntityGraph(attributePaths = {"user"}) // 이거는 Todo를 조회할 때 User도 같이!
    Page<Todo> findAllByOrderByModifiedAtDesc(Pageable pageable);

//    @Query("SELECT t FROM Todo t " +
//            "LEFT JOIN FETCH t.user " +
//            "WHERE t.id = :todoId")
    @EntityGraph(attributePaths = {"user"})
    Optional<Todo> findByIdWithUser(@Param("todoId") Long todoId);

    int countById(Long todoId);
}
