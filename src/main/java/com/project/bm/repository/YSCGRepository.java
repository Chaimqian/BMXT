package com.project.bm.repository;

import com.project.bm.entity.SGSP;
import com.project.bm.entity.YSCG;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface YSCGRepository extends JpaRepository<YSCG,Integer> {

    /**
     * 根据用户id查询
     * @param personId
     * @return
     */
    @Query(name="findByPersonId",nativeQuery = true,
            value = "select * from private_travel_abroad where Person_id=:personId")
    List<YSCG> findByPersonId(@Param("personId")Integer personId);
}
