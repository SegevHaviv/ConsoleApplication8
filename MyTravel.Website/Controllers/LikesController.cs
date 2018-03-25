using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using System.Web.Script.Serialization;
using MyTravel.Website.Models.Likes;

namespace MyTravel.Website.Controllers
{
    public class LikesController : Controller
    {
        // GET: Likes
        public ActionResult Index()
        {
            return View();
        }

        public JsonResult GetOptions(UserLikes model)
        {
            model.Choose = new JavaScriptSerializer().Deserialize<int[]>(Request["Choose"]);
            model.Shown = new JavaScriptSerializer().Deserialize<List<int>>(Request["Shown"]);
            model.Vector = new JavaScriptSerializer().Deserialize<int[]>(Request["Vector"]);


            var service = new TravelService.TravelServiceClient();
            try
            {
                service.Open();
                var factory = new ImageLikeFactory();
                var list = new List<LikeOption>();
                var result = service.GetOptions(model.Choose, model.Shown.ToArray());

                for (var i = 0; i < 9; i++)
                {
                    model.Shown.Add(result[0][i]);
                    list.Add(new LikeOption() { Id = result[0][i], Image = factory.Dictionary[result[0][i]], IsChecked = false });
                }

                for (var i = 0; i < 4; i++)
                {
                    model.Vector[i] += result[1][i];
                }
                model.Options = list.ToArray();
                return Json(model, JsonRequestBehavior.AllowGet);

            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                throw;
            }
        }

        public JsonResult GetResults(UserLikes model)
        {
            model.Choose = new JavaScriptSerializer().Deserialize<int[]>(Request["Choose"]);
            model.Shown = new JavaScriptSerializer().Deserialize<List<int>>(Request["Shown"]);
            model.Vector = new JavaScriptSerializer().Deserialize<int[]>(Request["Vector"]);

            var service = new TravelService.TravelServiceClient();
            try
            {
                service.Open();
                var numbers = service.GetResults(model.Choose, model.Shown.ToArray());
                for (var i = 0; i < 4; i++)
                    model.Vector[i] = (model.Vector[i] + numbers[i]) / 3;

                var result = service.GetIntrest(model.Vector);
                return Json(result, JsonRequestBehavior.AllowGet);

            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                throw;
            }
        }

        public PartialViewResult _Result()
        {
            return PartialView("_Results");
        }
    }
}