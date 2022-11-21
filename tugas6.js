function calculateTransportFee(distance) {
  if (distance <= 2) {
    console.log(`total ongkos kirim jarak ${distance}km = 5000`);
    return;
  }
  const price = 5000 + (distance - 2) * 3000;
  console.log(`total ongkos kirim jarak ${distance}km = ${price}`);
}

calculateTransportFee(2);
calculateTransportFee(3);
calculateTransportFee(5);

if (typeof process === "object") {
  const readline = require("readline");
  const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout,
  });
  rl.question("\nTry it yourself (number):", (answer) => {
    rl.write(calculateTransportFee(answer));
    process.exit(0);
  });
}
