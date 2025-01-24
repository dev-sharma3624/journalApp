 package com.example.learningSpringboot.journalApp.controller;

import com.example.learningSpringboot.journalApp.entity.JournalEntry;
import com.example.learningSpringboot.journalApp.entity.User;
import com.example.learningSpringboot.journalApp.service.JournalEntryService;
import com.example.learningSpringboot.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

 @RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping("{username}")
    public ResponseEntity<List<JournalEntry>> getAllJournalEntriesOfUser(@PathVariable("username") String userName){
        User user = userService.findByUserName(userName);

        List<JournalEntry> all = user.getJournalEntries();
        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping("{userName}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry, @PathVariable("userName") String userName){
        try {
            journalEntryService.saveEntry(myEntry, userName);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable("myId") ObjectId myId){
         Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);
         if(journalEntry.isPresent()){
             return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
         }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("id/{userName}/{myId}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable("myId") ObjectId myId, @PathVariable("userName") String userName){
        journalEntryService.deleteById(myId, userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("id/{myId}")
    public ResponseEntity<?> updateJournalById(@PathVariable("myId") ObjectId myId,@RequestBody JournalEntry newEntry){

        JournalEntry oldEntry = journalEntryService.findById(myId).orElse(null);

        if(oldEntry != null){
            oldEntry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().isEmpty() ? newEntry.getTitle() : oldEntry.getTitle());
            oldEntry.setContent(newEntry.getContent() != null && !newEntry.getContent().isEmpty() ? newEntry.getContent() : oldEntry.getContent());
            journalEntryService.saveEntry(oldEntry);
            return new ResponseEntity<>(oldEntry, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}















