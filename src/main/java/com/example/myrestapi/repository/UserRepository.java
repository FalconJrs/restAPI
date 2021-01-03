package com.example.myrestapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.myrestapi.entity.User;

//JpaRepository<Table_name, PK_type>
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUserId(Long userId);

	List<User> findByActiveAndVerifyCheck(Boolean active, Boolean verifyCheck);

	@Modifying(clearAutomatically = true)
	@Query(
			value="UPDATE user u SET u.active=:x WHERE u.active=:y ;",
			  nativeQuery = true)
	void updateActive(@Param("x") Boolean active, @Param("y") boolean active2);


	/*
	 * @Modifying(clearAutomatically = true)
	 * 
	 * @Query("update RssFeedEntry feedEntry set feedEntry.read =:isRead where feedEntry.id =:entryId"
	 * ) void markEntryAsRead(@Param("entryId") Long
	 * rssFeedEntryId, @Param("isRead") boolean isRead);
	 */
}
