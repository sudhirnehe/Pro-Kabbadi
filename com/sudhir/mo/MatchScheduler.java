package com.sudhir.mo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class MatchScheduler  {
	
	
	private List<Match> initializeMatches(List<Team> teams) {
		// Define matches to be return
		List<Match> matches = new ArrayList<Match>();

		// Initialize matches
		for (Team teamA : teams) {
			for (Team teamB : teams) {
				if (teamA.getId() != teamB.getId()) {
					Match match = new Match(teamA, teamB);
					match.setLocation(teamA.getCity());
					
					matches.add(match);
				}
			}
		}

		// Return list of matches
		return matches;
	}
	
	private boolean isMatchScheduleRemaining(List<Match> matches) {
		for (Match match : matches) {
			if (match.getDate() == null) {
				return true;
			}
		}
		return false;
	}
	
	private List<Integer> prepareParticipatedTeamList(List<Match> matches, LocalDate date) {
		List<Integer> teamIds = new ArrayList<Integer>();
		for (Match match : matches) {
			if (match.getDate() != null) {
				if (date.equals(match.getDate())) {
					teamIds.add(match.getTeamA().getId());
					teamIds.add(match.getTeamB().getId());
				}
			}
		}
		return teamIds;
	}
	
	public List<Match> generate(List<Team> teams, Date startDate) {
		// Generate matches from combination of teams
		List<Match> matches = initializeMatches(teams);
		

		// Define current date
		LocalDate localDate = LocalDate.now();
		
		// Loop till one of the match date has not been scheduled
		while (isMatchScheduleRemaining(matches)) {
			
			// get list of team id already played on yesterday
			List<Integer> participatedTeamIds = prepareParticipatedTeamList(matches, localDate.minusDays(1));

			// Swap object in list 
			Random random = new Random();
			Collections.swap(matches, random.nextInt(matches.size()), random.nextInt(matches.size()));
			
			List<Match> todaysMatchList	=	new ArrayList<>();
			
			for (Match match : matches) {
				if (match.getDate() == null &&
						!participatedTeamIds.contains(match.getTeamA().getId()) &&
						!participatedTeamIds.contains(match.getTeamB().getId()) &&
						todaysMatchList.size() != 2) {
					match.setDate(localDate);
					participatedTeamIds.add(match.getTeamA().getId());
					participatedTeamIds.add(match.getTeamB().getId());
					
					todaysMatchList.add(match);
				}
			}
			
			// Increment current date by 1 days
			localDate	=	localDate.plusDays(1);
		}

		return matches;
	}
	
	public static void main(String[] args) {
		
		TeamDao teamDao		=	new TeamDao();
		
		List<Team> teamList = new ArrayList<Team>();
		teamList.add(teamDao.get(1));
		teamList.add(teamDao.get(2));
		teamList.add(teamDao.get(3));
		teamList.add(teamDao.get(4));
		teamList.add(teamDao.get(5));
		teamList.add(teamDao.get(6));
		
		List<Match> matches = new MatchScheduler().generate(teamList, new Date());
		
		System.out.println(" --------------------------------------------------------------------------------------------------------------------------------------------");
		
		matches.stream().sorted((m1,m2)->m1.getDate().compareTo(m2.getDate())).forEach(System.out::println);
		
		System.out.println(" --------------------------------------------------------------------------------------------------------------------------------------------");
		
	}
	
}
