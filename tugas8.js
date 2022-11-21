function star(val) {
  for (var i = val; i >= 1; i--) {
    console.log("* ".repeat(i));
  }
}

star(5);

if (typeof process === "object") {
  const readline = require("readline");
  const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout,
  });
  rl.question("\nTry it yourself (number):", (answer) => {
    rl.write(star(answer));
    process.exit(0);
  });
}
