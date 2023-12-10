const fs = require('fs');
const filePath = './src/input9.txt';

const readStream = fs.createReadStream(filePath);
let total = 0;
readStream.on('data', chunk => {
  const lines = chunk.toString().split('\n');

  lines.forEach(line => {
    let temp = line.split(' ').map(x => Number(x));
    let ans = 0;
    while(temp.length > 1) {
        const next = [];
        for(let i = 1; i < temp.length; i++) {
            next.push(temp[i] - temp[i-1]);
            if (i == temp.length - 1) ans += temp[i];
        }
        temp = next;
    }
    total += ans;
  })
  console.log(total);
});

readStream.on('end', () => {
    console.log('Finished reading file');
});

readStream.on('error', error => {
    console.error(`Error reading file: ${error}`);
});