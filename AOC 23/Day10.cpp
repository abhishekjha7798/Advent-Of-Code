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

int32_t main(){
freopen("./src/input10.txt", "r", stdin);
freopen("./src/Output/output.txt", "w", stdout);
ios_base::sync_with_stdio(false);
cin.tie(NULL);
cout.tie(NULL);
////////////////////////
// 1. no of lines in yr input
int x_max = 140;
// 2. row number of 'S'
int startRow = 26;
///////////////////////
vector<string> input;
for (int i = 0;i<x_max;i++) {
  string s;cin>>s;
  input.pb(s);
}

int y_max = input[0].size();

int start = input[startRow].find('S');

queue<pair<int, int>> q;
set<pair<int, int>> visited;
int d[x_max][y_max];
memset(d, 0, sizeof(d));

q.push(mp(startRow, start));
visited.insert(mp(startRow, start));

/////////////////////// will differ with different input
input[startRow][start] = '|';
///////////////////////
int ans =0;
while(!q.empty()) {
  pair<int, int> curr = q.front();
  q.pop();
  int x = curr.first, y = curr.second;
  if (x+1 < x_max && (input[x][y] == '7' || input[x][y] == '|' || input[x][y] == 'F') && (input[x+1][y] == 'L' || input[x+1][y] == '|' || input[x+1][y] == 'J') && visited.find(mp(x+1, y)) == visited.end() ) {
    q.push(mp(x+1, y));
    visited.insert(mp(x+1, y));
    d[x+1][y] = d[x][y] + 1;
    ans = max(ans, d[x][y] + 1);
  }
  if (x-1 >= 0 && (input[x][y] == 'L' || input[x][y] == '|' || input[x][y] == 'J') && (input[x-1][y] == '7' || input[x-1][y] == '|' || input[x-1][y] == 'F') && visited.find(mp(x-1, y)) == visited.end() ) {
    q.push(mp(x-1, y));
    visited.insert(mp(x-1, y));
    d[x-1][y] = d[x][y] + 1;
    ans = max(ans, d[x][y] + 1);
  }
  if (y-1 >= 0 && (input[x][y] == '7' || input[x][y] == '-' || input[x][y] == 'J') && (input[x][y-1] == 'L' || input[x][y-1] == '-' || input[x][y-1] == 'F') && visited.find(mp(x, y-1)) == visited.end() ) {
    q.push(mp(x, y-1));
    visited.insert(mp(x, y-1));
    d[x][y-1] = d[x][y] + 1;
    ans = max(ans, d[x][y] + 1);
  }
  if (y+1 < y_max && (input[x][y] == 'F' || input[x][y] == '-' || input[x][y] == 'L') && (input[x][y+1] == '7' || input[x][y+1] == '-' || input[x][y+1] == 'J') && visited.find(mp(x, y+1)) == visited.end() ) {
    q.push(mp(x, y+1));
    visited.insert(mp(x, y+1));
    d[x][y+1] = d[x][y] + 1;
    ans = max(ans, d[x][y] + 1);
  }
}

// for (int i =0; i< x_max; i++) {
//   for (int j = 0;j<y_max;j++) {
//     cout<<d[i][j]<<" ";
//   }
//   cout<<endl;
// }

cout<<ans;

return 0;
}