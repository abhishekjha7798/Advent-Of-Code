const fs = require('fs');
const filePath = './src/input5.txt';

const readStream = fs.createReadStream(filePath);
let total = 1000000000000;
readStream.on('data', chunk => {
  const lines = chunk.toString().split('\n');
  const seeds = lines[0].split(':')[1].split(' ');
  const soil = [];
  for (let i = 3 ; i < 5; i++) soil.push(lines[i]);
  const fertilizer = [];
  for (let i = 7 ; i < 10; i++) fertilizer.push(lines[i]);
  const water = [];
  for (let i = 12 ; i < 16; i++) water.push(lines[i]);
  const light = [];
  for (let i = 18 ; i < 20; i++) light.push(lines[i]);
  const temperature = [];
  for (let i = 22 ; i < 25; i++) temperature.push(lines[i]);
  const humidity = [];
  for (let i = 27 ; i < 29; i++) humidity.push(lines[i]);
  const location = [];
  for (let i = 31 ; i < 33; i++) location.push(lines[i]);
  console.log('%%%%', seeds, soil, fertilizer, water, light, temperature, humidity, location);
  
  for ( let i = 1; i < seeds.length; i++) {
    let seed = Number(seeds[i]);
    let a = seed;
    let b = a;
    for (let j = 0; j<soil.length;j++) {
        let s = soil[j].split(' ');
        if(b >= Number(s[1]) && b < Number(s[1]) + Number(s[2])) {
            a = Number(s[0]) + b - Number(s[1]);
        }
    }
    console.log('####', a);
    b = a;
    for (let j = 0; j<fertilizer.length;j++) {
        let s = fertilizer[j].split(' ');
        if(b >= Number(s[1]) && b < Number(s[1]) + Number(s[2])) {
            a = Number(s[0]) + b - Number(s[1]);
        }
    }
    console.log('####', a);
    b = a;
    for (let j = 0; j<water.length;j++) {
        let s = water[j].split(' ');
        if(b >= Number(s[1]) && b < Number(s[1]) + Number(s[2])) {
            console.log('*****', b, a, s[0], s[1], s[2]);
            a = Number(s[0]) + (b - Number(s[1]));
        }
        console.log('^^^^^', a);
    }
    console.log('####', a);
    b = a;
    for (let j = 0; j<light.length;j++) {
        let s = light[j].split(' ');
        if(b >= Number(s[1]) && b < Number(s[1]) + Number(s[2])) {
            a = Number(s[0]) + b - Number(s[1]);
        }
    }
    console.log('####', a);
    b = a;
    for (let j = 0; j<temperature.length;j++) {
        let s = temperature[j].split(' ');
        if(b >= Number(s[1]) && b < Number(s[1]) + Number(s[2])) {
            a = Number(s[0]) + b - Number(s[1]);
        }
    }
    console.log('####', a);
    b = a;
    for (let j = 0; j<humidity.length;j++) {
        let s = humidity[j].split(' ');
        if(b >= Number(s[1]) && b < Number(s[1]) + Number(s[2])) {
            a = Number(s[0]) + b - Number(s[1]);
        }
    }
    console.log('####', a);
    b = a;
    for (let j = 0; j<location.length;j++) {
        let s = location[j].split(' ');
        if(b >= Number(s[1]) && b < Number(s[1]) + Number(s[2])) {
            a = Number(s[0]) + b - Number(s[1]);
        }
    }
    console.log('####', a);
    if (total > a) total = a;
  }
  console.log(total);
});

readStream.on('end', () => {
  console.log('Finished reading file:');
});

readStream.on('error', error => {
  console.error(`Error reading file: ${error}`);
});

/**
  
  const seeds = lines[0].split(':')[1].split(' ');
  for (let i = 3 ; i < 5; i++) soil.push(lines[i]);
  for (let i = 7 ; i < 10; i++) fertilizer.push(lines[i]);
  for (let i = 12 ; i < 16; i++) water.push(lines[i]);
  for (let i = 18 ; i < 20; i++) light.push(lines[i]);
  for (let i = 22 ; i < 25; i++) temperature.push(lines[i]);
  for (let i = 27 ; i < 29; i++) humidity.push(lines[i]);
  for (let i = 31 ; i < 33; i++) location.push(lines[i]);
 */