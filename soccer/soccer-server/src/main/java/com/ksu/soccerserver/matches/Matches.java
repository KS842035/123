package com.ksu.soccerserver.matches;

import com.ksu.soccerserver.teams.Teams;
import lombok.*;

import javax.persistence.*;

@Getter @Setter @Builder
@Entity
@Table
@NoArgsConstructor @AllArgsConstructor
public class Matches {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String location;

    @ManyToOne
    private Teams teams;



}
