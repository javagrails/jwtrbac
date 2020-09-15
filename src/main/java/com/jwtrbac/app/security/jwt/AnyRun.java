package com.jwtrbac.app.security.jwt;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AnyRun {

    private static final String PATH = "/home/salman";
    //private static final String PATH = "/home/salman/Public";

    public static void main_v1(String[] args) {
        String kbUnit    = " KB";
        String mbUnit    = " MB";
        String gbUnit    = " GB";
        String printUnit = "";
        double printSize = 0.0;
        File   folders   = new File(PATH);

        try {
            File[] files = folders.listFiles();
            for (File file : files) {
                long size = Files.walk(Paths.get(file.toURI()))
                    .filter(p -> p.toFile().isFile())
                    .mapToLong(p -> p.toFile().length())
                    .sum();


                double calculatedSizeKB = (double) size / 1024; // KB
                double calculatedSizeMB = (double) size / 1024 / 1024; // MB
                double calculatedSizeGB = (double) size / 1024 / 1024 / 1024; // GB

                if (calculatedSizeGB < 1) {
                    if (calculatedSizeMB < 1) {
                        printSize = calculatedSizeKB;
                        printUnit = kbUnit;
                    } else {
                        printSize = calculatedSizeMB;
                        printUnit = mbUnit;
                    }
                } else {
                    printSize = calculatedSizeGB;
                    printUnit = gbUnit;
                }

                System.out.println(file.getName() + " : " + printSize + printUnit);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // System.out.println("gfgfxgfg");
    }

    public static void main(String[] args) {
        long   start     = System.currentTimeMillis();
        String kbUnit    = " KB";
        String mbUnit    = " MB";
        String gbUnit    = " GB";
        String printUnit = "";
        double printSize = 0.0;
        File   folders   = new File(PATH);

        try {
            File[] files = folders.listFiles();
            List<File> collect = Arrays.asList(files).stream().filter(file -> !file.getName().startsWith(".")).sorted().collect(Collectors.toList());
            //System.out.println(files.length);
            for (File file : collect) {
                if (!file.getName().startsWith("PlayOnLinux")) {
                    long size = 0;
                    size = getFileFolderSize(file);

                    double calculatedSizeKB = (double) size / 1024; // KB
                    double calculatedSizeMB = (double) size / 1024 / 1024; // MB
                    double calculatedSizeGB = (double) size / 1024 / 1024 / 1024; // GB

                    if (calculatedSizeGB < 1) {
                        if (calculatedSizeMB < 1) {
                            printSize = calculatedSizeKB;
                            printUnit = kbUnit;
                        } else {
                            printSize = calculatedSizeMB;
                            printUnit = mbUnit;
                        }
                    } else {
                        printSize = calculatedSizeGB;
                        printUnit = gbUnit;
                    }
                    System.out.println(file.getName() + "," + printSize + printUnit + "," + printUnit.replace(" ", ""));
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        long   end     = System.currentTimeMillis();
        double seconds = (end - start) / 1000.0;
        System.out.println("Code runtime is : " + seconds + " seconds");
        System.out.println("Code runtime is : " + (seconds / 60.0) + " Minutes");
    }

    public static long getFileFolderSize(File dir) {
        long size = 0;
        //System.out.println("dir.getName() : " + dir.getPath() + " : " + dir.canRead());
        //dir.setReadable(true); // https://stackoverflow.com/questions/14052388/setting-file-permissions-returns-false-always/14054278
        if(!dir.getName().equalsIgnoreCase("node_modules")) {
            if (dir.isDirectory() && dir.canRead()) {
                File[] files = dir.listFiles();
                //System.out.println("files : " + files.length);
                for (File file : files) {
                    if (file.isFile()) {
                        size += file.length();
                    } else
                        size += getFileFolderSize(file);
                }
            } else if (dir.isFile()) {
                size += dir.length();
            }
        }
        return size;
    }
}
