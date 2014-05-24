package com.dhlab.xephyr.bukkit.teams;

import com.dhlab.xephyr.bukkit.teams.color.TeamIdentifier;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.Collection;
import java.util.UUID;

/**
 * Generic Team interface.
 */
public interface Team extends Listener {
    public TeamIdentifier getIdentifier();
    public Collection<UUID> getMemberList();
    public UUID[] getMembers();
    void add(Player player);
    void add(UUID id);
    void remove(Player player);
    void remove(UUID id);
    boolean contains(Player player);
    boolean contains(UUID id);
}
