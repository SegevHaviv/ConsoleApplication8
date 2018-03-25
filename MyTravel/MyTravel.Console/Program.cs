using System;
using System.Globalization;
using System.ServiceModel;
using System.Threading;

namespace MyTravel.Console
{
    public class Program
    {
        static void Main(string[] args)
        {
            Thread.CurrentThread.CurrentUICulture = new CultureInfo("en-us");
            
            var travelService = new TravelService();
            using (var host = new ServiceHost(travelService))
            {
                host.Open();
                System.Console.WriteLine("Remoting service start at " + DateTime.Now);
                var input = string.Empty;
                while (input != "0")
                {
                    System.Console.WriteLine("Insert 0 to close the application");
                    input = System.Console.ReadLine();
                }
                host.Close();
            }
        }
    }
}
