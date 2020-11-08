package com.ksu.soccerserver.matches;

import com.ksu.soccerserver.teams.Teams;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchesRepository extends JpaRepository<Matches, Long> {
}