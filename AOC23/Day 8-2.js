const fs = require('fs');
const filePath = './src/input8.txt';

const readStream = fs.createReadStream(filePath);
let total = 0;
readStream.on('data', chunk => {
  const lines = chunk.toString().split('\n\n');
  const direction = lines[0];

  const map = lines[1].split('\n');
  const myMap = new Map();
  map.forEach(m => {
    let key = m.substring(0, 3)
    let value = { l: m.substring(7, 10), r: m.substring(12, 15)}
    myMap.set(key, value);
  })
  let start = []
  myMap.forEach((v, k) => {
    if (k.endsWith('A')) start.push(k);
  });
  const ans = [];
  start.forEach(key => {
    let i = 0;
    let res = 0;
    while(key[2] != 'Z') {
        res++;
        if (direction[i] == 'L') {
            key = myMap.get(key).l;
        } else {
            key = myMap.get(key).r;
        }
        i++;
        if (i == direction.length) i = 0;
    }
    ans.push(res);
  })
  let f = ans[0];
  for (let i = 1; i<ans.length; i++) {
    const t = lcm(f, ans[i]);
    f = t;
  }
  total = f;

  console.log(total);
});

const gcd = (a, b) => { 
    for (let i = b; b !== 0;) { 
        b = a % b; 
        a = i; 
        i = b; 
    } 
    return a; 
} 
  
const lcm = (a, b) => { 
    const hcf = gcd(a, b); 
    return (a * b) / hcf; 
} 

readStream.on('end', () => {
    console.log('Finished reading file');
});

readStream.on('error', error => {
    console.error(`Error reading file: ${error}`);
});


// const fs = require('fs');
// const filePath = './src/input8.txt';

// const readStream = fs.createReadStream(filePath);
// let total = 0;
// readStream.on('data', chunk => {
//   const lines = chunk.toString().split('\n\n');
//   const direction = lines[0];

//   const map = lines[1].split('\n');
//   const myMap = new Map();
//   map.forEach(m => {
//     let key = m.substr(0, 3)
//     let value = { l: m.substr(7, 3), r: m.substr(12, 3)}
//     myMap.set(key, value);
//   })
//   let start = []
//   myMap.forEach((v, k) => {
//     if (k.endsWith('A')) start.push(k);
//   });
//   let i = 0;
//   while(true) {
//     total++;
//     const newArray = [];
//     // console.log('&&', start);
//     start.forEach(key => {
//         if (direction[i] == 'L') {
//             newArray.push(myMap.get(key).l);
//         } else {
//             newArray.push(myMap.get(key).r);
//         }
//     })
//     if (newArray.every(a => a.endsWith('Z'))) break;
//     start = newArray;
//     i++;
//     if (i == direction.length) i = 0;
//   }

//   console.log('&&', myMap);
//   console.log(total);
// });

// readStream.on('end', () => {
//     console.log('Finished reading file');
// });

// readStream.on('error', error => {
//     console.error(`Error reading file: ${error}`);
// });