using System;
using System.Collections.Generic;
using System.Linq;
using System.IO;

using Map = System.Collections.Generic.Dictionary<char, System.Collections.Generic.List<char>>;
using Path = System.Collections.Generic.List<char>;

class Solution {

    static void Main(string[] args) {
        var map = new Map();

        var dest = Console.ReadLine().Single();

        string inp;
        while ((inp = Console.ReadLine()) != "A A")
        {
            var chars = inp.Split(' ');
            var road1 = chars[0].Single();
            var road2 = chars[1].Single();

            if (!map.ContainsKey(road1)) map.Add(road1, new List<char>());
            if (!map[road1].Contains(road2)) map[road1].Add(road2);

            if (!map.ContainsKey(road2)) map.Add(road2, new List<char>());
            if (!map[road2].Contains(road1)) map[road2].Add(road1);
        }

        var paths = FindPaths(map, new Path{'F'}, dest);

        if (paths.Count > 0) {
            Console.WriteLine("Total Routes: " + paths.Count);
            var shortest = paths.OrderBy(a => a.Count).ThenBy(a => new string(a.ToArray())).First();
            Console.WriteLine("Shortest Route Length: " + shortest.Count);
            Console.WriteLine("Shortest Route after Sorting of Routes of length " + shortest.Count + ": " + string.Join(" ", shortest));
        } else Console.WriteLine("No Route Available from F to " + dest);
    }

    static List<Path> FindPaths(Map map, Path prev, char dest) {
        var now = prev.Last();
        if (now == dest) return new List<Path>{prev};
        var Paths = new List<Path>();

        foreach (var next in map[now]) {
            if (prev.Contains(next)) continue;

            var curr = new Path();
            curr.AddRange(prev);
            curr.Add(next);
            Paths.AddRange(FindPaths(map, curr, dest));
        }

        return Paths;
    }
}