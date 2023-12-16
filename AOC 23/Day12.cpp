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

// ????  1, 1
int solve(string springs, vi blocks, int springsInd, int blockIndex, int curr) {
    if (springsInd == springs.size()) {
        if (blockIndex == blocks.size()-1 && (blocks[blockIndex] == curr )) {
            // cout<<"uio"<<curr<<endl;
            return 1;
        } else if (blockIndex == blocks.size() && curr == 0) {
            // cout<<"yui"<<curr<<endl;
            return 1;
        } else return 0;
    }
    int ans = 0;
    if (springs[springsInd] == '.') {
        if (blockIndex < blocks.size() && blocks[blockIndex] == curr) {
            ans += solve(springs, blocks, springsInd+1, blockIndex+1, 0);
        } else if (curr == 0) {
            ans += solve(springs, blocks, springsInd+1, blockIndex, 0);
        }
    } else if (springs[springsInd] == '#') {
        if (blockIndex < blocks.size() && blocks[blockIndex] > curr) {
            // cout<<"123456 "<<springsInd<<" "<<blockIndex<<" "<<curr<<endl;
            ans += solve(springs, blocks, springsInd+1, blockIndex, curr + 1);
        }
    } else {
        vector<char> possibleSubs{'.', '#'};
        int i, j, k;
        rep(i, 2){
            char currChar = possibleSubs[i];
            if (currChar == '.') {
                if (blockIndex < blocks.size() && blocks[blockIndex] == curr) {
                    ans += solve(springs, blocks, springsInd+1, blockIndex + 1, 0);
                } else if (curr == 0) {
                    ans += solve(springs, blocks, springsInd+1, blockIndex, 0);
                }
            } else {
                if (blockIndex < blocks.size() && blocks[blockIndex] > curr) {
                    // cout<<"123 "<<springsInd<<" "<<blockIndex<<" "<<curr<<endl;
                    ans += solve(springs, blocks, springsInd+1, blockIndex, curr + 1);
                }
            }
        }
    }
    return ans;
}

// int solve(string springs, vi input, int i, int j, int k) {
//     if (i==springs.size()) {
//         if (j==input.size() && k==0) {
//             return 1;
//         } else if (j==input.size()- 1 and input[j]==k){
//             return 1;
//         } else return 0;
//     }
//     vector<char> v;
//     v.pb('.');
//     v.pb('#');
//     int c;
//     int ans = 0;
//     rep(c, 2) {
//         char currC = v[c];
//         if (springs[i] == currC || springs[i] == '?') {
//             if (currC == '.' && k == 0) {
//                 ans += solve(springs, input, i+1, j, 0);
//             }
//         } else if (currC =='.' && k > 0 and j < input.size() && input[j]==k) {
//             ans += solve(springs, input, i+1, j+1, 0);
//         } else if (currC=='#') {
//             ans += solve(springs, input, i+1, j, k+1);
//         }
//     }
//     return ans;
// }

int32_t main(){
freopen("./src/input12.txt", "r", stdin);
freopen("./src/Output/output.txt", "w", stdout);
ios_base::sync_with_stdio(false);
cin.tie(NULL);
cout.tie(NULL);
////////////////////////
// no of lines in yr input
int x_max = 1000;
///////////////////////
vector<string> springs;
v2 input;
int i, j;
rep(i, 2*x_max) {
  string s;cin>>s;
  if (i%2) {
    s.append(",");
    vi arr;
    string curr = "";
    rep(j, s.size()) {
        if(s[j] == ',') {
            arr.pb(stoi(curr));
            curr = "";
        }
        else curr = curr + s[j];
    }

    input.pb(arr);
  } else {
    springs.pb(s);
  }
}


int ans = 0;
for (int i = 0;i <x_max;i++) {
    // cout<< springs[i]<<endl;
    // cout<<input[0].size()<<endl;
    ans += solve(springs[i], input[i], 0, 0, 0);
}

cout<<ans<<endl;

return 0;
}