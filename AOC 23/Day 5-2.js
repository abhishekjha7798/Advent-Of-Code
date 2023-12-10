const fs = require('fs');
const filePath = './src/input5.txt';

const readStream = fs.createReadStream(filePath);
readStream.on('data', chunk => {
  let input = chunk.toString().split('\n\n');
  let input1 = input[0].split(':')[1].split(' ').filter(x => !!x).map(y => Number(y));
  let seeds = [];

  for (let i = 0; i < input1.length; i+=2) seeds.push({ s: input1[i], e: input1[i] + input1[i+1]});
  for (let i = 1; i < input.length; i++) {
    let block = input[i].split('\n');
    let mappings = [];
    for (let j = 1; j < block.length; j++) mappings.push(block[j].split(' ').map(y => Number(y)));
    let temp = [];
    while (seeds.length > 0) {
        const {s, e} = seeds.pop();
        let j = 0;
        for (;j < mappings.length; j++) {
            let a = mappings[j][0], b = mappings[j][1], c = mappings[j][2];
            let segStart = Math.max(b, s);
            let segEnd = Math.min(b + c, e);
            if (segStart < segEnd) {
                temp.push({ s: segStart + a - b, e: segEnd + a - b});
                if (segStart > s) seeds.push({s: s, e: segStart});
                if (segEnd < e) seeds.push({s: segEnd, e: e});
                break;
            }
        }
        console.log(temp);
        if (j == mappings.length) temp.push({ s: s, e: e});
    }
    seeds = temp;
  }
  const ans = Math.min(...seeds.map(x => x.s));
  console.log(ans);
});

readStream.on('end', () => {
  console.log('Finished reading file:');
});

readStream.on('error', error => {
  console.error(`Error reading file: ${error}`);
});















// const fs = require('fs');
// const filePath = './src/input5.txt';

// const readStream = fs.createReadStream(filePath);
// let total = 1000000000000;
// const soil = [];
// const fertilizer = [];
// const water = [];
// const light = [];
// const temperature = [];
// const humidity = [];
// const location = [];
// readStream.on('data', chunk => {
//   const lines = chunk.toString().split('\n');
//   const seeds = lines[0].split(':')[1].split(' ');
//   for (let i = 3 ; i < 9; i++) soil.push(lines[i]);
//   for (let i = 11 ; i < 54; i++) fertilizer.push(lines[i]);
//   for (let i = 56 ; i < 95; i++) water.push(lines[i]);
//   for (let i = 97 ; i < 144; i++) light.push(lines[i]);
//   for (let i = 146 ; i < 173; i++) temperature.push(lines[i]);
//   for (let i = 175 ; i < 183; i++) humidity.push(lines[i]);
//   for (let i = 185 ; i < 201; i++) location.push(lines[i]);
//   console.log('%%%%', seeds, soil, fertilizer, water, light, temperature, humidity, location);
  
//   for (let i = 1; i< seeds.length; i += 2) {
//     let left = Number(seeds[i]); // 79
//     let right = Number(seeds[i]) + Number(seeds[i+1]) - 1; // 92
//     let arr1 = find(soil, left, right);
    
//     // console.log('!!!', arr1);
    
//     /////////////////////
//     let arr2 = [];
//     for (let j = 0; j< arr1.length; j++) {
//         arr2.push(...find(fertilizer, arr1[j].v, arr1[j].v + arr1[j].r - arr1[j].l)); 
//     }
//     // console.log('@@@@', arr2);
//     const arr3 = [];
//     for (let j = 0; j<arr2.length; j++) {
//         arr3.push(...find(water, arr2[j].v, arr2[j].v + arr2[j].r - arr2[j].l)); 
//     }
//     // console.log('####', arr3);
//     const arr4 = [];
//     for (let j = 0; j<arr3.length; j++) {
//         arr4.push(...find(light, arr3[j].v, arr3[j].v + arr3[j].r - arr3[j].l)); 
//     }
//     // console.log('$$$$', arr4);
//     const arr5 = [];
//     for (let j = 0; j<arr4.length; j++) {
//         arr5.push(...find(temperature, arr4[j].v, arr4[j].v + arr4[j].r - arr4[j].l)); 
//     }
//     // console.log('%%%%', arr5);
//     const arr6 = [];
//     for (let j = 0; j<arr5.length; j++) {
//         arr6.push(...find(humidity, arr5[j].v, arr5[j].v + arr5[j].r - arr5[j].l)); 
//     }
//     // console.log('^^^^', arr6);
//     const arr7 = [];
//     for (let j = 0; j<arr6.length; j++) {
//         arr7.push(...find(location, arr6[j].v, arr6[j].v + arr6[j].r - arr6[j].l)); 
//     }
//     // console.log('&&&&', arr7);
//     for (let j = 0; j < arr7.length; j++) {
//         if (arr7[j].v < total) total = arr7[j].v;
//     }
//   }
//   console.log(total);
// });

