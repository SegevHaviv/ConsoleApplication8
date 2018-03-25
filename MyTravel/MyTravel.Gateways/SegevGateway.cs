using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net.Sockets;
using System.Runtime.CompilerServices;
using System.Text;
using System.Threading.Tasks;
using System.Web.Script.Serialization;

namespace MyTravel.Gateways
{
    public class SegevGateway
    {
        public IEnumerable<FlightResult> GetFlights(string text)
        {
            //Madrid

            var tcpClient = new TcpClient("127.0.0.1", 3000);
            var netStream = tcpClient.GetStream();
            var list = new List<FlightResult>();
            using (var writer = new StreamWriter(netStream))
            using (var reader = new StreamReader(netStream))
            {
                writer.WriteLine(text);
                writer.Flush();
                var respone = reader.ReadToEnd();
                if (string.IsNullOrEmpty(respone))
                    return list;
                var ser = new JavaScriptSerializer().Deserialize<dynamic>(respone);
                foreach (var item in ser["m_Results"])
                {
                    list.Add(new FlightResult()
                    {
                        Airline = item["m_Airline"],
                        DepartureDate =DateTime.Parse(item["m_DepartureDate"]),
                        Destination = item["m_Destination"],
                        Price = float.Parse(item["m_Price"].ToString()),
                        ReturnDate =DateTime.Parse(item["m_ReturnDate"])
                    });
                }
            }
            return list.OrderBy(o=>o.DepartureDate).Take(5);
        }
    }
}
