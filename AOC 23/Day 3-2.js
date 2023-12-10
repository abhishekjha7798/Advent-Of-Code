const fs = require('fs');
const filePath = './src/input3.txt';

const readStream = fs.createReadStream(filePath);
let total = 0;
readStream.on('data', chunk => {
  const lines = chunk.toString().split('\n');
  const input = chunk.toString();
  const right = lines[0].length;
  const left = 0;
  const top = 0;
  const bottom = lines.length;
//   console.log('$%^^', right);
//   console.log('###$%', bottom);
  let i = top;
  while (i < bottom) {
    let j = left;
    while (j < right) {
        if (lines[i][j] == '*') {
            let number1 = 1, number2 = 1, number3 = 1, number4 = 1, number5 = 1, number6 = 1, number7 = 1, number8 = 1;
            let count  = 0;
            // line i-1
            if (i > 0 && !isNaN(lines[i-1][j])) {
                let k = j;
                let num1 = ''
                while (k >= 0 && !isNaN(lines[i-1][k])) {
                    num1 = lines[i-1][k] + num1;
                    k--;
                }
                k = j + 1;
                while (k < right && !isNaN(lines[i-1][k])) {
                    num1 = num1 + lines[i-1][k];
                    k++;
                }
                if (!isNaN(num1) && Number(num1) > 0) number1 = num1;
                count++;
            }
            if (i > 0 && isNaN(lines[i-1][j])) {
                let k = j - 1;
                let num1 = ''
                if (!isNaN(lines[i-1][k])) count ++; 
                while (k >= 0 && !isNaN(lines[i-1][k])) {
                    num1 = lines[i-1][k] + num1;
                    k--;
                }
                if (!isNaN(num1) && Number(num1) > 0) number2 = num1;
                let num2 = '';
                k = j + 1;
                if (!isNaN(lines[i-1][k])) count ++; 
                while (k < right && !isNaN(lines[i-1][k])) {
                    num2 = num2 + lines[i-1][k];
                    k++;
                }
                if (!isNaN(num2) && Number(num2) > 0) number3 = num2;
            }
            // line i
            if (j > 0 && !isNaN(lines[i][j-1])) {
                count++;
                let k = j - 1;
                let num1 = '';
                while (k >= 0 && !isNaN(lines[i][k])) {
                    num1 = lines[i][k] + num1;
                    k--;
                }
                if (!isNaN(num1) && Number(num1) > 0) number4 = num1;
            }
            if (j < right - 1 && !isNaN(lines[i][j+1])) {
                count++;
                let k = j + 1;
                let num1 = '';
                while (k < right && !isNaN(lines[i][k])) {
                    num1 = num1 + lines[i][k];
                    k++;
                }
                if (!isNaN(num1) && Number(num1) > 0) number5 = num1;
            }
            // line i+1
            if (i < bottom - 1 && !isNaN(lines[i+1][j])) {
                let k = j;
                let num1 = ''
                while (k >= 0 && !isNaN(lines[i+1][k])) {
                    num1 = lines[i+1][k] + num1;
                    k--;
                }
                k = j + 1;
                while (k < right && !isNaN(lines[i+1][k])) {
                    num1 = num1 + lines[i+1][k];
                    k++;
                }
                count++;
                if (!isNaN(num1) && Number(num1) > 0) number6 = num1;
            } 
            if (i < bottom -1 && isNaN(lines[i+1][j])) {
                let k = j - 1;
                let num1 = ''
                if (!isNaN(lines[i+1][k])) count ++; 
                while (k >= 0 && !isNaN(lines[i+1][k])) {
                    num1 = lines[i+1][k] + num1;
                    k--;
                }
                if (!isNaN(num1) && Number(num1) > 0) number7 = num1;
                let num2 = '';
                k = j + 1;
                if (!isNaN(lines[i+1][k])) count ++; 
                while (k < right && !isNaN(lines[i+1][k])) {
                    num2 = num2 + lines[i+1][k];
                    k++;
                }
                if (!isNaN(num2) && Number(num2) > 0) number8 = num2;
            }

            //// check
            if (count == 2) {
                // console.log('----------------------------', number1, '%%%', number2, '%%%', number3, '%%%', number4, '%%%', number5, '%%%', number6, '%%%', number7, '%%%', number8);
                total+= (Number(number1)*Number(number2)*Number(number3)*Number(number4)*Number(number5)*Number(number6)*Number(number7)*Number(number8));
            }
        }
        j++;
    }
    // console.log('----------------------------');
    i++;
  }
  console.log('######', total);
});

readStream.on('end', () => {
    console.log('Finished reading file:');
});
  
readStream.on('error', error => {
    console.error(`Error reading file: ${error}`);
});