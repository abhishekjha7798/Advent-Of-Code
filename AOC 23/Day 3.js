const fs = require('fs');
const filePath = './src/input3.txt';

const readStream = fs.createReadStream(filePath);
let total = 0;
readStream.on('data', chunk => {
  const lines = chunk.toString().split('\n');
  const right = lines[0].length;
  const left = 0;
  const top = 0;
  const bottom = lines.length;
//   console.log('$%^^', right);
//   console.log('###$%', bottom);
  let i = top;
  while (i < bottom) {
    let num = '';
    let flag = false ;
    let j = left;
    while (j < right) {
        if (!isNaN(lines[i][j])) {
            num+=lines[i][j];
            if (i > 0 && j > 0 && isSymbol(lines[i-1][j-1])){ flag = true}
            if (i > 0 && isSymbol(lines[i-1][j])){ flag = true }
            if (i > 0 && j < right - 1 && isSymbol(lines[i-1][j+1])){ flag = true }
            if (j > 0 && isSymbol(lines[i][j-1])){ flag = true }
            if (j < right-1 && isSymbol(lines[i][j+1])){ flag = true }
            if (i < bottom - 1 && j > 0 && isSymbol(lines[i+1][j-1])){ flag = true }
            if (i < bottom - 1 && isSymbol(lines[i+1][j])){ flag = true }
            if (i < bottom - 1 && j < right-1 && isSymbol(lines[i+1][j+1])){ flag = true }

            if(j == right - 1 && flag == true && !isNaN(num)) {
                total+= Number(num);
                flag = false;
                num = '';
            }
        } else {
            if (flag == true && !isNaN(num)) {
                // console.log('^^^^^^', num);
                total+= Number(num);
            }
            num = '';
            flag = false;
        }
        j++;
    }
    // console.log('----------------------------');
    i++;
  }
  console.log('######', total);
});

const isSymbol = (character) => {
    if (character == '.') return false;
    if (!isNaN(character)) return false;
    return true;
}

readStream.on('end', () => {
    console.log('Finished reading file');
});
  
readStream.on('error', error => {
    console.error(`Error reading file: ${error}`);
});