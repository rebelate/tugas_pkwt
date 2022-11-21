// Ada beberapa perbedaan pada var, let, dan const
// Perbedaan utama yaitu adalah SCOPE
// variable yang di deklarasikan menggunakan var akan menjadi global, 
// atau juga secara lokal ke sebuah fungsi terlepas dari sebuah block scope
// Sedangkan let memungkinkan kita untuk mendeklarasikan variabel yang hanya dapat akses pada suatu block scope
let x = 1;
if (x === 1) {
  let x = 2;
  console.log(x);
  // output: 2
}
console.log(x);
// output: 1

var y = 1;
if (y === 1) {
  var y = 2;
  console.log(y);
  // output: 2
}
console.log(y);
// output: 2

// penggunaan var pada loop juga membuat suatu bug seperti berikut
var funcs = [];
for (var i = 0; i < 3; i++) {
  funcs[i] = function () {
    console.log("value:", i);
  };
}
for (var j = 0; j < 3; j++) {
  funcs[j]();
}
//output
// value: 3
// value: 3
// value: 3

// Selain itu deklarasi menggunakan var akan mempunyai value undefined ketika di-inisialisasi 
// yang memungkinkan akses terhadap variable tersebut sebelum dideklarasikan
console.log(test1); // undefined
var test1 = "Foo";
console.log(test1); // Foo

// console.log(test2); // ReferenceError (uncomment untuk mencoba)
let test2 = "Foo";
console.log(test2); // Foo

// var dapat dideklarasi ulang sedangkan let tidak
var foo = "foo1";
var foo = "foo2";

// let dapat di update atau diubah tapi bukan di deklarasi ulang
let bar = "bar1"; 
// let bar = "bar2"; // SyntaxError: Identifier 'bar' has already been declared (uncomment untuk mencoba)

// Seperti deklarasi let, deklarasi const hanya dapat diakses di dalam blok yang dideklarasikan
// const tidak dapat diperbarui atau dideklarasikan ulang 
// Ini berarti nilai variabel yang dideklarasikan dengan const tetap sama dalam scopenya.
// dan tidak dapat diperbarui atau dideklarasikan ulang
const greeting1 = "hey";
// const greeting1 = "hello";// error: Identifier 'greeting1' has already been declared
const greeting2 = "Hi";
// greeting = "sup";// error: Assignment to constant variable