package tglanz;

import java.io.IOException;
import java.util.Collection;

import tglanz.examples.Example;
import tglanz.examples.ExamplesLoader;

public class App {

    private final Collection<Example> examples;

    public App(Collection<Example> examples) {
        this.examples = examples;
    }

    private void runExample(String exampleName) {
        var maybeExample = examples.stream()
            .filter(example -> example.name().equalsIgnoreCase(exampleName))
            .findAny();
        
        if (maybeExample.isEmpty()) {
            throw new Error("No such example: " + exampleName);
        }

        maybeExample.get().run();
    }

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.err.println("No example provided");
            System.exit(1);
        }

        Collection<Example> examples = new ExamplesLoader().loadExamples();
        new App(examples).runExample(args[0]);
    }

}
