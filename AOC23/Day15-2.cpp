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
#define v2 vector< vector< int > >
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

vector<pair<string, int> > lens[256];

int getHash(string text) {
    int n = text.size(), i, ans = 0;
    rep(i, n) {
        int val = text[i];
        ans += val;
        ans*=17;
        ans%=256;
    }
    return ans;
};

void solve(string text) {
    int ind = text.find('-');
    if (ind != -1) {
        string label = text.substr(0, ind);
        int box = getHash(label);
        auto it = find_if(lens[box].begin(), lens[box].end(), [&label](const pair<string, int>& element) { return element.first == label;} );
        if (it != lens[box].end()) {
            lens[box].erase(it);
        }
    } else {
        ind = text.find('=');
        string label = text.substr(0, ind);
        int value = stoi(text.substr(ind+1));
        int box = getHash(label);
        auto it = find_if(lens[box].begin(), lens[box].end(), [&label](const pair<string, int>& element) { return element.first == label;} );
        if (it != lens[box].end()) {
            it->second = value;
        } else {
            lens[box].pb(mp(label, value));
        }
    }
}

int32_t main(){
freopen("./src/input15.txt", "r", stdin);
freopen("./src/Output/output.txt", "w", stdout);
ios_base::sync_with_stdio(false);
cin.tie(NULL);
cout.tie(NULL);
////////////////////////
// no of lines in yr input
// int x_max = 1343;
///////////////////////
string input;
cin>>input;
int i, j;
int ans = 0;
string curr = "";
rep(i, input.size() + 1) {
    if (i == input.size() || input[i] == ',') {
        solve(curr);
        curr = "";
        continue;
    }
    curr+=input[i];
}

rep(i, 256) {
    rep(j, lens[i].size()) {
        ans += (i+1)*(j+1)*lens[i][j].second;
    }
}

cout<<ans<<endl;

return 0;
}
