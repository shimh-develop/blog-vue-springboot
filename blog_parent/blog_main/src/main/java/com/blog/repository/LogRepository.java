package com.blog.repository;

import com.blog.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author blog
 * <p>
 * 2018年4月18日
 */
public interface LogRepository extends JpaRepository<Log, Integer> {
}
