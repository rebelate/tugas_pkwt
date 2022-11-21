function isExpensive(value) {
  if (value >= 25000) return "Mahal";
  else if (value > 10000) return "Sedang";
  else if (value > 0 && value < 10000) return "Murah";
}

console.log(`25000 = ${isExpensive(25000)}`);
console.log(`20000 = ${isExpensive(20000)}`);
console.log(`8000 = ${isExpensive(8000)}`);

if (typeof process === "object") {
    const readline = require("readline");
    const rl = readline.createInterface({
      input: process.stdin,
      output: process.stdout,
    });
    rl.question("\nTry it yourself (number):", (answer) => {
      rl.write(`${answer} = ${isExpensive(answer)}\n`);
  
      process.exit(0);
    });
  }
  