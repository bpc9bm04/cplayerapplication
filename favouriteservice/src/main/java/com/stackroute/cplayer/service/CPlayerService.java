package com.stackroute.cplayer.service;

import java.util.List;

import com.stackroute.cplayer.domain.CPlayer;
import com.stackroute.cplayer.exception.CPlayerNotFoundException;
import com.stackroute.cplayer.exception.CPlayerAlreadyExistException;

public interface CPlayerService {
	
/**Method declaration for saving the CPlayer
 * @param cPlayer
 * @return boolean
 * @throws CPlayerAlreadyExistException
 */
boolean saveCPlayer(CPlayer cPlayer) throws CPlayerAlreadyExistException;

/**Method declaration for deleting the CPlayer
 * @param userId
 * @param pid
 * @return boolean
 * @throws CPlayerNotFoundException
 */
boolean deleteCPlayerById(String userId, int pid) throws CPlayerNotFoundException;

/**Method declaration for getting all the CPlayers from DB based on User Id
 * @param userId
 * @return List<CPlayer>
 */
List<CPlayer> getMyCPlayers(String userId);
}
