const fs = require('fs');
const filePath = './src/input2.txt';

const readStream = fs.createReadStream(filePath);
let total = 0;
readStream.on('data', chunk => {
  const lines = chunk.toString().split('\n');
  lines.forEach((line, index) => {
    console.log(line);
    const fnOp = solve(line, index+1);
    total+=fnOp;
  });
  console.log(total);
});

const solve = (text, gameNumber) => {
    const red = getIndex(text, 'red', 12);
    const blue = getIndex(text, 'blue', 14);
    const green = getIndex(text, 'green', 13);
    if (red ==-1 || green ==-1 || blue == -1) {
        return 0;
    }
    return red*blue*green;
}

const getIndex = (str, searchStr, comp) => {
    var startIndex = 0, index, indices = [];
    let ans = 0;
    str = str.toLowerCase();
    while ((index = str.indexOf(searchStr, startIndex)) > -1) {
        indices.push(index);
        const tens = str.charAt(index-3) == ' '? 0 : str.charAt(index-3);
        const number = tens*10 + parseInt(str.charAt(index-2));
        if (number > ans) {
            ans = number;
        }
        startIndex = index + searchStr.length;
    }
    return ans;
}
readStream.on('end', () => {
  console.log('Finished reading file');
});

readStream.on('error', error => {
  console.error(`Error reading file: ${error}`);
});