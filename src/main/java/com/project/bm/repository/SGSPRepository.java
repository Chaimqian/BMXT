package com.project.bm.repository;

import com.project.bm.entity.SGSP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SGSPRepository extends JpaRepository<SGSP,Integer> {

    /**
     * 根据用户id查询
     * @param personId
     * @return
     */
    @Query(name="findByPersonIdOrderByApplyTime",nativeQuery = true,
            value = "select * from onduty_qualification_approval_form where Person_id=:personId and is_del=0 order by apply_date desc ")
    List<SGSP> findByPersonIdOrderByApplyTime(@Param("personId")Integer personId);

}
