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

int getValue(vector<string> input) {
    int i, j, k, l, r;
    int n = input.size();
    int ans = 0;
    f(i, 1, n) {
        if (i%2 == 0) continue;
        int mid = i/2;
        rep(j, mid+1) {
            if (input[j] != input[mid*2 + 1 - j]) {
                break;
            }
        }
        if (j == mid+1) {
            ans = mid + 1;
        }
    }
    return ans;
}

int solve(vector<string> input) {
    int val1 = getValue(input);
    vector<string> reverseInput = input;
    reverse(reverseInput.begin(), reverseInput.end());
    int val2 = getValue(reverseInput);
    if (val2!=0) val2 = input.size() - val2;

    vector<string> col;
    int n = input[0].size();
    int i, j;
    rep(i, n) {
        string temp ="";
        rep(j, input.size()) {
            temp+=input[j][i];
        }
        col.pb(temp);
    }

    int val3 = getValue(col);
    vector<string> reverseCol = col;
    reverse(reverseCol.begin(), reverseCol.end());
    int val4 = getValue(reverseCol);
    if (val4!=0) val4 = col.size() - val4;
    cout<<val1<<" "<<val2<<" "<<val3<<" "<<val4<<endl;

    return (val1+val2)*100 + (val3 + val4);
}

int32_t main(){
freopen("./src/input13.txt", "r", stdin);
freopen("./src/Output/output.txt", "w", stdout);
ios_base::sync_with_stdio(false);
cin.tie(NULL);
cout.tie(NULL);
////////////////////////
// no of lines in yr input
int x_max = 1343;
///////////////////////
vector<string> input;
int i, j;
int ans = 0;
for (int i = 0;i < x_max;i++) {
    string s;getline(cin, s);
    if (s.size()==0) {
        ans += solve(input);
        // cout<<input.size()<<endl;
        input.clear();
        continue;
    }
    input.pb(s);
}
ans += solve(input);

cout<<ans<<endl;

return 0;
}



// #include <iostream>
// #include <algorithm>
// #include <bitset>
// #include <complex>
// #include <exception>
// #include <iomanip>
// #include <istream>
// #include <ostream>
// #include <sstream>
// #include <string>
// #include <iterator>
// #include <queue>
// #include <deque>
// #include <stack>
// #include <map>
// #include <set>
// #include <vector>
// #include <unordered_map>
// #include <unordered_set>
// //setbase - cout << setbase (16)a; cout << 100 << endl; Prints 64
// //setfill -   cout << setfill ('x') << setw (5); cout << 77 <<endl;prints xxx77
// //setprecision - cout << setprecision (14) << f << endl; Prints x.xxxx
// //cout.precision(x)  cout<<fixed<<val;  // prints x digits after decimal in val
// using namespace std;
// #define f(i,a,b) for(i=(a);i<(b);i++)
// #define rep(i,n) f(i,0,n)
// #define fd(i,a,b) for(i=(a);i>=(b);i--)
// #define pb push_back
// #define mp make_pair
// #define endl "\n"
// #define vi vector< int >
// #define v2 vector< vector< int > >
// #define ss second
// #define ff first
// #define ll long long
// #define pii pair< int,int >
// #define pll pair< ll,ll >
// #define sz(a) a.size()
// #define inf (1000*1000*1000+5)
// #define all(a) a.begin(),a.end()
// #define tri pair<int,pii>
// #define vii vector<pii>
// #define vll vector<pll>
// #define viii vector<tri>
// #define mod (1000000007)
// #define pqueue priority_queue< int >
// #define pdqueue priority_queue< int,vi ,greater< int > >
// #define int ll
// ///////////////////////////////////////////////////////////

// int solve(vector<string> input) {
//     // cout<<"hiiii"<<endl;
//     int i, j, k, l, r;
//     int n = input.size();
//     // rep(i, n) cout<<input[i]<<endl;
//     string end = input[n-1];
//     int ans = 0;
//     rep(i, n) {
//         l = i, r = n - 1;
//         while (l < r && input[l] == input[r]) {
//             l++;r--;
//         }
//         if (l > r) {
//             ans = i + (n-i)/2; 
//         }
//     }
//     if (ans == 0) {
//         string start = input[0];
//         fd(i, n-1, 0) {
//             l = 0, r = i;
//             while (l < r && input[l] == input[r]) {
//                 l++;r--;
//             }
//             if (l > r) {
//                 ans = l; 
//             }
//         }
//     }
//     // cout<<ans<<endl;
//     ans = ans*100;

//     int colAns = 0;
//     vector<string> col;
//     n = input[0].size();
//     rep(i, n) {
//         string temp ="";
//         rep(j, input.size()) {
//             temp+=input[j][i];
//         }
//         col.pb(temp);
//     }
//     // rep(i, n) cout<<col[i]<<endl;
//     rep(i, n) {
//         l = i, r = n - 1;
//         while (l < r && col[l] == col[r]) {
//             l++;r--;
//         }
//         if (l > r) {
//             colAns = i + (n-i)/2; 
//         }
//     }
//     if (ans == 0) {
//         string start = col[0];
//         fd(i, n-1, 0) {
//             l = 0, r = i;
//             while (l < r && col[l] == col[r]) {
//                 l++;r--;
//             }
//             if (l > r) {
//                 colAns = l; 
//             }
//         }
//     }
//     return ans + colAns;
// }

// int32_t main(){
// freopen("./src/input13.txt", "r", stdin);
// freopen("./src/Output/output.txt", "w", stdout);
// ios_base::sync_with_stdio(false);
// cin.tie(NULL);
// cout.tie(NULL);
// ////////////////////////
// // no of lines in yr input
// int x_max = 1343;
// ///////////////////////
// vector<string> input;
// int i, j;
// int ans = 0;
// for (int i = 0;i < x_max;i++) {
//     string s;getline(cin, s);
//     if (s.size()==0) {
//         ans += solve(input);
//         // cout<<input.size()<<endl;
//         input.clear();
//         continue;
//     }
//     input.pb(s);
// }
// ans += solve(input);

// cout<<ans<<endl;

// return 0;
// }