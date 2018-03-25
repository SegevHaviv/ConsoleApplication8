using System;
using System.Collections.Generic;
using System.ServiceModel;
using MyTravel.Gateways;

namespace MyTravel.Console
{
    [ServiceBehavior(
    ConcurrencyMode = ConcurrencyMode.Multiple,
    InstanceContextMode = InstanceContextMode.Single)]
    public class TravelService : ITravelService
    {

        private readonly ImagesGateway m_Gateway = new ImagesGateway();
        private readonly DavidGateway m_DavidGateway = new DavidGateway();
        private readonly SegevGateway m_Segev=new SegevGateway();


        public PlaceResult GetIntrest(IEnumerable<int> arr)
        {
            return m_DavidGateway.GetIntrest(arr);
        }

        public IEnumerable<int>[] GetOptions(IEnumerable<int> choose, IEnumerable<int> shown)
        {
            return m_Gateway.GetImages(choose, shown);
        }

        public IEnumerable<int> GetResults(IEnumerable<int> choose, IEnumerable<int> shown)
        {
            return m_Gateway.GetResults(choose, shown);
        }

        public IEnumerable<FlightResult> GetFlights(string text)
        {
            return m_Segev.GetFlights(text);
        }
    }
}
