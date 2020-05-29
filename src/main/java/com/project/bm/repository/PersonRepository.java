package com.project.bm.repository;


import com.project.bm.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepository  extends JpaRepository<Person,Integer> {

    /**
     * 根据角色id和部门id查询用户
     * @param deptId
     * @param jsId
     * @return
     */
    @Query(name="getPersonIdsByDeptIdAndRoleId",nativeQuery = true,
            value = "select pra.Person_id from person_role_association as pra left join sm_person as p on pra.Person_id = p.Person_id where JSID=:jsId and SSBMID =:deptId")
    List<String> getPersonIdsByDeptIdAndRoleId(@Param("deptId") Integer deptId, @Param("jsId") Integer jsId);
}
