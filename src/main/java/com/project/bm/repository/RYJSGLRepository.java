package com.project.bm.repository;

import com.project.bm.entity.Person;
import com.project.bm.entity.RYJSGL;
import com.project.bm.entity.SGSP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @Author :LX
 * @CreateTime :2020/5/20
 * @Description :人员角色管理
 */
public interface RYJSGLRepository extends JpaRepository<RYJSGL,Integer> {

    /**
     * 根据人员id查询角色
     * @param personId
     * @return
     */
    @Query(name="getAllByPersonId",nativeQuery = true,
            value = "select * from person_role_association where Person_id=:personId")
    List<RYJSGL> getAllByPersonId(@Param("personId")Integer personId);

    /**
     * 根据角色id查询人员
     * @param jsId
     * @return
     */
    @Query(name="findAllPersonByjsId",nativeQuery = true,
            value = "select Person_id from person_role_association where JSID=:jsId")
    List<String> findAllPersonByjsId(@Param("jsId")Integer jsId);
}
