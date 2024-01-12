namespace ExampleMVC.Models;

public class ReverseModel
{
    public string? Output { get; }

    public IEnumerable<string> AvailableCarManufacturers
    {
        get
        {
            yield return "Mazda";
            yield return "Hyunday";
            yield return "Subaru";
        }
    }

    public bool HasOutput
    {
        get
        {
            return Output != null;
        }
    }

    public ReverseModel()
    {
        Output = null;
    }

    public ReverseModel(string output)
    {
        Output = output;
    }
}