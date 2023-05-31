package com.heyudev.common.util;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;

import java.io.File;
import java.io.IOException;

public class ThumbnailUtil {
    public static void main(String[] args) throws IOException {
        Thumbnails.of(new File("path/to/directory").listFiles())
                .size(50, 50)
                .outputFormat("jpg")
                .toFiles(Rename.PREFIX_DOT_THUMBNAIL);
    }


}