// const find = (mapping, left, right) => {  // 79 92
//     let arr1 = [];
//     for (let j = 0; j< mapping.length; j++) {
//         let s = mapping[j].split(' ');
//         let dest = Number(s[0]);  // 52
//         let src = Number(s[1]);   // 50
//         let maxSrc = Number(s[1]) + Number(s[2]) - 1; // 98
//         if ( left >= src && right <= maxSrc) { // 79 >= 50 92 <= 92
//             arr1.push({l:left, r: right, v: dest + (left - src)});
//             // if (dest == 60)
//             // console.log('hii', j);
//         }
//         // Case 2
//         else if ( left < src && right >= src && right <= maxSrc) { // 46 < 56 56 >= 56 56 <= 92
//             arr1.push({l:src, r: right, v: dest});
//             // if (dest == 60)
//             // console.log('hiii', j);
//         }
//         // Case 3
//         else if ( left >= src && left <= maxSrc && right > maxSrc) {  // 52 >= 52 && 52 <= 53 && 65 > 53
//             arr1.push({l:left, r: maxSrc, v: dest + (left - src)});
//             // if (dest == 60)
//             // console.log('hiiiii', j);
//         }
//         // Case 4
//         else {
//             // arr1.push({l:left, r: right, v: 0});
//             // if (dest == 60)
//             // console.log('hiiiiiii', j);
//         }
//     }
//     arr1.sort(comp);
//     // console.log('QAZ', arr1);
//     let arr = []
//     for (let j = 0;j < arr1.length;j++) {
//         if (j == 0) {
//             if (left < arr1[j].l ) {
//                 arr.push({l:left, r: arr1[j].l - 1, v: left});
//             }
//         }
//         if (j == arr1.length - 1) {
//             if (arr1[j].r < right) {
//                 arr.push({l:arr1[j].r + 1, r: right, v: arr1[j].r + 1});
//             }
//             continue;
//         }
//         if (arr1[j].r != arr1[j+1].l - 1) {
//             arr.push({l:arr1[j].r + 1, r: arr1[j+1].l - 1, v: arr1[j].r + 1})
//         }
//     }
//     arr1.push(...arr);
//     if (arr1.length == 0) arr1.push({l:left, r: right, v: left});
//     arr1.sort(comp);
//     return arr1;
// }

// const comp = (a,b) => {
//     if (a.l < b.l) {
//         return -1;
//     }
//     if (a.l > b.l) {
//         return 1;
//     }
//     if (a.r < b.r) {
//         return -1;
//     }
//     if (a.r > b.r) {
//         return 1;
//     }
//     return 0;
// }

// readStream.on('end', () => {
//   console.log('Finished reading file:');
// });

// readStream.on('error', error => {
//   console.error(`Error reading file: ${error}`);
// });


// /**
 
//   for (let i = 3 ; i < 9; i++) soil.push(lines[i]);
//   for (let i = 11 ; i < 54; i++) fertilizer.push(lines[i]);
//   for (let i = 56 ; i < 95; i++) water.push(lines[i]);
//   for (let i = 97 ; i < 144; i++) light.push(lines[i]);
//   for (let i = 146 ; i < 173; i++) temperature.push(lines[i]);
//   for (let i = 175 ; i < 183; i++) humidity.push(lines[i]);
//   for (let i = 185 ; i < 201; i++) location.push(lines[i]);
//  */