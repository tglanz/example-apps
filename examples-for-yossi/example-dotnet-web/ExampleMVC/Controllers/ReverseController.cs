using System.Diagnostics;
using Microsoft.AspNetCore.Mvc;
using ExampleMVC.Models;
using ExampleMVC.Services;

namespace ExampleMVC.Controllers;

public class ReverseController : Controller
{
    private readonly ILogger<ReverseController> _logger;
    private readonly UserReverser _userReverser;
    private readonly CarReverser _carReverser;

    public ReverseController(ILogger<ReverseController> logger)
    {
        _logger = logger;
        _userReverser = new UserReverser();
        _carReverser = new CarReverser();
    }

    [AcceptVerbs("GET")]
    [ActionName("Index")]
    public IActionResult IndexGet()
    {
        return View(new ReverseModel());
    }

    [AcceptVerbs("POST")]
    [ActionName("User")]
    public IActionResult UserPost(string name, string gender)
    {
        var output = _userReverser.Reverse(name, gender);
        return View("Index", new ReverseModel(output));
    }

    [AcceptVerbs("POST")]
    [ActionName("Car")]
    public IActionResult CarPost(string manufacturer, string model)
    {
        var output = _carReverser.Reverse(manufacturer, model);
        return View("Index", new ReverseModel(output));
    }

    [ResponseCache(Duration = 0, Location = ResponseCacheLocation.None, NoStore = true)]
    public IActionResult Error()
    {
        return View(new ErrorViewModel { RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier });
    }
}
