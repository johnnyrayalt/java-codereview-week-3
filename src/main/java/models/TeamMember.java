package models;

import java.util.Objects;

public class TeamMember {
    private String name;
    private int teamId;
    private int id;

    public TeamMember() {
        this.name = name;
        this.teamId = teamId;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getTeamId() { return teamId; }
    public void setTeamId(int teamId) { this.teamId = teamId; }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamMember that = (TeamMember) o;
        return teamId == that.teamId &&
                id == that.id &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, teamId, id);
    }
}
