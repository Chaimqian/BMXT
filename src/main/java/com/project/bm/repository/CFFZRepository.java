package com.project.bm.repository;

import com.project.bm.entity.CFFZ;
import com.project.bm.entity.JWZZ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CFFZRepository extends JpaRepository<CFFZ,Integer> {
    /**
     * 根据人员id查询
     * @param personId
     * @return
     */
    @Query(name="findByPersonId",nativeQuery = true,
            value = "select * from disciplinary_criminal_record where Person_id=:personId")
    List<CFFZ> findByPersonId(@Param("personId")Integer personId);
}
