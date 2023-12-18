const fs = require('fs');
const filePath = './src/input7.txt';

const readStream = fs.createReadStream(filePath);
let total = 0;
readStream.on('data', chunk => {
  const lines = chunk.toString().split('\n');
  const input = [];
  lines.forEach((line) => {
    const a = line.split(' ');
    const x = a[0], y = a[1];
    input.push({x, y: Number(y)});
  });

  input.sort(comp);

  input.forEach((i, index) => {
    total = total + (index + 1) * i.y;
  })
  console.log(total);
});

const comp = (a, b) => {
    const hand1 = a.x;
    const hand2 = b.x;
    const t1 = getType (hand1);
    const t2 = getType (hand2);
    if (t1 < t2) return -1;
    if (t1 > t2) return 1;
    for (let i = 0; i<5;i ++) {
        if (hand1[i] == hand2[i]) {
            continue;
        }
        if (getValue(hand1[i]) < getValue(hand2[i])) {
            return -1;
        } else return 1;
    }
    return 0;
}

const getValue = (card) => {
    switch(card) {
        case 'A':
            return 14;
        case 'K':
            return 13;
        case 'Q':
            return 12;
        case 'J':
            return 1;
        case 'T':
            return 10;
        default:
            return Number(card);
    }
}

const getType = (hand) => {
    const unique = new Map();
    for (let i = 0; i < hand.length; i++) {
        const val = unique.get(hand[i]);
        unique.set(hand[i], !val ? 1: val+1);
    }
    if (unique.size == 1) return 7;
    if (unique.size == 4) {
        const jVal = unique.get('J');
        if (jVal == 2 || jVal == 1) return 4;
        return 2;
    }
    if (unique.size == 5) {
        const jVal = unique.get('J');
        if (jVal == 1) return 2;
        return 1;
    }
    if (unique.size == 2) {
        const values = unique.values();
        if (values.next().value == 4 || values.next().value == 4) {
            const jVal = unique.get('J');
            if (jVal == 4 || jVal == 1) return 7;
            return 6;
        }
        const jVal = unique.get('J');
        if (jVal == 3 || jVal == 2) return 7;
        return 5;
    }
    if (unique.size == 3) {
        const values = unique.values();
        if (values.next().value == 3 || values.next().value == 3 || values.next().value == 3) {
            const jVal = unique.get('J');
            if (jVal == 3 || jVal == 1) return 6;
            return 4;
        };
        const jVal = unique.get('J');
        if (jVal == 2) return 6;
        if (jVal == 1) return 5;
        return 3;
    }
    return 0;
}

readStream.on('end', () => {
  console.log('Finished reading file:');
});

readStream.on('error', error => {
  console.error(`Error reading file: ${error}`);
});