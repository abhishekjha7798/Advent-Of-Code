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

class VisitedNode {
    public:
    int x, y, count;
    char dir;
    VisitedNode(int x, int y, char dir, int count) {
        this->x = x;
        this->y = y;
        this->count = count;
        this->dir = dir;
    }
    bool operator<(const VisitedNode& other) const {
        if (this->x > other.x) return true;
        if (this->x < other.x) return false;
        if (this->y > other.y) return true;
        if (this->y < other.y) return false;
        if (this->count > other.count) return true;
        if (this->count < other.count) return false;
        if (this->dir > other.dir) return true;
        if (this->dir < other.dir) return false;
        return false;
    }
    bool operator=(const VisitedNode& other) const {
        return this->x == other.x && this->y == other.y && this->count == other.count && this->dir == other.dir;
    }
};


class Node {
    public:
    int x, y, dist, count;
    char dir;
    Node(int x, int y, int dist, char dir, int count) {
        this->x = x;
        this->y = y;
        this->dist = dist;
        this->count = count;
        this->dir = dir;
    }
    bool operator<(const Node& other) const {
        return this->dist > other.dist;
    }
};

bool isSafe(int x, int y, int x_max, int y_max) {
    return x>=0 && x<x_max && y>=0 && y<y_max;
}

int dijkstra(vector<string> input, int x_max, int y_max) {
    map<char, pair<int, int>> directions;
    directions['E'] = {0, 1}; directions['W'] = {0, -1};directions['N'] = {-1, 0};directions['S'] = {1, 0};
    map<char, char> reverseDirections;
    reverseDirections['E'] = 'W'; reverseDirections['W'] = 'E';reverseDirections['N'] = 'S';reverseDirections['S'] = 'N';
    
    set<VisitedNode> visited;
    priority_queue<Node> q;
    q.push(Node(0, 0, 0, 'D', 0));

    while(!q.empty()) {
        Node curr = q.top();
        q.pop();
        int x = curr.x;
        int y = curr.y;
        int dist = curr.dist;
        if (x == x_max - 1 && y == y_max -1) {
            return dist;
        }
        int count = curr.count;
        char dir = curr.dir;

        if (visited.find(VisitedNode(x, y, dir, count)) != visited.end()) continue;
        // cout<<"hiii "<<x<<" "<<y<<" "<<dir<<" "<<count<<" "<< dist<<endl;
        visited.insert(VisitedNode(x, y, dir, count));

        if (count < 3 && dir != 'D') {
            int nextRow = x + directions[dir].first;
            int nextCol = y + directions[dir].second;
            if (isSafe(nextRow, nextCol, x_max, y_max)) {
                q.push(Node(nextRow, nextCol, dist + input[nextRow][nextCol] - 48, dir, count + 1));
            }
        }

        for (auto it=directions.begin(); it!=directions.end(); it++) {
            char nextDir = it->first;
            int nextRow = x + it->second.first;
            int nextCol = y + it->second.second;
            if (isSafe(nextRow, nextCol, x_max, y_max) && nextDir != dir && nextDir != reverseDirections[dir]) {
                q.push(Node(nextRow, nextCol, dist + input[nextRow][nextCol] - 48, nextDir, 1));
            }
        }
    }

    return inf;
}

int32_t main(){
freopen("./src/input17.txt", "r", stdin);
freopen("./src/Output/output.txt", "w", stdout);
ios_base::sync_with_stdio(false);
cin.tie(NULL);
cout.tie(NULL);
////////////////////////
// 1. no of lines in yr input
int x_max = 141;
///////////////////////
vector<string> input;
for (int i = 0;i < x_max; i++) {
  string s;cin>>s;
  input.pb(s);
}

int y_max = input[0].size();

int ans = 0;

ans = dijkstra(input, x_max, y_max);

cout<<ans<<endl;

return 0;
}