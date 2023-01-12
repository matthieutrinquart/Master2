
class Rectangle {
    constructor(height, width) {
      this.height = height;
      this.width = width;
    }
    // Getter
    get area() {
      return this.calcArea();
    }
    // Method
    calcArea() {
      return this.height * this.width;
    }
  }

  function understands(nameMethode, o) {
    if (typeof o[nameMethode] === 'function') {
      // la méthode existe, on l'exécute
      o[nameMethode]();
      return true;
    } else {
      // la méthode n'existe pas
      return false;
    }
  }

const square = new Rectangle(10, 10);
console.log(understands("cacac",square))