package com.demo.crud.example.controller;

import com.demo.crud.example.entity.CreateTeamDto;
import com.demo.crud.example.service.PagerDutyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class PagerDutyController {

    @Autowired
    PagerDutyService pagerDutyService;

    @PostMapping("/createTeam")
    public String createTeam(@RequestBody CreateTeamDto createTeamDto){
        int response = pagerDutyService.populateTeamWithDeveloper (createTeamDto);
        if(response == -1) return "Team not created";
        else
            return "Team created with teamid " + response;
    }

    @PostMapping("/sendAlert/{team_id}")
    public String sendAlert(@PathVariable Integer team_id, @RequestBody String message){
        String response = null;
        try {
            response = pagerDutyService.sendAlertToTeam (team_id, message);
        } catch (IOException e) {
            return "Error while sending the alert";
        }
        return response;
    }
}
