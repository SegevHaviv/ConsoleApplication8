using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace MyTravel.Website.Models.Likes
{
    public class ImageLikeFactory
    {
        public IDictionary<int, string> Dictionary { get; private set; }

        public ImageLikeFactory()
        {
            var dic = new Dictionary<int, string>
            {
                [1] = "/Content/images/likes/1.jpg",
                [2] = "/Content/images/likes/2.jpg",
                [3] = "/Content/images/likes/3.jpg",
                [4] = "/Content/images/likes/4.jpg",
                [5] = "/Content/images/likes/5.jpg",
                [6] = "/Content/images/likes/6.jpg",
                [7] = "/Content/images/likes/7.jpg",
                [8] = "/Content/images/likes/8.jpg",
                [9] = "/Content/images/likes/9.jpg",
                [10] = "/Content/images/likes/10.jpg",
                [11] = "/Content/images/likes/11.jpg",
                [12] = "/Content/images/likes/12.jpg",
                [13] = "/Content/images/likes/13.jpg",
                [14] = "/Content/images/likes/14.jpg",
                [15] = "/Content/images/likes/15.jpg",
                [16] = "/Content/images/likes/16.jpg",
                [17] = "/Content/images/likes/17.jpg",
                [18] = "/Content/images/likes/18.jpg",
                [19] = "/Content/images/likes/19.jpg",
                [20] = "/Content/images/likes/20.jpg",
                [21] = "/Content/images/likes/21.jpeg",
                [22] = "/Content/images/likes/22.jpg",
                [23] = "/Content/images/likes/23.jpg",
                [24] = "/Content/images/likes/24.jpg",
                [25] = "/Content/images/likes/25.jpg",
                [26] = "/Content/images/likes/26.jpg",
                [27] = "/Content/images/likes/27.jpg",
                [28] = "/Content/images/likes/28.jpg",
                [29] = "/Content/images/likes/29.jpeg",
                [30] = "/Content/images/likes/30.jpg",
                [31] = "/Content/images/likes/31.jpg",
                [32] = "/Content/images/likes/32.jpg",
                [33] = "/Content/images/likes/33.jpg",
                [34] = "/Content/images/likes/34.jpg",
                [35] = "/Content/images/likes/35.jpg",
                [36] = "/Content/images/likes/36.jpg",
                [37] = "/Content/images/likes/37.jpg",
                [38] = "/Content/images/likes/38.jpg",
                [39] = "/Content/images/likes/39.jpg",
                [40] = "/Content/images/likes/40.jpg"

            };
            Dictionary = dic;
        }
    }
}