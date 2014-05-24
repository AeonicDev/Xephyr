package com.dhlab.xephyr.bukkit.teams;

import com.dhlab.xephyr.bukkit.plugin.PluginBootstrapper;
import com.dhlab.xephyr.bukkit.teams.color.TeamIdentifier;
import com.dhlab.xephyr.generic.Enableable;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Generic manager for teams.
 * @author maladr0it
 */
public class TeamManager implements Enableable {

    private final PluginBootstrapper bootstrapper;
    protected final Map<TeamIdentifier, Team> teams = new HashMap<TeamIdentifier, Team>();

    public TeamManager(PluginBootstrapper bootstrapper) {
        this.bootstrapper = bootstrapper;
    }


    public void addTeam(Team team) {
        if (team == null)
            throw new NullPointerException("Team cannot be null!");
        teams.put(team.getIdentifier(), team);
    }

    public void removeTeam(TeamIdentifier ident) {
        teams.remove(ident);
    }

    public Team[] getTeams() {
        return teams.values().toArray(new Team[teams.size()]);
    }

    @Override
    public void onEnable() {
        for (Team team : getTeams()) {
            Bukkit.getPluginManager().registerEvents(team, bootstrapper.getPlugin());
        }
    }

    @Override
    public void onDisable() {
        for (Team team : getTeams()) {
            HandlerList.unregisterAll(team);
        }
    }

    public TeamIdentifier findTeam(Player p) {
        for (Team team : getTeams()) {
            if (team.contains(p))
                return team.getIdentifier();
        }
        return null;
    }

    public TeamIdentifier findTeam(UUID id) {
        for (Team team : getTeams()) {
            if (team.contains(id))
                return team.getIdentifier();
        }
        return null;
    }
}
