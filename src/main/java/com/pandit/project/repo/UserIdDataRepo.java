package com.pandit.project.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pandit.project.model.UserIdData;

public interface UserIdDataRepo  extends JpaRepository<UserIdData, String>{

	Optional<UserIdData> findByUserId(Integer userId);

}
