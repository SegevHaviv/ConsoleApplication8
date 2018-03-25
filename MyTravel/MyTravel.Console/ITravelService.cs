using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.Text;

namespace MyTravel.Console
{
    // NOTE: You can use the "Rename" command on the "Refactor" menu to change the interface name "ITravelService" in both code and config file together.
    [ServiceContract]
    public interface ITravelService
    {
        [OperationContract]
        IEnumerable<int>[] GetOptions(IEnumerable<int> choose,IEnumerable<int> shown);

        [OperationContract]
        IEnumerable<int> GetResults(IEnumerable<int> choose, IEnumerable<int> shown);

        [OperationContract]
        PlaceResult GetIntrest(IEnumerable<int> arr);

        [OperationContract]
        IEnumerable<FlightResult> GetFlights(string text);
    }
}
