export function delay(delay) {
    return new Promise(function(resolve) {
      setTimeout(resolve, delay);
    });
  }