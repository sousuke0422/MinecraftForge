package net.rikkalab.forge.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;
import javax.annotation.Nullable;

public enum ChatFormatting {
    BLACK("BLACK", '0', 0),
    DARK_BLUE("DARK_BLUE", '1', 1),
    DARK_GREEN("DARK_GREEN", '2', 2),
    DARK_AQUA("DARK_AQUA", '3', 3),
    DARK_RED("DARK_RED", '4', 4),
    DARK_PURPLE("DARK_PURPLE", '5', 5),
    GOLD("GOLD", '6', 6),
    GRAY("GRAY", '7', 7),
    DARK_GRAY("DARK_GRAY", '8', 8),
    BLUE("BLUE", '9', 9),
    GREEN("GREEN", 'a', 10),
    AQUA("AQUA", 'b', 11),
    RED("RED", 'c', 12),
    LIGHT_PURPLE("LIGHT_PURPLE", 'd', 13),
    YELLOW("YELLOW", 'e', 14),
    WHITE("WHITE", 'f', 15),
    OBFUSCATED("OBFUSCATED", 'k', true),
    BOLD("BOLD", 'l', true),
    STRIKETHROUGH("STRIKETHROUGH", 'm', true),
    UNDERLINE("UNDERLINE", 'n', true),
    ITALIC("ITALIC", 'o', true),
    RESET("RESET", 'r', -1);

    private static final Map<String, ChatFormatting> NAME_MAPPING = Maps.newHashMap();
    private static final Pattern FORMATTING_CODE_PATTERN = Pattern.compile("(?i)§[0-9A-FK-OR]");
    private final String name;
    private final char formattingCode;
    private final boolean fancyStyling;
    private final String controlString;
    private final int colorIndex;

    private static String lowercaseAlpha(String string) {
        return string.toLowerCase(Locale.ROOT).replaceAll("[^a-z]", "");
    }

    private ChatFormatting(String name, char formattingCode, int colorIndex) {
        this(name, formattingCode, false, colorIndex);
    }

    private ChatFormatting(String name, char formattingCode, boolean fancyStyling) {
        this(name, formattingCode, fancyStyling, -1);
    }

    private ChatFormatting(String name, char formattingCode, boolean fancyStyling, int colorIndex) {
        this.name = name;
        this.formattingCode = formattingCode;
        this.fancyStyling = fancyStyling;
        this.colorIndex = colorIndex;
        this.controlString = "§" + formattingCode;
    }

    public int getColorIndex() {
        return this.colorIndex;
    }

    public boolean isFancyStyling() {
        return this.fancyStyling;
    }

    public boolean isColor() {
        return !this.fancyStyling && this != RESET;
    }

    public String getFriendlyName() {
        return this.name().toLowerCase(Locale.ROOT);
    }

    public String toString() {
        return this.controlString;
    }

    @Nullable
    public static String getTextWithoutFormattingCodes(@Nullable String text) {
        return text == null ? null : FORMATTING_CODE_PATTERN.matcher(text).replaceAll("");
    }

    @Nullable
    public static ChatFormatting getValueByName(@Nullable String friendlyName) {
        return friendlyName == null ? null : (ChatFormatting)NAME_MAPPING.get(lowercaseAlpha(friendlyName));
    }

    @Nullable
    public static ChatFormatting fromColorIndex(int index) {
        if (index < 0) {
            return RESET;
        } else {
            ChatFormatting[] var1 = values();
            int var2 = var1.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                ChatFormatting lvt_4_1_ = var1[var3];
                if (lvt_4_1_.getColorIndex() == index) {
                    return lvt_4_1_;
                }
            }

            return null;
        }
    }

    public static Collection<String> getValidValues(boolean getColor, boolean getFancyStyling) {
        List<String> lvt_2_1_ = Lists.newArrayList();
        ChatFormatting[] var3 = values();
        int var4 = var3.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            ChatFormatting lvt_6_1_ = var3[var5];
            if ((!lvt_6_1_.isColor() || getColor) && (!lvt_6_1_.isFancyStyling() || getFancyStyling)) {
                lvt_2_1_.add(lvt_6_1_.getFriendlyName());
            }
        }

        return lvt_2_1_;
    }

    static {
        ChatFormatting[] var0 = values();
        int var1 = var0.length;

        for(int var2 = 0; var2 < var1; ++var2) {
            ChatFormatting lvt_3_1_ = var0[var2];
            NAME_MAPPING.put(lowercaseAlpha(lvt_3_1_.name), lvt_3_1_);
        }

    }
}
