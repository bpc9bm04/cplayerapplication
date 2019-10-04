package com.stackroute.cplayer.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.cplayer.domain.CPlayer;
import com.stackroute.cplayer.exception.CPlayerAlreadyExistException;
import com.stackroute.cplayer.exception.CPlayerNotFoundException;
import com.stackroute.cplayer.service.CPlayerService;

import io.jsonwebtoken.Jwts;

@RestController
@RequestMapping(path = "/api/v1/cplayerservice")
@CrossOrigin
public class CPlayerController {
	
	private CPlayerService cPlayerService;
	
	@Autowired
	public CPlayerController(CPlayerService cPlayerService) {
		this.cPlayerService = cPlayerService;
	}

	/**
	 * This method is used to Save a new C Player in DB
	 * 
	 * @param movie
	 * @return
	 */
	@PostMapping("/cplayer")
	public ResponseEntity<?> saveNewPlayer(@RequestBody final CPlayer cPlayer, HttpServletRequest request, HttpServletResponse response) {
		ResponseEntity<?> responseEntity;
		try {
			final String authHeader = request.getHeader("authorization");
			final String token = authHeader.substring(7);
			String userId = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();
			cPlayer.setUserId(userId);
			cPlayerService.saveCPlayer(cPlayer);
			responseEntity = new ResponseEntity<CPlayer>(cPlayer, HttpStatus.CREATED);
		} catch (CPlayerAlreadyExistException e) {
			responseEntity = new ResponseEntity<String>("message:" + e.getMessage(), HttpStatus.CONFLICT);
		}
		return responseEntity;

	}

	@DeleteMapping(value = "/cplayer/{pid}")
	public ResponseEntity<?> deletePlayerById(@PathVariable("pid") final int pid,HttpServletRequest request, HttpServletResponse response) {
		ResponseEntity<?> responseEntity;
		try {
			final String authHeader = request.getHeader("authorization");
			final String token = authHeader.substring(7);
			String userId = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();
			cPlayerService.deleteCPlayerById(userId, pid);
			responseEntity = new ResponseEntity<String>(HttpStatus.OK);
		} catch (CPlayerNotFoundException e) {
			responseEntity = new ResponseEntity<String>("message:" + e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}

	/**
	 * This method is used to fetch all my existing CPlayers from DB
	 * @return List<CPlayer>
	 */
	@GetMapping("/cplayer")
	public ResponseEntity<List<CPlayer>> getMyCPlayers(HttpServletRequest request, HttpServletResponse response) {
		final String authHeader = request.getHeader("authorization");
		final String token = authHeader.substring(7);
		String userId = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();
		return new ResponseEntity<List<CPlayer>>(cPlayerService.getMyCPlayers(userId), HttpStatus.OK);
	}
}
