package com.sudhir.mo;

import java.util.HashMap;
import java.util.Map;

public class TeamDao {
	
	private Map<Integer, Team> teamMap = new HashMap<Integer, Team>();
	
	public TeamDao() {
		initializeTeam();
	}
	
	// TODO : As soon as DB supports get added below method should get removed
	private void initializeTeam() {
		teamMap.put(1, prepareTeam(1, "Team 1", "Team 1 Description", "Location 1"));
		teamMap.put(2, prepareTeam(2, "Team 2", "Team 2 Description", "Location 2"));
		teamMap.put(3, prepareTeam(3, "Team 3", "Team 3 Description", "Location 3"));
		teamMap.put(4, prepareTeam(4, "Team 4", "Team 4 Description", "Location 4"));
		teamMap.put(5, prepareTeam(5, "Team 5", "Team 5 Description", "Location 5"));
		teamMap.put(6, prepareTeam(6, "Team 6", "Team 6 Description", "Location 6"));
	}
	
	private Team prepareTeam(int id, String name, String description, String city) {
		Team team = new Team(id);
		team.setName(name);
		team.setDescription(description);
		team.setCity(city);
		
		return team;
	}

	public Team get(int id) {
		return teamMap.get(id);
	}

}