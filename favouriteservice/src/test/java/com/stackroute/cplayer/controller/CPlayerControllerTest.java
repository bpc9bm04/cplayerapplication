
package com.stackroute.cplayer.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.cplayer.domain.CPlayer;
import com.stackroute.cplayer.service.CPlayerService;

@RunWith(SpringRunner.class)
@WebMvcTest(CPlayerController.class)
public class CPlayerControllerTest {

	/**
	 * Reference to MockMvc
	 */
	@Autowired
	private transient MockMvc mvc;
	@MockBean
	private transient CPlayerService cPlayerService;

	@Before
	public void setUp() {
	}

	/**
	 * to test save movie
	 * @throws Exception
	 */
	@Test
	public void testSaveNewPlayer_success() throws Exception {
		CPlayer player = new CPlayer(1, 33757, "Sachin", "Sachin Ramesh Tendulkar","tarun");
		String token="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0YXJ1biIsImlhdCI6MTU2OTI1MTMzNH0.HzPBkQth7hQwgxxrx_RUi9za6AG8CoX1FDSPFyqJO1c";
		when(cPlayerService.saveCPlayer(player)).thenReturn(true);
		mvc.perform(post("/api/v1/cplayerservice/cplayer").header("authorization", "Bearer " +  token).contentType(MediaType.APPLICATION_JSON).content(jsonToString(player))).andExpect(status().isCreated());
		verify(cPlayerService, times(1)).saveCPlayer(Mockito.any(CPlayer.class));
		verifyNoMoreInteractions(cPlayerService);
	}
	/**
	 * to test delete movie
	 * @throws Exception
	 */
	@Test
	public void testDeletePlayerById_success() throws Exception {
		String token="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0YXJ1biIsImlhdCI6MTU2OTI1MTMzNH0.HzPBkQth7hQwgxxrx_RUi9za6AG8CoX1FDSPFyqJO1c";
		when(cPlayerService.deleteCPlayerById("tarun", 33757)).thenReturn(true);
		mvc.perform(delete("/api/v1/cplayerservice/cplayer/{id}", 33757).header("authorization", "Bearer " +  token)).andExpect(status().isOk());
		verify(cPlayerService, times(1)).deleteCPlayerById("tarun", 33757);
		verifyNoMoreInteractions(cPlayerService);
	}

	/**
	 * to test list all movies
	 * @throws Exception
	 */
	@Test
	public void testGetMyCPlayers_success() throws Exception {
		String token="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0YXJ1biIsImlhdCI6MTU2OTI1MTMzNH0.HzPBkQth7hQwgxxrx_RUi9za6AG8CoX1FDSPFyqJO1c";
		when(cPlayerService.getMyCPlayers("tarun")).thenReturn(null);
		mvc.perform(get("/api/v1/cplayerservice/cplayer").header("authorization", "Bearer " +  token)).andExpect(status().isOk());
		verify(cPlayerService, times(1)).getMyCPlayers("tarun");
		verifyNoMoreInteractions(cPlayerService);
	}

	/**
	 * Parse string format object to json format
	 * @param object
	 * @return
	 * @throws JsonProcessingException
	 */
	private static String jsonToString(final Object object) throws JsonProcessingException {

		final ObjectMapper objMapper = new ObjectMapper();
		String stringValue;
		try {
			final String jsonValue = objMapper.writeValueAsString(object);
			stringValue = jsonValue;
		} catch (JsonProcessingException e) {
			stringValue = "Json processing exception";
		}
		return stringValue;
	}
}
