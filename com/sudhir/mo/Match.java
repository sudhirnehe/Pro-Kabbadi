package com.sudhir.mo;

import java.time.LocalDate;

public class Match {

	private final Team teamA;
	private final Team teamB;
	private String location;
	private LocalDate date;

	public Match(Team teamA, Team teamB) {
		this.teamA = teamA;
		this.teamB = teamB;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Team getTeamA() {
		return teamA;
	}

	public Team getTeamB() {
		return teamB;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Match of '");
		builder.append(teamA.getName());
		builder.append("' with '");
		builder.append(teamB.getName());
		builder.append("' at location '");
		builder.append(location);
		builder.append("' on date '");
		builder.append(date.toString());
		builder.append("' ");
		return builder.toString();
	}

}