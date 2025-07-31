package com.mymanger.moneymanager.repository;

import com.mymanger.moneymanager.entity.CatagoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CatagoryRepository extends JpaRepository<CatagoryEntity,Long> {

    //select  from tbl_catagories where profileId=?
    List<CatagoryEntity> findByProfileId(Long profileId);

    //select  from tbl_catagories where id= ? and  profileId=?
    Optional<CatagoryEntity> findByIdAndProfileId(Long id, Long profileId);

    List<CatagoryEntity>findByTypeAndProfileId(String type, Long profileId);

   // Boolean existByNameProfileId(String name,Long profileId);
    Boolean existsByNameAndProfileId(String name, Long profileId);

}
