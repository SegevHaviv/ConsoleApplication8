using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MyTravel.Gateways
{
    public interface IPlacesAcGateway
    {
        IEnumerable<PlaceAutoComplete> Search(string text);
    }
}
