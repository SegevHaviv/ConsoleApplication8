using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading.Tasks;

namespace MyTravel.Gateways
{
    public class ImagesGateway
    {
        public IEnumerable<int>[] GetImages(IEnumerable<int> choose, IEnumerable<int> shown)
        {
            var tcpClient = new TcpClient("127.0.0.1", 2200);
            var netStream = tcpClient.GetStream();
            var arr = new int[9];
            var vector = new int[4];
            netStream.ReadTimeout = 2000;

            using (var writer = new StreamWriter(netStream))
            using (var reader = new StreamReader(netStream))
            {
                writer.WriteLine(shown.Count().ToString());
                foreach (var c in shown)
                {
                    writer.WriteLine(c.ToString());
                }
                writer.Flush();
                if (shown.Count() != 0)
                {
                    writer.WriteLine(choose.Count().ToString());
                    foreach (var c in choose)
                    {
                        writer.WriteLine(c.ToString());
                    }
                    writer.Flush();
                }
                for (var i = 0; i < 9; i++)
                {
                    arr[i] = int.Parse(reader.ReadLine());
                }
                if (choose.Count() != 0)
                    for (var i = 0; i < 4; i++)
                        vector[i] = int.Parse(reader.ReadLine());
            }
            return new[] { arr, vector.Reverse() };
        }

        public IEnumerable<int> GetResults(IEnumerable<int> choose, IEnumerable<int> shown)
        {
            var tcpClient = new TcpClient("127.0.0.1", 2200);
            var netStream = tcpClient.GetStream();
            var arr = new int[4];
            netStream.ReadTimeout = 1000;

            using (var writer = new StreamWriter(netStream))
            using (var reader = new StreamReader(netStream))
            {
                writer.WriteLine(shown.Count().ToString());
                foreach (var c in shown)
                {
                    writer.WriteLine(c.ToString());
                }
                writer.Flush();
                if (shown.Count() != 0)
                {
                    writer.WriteLine(choose.Count().ToString());
                    foreach (var c in choose)
                    {
                        writer.WriteLine(c.ToString());
                    }
                    writer.Flush();
                }
                for (var i = 0; i < 9; i++)
                    reader.ReadLine();

                for (var i = 0; i < 4; i++)
                {
                    arr[i] = int.Parse(reader.ReadLine());
                }
            }
            return arr.Reverse();
        }

    }
}
