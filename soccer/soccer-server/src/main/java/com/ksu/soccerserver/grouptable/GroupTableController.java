package com.ksu.soccerserver.grouptable;

import com.ksu.soccerserver.accounts.Account;
import com.ksu.soccerserver.accounts.AccountRepository;
import com.ksu.soccerserver.teams.Teams;
import com.ksu.soccerserver.teams.TeamsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api/GroupTable")
@RequiredArgsConstructor
@RestController
public class GroupTableController {

    private final GroupTableRepository groupTableRepository;
    private final AccountRepository accountRepository;
    private final TeamsRepository teamsRepository;

    ///////////////////////////
    //유저->팀
    @PostMapping("/accounts/{accountId}/apply/{teamId}")
    public ResponseEntity<?> postApply(@PathVariable Long accountId, @PathVariable Long teamId) {


        Account accounts = accountRepository.findById(accountId).get();
        Teams teams = teamsRepository.findById(teamId).get();
        GroupTable groupTable = groupTableRepository.save(GroupTable.builder().build());
        List<GroupTable> groupTables = groupTableRepository.findAll();


        groupTable.joinTeam(accounts,teams);
      /*  for(int i=0; i< groupTables.size();i++) {
            if (accounts.getId()== groupTables.get(i).getAccounts().getId()) {
                if (teams.getName() == groupTables.get(i).getTeams().getName()) {
                    return new ResponseEntity<>("already apply teams", HttpStatus.OK);
                }
            }
        }*/
        GroupTable savegroupTable =groupTableRepository.save(groupTable);


        return new ResponseEntity<>(savegroupTable, HttpStatus.CREATED);

    }

    @GetMapping("/accounts/{accountId}/apply")
    public ResponseEntity<?> getApply(@PathVariable Long accountId){
        Account account = accountRepository.findById(accountId).get();
        List<GroupTable> groupTables = groupTableRepository.findAll();
        List<GroupTable> groupTableList = new ArrayList<>();

        for(int i=0; i< groupTables.size();i++){
            if(groupTables.get(i).getAccounts().getId() == account.getId())
            {
                groupTableList.add(groupTables.get(i));
            }
        }
        return new ResponseEntity<>(groupTableList, HttpStatus.OK);

    }

    @PutMapping("/accounts/{accountId}/apply/{grouptableId}")
    public ResponseEntity<?> putApply(@PathVariable Long accountId, @PathVariable Long grouptableId,@RequestBody GroupTable groupTable){
        GroupTable findgroupTable = groupTableRepository.findById(grouptableId).get();
        findgroupTable.setStatus(groupTable.getStatus());
        groupTableRepository.save(findgroupTable);

        return new ResponseEntity<>(findgroupTable, HttpStatus.OK);

    }

    @DeleteMapping("/accounts/{accountId}/apply/{grouptableId}")
    public ResponseEntity<?> deleteApply(@PathVariable Long accountId, @PathVariable Long grouptableId){
        GroupTable groupTable = groupTableRepository.findById(grouptableId).get();

        groupTableRepository.deleteById(grouptableId);
        return new ResponseEntity<>("Delete apply team   " + groupTable.getTeams().getName(), HttpStatus.OK );

    }


    /////////////////////////
    //팀->유저
    @PostMapping("/teams/{teamId}/offer/{accountId}")
    public ResponseEntity<?> postOffer(@PathVariable Long teamId, @PathVariable Long accountId) {
        Account accounts = accountRepository.findById(accountId).get();
        Teams teams = teamsRepository.findById(teamId).get();
        GroupTable groupTable = groupTableRepository.save(GroupTable.builder().build());
        List<GroupTable> groupTables = groupTableRepository.findAll();


        groupTable.joinTeam(accounts,teams);
      /*  for(int i=0; i< groupTables.size();i++) {
            if (accounts.getId()== groupTables.get(i).getAccounts().getId()) {
                if (teams.getName() == groupTables.get(i).getTeams().getName()) {
                    return new ResponseEntity<>("already apply teams", HttpStatus.OK);
                }
            }
        }*/
        GroupTable savegroupTable =groupTableRepository.save(groupTable);


        return new ResponseEntity<>(savegroupTable, HttpStatus.CREATED);

    }

    @GetMapping("/teams/{teamId}/offer")
    public ResponseEntity<?> getOffer(@PathVariable Long teamId){
        Teams teams = teamsRepository.findById(teamId).get();
        List<GroupTable> groupTables = groupTableRepository.findAll();
        List<GroupTable> groupTableList = new ArrayList<>();

        for(int i=0; i< groupTables.size();i++){
            if(groupTables.get(i).getAccounts().getId() == teams.getId())
            {
                groupTableList.add(groupTables.get(i));
            }
        }
        return new ResponseEntity<>(groupTableList, HttpStatus.OK);

    }

    @PutMapping("/teams/{teamId}/offer/{grouptableId}")
    public ResponseEntity<?> putOffer(@PathVariable Long teamId, @PathVariable Long grouptableId,@RequestBody GroupTable groupTable){
        GroupTable findgroupTable = groupTableRepository.findById(grouptableId).get();
        findgroupTable.setStatus(groupTable.getStatus());
        groupTableRepository.save(findgroupTable);

        return new ResponseEntity<>(findgroupTable, HttpStatus.OK);

    }

    @DeleteMapping("/teams/{teamId}/offer/{grouptableId}")
    public ResponseEntity<?> deleteOffer(@PathVariable Long teamId, @PathVariable Long grouptableId){
        GroupTable groupTable = groupTableRepository.findById(grouptableId).get();
        groupTableRepository.deleteById(grouptableId);

        return new ResponseEntity<>("Delete offer user    " + groupTable.getAccounts().getEmail(), HttpStatus.OK);
    }


}
