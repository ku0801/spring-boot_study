package com.study.boot09.persistence;

import com.study.boot09.domain.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepositroy extends CrudRepository<Member,String> {
}
