#include <iostream>
#include <algorithm>
#include <bitset>
#include <complex>
#include <exception>
#include <iomanip>
#include <istream>
#include <ostream>
#include <sstream>
#include <string>
#include <iterator>
#include <queue>
#include <deque>
#include <stack>
#include <map>
#include <set>
#include <vector>
#include <unordered_map>
#include <unordered_set>
//setbase - cout << setbase (16)a; cout << 100 << endl; Prints 64
//setfill -   cout << setfill ('x') << setw (5); cout << 77 <<endl;prints xxx77
//setprecision - cout << setprecision (14) << f << endl; Prints x.xxxx
//cout.precision(x)  cout<<fixed<<val;  // prints x digits after decimal in val
using namespace std;
#define f(i,a,b) for(i=(a);i<(b);i++)
#define rep(i,n) f(i,0,n)
#define fd(i,a,b) for(i=(a);i>=(b);i--)
#define pb push_back
#define mp make_pair
#define endl "\n"
#define vi vector< int >
#define vl vector< ll >
#define ss second
#define ff first
#define ll long long
#define pii pair< int,int >
#define pll pair< ll,ll >
#define sz(a) a.size()
#define inf (1000*1000*1000+5)
#define all(a) a.begin(),a.end()
#define tri pair<int,pii>
#define vii vector<pii>
#define vll vector<pll>
#define viii vector<tri>
#define mod (1000000007)
#define pqueue priority_queue< int >
#define pdqueue priority_queue< int,vi ,greater< int > >
#define int ll
///////////////////////////////////////////////////////////

int solve(vector<pair<string, int>> input) {
    int n = input.size();
    int i;
    vector<pair<int, int>> points;
    points.pb({0,0});
    map<string, pair<int, int>> directions;
    directions["R"] = {0, 1}; directions["L"] = {0, -1};directions["U"] = {-1, 0};directions["D"] = {1, 0};
    int boundaryPoints = 0;
    rep(i, n) {
        string dir = input[i].first;
        int len = input[i].second;
        boundaryPoints+=len;

        pair<int, int> lastPoint = points.back();
        pair<int, int> nextPoint = {lastPoint.first + directions[dir].first*len, lastPoint.second + directions[dir].second*len};
        points.pb(nextPoint);
    }

    // apply shoelace formula A = sum(Xi(Yi+1 - Yi))/2
    int area = 0;
    f(i, 1, points.size() - 1) {
        // cout<<points[i].first<<" "<<points[i].second<<endl;
        int xi = points[i].first;
        int y2 = points[i+1].second;
        int y1 = points[i-1].second;
        area+= (xi)*(y2-y1);
    }
    area = abs(area)/2;

    // apply pick's theorem A = i + b/2 - 1;

    int interiorArea = area - boundaryPoints/2 + 1;
    area = interiorArea + boundaryPoints;
    return area;
}


int32_t main(){
freopen("./src/input18.txt", "r", stdin);
freopen("./src/Output/output.txt", "w", stdout);
ios_base::sync_with_stdio(false);
cin.tie(NULL);
cout.tie(NULL);
////////////////////////
// 1. no of lines in yr input
int x_max = 712;
///////////////////////
vector<pair<string, int>> input;
map<char, string> directions;
directions['0'] = "R"; directions['1'] = "D"; directions['2'] = "L"; directions['3'] = "U";
string a, c;
int b;
for (int i = 0;i < x_max*3; i++) {
    string s;cin>>s;
    if (i%3 == 0) {
        a = s;
    } else if (i%3 == 1) {
        b = stoi(s);
    } else {
        c = s.substr(1, s.size()-2);
        // part 2  // comment this for faster part 1
        a = directions[c[6]];
        b = stoi(c.substr(1, 5), 0, 16);
        // part 2 end
        input.pb({a, b});
    }
}

int ans = 0;

ans = solve(input);


cout<<ans<<endl;

return 0;
}