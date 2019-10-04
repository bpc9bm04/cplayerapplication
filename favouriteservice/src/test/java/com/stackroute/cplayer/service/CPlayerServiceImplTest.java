/**
 * 
 */
package com.stackroute.cplayer.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.stackroute.cplayer.domain.CPlayer;
import com.stackroute.cplayer.exception.CPlayerNotFoundException;
import com.stackroute.cplayer.exception.CPlayerAlreadyExistException;
import com.stackroute.cplayer.repository.CPlayerRepository;
import com.stackroute.cplayer.service.CPlayerServiceImpl;

/**
 * @author ubuntu
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class CPlayerServiceImplTest {

	@Mock
	private CPlayerRepository cPlayerRepositoryMock;

	private CPlayer cPlayer;

	@InjectMocks
	private CPlayerServiceImpl cPlayerServiceImpl;

	private Optional<CPlayer> options;

	@Before
	public void setUp() {
		cPlayer = new CPlayer(1, 101, "Sachin", "Sachin Ramesh Tendulkar","userId");
		options = Optional.of(cPlayer);
	}

	/**
	 * @throws CPlayerAlreadyExistException
	 */
	@Test
	public void testSaveCPlayerSuccess() throws CPlayerAlreadyExistException {
		when(cPlayerRepositoryMock.save(cPlayer)).thenReturn(cPlayer);
		boolean cPlayerSaved = cPlayerServiceImpl.saveCPlayer(cPlayer);
		assertTrue("cPlayer saved successfully", cPlayerSaved);
		verify(cPlayerRepositoryMock, times(1)).save(cPlayer);
	}

	/**
	 * @throws CPlayerAlreadyExistException
	 */
	@Test(expected = CPlayerAlreadyExistException.class)
	public void testSaveCPlayerFailure() throws CPlayerAlreadyExistException {
		when(cPlayerRepositoryMock.findByUserIdAndPid(cPlayer.getUserId(), cPlayer.getPid())).thenReturn(cPlayer);
		cPlayerServiceImpl.saveCPlayer(cPlayer);
	}

	
	/**
	 * @throws CPlayerNotFoundException
	 */
	@Test
	public void testDeleteMovieByIdSuccess() throws CPlayerNotFoundException {
		when(cPlayerRepositoryMock.findByUserIdAndPid(cPlayer.getUserId(), cPlayer.getPid())).thenReturn(cPlayer);
		boolean cPlayerDeleted = cPlayerServiceImpl.deleteCPlayerById(cPlayer.getUserId(), cPlayer.getPid());
		assertTrue("CPlayer deleted successfully", cPlayerDeleted);
		verify(cPlayerRepositoryMock, times(1)).findByUserIdAndPid(cPlayer.getUserId(), cPlayer.getPid());
		verify(cPlayerRepositoryMock, times(1)).deleteByUserIdAndPid(cPlayer.getUserId(), cPlayer.getPid());
	}

	/**
	 * @throws CPlayerNotFoundException
	 */
	@Test(expected = CPlayerNotFoundException.class)
	public void testDeleteMovieByIdFailure() throws CPlayerNotFoundException {
		cPlayerServiceImpl.deleteCPlayerById(cPlayer.getUserId(),cPlayer.getPid());
	}

	@Test
	public void testGetAllCPlayers() {
		List<CPlayer> cPlayers = new ArrayList<CPlayer>();
		cPlayers.add(cPlayer);
		when(cPlayerRepositoryMock.findByUserId("userId")).thenReturn(cPlayers);
		List<CPlayer> Movies = cPlayerServiceImpl.getMyCPlayers("userId");
		assertEquals(Movies, cPlayers);
		verify(cPlayerRepositoryMock, times(1)).findByUserId("userId");
	}
}
