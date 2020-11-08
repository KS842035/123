package com.ksu.soccerserver.teams;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ksu.soccerserver.accounts.Account;
import com.ksu.soccerserver.grouptable.GroupTable;
import com.ksu.soccerserver.matches.Matches;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter @Setter @Builder
@Entity
@Table
@NoArgsConstructor @AllArgsConstructor

public class Teams {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String location;

    @JsonIgnore
    @OneToMany(mappedBy = "teams")
    private Set<Account> accounts;

    @JsonIgnore
    @OneToMany (mappedBy = "teams")
    private Set<GroupTable> groupTable;

    @JsonIgnore
    @OneToMany (mappedBy = "teams")
    private Set<Matches> matches;




}
