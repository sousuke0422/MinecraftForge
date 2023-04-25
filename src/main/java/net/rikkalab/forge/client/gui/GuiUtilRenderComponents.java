package net.rikkalab.forge.client.gui;

import com.google.common.collect.Lists;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.rikkalab.forge.util.ChatFormatting;

@SideOnly(Side.CLIENT)
public class GuiUtilRenderComponents {
    public GuiUtilRenderComponents() {
    }

    public static String removeTextColorsIfConfigured(String text, boolean forceColor) {
        return !forceColor && !Minecraft.getMinecraft().gameSettings.chatColours ? ChatFormatting.getTextWithoutFormattingCodes(text) : text;
    }

    public static List<IChatComponent> splitText(IChatComponent chatComponent, int maxTextLenght, FontRenderer fontRendererIn, boolean p_178908_3_, boolean forceTextColor) {
        int i = 0;
        IChatComponent itextcomponent = new ChatComponentText("");
        List<IChatComponent> list = Lists.newArrayList();
        List<IChatComponent> list1 = Lists.newArrayList(chatComponent);

        for(int j = 0; j < list1.size(); ++j) {
            IChatComponent itextcomponent1 = (IChatComponent)list1.get(j);
            String s = itextcomponent1.getUnformattedText();
            boolean flag = false;
            String s5;
            if (s.contains("\n")) {
                int k = s.indexOf(10);
                s5 = s.substring(k + 1);
                s = s.substring(0, k + 1);
                IChatComponent itextcomponent2 = new ChatComponentText(s5);
                itextcomponent2.setChatStyle(itextcomponent1.getChatStyle().createShallowCopy());
                list1.add(j + 1, itextcomponent2);
                flag = true;
            }

            String s4 = removeTextColorsIfConfigured(itextcomponent1.getChatStyle().getFormattingCode() + s, forceTextColor);
            s5 = s4.endsWith("\n") ? s4.substring(0, s4.length() - 1) : s4;
            int i1 = fontRendererIn.getStringWidth(s5);
            ChatComponentText textcomponentstring = new ChatComponentText(s5);
            textcomponentstring.setChatStyle(itextcomponent1.getChatStyle().createShallowCopy());
            if (i + i1 > maxTextLenght) {
                String s2 = fontRendererIn.trimStringToWidth(s4, maxTextLenght - i, false);
                String s3 = s2.length() < s4.length() ? s4.substring(s2.length()) : null;
                if (s3 != null && !s3.isEmpty()) {
                    int l = s2.lastIndexOf(32);
                    if (l >= 0 && fontRendererIn.getStringWidth(s4.substring(0, l)) > 0) {
                        s2 = s4.substring(0, l);
                        if (p_178908_3_) {
                            ++l;
                        }

                        s3 = s4.substring(l);
                    } else if (i > 0 && !s4.contains(" ")) {
                        s2 = "";
                        s3 = s4;
                    }

                    s3 = getFormatFromString(s2) + s3;
                    ChatComponentText textcomponentstring1 = new ChatComponentText(s3);
                    textcomponentstring1.setChatStyle(itextcomponent1.getChatStyle().createShallowCopy());
                    list1.add(j + 1, textcomponentstring1);
                }

                i1 = fontRendererIn.getStringWidth(s2);
                textcomponentstring = new ChatComponentText(s2);
                textcomponentstring.setChatStyle(itextcomponent1.getChatStyle().createShallowCopy());
                flag = true;
            }

            if (i + i1 <= maxTextLenght) {
                i += i1;
                itextcomponent.appendSibling(textcomponentstring);
            } else {
                flag = true;
            }

            if (flag) {
                list.add(itextcomponent);
                i = 0;
                itextcomponent = new ChatComponentText("");
            }
        }

        list.add(itextcomponent);
        return list;
    }

    private static boolean isFormatColor(char colorChar)
    {
        return colorChar >= '0' && colorChar <= '9' || colorChar >= 'a' && colorChar <= 'f' || colorChar >= 'A' && colorChar <= 'F';
    }

    private static boolean isFormatSpecial(char formatChar)
    {
        return formatChar >= 'k' && formatChar <= 'o' || formatChar >= 'K' && formatChar <= 'O' || formatChar == 'r' || formatChar == 'R';
    }

    public static String getFormatFromString(String text)
    {
        String s = "";
        int i = -1;
        int j = text.length();

        while ((i = text.indexOf(167, i + 1)) != -1)
        {
            if (i < j - 1)
            {
                char c0 = text.charAt(i + 1);

                if (isFormatColor(c0))
                {
                    s = "\u00a7" + c0;
                }
                else if (isFormatSpecial(c0))
                {
                    s = s + "\u00a7" + c0;
                }
            }
        }

        return s;
    }}
