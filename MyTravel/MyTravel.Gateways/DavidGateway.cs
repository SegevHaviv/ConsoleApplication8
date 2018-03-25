using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net.Sockets;
using System.Text;
using System.Threading.Tasks;

namespace MyTravel.Gateways
{
    public class DavidGateway
    {
        public PlaceResult GetIntrest(IEnumerable<int> arr)
        {
            var tcpClient = new TcpClient("127.0.0.1", 9999);
            var netStream = tcpClient.GetStream();
            var value = string.Empty;
            foreach (var num in arr)
                value += num + ",";
            value = value.Remove(value.Length - 1);

            var placeRes = new PlaceResult(); ;
            using (var writer = new StreamWriter(netStream))
            using (var reader = new StreamReader(netStream))
            {
                writer.WriteLine(value);
                writer.Flush();
                placeRes.Cities = reader.ReadLine().Split(',');
                placeRes.Intrest = reader.ReadLine().Split(',');
            }
            return placeRes;
        }
    }
}
