package com.migu.pandora.search.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeTool {

    private static final Logger LOGGER = LoggerFactory.getLogger(TimeTool.class);

    /**
     * 北京时间->世界协调时间
     *
     * @param cst
     * @param cstPattern
     * @return
     */
    public static String cst2utc(String cst, String cstPattern) {
        String utc = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(cstPattern);
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(cst));
            // 时区时差
            int zoneOffset = cal.get(Calendar.ZONE_OFFSET);
            // 夏令时差
            int dstOffset = cal.get(Calendar.DST_OFFSET);
            cal.add(Calendar.MILLISECOND, -(zoneOffset + dstOffset));
            sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            utc = sdf.format(cal.getTime());
        } catch (ParseException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return utc;
    }

}
