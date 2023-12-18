const fs = require('fs');
const filePath = './src/input9.txt';

const readStream = fs.createReadStream(filePath);
let total = 0;
readStream.on('data', chunk => {
  const lines = chunk.toString().split('\n');

  lines.forEach(line => {
    let temp = line.split(' ').map(x => Number(x));
    let ans = [];
    while(temp.length > 1) {
        const next = [];
        for(let i = 0; i < temp.length - 1; i++) {
            next.push(temp[i+1] - temp[i]);
            if (i == 0) ans.push(temp[i]);
        }
        temp = next;
    }
    // ans.push(0);
    for(let i = ans.length - 1;i>=0;i--) {
        total = ans[i] - total;
    }
  })
  console.log(total);
});

readStream.on('end', () => {
    console.log('Finished reading file');
});

readStream.on('error', error => {
    console.error(`Error reading file: ${error}`);
});