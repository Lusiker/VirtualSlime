package com.virtualSlime.Utils.InfoWrapper;

import com.virtualSlime.Entity.User;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class GlobalDefaultAvatarCache {
    private static final String targetPath = "../VirtualSlimeVue/src/assets/user/";
    private static final String resPath = "./src/main/resources/static/default_avatar.jpg";
    private static FileInputStream defaultAvatar = null;

    static {
        try {
            defaultAvatar = new FileInputStream(resPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void createAvatar(User user) throws IOException {
        String finalPath = targetPath + user.getUid();

        File dir = new File(finalPath);
        dir.mkdirs();

        FileOutputStream out = new FileOutputStream(finalPath + "/avatar.jpg");
        byte[] buf = new byte[1024];
        int bytesRead;
        while ((bytesRead = defaultAvatar.read(buf)) != -1) {
            out.write(buf, 0, bytesRead);
        }

        out.close();
    }
}
