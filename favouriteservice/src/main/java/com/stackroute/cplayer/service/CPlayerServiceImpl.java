package com.stackroute.cplayer.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.cplayer.domain.CPlayer;
import com.stackroute.cplayer.exception.CPlayerNotFoundException;
import com.stackroute.cplayer.exception.CPlayerAlreadyExistException;
import com.stackroute.cplayer.repository.CPlayerRepository;
@Service
public class CPlayerServiceImpl implements CPlayerService {
	
	private CPlayerRepository cPlayerRepository;
	
	@Autowired
	public CPlayerServiceImpl(CPlayerRepository cPlayerRepository) {
		this.cPlayerRepository = cPlayerRepository;
	}

	@Override
	public boolean saveCPlayer(CPlayer cPlayer) throws CPlayerAlreadyExistException {
		CPlayer cPlayerFromDB=cPlayerRepository.findByUserIdAndPid(cPlayer.getUserId(), cPlayer.getPid());
		if(Objects.nonNull(cPlayerFromDB)) {
			throw new CPlayerAlreadyExistException("CPlayer already present in DB, can't save it!");
		}
		cPlayerRepository.save(cPlayer);
		return true;
	}

	@Override
	public boolean deleteCPlayerById(String userId, int pid) throws CPlayerNotFoundException {
		CPlayer cPlayerFromDB=cPlayerRepository.findByUserIdAndPid(userId, pid);
		if(Objects.isNull(cPlayerFromDB)) {
			throw new CPlayerNotFoundException("CPlayer does not exist in DB, can't delete it!");
		}
		cPlayerRepository.deleteByUserIdAndPid(userId, pid);
		return true;
	}

	@Override
	public List<CPlayer> getMyCPlayers(String userId) {
		return cPlayerRepository.findByUserId(userId);
	}

}
