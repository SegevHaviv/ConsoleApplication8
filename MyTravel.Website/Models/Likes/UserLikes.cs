using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace MyTravel.Website.Models.Likes
{
    public class UserLikes
    {
        public int Step { get; set; }

        public int[] Choose { get; set; }

        public LikeOption[] Options { get; set; }

        public List<int> Shown { get; set; }

        public int[] Vector { get; set; }
    }
}