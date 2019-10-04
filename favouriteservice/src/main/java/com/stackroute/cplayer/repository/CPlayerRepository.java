/**
 * 
 */
package com.stackroute.cplayer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stackroute.cplayer.domain.CPlayer;

/**
 * @author ubuntu
 *
 */
@Repository
public interface CPlayerRepository extends JpaRepository<CPlayer, Integer> {
	List<CPlayer> findByUserId(String userId);

	CPlayer findByUserIdAndPid(String userId, int pid);

	@Transactional
	public void deleteByUserIdAndPid(String userId, int pid);
}
