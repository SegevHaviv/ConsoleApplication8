using System.Linq;
using System.Web.Mvc;
using MyTravel.Gateways;

namespace MyTravel.Website.Controllers
{
    public class HomeController : Controller
    {
        // GET: Home
        public ActionResult Index()
        {
            return View();
        }

        public ActionResult Place(string id)
        {
            var ser = new TravelService.TravelServiceClient();
            ViewBag.Name = id;
            return View(ser.GetFlights(id));
        }

        public JsonResult GetData(string id)
        {
            var t =Request.QueryString["id"].First();
            return Json(new Models.Home.Intrest() { Name = id }, JsonRequestBehavior.AllowGet);
        }
    }
}