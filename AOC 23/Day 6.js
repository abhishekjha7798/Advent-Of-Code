const fs = require('fs');
const filePath = './src/input6.txt';

const readStream = fs.createReadStream(filePath);
let total = 0;
readStream.on('data', chunk => {
  solve();
});

const solve = () => {
    // const x = [58, 81, 96, 76];
    // const y = [434, 1041, 2219, 1218];
    const x = [58819676];
    const y = [434104122191218];

    let res = 1;
    for(let i = 0;i < x.length; i++) {
        const a = x[i];
        const t = y[i];

        const z = (a - Math.sqrt(a*a - 4*t))/2.0;

        res = res*(a - 2*Math.floor(z) - 1);
    }

    console.log(res);
}

readStream.on('end', () => {
  console.log('Finished reading file:');
});

readStream.on('error', error => {
  console.error(`Error reading file: ${error}`);
});