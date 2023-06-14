package com.noximity.tools;

public enum BanReason {

    EXCESSIVE_SPAMMING("Excessive spamming", "The user has been repeatedly sending spam messages, disrupting the conversation flow."),
    HARASSMENT("Harassment", "The user has been engaging in disrespectful, offensive, or harassing behavior towards others."),
    VIOLATION_OF_COMMUNITY_GUIDELINES("Violation of community guidelines", "The user has breached the community guidelines, which may include actions like sharing inappropriate content or engaging in hate speech."),
    ADVERTISING("Advertising", "The user has been promoting external websites, services, or products without permission, which goes against the community rules."),
    TROLLING("Trolling", "The user has been intentionally provoking and instigating arguments or conflicts, disrupting the community's harmony."),
    INAPPROPRIATE_LANGUAGE("Inappropriate language", "The user has been using vulgar or explicit language, making others uncomfortable or violating the community's language policies."),
    IMPERSONATION("Impersonation", "The user has been pretending to be someone else, creating confusion or misleading others."),
    SPOILERS("Spoilers", "The user has been sharing plot twists, endings, or other important details without proper spoiler warnings, ruining the experience for others."),
    DISRUPTIVE_BEHAVIOR("Disruptive behavior", "The user has been consistently disruptive, causing disturbances, or intentionally derailing conversations."),
    NON_COMPLIANCE_WITH_INSTRUCTIONS("Non-compliance with instructions", "The user has repeatedly ignored or refused to follow instructions given by moderators or admins."),
    INTOLERANCE_OR_DISCRIMINATION("Intolerance or discrimination", "The user has been expressing discriminatory views or showing intolerance towards specific groups based on characteristics such as race, gender, religion, etc."),
    PERSONAL_ATTACKS("Personal attacks", "The user has been engaging in personal attacks, insults, or derogatory comments towards others."),
    BREACH_OF_PRIVACY("Breach of privacy", "The user has invaded someone's privacy by sharing personal information without consent."),
    IMPEDING_MODERATION_EFFORTS("Impeding moderation efforts", "The user has been actively obstructing or interfering with the moderation team's efforts to maintain order in the community."),
    MULTIPLE_ACCOUNT_ABUSE("Multiple account abuse", "The user has been using multiple accounts to evade bans or engage in deceptive activities."),
    DISRUPTIVE_VOICE_CHAT_BEHAVIOR("Disruptive voice chat behavior", "The user has been causing disruptions or excessive noise in voice channels, making it difficult for others to communicate."),
    INAPPROPRIATE_AVATARS_OR_USERNAMES("Inappropriate avatars or usernames", "The user's profile picture or username contains explicit, offensive, or inappropriate content."),
    EXCESSIVE_OFF_TOPIC_DISCUSSIONS("Excessive off-topic discussions", "The user has been consistently diverting conversations from the intended topic, making it difficult for others to stay on track."),
    UNWANTED_DIRECT_MESSAGES("Unwanted direct messages", "The user has been sending unsolicited or inappropriate direct messages to others without their consent."),
    SERVER_RAIDING_OR_MASS_MENTION_SPAM("Server raiding or mass mention/spam", "The user has organized or participated in a coordinated effort to raid the server or engage in mass mention or spam attacks.");

    private final String reason;
    private final String description;

    BanReason(String reason, String description) {
        this.reason = reason;
        this.description = description;
    }

    public String getReason() {
        return reason;
    }

    public String getDescription() {
        return description;
    }

    public static String getDescription(String reason){
        for(TimeoutReason timeoutReason : TimeoutReason.values()){
            if(timeoutReason.getReason().equals(reason)){
                return timeoutReason.getDescription();
            }
        }
        return null;
    }

}
