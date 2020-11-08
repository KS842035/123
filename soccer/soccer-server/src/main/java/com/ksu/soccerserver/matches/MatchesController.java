package com.ksu.soccerserver.matches;


import com.ksu.soccerserver.teams.TeamsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/Matches")
@RequiredArgsConstructor
@RestController

public class MatchesController {

    private final MatchesRepository matchesRepository;
    private final TeamsRepository teamsRepository;

    @PostMapping
    public ResponseEntity<?> postMatches(@RequestBody Matches matches){
        matchesRepository.save(matches);
        return new ResponseEntity<>("Create new Matches", HttpStatus.CREATED);
    }


}
