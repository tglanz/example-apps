using System.Diagnostics;
using Microsoft.AspNetCore.Mvc;
using ExampleMVC.Models;
using ExampleMVC.Services;

namespace ExampleMVC.Controllers;

public class ClientSideRequestsController : Controller
{
    private readonly ILogger<ClientSideRequestsController> _logger;

    public class SampleDataResponse {
        public string? Value { get; set; }
    }

    public ClientSideRequestsController(ILogger<ClientSideRequestsController> logger)
    {
        _logger = logger;
    }

    [AcceptVerbs("GET")]
    [ActionName("Index")]
    public IActionResult IndexGet()
    {
        return View(new ReverseModel());
    }

    [AcceptVerbs("GET")]
    [ActionName("SampleData")]
    public SampleDataResponse GetSampleData() {
        return new SampleDataResponse {
            Value = DateTime.Now.ToString(),
        };
    }

    [ResponseCache(Duration = 0, Location = ResponseCacheLocation.None, NoStore = true)]
    public IActionResult Error()
    {
        return View(new ErrorViewModel { RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier });
    }
}
