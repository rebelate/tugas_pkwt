function hollow(row) {
  let output = "";
  for (var i = 0; i < row; i++) {
    for (var j = 0; j < row; j++) {
      if (i == 0 || i == row - 1 || j == 0 || j == row - 1) {
        output += "* ";
      } else {
        output += "  ";
      }
    }
    output += "\n";
  }
  console.log(output);
}
hollow(3);

if (typeof process === "object") {
  const readline = require("readline");
  const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout,
  });
  rl.question("\nTry it yourself (number):", (answer) => {
    rl.write(hollow(answer));
    process.exit(0);
  });
}
