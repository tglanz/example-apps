namespace ExampleMVC.Services;

public class CarReverser
{
    public string Reverse(string manufacturer, string model)
    {
        model = new string(model.Reverse().ToArray());
        return "Car: " + new string($"{manufacturer}-{model}".ToLower());
    }
}