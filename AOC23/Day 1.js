const fs = require('fs');
const filePath = './src/input1.txt';

const readStream = fs.createReadStream(filePath);
let total = 0;
readStream.on('data', chunk => {
  const lines = chunk.toString().split('\n');

  lines.forEach(line => {
    const res = findFirstAndLastNumber(line);
    total += res;
  });
});

const list1 = [
    "zero",
    "one",
    "two",
    "three",
    "four",
    "five",
    "six",
    "seven",
    "eight",
    "nine"
]

const list2 = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9']

const findFirstAndLastNumber = (text) => {
    let minPos = 100000000;
    let maxPos = -1;
    let minTillNow;
    let maxTillNow;
    list1.forEach((l, index) => {
        const pos = text.search(l);
        const pos2 = text.lastIndexOf(l);
        if (pos!= -1 && pos<minPos) {
            minPos = pos;
            minTillNow = index;
        }
        if (pos!= -1 && pos>maxPos) {
            maxPos = pos;
            maxTillNow = index;
        }
        if (pos2!= -1 && pos2>maxPos) {
            maxPos = pos2;
            maxTillNow = index;
        }
    })
    list2.forEach((l, index) => {
        const pos = text.search(l);
        const pos2 = text.lastIndexOf(l);
        if (pos!= -1 && pos<minPos) {
            minPos = pos;
            minTillNow = index;
        }
        if (pos!= -1 && pos>maxPos) {
            maxPos = pos;
            maxTillNow = index;
        }
        if (pos2!= -1 && pos2>maxPos) {
            maxPos = pos2;
            maxTillNow = index;
        }
    })
    if (minPos == 100000000 || maxPos == -1) return 0;
    return (minTillNow*10 + maxTillNow);
}

readStream.on('end', () => {
  console.log('Finished reading file', total);
});

readStream.on('error', error => {
  console.error(`Error reading file: ${error}`);
});