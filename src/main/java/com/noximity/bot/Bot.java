package com.noximity.bot;

import com.noximity.commands.BanCommand;
import com.noximity.commands.KickCommand;
import com.noximity.commands.TimeoutCommand;
import com.noximity.commands.WarnCommand;
import com.noximity.console.Console;
import com.noximity.console.LogType;
import com.noximity.listeners.ActivateListener;
import com.noximity.listeners.KickCommandListener;
import com.noximity.listeners.TimeoutCommandListener;
import com.noximity.listeners.WarnCommandListener;
import com.noximity.tools.Permissions;
import com.noximity.tools.Resources;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Guild;

import net.dv8tion.jda.api.requests.GatewayIntent;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.simpleyaml.configuration.file.YamlFile;

public class Bot {
    private final YamlFile config = new YamlFile("configuration.yml");

    private String token;

    private JDA jda;
    private Permissions permissions;

    private Guild server;

    public void init() {
        loadConfigFile();

        // Get the token from the configuration file
        this.token = retrieveToken();

        Console.log(LogType.INFO, "Starting NoxiBot...");

        wakeUpNoxi();
        registerListeners();

        this.permissions = new Permissions(this);

        try {
            jda.awaitReady();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Console.log(LogType.INFO, "NoxiBot has successfully started.");

    }


    // GETTERS

    public JDA getJda() {
        return jda;
    }

    public YamlFile getConfig() {
        return config;
    }

    public String getToken() {
        return token;
    }

    public Permissions getPermissions() {
        return permissions;
    }

    // SETTERS

    public void setServer(Guild server) {
        this.server = server;
    }

    public void setJda(JDA jda) {
        this.jda = jda;
    }

    // OTHER METHODS

    private void registerListeners() {
        jda.addEventListener(new ActivateListener(this));
        jda.addEventListener(new WarnCommandListener(this));
        jda.addEventListener(new KickCommandListener(this));
        jda.addEventListener(new TimeoutCommandListener(this));
    }

    public void registerCommands() {
        Console.log(LogType.INFO, "Registering commands...");

        server.updateCommands().addCommands(
                        new WarnCommand().getCommand(),
                        new KickCommand().getCommand(),
                        new TimeoutCommand().getCommand(),
                        new BanCommand().getCommand()).
                queue();
    }

    /**
     * The wakeUpNoxi function is responsible for starting the bot.
     * It does this by creating a new JDABuilder object, and then building it.
     */
    private void wakeUpNoxi() {

        try {
            this.jda = JDABuilder.createLight(token, GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_VOICE_STATES, GatewayIntent.GUILD_PRESENCES)
                    .setStatus(OnlineStatus.ONLINE)
                    .build();
        } catch (Exception e) {
            Console.log(LogType.ERROR, "An error occurred when starting the bot. Please double check your token and try again ("
                    + this.token + ")." + "\n" + ExceptionUtils.getStackTrace(e));
            System.exit(0);
        }
    }

    /**
     * The getToken function retrieves the bot token from the configuration file.
     *
     * @return the bot token
     */
    private String retrieveToken() {

        Console.log(LogType.INFO, "Retrieving bot token from configuration file...");

        try {
            Console.log(LogType.INFO, "Bot token retrieved.");
            return config.getString("token");
        } catch (Exception e) {
            Console.log(LogType.ERROR, "Invalid token. Please fill out the configuration file with valid information and restart the bot.");
            System.exit(0);
        }
        return null;
    }

    /**
     * The loadConfigFile function is responsible for loading the configuration file.
     * If it does not exist, it will generate a default one and exit the program.
     */
    private void loadConfigFile() {
        try {
            if (!config.exists()) {
                Console.log(LogType.WARNING, "Generating the configuration file. Please fill it out with valid information " +
                        "and restart the bot.");
                Resources.saveDefaultResource("configuration.yml", "configuration.yml");
                System.exit(0);
            }

            Console.log(LogType.INFO, "Configuration file loaded.");
            config.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
