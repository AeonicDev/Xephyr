package com.dhlab.xephyr.bukkit.teams.impl;

import com.dhlab.xephyr.bukkit.teams.Team;
import com.dhlab.xephyr.bukkit.teams.color.TeamIdentifier;
import org.bukkit.entity.Player;

import java.util.*;

/**
 * Generic team implementation.
 * @author maladr0it
 */
public class GenericTeam implements Team {
    protected final TeamIdentifier identifier;
    protected final Map<UUID, Boolean> members = new HashMap <UUID, Boolean>();

    public GenericTeam(TeamIdentifier id) {
        this.identifier = id;
    }

    @Override
    public TeamIdentifier getIdentifier() {
        return identifier;
    }

    @Override
    public Collection<UUID> getMemberList() {
        return Collections.unmodifiableCollection(members.keySet());
    }

    public UUID[] getMembers() {
        return members.values().toArray(new UUID[members.size()]);
    }

    @Override
    public void add(Player player) {
        members.put(player.getUniqueId(), true);
    }

    @Override
    public void add(UUID id) {
        members.put(id, true);
    }

    @Override
    public void remove(Player player) {
        members.remove(player.getUniqueId());
    }

    @Override
    public void remove(UUID id) {
        members.remove(id);
    }

    @Override
    public boolean contains(Player player) {
        return members.containsKey(player.getUniqueId());
    }

    @Override
    public boolean contains(UUID id) {
        return members.containsKey(id);
    }
}
