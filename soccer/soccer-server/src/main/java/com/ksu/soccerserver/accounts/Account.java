package com.ksu.soccerserver.accounts;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ksu.soccerserver.grouptable.GroupTable;
import com.ksu.soccerserver.teams.Teams;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter @Setter @Builder
@Entity
@Table
@NoArgsConstructor @AllArgsConstructor

public class Account{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;

    @Column
    private String password;

    @ManyToOne
    private Teams teams;

    @JsonIgnore
    @OneToMany (mappedBy = "accounts")
    private Set<GroupTable> groupTable;

    public void joinTeam(Teams teams){
        this.teams = teams;
    }


}
