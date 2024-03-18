package tglanz.examples;

public class HelloWorld implements Example {

    @Override
    public String name() {
        return "hello-world";
    }

    @Override
    public void run() {
       System.out.println("Hello world"); 
    }
}
