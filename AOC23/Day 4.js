const fs = require('fs');
const filePath = './src/input4.txt';

const readStream = fs.createReadStream(filePath);
let total = 0;
readStream.on('data', chunk => {
  const lines = chunk.toString().split('\n');
  let ans = 0;
  let card = 1;
  lines.forEach((line) => {
    // console.log(line);
    const fnOp = solve(line, card++);
    ans+=fnOp;
  });
  console.log(ans);
});
const arr = new Array(207);
arr.fill(1);
const solve = (text, card) => {
    const a = text.split(':');
    const win = a[1].split('|');
    const first = [];
    const second = [];
    let x = win[0].split(' ');
    x.forEach(y => {
        if (!isNaN(y) && Number(y) > 0) first.push(Number(y));
    })
    x = win[1].split(' ');
    x.forEach(y => {
        if (!isNaN(y) && Number(y) > 0) second.push(Number(y));
    })
    let count = 0;
    // let ans = 1;
    second.forEach(s => {
        if (first.includes(s)) count++;
    })
    // if (count ==0) return 0;
    // for (let i =1; i<count; i++) {
    //     ans*=2;
    // }

    for (let i = 1;i<=count; i++) {
        arr[card + i] += (arr[card]);
    }
    console.log('@@@@', card, arr[card]);
    return arr[card];
}

const solve1 = (text) => {
    const a = text.split(':');
    const win = a[1].split('|');
    const first = [];
    const second = [];
    let x = win[0].split(' ');
    x.forEach(y => {
        if (!isNaN(y) && Number(y) > 0) first.push(Number(y));
    })
    x = win[1].split(' ');
    x.forEach(y => {
        if (!isNaN(y) && Number(y) > 0) second.push(Number(y));
    })
    let count = 0;
    let ans = 1;
    second.forEach(s => {
        if (first.includes(s)) count++;
    })
    if (count ==0) return 0;
    for (let i =1; i<count; i++) {
        ans*=2;
    }
    return ans;
}

readStream.on('end', () => {
  console.log('Finished reading file');
});

readStream.on('error', error => {
  console.error(`Error reading file: ${error}`);
});