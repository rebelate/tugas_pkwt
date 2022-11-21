function isEven(value) {
  if (value % 2 == 0) return "Genap";
  else return "Ganjil";
}

console.log(`1 adalah ${isEven(1)}`);
console.log(`2 adalah ${isEven(2)}`);

if (typeof process === "object") {
  const readline = require("readline");
  const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout,
  });
  rl.question("\nTry it yourself (number):", (answer) => {
    rl.write(`${answer} adalah ${isEven(answer)}\n`);

    process.exit(0);
  });
}
