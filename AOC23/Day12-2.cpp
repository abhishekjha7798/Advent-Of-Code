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

int dp[200][100][200];


// ????  1, 1
// int solve(string springs, vi blocks, int springsInd, int blockIndex, int curr) {
//     if (springsInd == springs.size()) {
//         if (blockIndex == blocks.size()-1 && (blocks[blockIndex] == curr )) {
//             cout<<"uio"<<curr<<endl;
//             dp[springsInd][blockIndex][curr] = 1;
//             return 1;
//         } else if (blockIndex == blocks.size() && curr == 0) {
//             dp[springsInd][blockIndex][curr] = 1;
//             cout<<"yui"<<curr<<endl;
//             return 1;
//         } else return 0;
//     }
//     int ans = 0;
//     if (springs[springsInd] == '.') {
//         if (blockIndex < blocks.size() && blocks[blockIndex] == curr) {
//             if (dp[springsInd+1][blockIndex+1][curr] != -1) {
//                 ans+= dp[springsInd+1][blockIndex+1][curr];
//             } else ans += solve(springs, blocks, springsInd+1, blockIndex+1, 0);
//         } else if (curr == 0) {
//             if (dp[springsInd][blockIndex][curr] != -1) {
//                 ans += dp[springsInd+1][blockIndex][curr];
//             } else ans += solve(springs, blocks, springsInd+1, blockIndex, 0);
//         }
//     } else if (springs[springsInd] == '#') {
//         if (blockIndex < blocks.size() && blocks[blockIndex] > curr) {
//             cout<<"123456 "<<springsInd<<" "<<blockIndex<<" "<<curr<<endl;
//             if (dp[springsInd][blockIndex][curr] != -1) {
//                 ans+=dp[springsInd+1][blockIndex][curr+1];
//             } else ans += solve(springs, blocks, springsInd+1, blockIndex, curr + 1);
//         }
//     } else {
//         vector<char> possibleSubs{'.', '#'};
//         int i, j, k;
//         rep(i, 2){
//             char currChar = possibleSubs[i];
//             if (currChar == '.') {
//                 if (blockIndex < blocks.size() && blocks[blockIndex] == curr) {
//                     if (dp[springsInd+1][blockIndex+1][curr] != -1) {
//                         ans += dp[springsInd+1][blockIndex+1][curr];
//                     } else ans += solve(springs, blocks, springsInd+1, blockIndex + 1, 0);
//                 } else if (curr == 0) {
//                     if (dp[springsInd][blockIndex][curr] != -1) {
//                         ans += dp[springsInd+1][blockIndex][curr];
//                     } else ans += solve(springs, blocks, springsInd+1, blockIndex, 0);
//                 }
//             } else {
//                 if (blockIndex < blocks.size() && blocks[blockIndex] > curr) {
//                     cout<<"123 "<<springsInd<<" "<<blockIndex<<" "<<curr<<endl;
//                     if (dp[springsInd][blockIndex][curr] != -1) {
//                         ans += dp[springsInd+1][blockIndex][curr+1];
//                     } else ans += solve(springs, blocks, springsInd+1, blockIndex, curr + 1);
//                 }
//             }
//         }
//     }
//     dp[springsInd][blockIndex][curr] = ans;
//     return ans;
// }

// ????  1, 1
int solve(string springs, vi blocks, int springsInd, int blockIndex, int curr) {
    if (springsInd == springs.size()) {
        if (blockIndex == blocks.size()-1 && (blocks[blockIndex] == curr )) {
            // cout<<"uio"<<curr<<endl;
            // dp[springsInd][blockIndex][curr] = 1;
            return 1;
        } else if (blockIndex == blocks.size() && curr == 0) {
            // dp[springsInd][blockIndex][curr] = 1;
            // cout<<"yui"<<curr<<endl;
            return 1;
        } else return 0;
    }
    if (dp[springsInd][blockIndex][curr] != -1) return dp[springsInd][blockIndex][curr];
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
    dp[springsInd][blockIndex][curr] = ans;
    return ans;
}

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
int i, j, k;
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
    vi newArr;
    rep(j, 5) rep(k, arr.size()) newArr.pb(arr[k]);
    input.pb(newArr);
  } else {
    string newString = "";
    rep(j, 5) {
        newString = newString + s;
        if (j < 4) {
            newString = newString + "?";
        }
    }
    springs.pb(newString);
  }
}

// rep(i, 200) rep(j, 100) rep(k, 200) dp[i][j][k] = -1;
int ans = 0;
for (int i = 0;i < x_max;i++) {
    // cout<< springs[i]<<" "<<input[i].size()<<endl;
    // cout<<input[0].size()<<endl;
    memset(dp, -1, sizeof(dp));
    ans += solve(springs[i], input[i], 0, 0, 0);
}

cout<<ans<<endl;

return 0;
}