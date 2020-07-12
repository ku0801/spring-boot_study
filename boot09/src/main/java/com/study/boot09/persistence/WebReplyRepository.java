package com.study.boot09.persistence;

import com.study.boot09.domain.WebBoard;
import com.study.boot09.domain.WebReply;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WebReplyRepository extends CrudRepository<WebReply ,Long> {
    @Query("select r from WebReply r where r.board =?1"+" and r.rno>0 order by r.rno ASC ")
     List<WebReply> getRepliesOfBoard(WebBoard board);


}
