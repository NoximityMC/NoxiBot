package com.noximity.tools;

import com.noximity.bot.Bot;
import com.noximity.console.Console;
import com.noximity.console.LogType;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.ArrayList;
import java.util.List;

public class Permissions {

    private final Bot bot;

    private final List<String> warnRoles = new ArrayList<>();
    private final List<String> kickRoles = new ArrayList<>();
    private final List<String> timeoutRoles = new ArrayList<>();
    private final List<String> banRoles = new ArrayList<>();
    private final List<String> clearRoles = new ArrayList<>();
    private final List<String> userInfoRoles = new ArrayList<>();

    public Permissions(Bot bot) {
        this.bot = bot;
        assignRoles();
    }

    // Other methods


    private void assignRoles() {
        assignWarnRoles();
        assignKickRoles();
        assignTimeoutRoles();
        assignBanRoles();
        assignClearRoles();
        assignUserInfoRoles();

    }

    /**
     * The assignWarnRoles function is used to assign the roles that are allowed to use the warn command.
     * It does this by adding all the roles listed in cmd-perms.warn in config.yml into a list called warnRoles, which is then used later on when checking if a user has permission to use this command or not.
     */
    private void assignWarnRoles() {
        try {
            warnRoles.addAll(bot.getConfig().getStringList("cmd-perms.warn"));
        } catch (Exception e) {
            Console.log(LogType.WARNING, "No roles for the warn command were found. Please add them to the config file.");
        }
    }

    private void assignTimeoutRoles() {
        try {
            timeoutRoles.addAll(bot.getConfig().getStringList("cmd-perms.timeout"));
        } catch (Exception e) {
            Console.log(LogType.WARNING, "No roles for the timeout command were found. Please add them to the config file.");
        }
    }

    private void assignUserInfoRoles() {
        try {
            userInfoRoles.addAll(bot.getConfig().getStringList("cmd-perms.userinfo"));
        } catch (Exception e) {
            Console.log(LogType.WARNING, "No roles for the user-info command were found. Please add them to the config file.");
        }
    }

    private void assignClearRoles() {
        try {
            clearRoles.addAll(bot.getConfig().getStringList("cmd-perms.clear"));
        } catch (Exception e) {
            Console.log(LogType.WARNING, "No roles for the clear command were found. Please add them to the config file.");
        }
    }

    private void assignBanRoles() {
        try {
            banRoles.addAll(bot.getConfig().getStringList("cmd-perms.ban"));
        } catch (Exception e) {
            Console.log(LogType.WARNING, "No roles for the ban command were found. Please add them to the config file.");
        }
    }

    private void assignKickRoles() {
        try {
            kickRoles.addAll(bot.getConfig().getStringList("cmd-perms.kick"));
        } catch (Exception e) {
            Console.log(LogType.WARNING, "No roles for the kick command were found. Please add them to the config file.");
        }
    }

    // The following methods simply check if a member has the permission to execute the specified command.
    public boolean canWarn(Member member) {
        try {
            for(Role role : member.getRoles()){

                if(warnRoles.contains(role.getId())){
                    return true;
                }
            }
        } catch (Exception e) {
            Console.log(LogType.WARNING, "An error occurred while checking if a user can warn another user. Error: " + ExceptionUtils.getStackTrace(e));
        }
        return false;
    }

    public boolean canKick(Member member) {
        try {
            for(Role role : member.getRoles()){
                if(kickRoles.contains(role.getId())){
                    return true;
                }
            }
        } catch (Exception e) {
            Console.log(LogType.WARNING, "An error occurred while checking if a user can kick another user. Error: " + ExceptionUtils.getStackTrace(e));
        }
        return false;
    }

    public boolean canTimeout(Member member) {
        try {
            for(Role role : member.getRoles()){
                if(timeoutRoles.contains(role.getId())){
                    return true;
                }
            }
        } catch (Exception e) {
            Console.log(LogType.WARNING, "An error occurred while checking if a user can timeout another user. Error: " + ExceptionUtils.getStackTrace(e));
        }
        return false;
    }

    public boolean canBan(Member member) {
        try {
            for(Role role : member.getRoles()){
                if(banRoles.contains(role.getId())){
                    return true;
                }
            }
        } catch (Exception e) {
            Console.log(LogType.WARNING, "An error occurred while checking if a user can ban another user. Error: " + ExceptionUtils.getStackTrace(e));
        }
        return false;
    }

    public boolean canClear(Member member) {
        try {
            for(Role role : member.getRoles()){
                if(clearRoles.contains(role.getId())){
                    return true;
                }
            }
        } catch (Exception e) {
            Console.log(LogType.WARNING, "An error occurred while checking if a user can clear messages. Error: " + ExceptionUtils.getStackTrace(e));
        }
        return false;
    }

    public boolean canGetUserInfo(Member member) {
        try {
            for(Role role : member.getRoles()){
                if(userInfoRoles.contains(role.getId())){
                    return true;
                }
            }
        } catch (Exception e) {
            Console.log(LogType.WARNING, "An error occurred while checking if a user can get user info. Error: " + ExceptionUtils.getStackTrace(e));
        }
        return false;
    }

    // Getters

    public Bot getBot() {
        return bot;
    }

    public List<String> getWarnRoles() {
        return warnRoles;
    }

    public List<String> getKickRoles() {
        return kickRoles;
    }

    public List<String> getTimeoutRoles() {
        return timeoutRoles;
    }

    public List<String> getBanRoles() {
        return banRoles;
    }

    public List<String> getClearRoles() {
        return clearRoles;
    }

    public List<String> getUserInfoRoles() {
        return userInfoRoles;
    }

    // Setters
}
