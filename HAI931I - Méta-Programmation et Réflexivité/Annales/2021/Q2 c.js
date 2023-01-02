
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

  function getMethodsOf(obj, namemethode){
    if(Object.getOwnPropertyNames( Object.getPrototypeOf(obj) ).getOwnPropertyNames(namemethode)){

        return true
    }else{
        return false;
    }
  }

  function understands(o, selector) {
	return Reflect.has(o, selector);
}

const square = new Rectangle(10, 10);
console.log(understands(square, "calcArea"))