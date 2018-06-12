import java.util.*;

import dao.Sql2oTeamDao;
import models.Team;
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

        // root
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Team> teams = teamDao.getAll();
            model.put("team", teams);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //delete all teams
        get("/team/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            teamDao.clearAllTeams();
            response.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //delete team by id
        get("/team/:id/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
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
            Map<String, Object> model = new HashMap<>();
            String newTeamName = request.queryParams("teamName");
            int idOfTeamToEdit = Integer.parseInt(request.params("id"));
            teamDao.update(idOfTeamToEdit, newTeamName);
            response.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

    }
}
