package com.ksu.soccerserver.accounts;


import com.ksu.soccerserver.teams.Teams;
import com.ksu.soccerserver.teams.TeamsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/accounts")
@RestController
public class AccountController {

    private final AccountRepository accountRepository;
    private final TeamsRepository teamsRepository;

    public AccountController(AccountRepository accountRepository, TeamsRepository teamsRepository){
        this.accountRepository = accountRepository;
        this.teamsRepository = teamsRepository;
    }

    @PostMapping
    public ResponseEntity<?> postAccount(@RequestBody Account account){
        accountRepository.save(account);
        return new ResponseEntity<>("Create new Account", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAccount(){
        List<Account> accounts = accountRepository.findAll();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<?> getAccount(@PathVariable Long accountId){
        Account account = accountRepository.findById(accountId).get();
        return  new ResponseEntity<>(account, HttpStatus.OK);
    }

    @PutMapping("/{accountId}")
    public ResponseEntity<?> putAccount(@PathVariable Long accountId, @RequestBody Account account){
        Account findAccount = accountRepository.findById(accountId).get();
        findAccount.setEmail(account.getEmail());
        findAccount.setPassword(account.getPassword());
        accountRepository.save(findAccount);

        return new ResponseEntity<>(findAccount, HttpStatus.OK);
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long accountId){
        accountRepository.deleteById(accountId);
        return new ResponseEntity<>("Delete accountId:" + accountId, HttpStatus.OK);


    }

    @PutMapping("/{accountId}/join/{teamId}")
    public ResponseEntity<?>joinTeams(@PathVariable Long accountId, @PathVariable Long teamId){
        Account account = accountRepository.findById(accountId).get();
        Teams teams = teamsRepository.findById(teamId).get();

        account.joinTeam(teams);
        Account saveAccount = accountRepository.save(account);

        return new ResponseEntity<>(saveAccount, HttpStatus.OK);
    }

}
