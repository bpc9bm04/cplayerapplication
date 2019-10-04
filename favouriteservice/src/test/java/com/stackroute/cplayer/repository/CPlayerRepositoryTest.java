package com.stackroute.cplayer.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.stackroute.cplayer.domain.CPlayer;
import com.stackroute.cplayer.repository.CPlayerRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Transactional
public class CPlayerRepositoryTest {

	private transient CPlayerRepository cPlayerRepository;
	
	@Autowired
	public void setcPlayerRepository(CPlayerRepository cPlayerRepository) {
		this.cPlayerRepository = cPlayerRepository;
	}

	@Test
	public void testSaveCPlayer() {
		cPlayerRepository.save(new CPlayer(1, 101, "Sachin", "Sachin Ramesh Tendulkar","userId"));
		List<CPlayer> movies = cPlayerRepository.findByUserId("userId");
		assertEquals(101, movies.get(0).getPid());
	}

	@Test
	public void testDeleteCPlayer() {
		cPlayerRepository.save(new CPlayer(1, 101, "Sachin", "Sachin Ramesh Tendulkar","userId"));
		CPlayer cPlayer = cPlayerRepository.findByUserId("userId").get(0);
		cPlayerRepository.delete(cPlayer);
		assertFalse(cPlayerRepository.existsById(1));
	}

	@Test
	public void testGetMyCPlayers() {
		cPlayerRepository.save(new CPlayer(1, 101, "Sachin", "Sachin Ramesh Tendulkar","userId"));
		cPlayerRepository.save(new CPlayer(2, 202, "Sahwag", "Virendar Sahwag", "userId"));
		List<CPlayer> cPlayers = cPlayerRepository.findByUserId("userId");
		assertTrue(cPlayers.size()>=2);
	}
}
