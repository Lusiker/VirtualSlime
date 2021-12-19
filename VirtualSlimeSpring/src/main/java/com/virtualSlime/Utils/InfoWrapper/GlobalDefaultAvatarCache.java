package com.virtualSlime.Utils.InfoWrapper;

import com.virtualSlime.Entity.User;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class GlobalDefaultAvatarCache {
    private static final String targetPath = "../VirtualSlimeVue/src/assets/user/";
    private static final String resPath = "./src/main/resources/static/default_avatar.jpg";

    public static void createAvatar(User user) throws IOException {
        String finalPath = targetPath + user.getUid();

        File dir = new File(finalPath);
        dir.mkdirs();

        FileInputStream in = new FileInputStream(resPath);
        FileOutputStream out = new FileOutputStream(finalPath + "/avatar.jpg");
        byte[] buf = new byte[1024];
        int bytesRead;
        while ((bytesRead = in.read(buf)) != -1) {
            out.write(buf, 0, bytesRead);
        }
    }
}
