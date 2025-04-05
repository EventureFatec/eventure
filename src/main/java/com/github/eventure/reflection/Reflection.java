package com.github.eventure.reflection;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Reflection {
    public static ArrayList<Class<?>> getClassesInPackage(String packageName) throws ClassNotFoundException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace(".", "/");
        URL url = loader.getResource(path);

        if (url == null) {
            throw new ClassNotFoundException("Package not found: " + packageName);
        }

        // Get all classes for each protocol
        var protocol = url.getProtocol();
        if (protocol.equals("jar")) {
            return deriveClassesFromJarProtocol(url, packageName);
        } else if (protocol.equals("file")) {
            return deriveClassesFromFileProtocol(url, packageName);
        } else {
            System.out.println("Invalid protocol");
            return null;
        }
    }

    private static ArrayList<Class<?>> deriveClassesFromJarProtocol(URL packageUrl, String packageName) {
        ArrayList<Class<?>> classes = new ArrayList<>();

        String jarFilePath = packageUrl.getFile().split("!")[0].substring(5);
        String cleanedPackagePath = packageUrl.getPath().split("!")[1].substring(1);

        try (JarFile jarFile = new JarFile(new File(jarFilePath))) {
            Enumeration<JarEntry> entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                String name = entry.getName();

                // Check if the entry is a class file inside the target package
                if (name.startsWith(cleanedPackagePath) && name.endsWith(".class")) {
                    // Load each class - formatting the class name correctly
                    String className = name.substring(0, name.length() - 6).replace('/', '.');
                    Class<?> cls = Class.forName(className);

                    classes.add(cls);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return classes;
    }

    private static ArrayList<Class<?>> deriveClassesFromFileProtocol(URL packageUrl, String packageName) {
        ArrayList<Class<?>> classes = new ArrayList<>();

        // Get all class files
        File directory;
        try {
            directory = new File(packageUrl.toURI());
        } catch (URISyntaxException e) {
            System.out.println("Invalid class URI");
            return null;
        }

        File[] files = directory.listFiles((file, name) -> name.endsWith(".class"));

        // Load each class
        for (int i = 0; i < files.length; ++i) {
            String className = packageName + "." + files[i].getName().replace(".class", "");
            try {
                classes.add(Class.forName(className));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return classes;
    }
}
