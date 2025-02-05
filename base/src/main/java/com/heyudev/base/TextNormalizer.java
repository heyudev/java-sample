package com.heyudev.base;

import java.text.Normalizer;

public class TextNormalizer {

    public static void main(String[] args) {
        String text1 = "📍,⭕️,🔥,📣,🎈,⚡,🔔,🚩,💢,💎,👉,啊啊，啊💓,❗️,🔴,🔺,♦️,♥️,👉,👈,🏆,...,~~";
        String normalizedText1 = normalizeText(text1);
        System.out.println(normalizedText1);

        String text2 = "\n" +
                "3种方法可快速增强免疫力，延长寿命30年！";
        String normalizedText2 = normalizeText(text2);
        System.out.println(normalizedText2);
    }

    public static String normalizeText(String input) {
        // Remove emoji and special characters
        String noEmoji = input.replaceAll("[^\\p{L}\\p{N}\\p{P}\\p{Z}]", "");

        // Remove punctuation and numbers from the start and end of the string
        while (noEmoji.length() > 0 && noEmoji.substring(0, 1).matches("[\\p{P}]")) {
            noEmoji = noEmoji.substring(1);
        }
        while (noEmoji.length() > 0 && noEmoji.substring(noEmoji.length() - 1).matches("[\\p{P}\\p{N}]")) {
            noEmoji = noEmoji.substring(0, noEmoji.length() - 1);
        }

        // Normalize to remove accents and diacritics
        String normalized = Normalizer.normalize(noEmoji, Normalizer.Form.NFD);
        normalized = normalized.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");

        // Remove extra spaces and trim

        return normalized.trim().replaceAll(" +", " ");
    }
}