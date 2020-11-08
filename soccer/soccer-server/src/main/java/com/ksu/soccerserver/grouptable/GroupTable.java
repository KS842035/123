package com.ksu.soccerserver.grouptable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ksu.soccerserver.accounts.Account;
import com.ksu.soccerserver.teams.Teams;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter @Setter @Builder
@Entity
@Table
@NoArgsConstructor @AllArgsConstructor
public class GroupTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Account accounts;

    @ManyToOne
    private Teams teams;

    @Column
    private String status;


    public void joinTeam(Account account,Teams team) {
        this.accounts = account;
        this.teams = team;
        this.status = "waiting";
    }




}
