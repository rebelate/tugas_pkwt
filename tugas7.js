// function printFazzTrack(num) {
//   let output = "";
//   if (num % 3 === 0) output += "fazz";
//   if (num % 5 === 0) output += "track";
//   console.log(output);
// }

function printFazzTrack(num) {
  let output = "";
  for (let i = 1; i <= num; i++) {
    if (i % 3 === 0 && i % 5 === 0) output += "fazztrack ";
    else if (i % 3 === 0) output += "fazz ";
    else if (i % 5 === 0) output += "track ";
  }
  console.log(output);
}

printFazzTrack(3);
printFazzTrack(5);
printFazzTrack(15);

if (typeof process === "object") {
  const readline = require("readline");
  const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout,
  });
  rl.question("\nTry it yourself (number):", (answer) => {
    rl.write(printFazzTrack(answer));
    process.exit(0);
  });
}
