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
freopen("./src/input11.txt", "r", stdin);
// freopen("./src/Output/output.txt", "w", stdout);
ios_base::sync_with_stdio(false);
cin.tie(NULL);
cout.tie(NULL);
////////////////////////
// no of lines in yr input
int x_max = 140;
///////////////////////
vector<string> input;
for (int i = 0;i<x_max;i++) {
  string s;cin>>s;
  input.pb(s);
}

int y_max = input[0].size();
int i, j, k;

vector<int> xCount(x_max, 0);
rep(i, x_max) {
    int flag = 0;
    rep(j, y_max) {
        if (input[i][j] == '#'){
            flag = 1;
            break;
        }
    }
    if (i == 0) xCount[i] = flag ? 0: 1;
    else xCount[i] = flag ? xCount[i-1]: xCount[i-1] + 1;
}

vector<int> yCount(y_max, 0);
rep(i, y_max) {
    int flag = 0;
    rep(j, x_max) {
        if (input[j][i] == '#'){
            flag = 1;
            break;
        }
    }
    if (i == 0 ) yCount[i] = flag ? 0: 1;
    else yCount[i] = flag ? yCount[i-1]: yCount[i-1] + 1;
}


// rep(i, x_max) cout<<xCount[i]<<" ";
// cout<<endl;
// rep(i, y_max) cout<<yCount[i]<<" ";
// cout<<endl;
vector<pair<int,int>> pos;
rep(i, x_max) {
    rep(j, y_max) {
        if (input[i][j] == '#'){
            pos.pb({i,j});
        }
    }
}

int ans =0;

rep(i, pos.size()) {
    f(j, i+1, pos.size()) {
        int x1 = pos[i].first, y1 = pos[i].second, x2 = pos[j].first, y2 = pos[j].second;
        int smallAns = abs(x1-x2) + abs(y1-y2);
        smallAns += (abs(xCount[x1] - xCount[x2])*1e6 + abs(yCount[y1] - yCount[y2])*1e6);
        smallAns-=(abs(xCount[x1] - xCount[x2]));
        smallAns-=(abs(yCount[y1] - yCount[y2]));
        // cout<<smallAns<<endl;
        ans += smallAns;
    }
}

cout<<ans<<endl;

return 0;
}