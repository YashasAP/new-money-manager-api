package com.mymanger.moneymanager.repository;

import com.mymanger.moneymanager.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<ProfileEntity,Long> {

    //select * from tbl_profiels where email=?
     Optional<ProfileEntity> findByEmail(String email);


     //select * from tbl_profiles where activaion_token+?
     Optional<ProfileEntity> findByActivationToken(String activationToken);


}
