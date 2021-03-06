import java.util.*;

import dao.Sql2oTeamDao;
import dao.Sql2oTeamMemberDao;
import models.Team;
import models.TeamMember;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;


public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        String connectionString = "jdbc:h2:~/hackaton_teams.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        Sql2oTeamDao teamDao = new Sql2oTeamDao(sql2o);
        Sql2oTeamMemberDao teamMemberDao = new Sql2oTeamMemberDao(sql2o);

        // root
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Team> teams = teamDao.getAll();
            model.put("team", teams);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //delete all teams
        get("/team/delete", (request, response) -> {
            teamDao.clearAllTeams();
            response.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //delete team by id
        get("/team/:id/delete", (request, response) -> {
            int idOfTeamToDelete = Integer.parseInt(request.params("id"));
            teamDao.deleteById(idOfTeamToDelete);
            response.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());


        //add new team
        get("/team/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "team-update-form.hbs");
        }, new HandlebarsTemplateEngine());


        //process new team form
        post("/team", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String teamName = request.queryParams("teamName");
            String teamDescription = request.queryParams("teamDescription");
            Team newTeam = new Team();
            newTeam.setTeamName(teamName);
            newTeam.setTeamDescription(teamDescription);
            teamDao.add(newTeam);
            response.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //view individual team
        get("/team/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfTeamToFind = Integer.parseInt(req.params("id"));
            Team foundTeam = teamDao.findById(idOfTeamToFind);
            model.put("team", foundTeam);
            List<TeamMember> teamMembers = teamMemberDao.getAllMembersByTeamId(idOfTeamToFind);
            model.put("teamMembers", teamMembers);
            return new ModelAndView(model, "team-details.hbs");
        }, new HandlebarsTemplateEngine());

        //update individual team
        get("/team/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfTeamToEdit = Integer.parseInt(req.params("id"));
            Team editTeam = teamDao.findById(idOfTeamToEdit);
            model.put("editTeam", editTeam);
            return new ModelAndView(model, "team-update-form.hbs");
        }, new HandlebarsTemplateEngine());

        //process update form
        post("/team/:id", (request, response) -> {
            String newTeamName = request.queryParams("teamName");
            String newTeamDescription = request.queryParams("teamDescription");
            int idOfTeamToEdit = Integer.parseInt(request.params("id"));
            teamDao.update(idOfTeamToEdit, newTeamName, newTeamDescription);
            response.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //process new members
        post("/team/:id/addteammember", (request, response) -> {
            String teamMemberName = request.queryParams("teamMember");
            int teamId = Integer.parseInt(request.params("id"));
            TeamMember teamMember = new TeamMember();
            teamMember.setName(teamMemberName);
            teamMember.setTeamId(teamId);
            teamMemberDao.add(teamMember);
            response.redirect("/team/" + teamId);
            return null;
        }, new HandlebarsTemplateEngine());

        get("/team/:id/deleteteammember", (request, response) -> {
            int idOfTeamMemberToDelete = Integer.parseInt(request.params("id"));
            TeamMember teamMemberToDelete = teamMemberDao.findById(idOfTeamMemberToDelete);
            int teamId = teamMemberToDelete.getTeamId();
            teamMemberDao.deleteById(idOfTeamMemberToDelete);
            response.redirect("/team/" + teamId);
            return null;
        }, new HandlebarsTemplateEngine());

    }
}
