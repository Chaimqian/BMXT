package com.project.bm.repository;

import com.project.bm.entity.YSCG;
import com.project.bm.entity.YWHJ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @Author :LX
 * @CreateTime :2020/5/20
 * @Description :
 */
public interface YWHJRepository extends JpaRepository<YWHJ,Integer> {

    /**
     * 根据业务id查询业务环节
     * @param ywId
     * @return
     */
    @Query(name="findAllByYwId",nativeQuery = true,
            value = "select * from service_links where YWID=:ywId")
    List<YWHJ> findAllByYwId(@Param("ywId")Integer ywId);
}
