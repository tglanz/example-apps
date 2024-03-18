package tglanz.examples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExamplesLoader {
    public Collection<Example> loadExamples() throws IOException {
        var packageName = getClass().getPackageName();
        var packageResourceName = packageName.replaceAll("\\.", "/");

        Collection<Example> examples = new LinkedList<>();
        
        try (var stream = ClassLoader.getSystemClassLoader().getResourceAsStream(packageResourceName)) {
            try (var reader = new BufferedReader(new InputStreamReader(stream))) {
                Collection<Class<?>> classes = reader.lines()
                    .filter(x -> x.endsWith(".class"))
                    .map(fullName -> fullName.split(".class")[0])
                    .map(name -> String.format("%s.%s", packageName, name))
                    .map(name -> {
                        try {
                            return Class.forName(name);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .filter(ExamplesLoader::isExample)
                    .collect(Collectors.toList());

                for (var cls : classes) {
                    try {
                        var ctor = cls.getDeclaredConstructor();
                        var example = (Example)ctor.newInstance();
                        examples.add(example);
                    } catch (NoSuchMethodException |
                        InstantiationException |
                        IllegalAccessException |
                        IllegalArgumentException |
                        InvocationTargetException ex) {
                            // Left blank intentionally
                    }
                }
            }
        }

        return examples;
    }

    private static boolean isExample(Class<?> cls) {
        var hasInteface = Arrays.asList(cls.getInterfaces())
            .stream()
            .anyMatch(iface -> iface.equals(Example.class));

        return hasInteface;
    }
}
