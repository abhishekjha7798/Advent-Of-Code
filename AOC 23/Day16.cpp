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

bool canGoEast(vector<string>& input, char dir, int x, int y) {
    if (dir == 'W') {
        return input[x][y] =='.' || input[x][y] =='-';
    }
    if (dir == 'N') {
        return input[x][y] =='\\' || input[x][y] =='-';
    }
    if (dir == 'S') {
        return input[x][y] =='/' || input[x][y] =='-';
    }
    if (dir == 'E') {
        return false;
    }
    return false;
}

bool canGoWest(vector<string>& input, char dir, int x, int y) {
    if (dir == 'W') {
        return false;
    }
    if (dir == 'N') {
        return input[x][y] =='/' || input[x][y] =='-';
    }
    if (dir == 'S') {
        return input[x][y] =='\\' || input[x][y] =='-';
    }
    if (dir == 'E') {
        return input[x][y] =='.' || input[x][y] =='-';
    }
    return false;
}

bool canGoNorth(vector<string>& input, char dir, int x, int y) {
    if (dir == 'W') {
        return input[x][y] =='/' || input[x][y] =='|';
    }
    if (dir == 'N') {
        return false;
    }
    if (dir == 'S') {
        return input[x][y] =='.' || input[x][y] =='|';
    }
    if (dir == 'E') {
        return input[x][y] =='\\' || input[x][y] =='|';
    }
    return false;
}

bool canGoSouth(vector<string>& input, char dir, int x, int y) {
    if (dir == 'W') {
        return input[x][y] =='\\' || input[x][y] =='|';
    }
    if (dir == 'N') {
        return input[x][y] =='.' || input[x][y] =='|';
    }
    if (dir == 'S') {
        return false;
    }
    if (dir == 'E') {
        return input[x][y] =='/' || input[x][y] =='|';
    }
    return false;
}

int floodFill(vector<string>& input, char dir, int x, int y, int x_max, int y_max) {
    queue<pair<pair<int, int>, char>> q;
    set<pair<pair<int, int>, char>> visited;
    int d[x_max][y_max];
    memset(d, 0, sizeof(d));

    q.push(mp(mp(x, y), dir));
    visited.insert(mp(mp(x, y), dir));
    d[x][y] = 1;

    int ans =0;
    while(!q.empty()) {
    pair<int, int> curr = q.front().first;
    char dir = q.front().second;
    q.pop();
    int x = curr.first, y = curr.second;
    if (x+1 < x_max && canGoSouth(input, dir, x, y) && visited.find(mp(mp(x+1, y), 'N')) == visited.end() ) {
        q.push(mp(mp(x+1, y), 'N'));
        visited.insert(mp(mp(x+1, y), 'N'));
        d[x+1][y] = 1;
    }
    if (x-1 >= 0 && canGoNorth(input, dir, x, y) && visited.find(mp(mp(x-1, y), 'S')) == visited.end()  ) {
        q.push(mp(mp(x-1, y), 'S'));
        visited.insert(mp(mp(x-1, y), 'S'));
        d[x-1][y] = 1;
    }
    if (y-1 >= 0 && canGoWest(input, dir, x, y) && visited.find(mp(mp(x, y-1), 'E')) == visited.end() ) {
        q.push(mp(mp(x, y-1), 'E'));
        visited.insert(mp(mp(x, y-1), 'E'));
        d[x][y-1] = 1;
    }
    if (y+1 < y_max && canGoEast(input, dir, x, y) && visited.find(mp(mp(x, y+1), 'W')) == visited.end() ) {
        q.push(mp(mp(x, y+1), 'W'));
        visited.insert(mp(mp(x, y+1), 'W'));
        d[x][y+1] = 1;
    }
    }

    int i,j;
    rep(i, x_max) {
        rep(j, y_max) {
            if (d[i][j]) ans++;
        }
    }
    return ans;
}

int32_t main(){
freopen("./src/input16.txt", "r", stdin);
freopen("./src/Output/output.txt", "w", stdout);
ios_base::sync_with_stdio(false);
cin.tie(NULL);
cout.tie(NULL);
////////////////////////
// 1. no of lines in yr input
int x_max = 110;
///////////////////////
vector<string> input;
for (int i = 0;i < x_max; i++) {
  string s;cin>>s;
  input.pb(s);
}

int y_max = input[0].size();

int ans = 0;
// part 1
// ans = floodFill(input, 'W', 0, 0, x_max, y_max);



// part 2
int i;
rep(i, x_max) {
    ans = max(ans, floodFill(input, 'W', i, 0, x_max, y_max));
    ans = max(ans, floodFill(input, 'E', i, y_max - 1, x_max, y_max));
}
rep(i, y_max) {
    ans = max(ans, floodFill(input, 'N', 0, i, x_max, y_max));
    ans = max(ans, floodFill(input, 'S', x_max - 1, i, x_max, y_max));
}

cout<<ans<<endl;

return 0;
}