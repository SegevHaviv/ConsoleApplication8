using System.Collections.Generic;
using System.Net;
using System.Web.Script.Serialization;

namespace MyTravel.Gateways
{
    public class GooglePlacesAcGateway:IPlacesAcGateway
    {
        private const string Url = "https://maps.googleapis.com/maps/api/place/autocomplete/json?input={text}&types=(cities)&language=en&key=AIzaSyDQqOZW42AWexM3iFEubLITPLdTDj2XyCU";


        public IEnumerable<PlaceAutoComplete> Search(string text)
        {
            var wc = new WebClient();
            var str = wc.DownloadString(Url.Replace("{text}", text));
            var des = new JavaScriptSerializer().Deserialize<dynamic>(str);
            var results = new List<PlaceAutoComplete>();
            foreach (var item in des["predictions"])
            {
                results.Add(new PlaceAutoComplete()
                {
                    Description = item["description"],
                    PlaceId = item["place_id"],
                    MainText = item["structured_formatting"]["main_text"]
                });
            }
            return results;
        }
    }
}
