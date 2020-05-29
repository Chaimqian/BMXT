package com.project.bm.repository;

import com.project.bm.entity.JSYW;

import com.project.bm.entity.YWHJ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JSYWRepository extends JpaRepository<JSYW,Integer> {
    /**
     * //根据业务id和业务环节id查询
     * @param YWID
     * @param YWHJID
     * @return
     */

    @Query(name="findRoleByYwIdAndYwhjId",nativeQuery = true,
            value = "select * from role_servicelinks_association where YWID=:YWID and YWHJID =:YWHJID")
    JSYW findRoleByYwIdAndYwhjId(@Param("YWID")Integer YWID,@Param("YWHJID")Integer YWHJID);

    /**
     * 根据业务id查询
     * @param YWID
     * @return
     */
    @Query(name="findAllByYwId",nativeQuery = true,
            value = "select * from role_servicelinks_association where YWID=:YWID")
    List<JSYW> findAllByYwId(@Param("YWID")Integer YWID);


}
