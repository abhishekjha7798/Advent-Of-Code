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

map<vector<string>, pair<int, int> > arrived;
int previous = 0;

void solveNorth(string input, vector<string>& next, int col, int c, map<int, int>& countMap) {
    // cout<<input<<endl;
    int base = 0, i, lastEmpty = 0, n = next.size();
    rep(i, input.size()) {
        if (input[i] == 'O') {
            next[i][col] = '.';
            next[base + lastEmpty][col] = 'O';
            countMap[base + lastEmpty++]++;
        } else if (input[i] == '#') {
            base = i + 1;
            lastEmpty = 0;
        }
    }
}

void solveWest(string input, vector<string>& next, int row, int c, map<int, int>& countMap) {
    // cout<<input<<endl;
    int base = 0, i, lastEmpty = 0, n = next.size();
    vector<string> key(next.begin(), next.end());;

    rep(i, input.size()) {
        if (input[i] == 'O') {
            next[row][i] = '.';
            next[row][base + lastEmpty++] = 'O';
        } else if (input[i] == '#') {
            base = i + 1;
            lastEmpty = 0;
        }
    }
}

void solveSouth(string input, vector<string>& next, int col, int c, map<int, int>& countMap) {
    // cout<<input<<endl;
    int sz = input.size();
    int base = sz-1, i, lastEmpty = 0, n = next.size();
    vector<string> key(next.begin(), next.end());;
    fd(i, sz-1, 0) {
        if (input[i] == 'O') {
            next[i][col] = '.';
            next[base - lastEmpty][col] = 'O';
            countMap[base - lastEmpty++]++;
        } else if (input[i] == '#') {
            base = i - 1;
            lastEmpty = 0;
        }
    }
}

void solveEast(string input, vector<string>& next, int row, int c, map<int, int>& countMap) {
    // cout<<input<<endl;
    int sz = input.size();
    int base = sz-1, i, lastEmpty = 0, n = next.size();
    vector<string> key(next.begin(), next.end());;

    fd(i, sz-1, 0) {
        if (input[i] == 'O') {
            next[row][i] = '.';
            next[row][base - lastEmpty++] = 'O';
        } else if (input[i] == '#') {
            base = i - 1;
            lastEmpty = 0;
        }
    }
}

string getString(vector<string> v) {
    string s = "";
    int i;
    rep(i, v.size()) {
        s+=v[i];
    }
    return s;
}

vector<string> cycle(vector<string>& col, int c) {
    int x_max = col.size();
    int y_max = col[0].size();

    int i, j;

    vector<string> key(col.begin(), col.end());
    if (arrived.find(key) != arrived.end()) {
        // cout<<"Yoohoo"<<endl;
        return key;
    }

    map<int, int> countMap;

    //NORTH
    rep(i, y_max) {
        string temp = "";
        rep(j, x_max) {
            temp+=col[j][i];
        }
        solveNorth(temp, col, i, c, countMap);
    }

    int ans = 0;
    for(auto it = countMap.begin(); it!=countMap.end(); it++) {
        // cout<<it->first<<" "<<it->second<<endl;
        ans += (col.size() - it->first)*(it->second);
    }
    previous = ans;
    countMap.clear();
    ans = 0;

    // rep(i, x_max) cout<<col[i]<<endl;
    // cout<<endl;

    //WEST

    rep(i, x_max) {
        solveWest(col[i], col, i, c, countMap);
    }

    // rep(i, x_max) cout<<col[i]<<endl;
    // cout<<endl;

    //SOUTH
    rep(i, y_max) {
        string temp = "";
        rep(j, x_max) {
            temp+=col[j][i];
        }
        solveSouth(temp, col, i, c, countMap);
    }

    for(auto it = countMap.begin(); it!=countMap.end(); it++) {
        // cout<<it->first<<" "<<it->second<<endl;
        ans += (col.size() - it->first)*(it->second);
    }
    previous = ans;
    countMap.clear();
    ans = 0;

    // rep(i, x_max) cout<<col[i]<<endl;
    // cout<<endl;
    
    //EAST

    rep(i, x_max) {
        solveEast(col[i], col, i, c, countMap);
    }
    arrived[key] = mp(c, previous);
    // cout<<previous<<endl;
    previous = 0;

    // rep(i, x_max) cout<<col[i]<<endl;
    // cout<<endl;

    return vector<string> ();
}

vector<string> cycle2(vector<string>& col, int c) {
    int x_max = col.size();
    int y_max = col[0].size();

    int i, j;

    map<int, int> countMap;

    //NORTH
    rep(i, y_max) {
        string temp = "";
        rep(j, x_max) {
            temp+=col[j][i];
        }
        solveNorth(temp, col, i, c, countMap);
    }

    int ans = 0;
    for(auto it = countMap.begin(); it!=countMap.end(); it++) {
        // cout<<it->first<<" "<<it->second<<endl;
        ans += (col.size() - it->first)*(it->second);
    }

    //WEST

    rep(i, x_max) {
        solveWest(col[i], col, i, c, countMap);
    }

    //SOUTH
    rep(i, y_max) {
        string temp = "";
        rep(j, x_max) {
            temp+=col[j][i];
        }
        solveSouth(temp, col, i, c, countMap);
    }
    
    //EAST

    rep(i, x_max) {
        solveEast(col[i], col, i, c, countMap);
    }

    return col;
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

vector<string> ans;
int c = 0, i;
while (true) {
    vector<string> currAns = cycle(input, c);
    if (currAns.size() != 0) {
        ans = currAns;
        break;
    }
    c++;
}

// rep(i, x_max) cout<<ans[i]<<endl;
// cout<<endl;
// cout<<c<<endl;
int totalCycle = 1e9;
int cycleLength = c - arrived[ans].first;
int discard = arrived[ans].first;
int turn = (totalCycle - discard) % cycleLength;

// cout<<c<<" "<<cycleLength<<" "<<turn<<endl;
vector<string> currAns;
rep(i, turn-1) {
    currAns = cycle2(ans, 0);
    // cout<<arrived[currAns].second<<endl;
}

cout<<arrived[currAns].second<<endl;

return 0;
}