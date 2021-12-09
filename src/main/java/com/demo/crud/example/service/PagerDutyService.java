package com.demo.crud.example.service;

import com.demo.crud.example.entity.CreateTeamDto;
import com.demo.crud.example.entity.Developer;
import com.demo.crud.example.entity.Team;
import com.demo.crud.example.repository.DeveloperRepository;
import com.demo.crud.example.repository.TeamRepository;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class PagerDutyService {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    DeveloperRepository developerRepository;

    public Integer populateTeamWithDeveloper(CreateTeamDto createTeamDto){
        try {
            List<Developer> developerList = createTeamDto.getDevelopers ();
            Team team = teamRepository.save (new Team (createTeamDto.getTeam ().getName ()));
            //null check
            for (Developer developer : developerList) {
                developerRepository.save (new Developer (team.getId (), developer.getName (), developer.getPhone_number ()));
            }
            return team.getId ();
        }catch (Exception e){
            return -1;
        }
    }

    public String sendAlertToTeam(Integer team_id, String message) throws IOException {
        Optional<Team> team = teamRepository.findById (team_id);
        if(team.isPresent ()){
            List<Developer> developerList = developerRepository.findByTeamId (team_id);
            int size = developerList.size ();
            Random random = new Random ();
            int randomIndex = random.nextInt (size);
            Developer developer = developerList.get (randomIndex);
            return sendAlertToDeveloper(developer, message);
        }
        return "Team is not with team id " + team_id;
    }

    private String sendAlertToDeveloper(Developer developer, String message) throws IOException {
        //Post API
        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost("https://run.mocky.io/v3/fd99c100-f88a-4d70-aaf7-393dbbd5d99f");
        HttpResponse response = httpclient.execute (httppost);
        if(response.getStatusLine ().getStatusCode () == 200){
            return "Successfully sent message :" + message + " to phone number " + developer.getPhone_number ()  ;
        }else{
            return "Failed to sent message :" + message + " to phone number " + developer.getPhone_number () ;
        }
    }
}
