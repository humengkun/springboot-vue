package com.hmk.springboot.util;

import java.util.UUID;

public class CreatUuid {
    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        String uuidStr = uuid.toString().replaceAll("-", "");
        return uuidStr;
    }
}
