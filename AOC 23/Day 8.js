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
  let key = 'AAA';
  let i = 0;
  while(key !== 'ZZZ') {
    total++;
    if (direction[i] == 'L') {
        key = myMap.get(key).l;
    } else {
        key = myMap.get(key).r;
    }
    i++;
    if (i == direction.length) i = 0;
  }

  console.log('&&', myMap);
  console.log(total);
});

readStream.on('end', () => {
    console.log('Finished reading file');
});

readStream.on('error', error => {
    console.error(`Error reading file: ${error}`);
});