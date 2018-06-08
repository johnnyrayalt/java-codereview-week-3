package models;

import java.util.Objects;

public class Team {
    private String teamName;
    private String teamDescription;
    private int id;

    public Team() {
        this.teamName = teamName;
        this.teamDescription = teamDescription;
    }

    public String getTeamName() {
        return teamName;
    }
    public void setTeamName(String teamName) { this.teamName = teamName; }

    public String getTeamDescription() { return teamDescription; }
    public void setTeamDescription(String teamDescription) { this.teamDescription = teamDescription; }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return id == team.id &&
                Objects.equals(teamName, team.teamName) &&
                Objects.equals(teamDescription, team.teamDescription);
    }

    @Override
    public int hashCode() {

        return Objects.hash(teamName, teamDescription, id);
    }
}
