package com.shimh.repository;

import com.shimh.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author shimh
 * <p>
 * 2018年4月18日
 */
public interface LogRepository extends JpaRepository<Log, Integer> {
}
