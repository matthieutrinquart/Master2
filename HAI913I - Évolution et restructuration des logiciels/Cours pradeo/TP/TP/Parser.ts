
const fs = require('fs')

var s = require("acorn")

var hello = fs.readFileSync('Test.js').toString();
/* Use acorn to generate the AST of hello.js */
var ast = s.parse(hello);