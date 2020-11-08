package com.ksu.soccerserver.teams;

import com.ksu.soccerserver.accounts.Account;
import com.ksu.soccerserver.accounts.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequestMapping("/api/teams")

@RequiredArgsConstructor

@RestController
public class TeamsController {
    private final TeamsRepository teamsRepository;
    private final AccountRepository accountRepository;

    @PostMapping
    ResponseEntity<?> postTeams(@RequestBody Teams teams) {
        teamsRepository.save(teams);
        return new ResponseEntity<>("Create Teams", HttpStatus.CREATED);

    }

    @GetMapping
    ResponseEntity<?> getTeams(){
        List<Teams> teams = teamsRepository.findAll();
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    @GetMapping("/{teamId}")
    ResponseEntity<?> getTeam(@PathVariable Long teamId){
        Teams teams = teamsRepository.findById(teamId).get();
        return new ResponseEntity<>(teams, HttpStatus.OK);

    }

    @GetMapping("/{teamId}/members")
    ResponseEntity<?> getTeammember(@PathVariable Long teamId){
        Teams teams = teamsRepository.findById(teamId).get();
        List<Account> accounts = accountRepository.findAll();
        List<Account> accountList = new ArrayList<>();

        for(int i=0; i< accounts.size(); i++){
            if(accounts.get(i).getTeams().getName()== teams.getName())
            {
                accountList.add(accounts.get(i));

            }
        }
        return new ResponseEntity<>(accountList, HttpStatus.OK);


    }

    @PutMapping("/{teamId}")
    ResponseEntity<?> putteam(@PathVariable Long teamId, @RequestBody Teams teams){
        Teams findteam = teamsRepository.findById(teamId).get();
        findteam.setName(teams.getName());
        findteam.setLocation(teams.getLocation());
        teamsRepository.save(findteam);
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    @DeleteMapping("/{teamId}")
    ResponseEntity<?> deleteteam(@PathVariable Long teamId){
        teamsRepository.deleteById(teamId);
        return new ResponseEntity<>("teamsId :" + teamId, HttpStatus.OK);
    }





}
