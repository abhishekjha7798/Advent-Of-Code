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

map<int, int> countMap;

void solve(string input) {
    // cout<<input<<endl;
    int base = 0, i, lastEmpty = 0;
    rep(i, input.size()) {
        if (input[i] == 'O') {
            countMap[base + lastEmpty++]++;
        } else if (input[i] == '#') {
            base = i + 1;
            lastEmpty = 0;
        }
    }
}

int32_t main(){
freopen("./src/input14.txt", "r", stdin);
freopen("./src/Output/output.txt", "w", stdout);
ios_base::sync_with_stdio(false);
cin.tie(NULL);
cout.tie(NULL);
////////////////////////
// 1. no of lines in yr input
int x_max = 100;
///////////////////////
vector<string> input;
for (int i = 0;i < x_max; i++) {
  string s;cin>>s;
  input.pb(s);
}

int y_max = input[0].size();

int ans = 0;

vector<string> col;

int i, j;
rep(i, y_max) {
    string temp = "";
    rep(j, x_max) {
        temp+=input[j][i];
    }
    solve(temp);
    col.pb(temp);
}

for(auto it = countMap.begin(); it!=countMap.end(); it++) {
    // cout<<it->first<<" "<<it->second<<endl;
    ans += (x_max - it->first)*(it->second);
}

cout<<ans<<endl;

return 0;
}