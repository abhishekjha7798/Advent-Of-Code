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


int arr[1000][1000];


int solve(vector<vector<string>> input) {
    int n = input.size();
    int iR = 500, iC = 500;
    int i, j, k = 0, r = iR, c = iC;
    // arr[r][c] = 9;
    int ans = 0;
    rep(i, n) {
        string dir = input[i][0];
        string len = input[i][1];
        ans += stoi(len);
        if (dir == "R") {
            f(j, c + 1, c + 1 + stoi(len)) {
                arr[r][j] = 1;
            }
            c = j - 1;
        } else if (dir == "D") {
            f(j, r + 1, r + 1 + stoi(len)) {
                arr[j][c] = 2;
            }
            r = j - 1;
        } else if (dir == "L") {
            fd(j, c - 1, c - stoi(len)) {
                arr[r][j] = 3;
            }
            c = j + 1;
        } else if (dir == "U") {
            fd(j, r - 1, r - stoi(len)) {
                arr[j][c] = 4;
            }
            r = j + 1;
        }
    }

    rep(i, 1000) {
        rep(j, 1000) {
            int count = 0, flag = 0;
            if (arr[i][j] == 0) {
                k = j+1;
                while(k < 1000) {
                    if(arr[i][k]!=0 && arr[i][k] <= 4) {
                        if(arr[i][k] == 3) {
                            if (arr[i+1][k] == 2) {
                                while (k < 1000 && arr[i][k] == 3)
                                {
                                    k++;
                                }
                                if (arr[i][k] == 2) {
                                    // arr[i][j] = 9;
                                    k++;
                                    count++;
                                }
                            }
                            if (arr[i-1][k] == 4) {
                                while (k < 1000 && arr[i][k] == 3)
                                {
                                    k++;
                                }
                                if (arr[i][k] == 4) {
                                    k++;
                                    // arr[i][j] = 5;
                                    count++;
                                }
                            }
                        }
                        if(arr[i][k] == 2) {
                            if ((arr[i+1][k] == 2 && arr[i-1][k] == 2) || (arr[i+1][k] == 2 && arr[i-1][k] == 1) || (arr[i+1][k] == 2 && arr[i-1][k] == 3)) {
                                // arr[i][j] = 8;
                                k++;
                                count++;
                            } else {
                                k++;
                                while (k < 1000 && arr[i][k] == 1)
                                {
                                    k++;
                                }
                                if (arr[i+1][k-1] == 2) {
                                    // arr[i][j] = 6;
                                    count++;
                                }
                            }
                        }
                        if(arr[i][k] == 4) {
                            if (arr[i+1][k] == 4 && arr[i-1][k] == 4 || (arr[i+1][k] == 3 && arr[i-1][k] == 4) || (arr[i+1][k] == 1 && arr[i-1][k] == 4)) {
                                // arr[i][j] = 8;
                                k++;
                                count++;
                            } else {
                                k++;
                                while (k < 1000 && arr[i][k] == 1)
                                {
                                    k++;
                                }
                                if (arr[i-1][k-1] == 4) {
                                    // arr[i][j] = 7;
                                    count++;
                                }
                            }
                        }
                    }
                    k++;
                }
            }
            if (count%2 == 1) {
                arr[i][j] = 9;
                ans++;
            }
            cout<<arr[i][j]<<" ";
        }
        cout<<endl;
    }
    return ans;;
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
vector<vector<string>> input;
string a, b, c;
for (int i = 0;i < x_max*3; i++) {
    string s;cin>>s;
    if (i%3 == 0) {
        a = s;
    } else if (i%3 == 1) {
        b = s;
    } else {
        c = s.substr(1, s.size()-2);
        input.pb(vector<string> {a, b, c});
    }
}

int ans = 0;

ans = solve(input);


cout<<ans<<endl;

return 0;
}