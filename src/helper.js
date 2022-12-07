export function delay(delay) {
  return new Promise(function (resolve) {
    setTimeout(resolve, delay);
  });
}

export function makeid() {
  const length = 5;
  let result = "";
  let characters =
    "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
  let charactersLength = characters.length;
  for (let i = 0; i < length; i++) {
    result += characters.charAt(Math.floor(Math.random() * charactersLength));
  }
  return result;
}

export function debounce(callback, delay) {
  let timeout;
  return function () {
    let args = arguments;
    clearTimeout(timeout);
    timeout = setTimeout(
      function () {
        callback.apply(this, args);
      }.bind(this),
      delay
    );
  };
}

export function getDate() {
  const date = new Date();
  const formattedDate = date
    .toLocaleDateString("en-GB", {
      day: "2-digit",
      month: "long",
      year: "numeric",
    })
  return formattedDate;
}
