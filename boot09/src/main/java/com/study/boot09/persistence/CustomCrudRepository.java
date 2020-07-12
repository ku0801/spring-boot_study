package com.study.boot09.persistence;

import com.study.boot09.domain.WebBoard;
import org.springframework.data.repository.CrudRepository;

public interface CustomCrudRepository extends CrudRepository<WebBoard,Long>,CustomWebBoard {

}
