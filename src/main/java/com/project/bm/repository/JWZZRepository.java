package com.project.bm.repository;

import com.project.bm.entity.JWZZ;
import com.project.bm.entity.YSCG;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JWZZRepository extends JpaRepository<JWZZ,Integer> {

    /**
     * 根据人员id查询
     * @param personId
     * @return
     */
    @Query(name="findByPersonId",nativeQuery = true,
            value = "select * from accepted_overseas_funding where Person_id=:personId")
    List<JWZZ> findByPersonId(@Param("personId")Integer personId);
}
