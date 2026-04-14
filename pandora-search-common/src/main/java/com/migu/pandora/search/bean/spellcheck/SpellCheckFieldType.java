package com.migu.pandora.search.bean.spellcheck;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by piguangtao on 1/2/18.
 */
public enum SpellCheckFieldType {
    NONE("-1"),
    GAME_NAME("11"), GAME_PEOPLE("12"),
    COMIC_NAME("21"), COMIC_PEOPLE("22"),
    MUSIC_SONG("31"), MUSIC_SINGER("32"), MUSIC_ALBUM("33"), MUSIC_LABEL("34"), MUSIC_FILM("35"), MUSIC_VARIETY("36"), MUSIC_MV("37"), MUSIC_LYRIC("39"), MUSIC_RM_USER("38"),
    MEDIA_NAME("41"), MEDIA_WRITER("42"),
    VIDEO_NAME("51"), VIDEO_PEOPLE("52");


    private String code;

    private SpellCheckFieldType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static SpellCheckFieldType get(String code) {
        SpellCheckFieldType result = NONE;
        if (StringUtils.isNotBlank(code)) {
            switch (code) {
                case "11": {
                    result = GAME_NAME;
                    break;
                }
                case "12": {
                    result = GAME_PEOPLE;
                    break;
                }
                case "21": {
                    result = COMIC_NAME;
                    break;
                }
                case "22": {
                    result = COMIC_PEOPLE;
                    break;
                }
                case "31": {
                    result = MUSIC_SONG;
                    break;
                }
                case "32": {
                    result = MUSIC_SINGER;
                    break;
                }
                case "33": {
                    result = MUSIC_ALBUM;
                    break;
                }
                case "34": {
                    result = MUSIC_LABEL;
                    break;
                }
                case "41": {
                    result = MEDIA_NAME;
                    break;
                }

                case "42": {
                    result = MEDIA_WRITER;
                    break;
                }

                case "51": {
                    result = VIDEO_NAME;
                    break;
                }
                case "52": {
                    result = VIDEO_PEOPLE;
                    break;
                }
                case "38": {
                    result = MUSIC_RM_USER;
                    break;
                }
                default: {
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return this.getCode();
    }
}
