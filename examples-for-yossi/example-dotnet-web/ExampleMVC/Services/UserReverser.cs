namespace ExampleMVC.Services;

public class UserReverser
{
    public string Reverse(string name, string gender)
    {
        return "User: " + new string($"{name}-{gender}".ToLower().Reverse().ToArray());
    }
}