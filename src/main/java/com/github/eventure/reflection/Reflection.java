package com.github.eventure.reflection;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

public class Reflection {
    public static Class<?>[] getClassesInPackage(String packageName) throws ClassNotFoundException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace(".", "/");
        URL url = loader.getResource(path);

        if (url == null) {
            throw new ClassNotFoundException("Package not found: " + packageName);
        }

        // Get all class files
        File directory;
        try {
            directory = new File(url.toURI());
        } catch (URISyntaxException e) {
            System.out.println("Invalid class URI");
            return null;
        }

        File[] files = directory.listFiles((_, name) -> name.endsWith(".class"));

        // Load each class
        Class<?>[] classes = new Class[files.length];
        for (int i = 0; i < classes.length; i++) {
            String className = packageName + "." + files[i].getName().replace(".class", "");
            classes[i] = Class.forName(className);
        }

        return classes;
    }
}
